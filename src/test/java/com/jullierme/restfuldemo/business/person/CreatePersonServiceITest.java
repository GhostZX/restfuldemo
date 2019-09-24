package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Integration test suite of the class: CreatePersonService")
class CreatePersonServiceITest {
    @Autowired
    private CreatePersonService createPersonService;

    @Autowired
    private DeletePersonService deletePersonService;

    @BeforeEach
    void setUp() {
        deletePersonService.deleteAll();
    }

    @Test
    void givenParameters_whenCreate_thenShouldCreate() {
        //given
        String name = "Jullierme";
        Integer age = 34;

        //when
        Person person = createPersonService.create(name, age);

        //then
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals(person.getName(), name);
        assertEquals(person.getAge(), age);
    }
}
