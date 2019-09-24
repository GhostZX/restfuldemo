package com.jullierme.restfuldemo.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test suite of the class Person")
public class PersonUTest {

    @Test
    @DisplayName("smoke test of the entity Person")
    void givenParameters_whenNewPerson_thenPropertiesShouldNotBeNull() {
        //given
        long id = 1;
        String name = "Jullierme";
        int age = 34;
        //when
        Person p = new Person(id, name, age);

        //then
        assertNotNull(p.getId());
        assertNotNull(p.getName());
        assertNotNull(p.getAge());

        assertEquals(p.getId(), id);
        assertEquals(p.getName(), name);
        assertEquals(p.getAge(), age);
    }
}
