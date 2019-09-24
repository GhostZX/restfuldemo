package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;

public interface UpdatePersonService {
    Person update(Long id, Person entity);
}
