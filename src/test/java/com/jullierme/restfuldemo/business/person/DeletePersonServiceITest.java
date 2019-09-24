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
@DisplayName("Integration test suite of the class DeletePersonService")
class DeletePersonServiceITest {

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
    @DisplayName("should delete a person")
    void givenNewPerson_whenDelete_thenShouldNotExist() {
        //given
        String name = "Jullierme";
        Integer age = 34;

        Person entity = createPersonService.create(name, age);
        assertNotNull(entity);

        Person p = findPersonService.find(entity.getId()).orElse(null);
        assertNotNull(p);

        //when
        deletePersonService.delete(entity.getId());

        //then
        p = findPersonService.find(entity.getId()).orElse(null);
        assertNull(p);
    }
}
