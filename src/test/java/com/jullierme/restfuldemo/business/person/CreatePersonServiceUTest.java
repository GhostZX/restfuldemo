package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test suite of the class CreatePersonService")
class CreatePersonServiceUTest {

    @Mock
    private PersonRepository personRepository;
    private CreatePersonService createPersonService;

    @BeforeEach
    void setUp() {
        createPersonService = new PersonServiceImpl(personRepository);
    }

    @Test
    void givenNameAndAge_whenCreate_thenShouldCreatePerson() {
        //given
        String name = "Jullierme";
        int age = 34;

        when(personRepository.save(any(Person.class)))
                .thenReturn(new Person(1L, name, age));
        //when

        Person entity = createPersonService.create(name, age);

        //then
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertNotNull(entity.getName());
        assertNotNull(entity.getAge());

        assertEquals(entity.getId(), 1L);
        assertEquals(entity.getName(), name);
        assertEquals(entity.getAge(), age);
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToCreatePerson")
    void givenInvalidParameters_whenCreate_thenShouldThrowException(String name, Integer age ) {
        //given parameters
        //when
        Executable executable = () -> createPersonService.create(name, age);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    protected static Stream<Arguments> invalidParametersToCreatePerson() {
        return Stream.of(
                arguments("Jullierme", null),
                arguments(null, 34)
        );
    }
}
