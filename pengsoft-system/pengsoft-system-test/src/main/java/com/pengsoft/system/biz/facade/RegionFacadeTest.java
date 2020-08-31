package com.pengsoft.system.biz.facade;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.support.test.BaseFacadeTest;
import com.pengsoft.system.domain.entity.QRegion;
import com.pengsoft.system.domain.entity.Region;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@link RegionFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class RegionFacadeTest extends BaseFacadeTest<RegionFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Region.class);
        authorityFacade.saveEntityAdminAuthorities(Region.class);
    }

    @Test
    public void loadFromJson() throws Exception {
        final var resource = this.getClass().getResource("/pcas-code.json");
        final var regions = objectMapper.readValue(resource, new TypeReference<List<Map>>() {
        }).stream().map(this::getRegion).collect(Collectors.toList());
        regions.forEach(this::save);
    }

    private void save(final Region region) {
        final var optional = getFacade().findOneByCode(region.getCode());
        if (optional.isPresent()) {
            region.getChildren().forEach(child -> child.setParent(optional.get()));
        } else {
            getFacade().save(region);
            region.getChildren().forEach(child -> child.setParent(region));
        }
        region.getChildren().forEach(this::save);
    }


    @SuppressWarnings("rawtypes")
    public Region getRegion(Map<String, Object> value) {
        var region = new Region();
        region.setCode((String) value.get("code"));
        region.setName((String) value.get("name"));
        Optional.ofNullable(value.get("children")).ifPresent(children ->
                region.setChildren(((List<Map<String, Object>>) children).stream().map(this::getRegion).collect(Collectors.toList())));
        return region;
    }

    @Test
    public void changeParent() {
        String[] codes = { "4419", "4420", "4604" };
        for (int i = 0; i < codes.length; i++) {
            var code = codes[i];
            getFacade().findOneByCode(code).ifPresent(parent -> {
                getFacade().findAll(QRegion.region.code.startsWith(code + "00"), Sort.unsorted()).forEach(region -> {
                    region.setParent(parent);
                    getFacade().save(region);
                });
            });
            getFacade().findOneByCode(code + "00").ifPresent(getFacade()::delete);
        }
    }

}
