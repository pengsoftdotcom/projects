package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.starter.BasedataApplication;
import com.pengsoft.support.test.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * {@link PersonRepository} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = BasedataApplication.class)
@ActiveProfiles({ "security", "system", "basedata" })
public class PersonRepositoryTest extends BaseRepositoryTest<PersonRepository> {

    @Test
    public void countByIdInAndBelongsTo() {
        getRepository().countByIdInAndBelongsTo(List.of("1"), "2");
    }

    @Test
    public void countByIdInAndControlledBy() {
        getRepository().countByIdInAndControlledBy(List.of("1"), "2");
    }

}
