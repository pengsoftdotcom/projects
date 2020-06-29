package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PersonService;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link Person}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PersonFacade extends BeanFacade<PersonService, Person, String>, PersonService {

}
