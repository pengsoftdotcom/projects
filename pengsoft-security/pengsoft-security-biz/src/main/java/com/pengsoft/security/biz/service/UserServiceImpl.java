package com.pengsoft.security.biz.service;

import com.pengsoft.security.biz.repository.UserRepository;
import com.pengsoft.security.biz.repository.UserRoleRepository;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The implementer of {@link UserService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class UserServiceImpl extends BeanServiceImpl<UserRepository, User, String> implements UserService {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    public User save(final User user) {
        findOneByUsername(user.getUsername()).ifPresent(source -> usernameAlreadyExists(user, source));
        findOneByMobile(user.getUsername()).ifPresent(source -> usernameAlreadyExists(user, source));
        findOneByEmail(user.getUsername()).ifPresent(source -> usernameAlreadyExists(user, source));
        findOneByMpOpenId(user.getUsername()).ifPresent(source -> usernameAlreadyExists(user, source));
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.save(user);
    }

    private void usernameAlreadyExists(final User user, final User source) {
        if (EntityUtils.ne(source, user)) {
            throw exceptions.constraintViolated("username", "Exists", user.getUsername());
        }
    }


    @Override
    public void changePassword(final String id, final String oldPassword, final String newPassword) {
        final var user = findOne(id).orElseThrow(() -> new IllegalArgumentException("the entity with given id has been deleted or the given id is invalid."));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            resetPassword(id, newPassword);
        } else {
            throw exceptions.constraintViolated("oldPassword", "WrongPassword", oldPassword);
        }
    }

    @Override
    public void resetPassword(final String id, final String password) {
        if (findOne(id).isEmpty()) {
            throw exceptions.entityNotFound(id);
        }
        getRepository().resetPassword(id, passwordEncoder.encode(password));
    }

    @Override
    public void grantRoles(final User user, final List<Role> roles) {
        final var source = user.getUserRoles();
        final var target = roles.stream().map(role -> new UserRole(user, role)).collect(Collectors.toList());
        final var deleted = source.stream()
                .filter(s -> target.stream().noneMatch(t -> EntityUtils.eq(s.getUser(), t.getUser()) && EntityUtils.eq(s.getRole(), t.getRole())))
                .collect(Collectors.toList());
        userRoleRepository.deleteAll(deleted);
        source.removeAll(deleted);
        final var created = target.stream()
                .filter(t -> source.stream().noneMatch(s -> EntityUtils.eq(t.getUser(), s.getUser()) && EntityUtils.eq(t.getRole(), s.getRole())))
                .collect(Collectors.toList());
        userRoleRepository.saveAll(created);
        source.addAll(created);
        super.save(user);
        if (!source.isEmpty() && source.stream().noneMatch(UserRole::isMajor)) {
            setMajorRole(user, source.get(0).getRole());
        }
    }

    @Override
    public void setMajorRole(final User user, final Role role) {
        user.getUserRoles().forEach(userRole -> {
            if (userRole.isMajor() && EntityUtils.ne(userRole.getRole(), role)) {
                userRole.setMajor(false);
                userRoleRepository.save(userRole);
            }
            if (!userRole.isMajor() && EntityUtils.eq(userRole.getRole(), role)) {
                userRole.setMajor(true);
                userRoleRepository.save(userRole);
            }
        });
        super.save(user);
    }

    @Override
    public void signInSuccess(final String username) {
        final var user = findOneByUsername(username).orElseThrow(() -> exceptions.entityNotFound(username));
        user.setSignedInAt(DateUtils.currentDateTime());
        user.setSignInFailureCount(0L);
        save(user);
    }

    @Override
    public void signInFailure(final String username, final int allowSignInFailure) {
        final var optional = findOneByUsername(username);
        if (optional.isPresent()) {
            final var user = optional.get();
            user.setSignInFailureCount(user.getSignInFailureCount() + 1);
            if (user.getSignInFailureCount() >= allowSignInFailure) {
                user.setEnabled(false);
            }
            save(user);
        }
    }

    @Override
    public Optional<User> findOneByUsername(final String username) {
        return getRepository().findOneByUsername(username);
    }

    @Override
    public Optional<User> findOneByMobile(final String mobile) {
        return getRepository().findOneByMobile(mobile);
    }

    @Override
    public Optional<User> findOneByEmail(final String email) {
        return getRepository().findOneByEmail(email);
    }

    @Override
    public Optional<User> findOneByMpOpenId(final String mpOpenId) {
        return getRepository().findOneByMpOpenId(mpOpenId);
    }

}
