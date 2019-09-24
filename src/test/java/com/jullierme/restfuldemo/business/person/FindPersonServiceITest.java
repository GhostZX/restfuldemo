package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Integration test suite of the class FindPersonService")
class FindPersonServiceITest {

    @Autowired
    private DeletePersonService deletePersonService;

    @Autowired
    private CreatePersonService createPersonService;

    @Autowired
    private FindPersonService findPersonService;

    @BeforeEach
    void setUp() {
        deletePersonService.deleteAll();
    }

    @Test
    void givenNewPerson_whenFind_thenShouldExist() {
        //given
        String name = "Jullierme";
        Integer age = 34;

        Person entity = createPersonService.create(name, age);
        assertNotNull(entity);

        //when
        Person p = findPersonService.find(entity.getId()).orElse(null);

        //then
        assertNotNull(p);
        assertEquals(p.getName(), name);
        assertEquals(p.getAge(), age);
    }
}
