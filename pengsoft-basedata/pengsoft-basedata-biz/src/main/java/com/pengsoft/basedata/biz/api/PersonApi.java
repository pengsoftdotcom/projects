package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.PersonFacade;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Person}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/person")
public class PersonApi extends EntityApi<PersonFacade, Person, String> {

}
