package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;

import java.util.Optional;

public interface FindPersonService {
    Optional<Person> find(Long id);
}
