package com.pengsoft.system.biz.facade;

import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.test.BaseFacadeTest;
import com.pengsoft.system.domain.entity.Message;
import com.pengsoft.system.domain.entity.MessageType;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link MessageFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class MessageFacadeTest extends BaseFacadeTest<MessageFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Message.class);
        authorityFacade.saveEntityAdminAuthorities(Message.class);
    }

    @Test
    @WithUserDetails("admin")
    public void save() {
        final var user = SecurityUtils.getUser();
        final var message = new Message();
        message.setSender(user);
        message.setRecipient(user);
        message.setSubject("test");
        message.setContent("test");
        message.setTypes(StringUtils.join(new Object[] { MessageType.SMS, MessageType.EMAIL, MessageType.INTERNAL, MessageType.MP_TEMPLATE }, StringUtils.COMMA));
        getFacade().save(message);
    }

}
