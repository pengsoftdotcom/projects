package com.pengsoft.system.biz.repository;

import com.pengsoft.support.test.BaseRepositoryTest;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * {@link AssetRepository} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class AssetRepositoryTest extends BaseRepositoryTest<AssetRepository> {

    @Test
    public void countByIdInAndCreatedBy() {
        getRepository().countByIdInAndCreatedBy(List.of("1"), "2");
    }

}
