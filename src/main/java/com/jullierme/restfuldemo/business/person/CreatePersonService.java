package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;

public interface CreatePersonService {
    Person create(Person person);
    Person create(String name, Integer age);
}
