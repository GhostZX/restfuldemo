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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test suite of the class UpdatePersonService")
class UpdatePersonServiceUTest {
    @Mock
    private PersonRepository personRepository;

    private UpdatePersonService updatePersonService;

    @BeforeEach
    void setUp() {
        updatePersonService = new PersonServiceImpl(personRepository);
    }

    @Test
    void givenAnSavedEntity_whenUpdate_thenShouldUpdate() {
        //given
        Long id = 1L;
        String name = "Jullierme";
        Integer age = 34;
        Person entity = new Person(id, name, age);

        given(personRepository.save(any(Person.class)))
                .willReturn(new Person(id, name, age));

        //when
        Person savedEntity = updatePersonService.update(id, entity);

        //then
        assertNotNull(savedEntity);

        verify(personRepository, times(1)).save(entity);
    }

    @Test
    void givenDifferentIds_whenUpdate_thenShouldThrowException() {
        //given
        Long id = 1L;
        Person entity = new Person(2L, "name", 10);

        //when
        Executable executable = () -> updatePersonService.update(id, entity);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("invalidParameterWhenUpdate")
    void givenInvalidParameter_whenUpdate_thenShouldThrowException(Long id, Person entity) {
        //given parameters

        //when
        Executable executable = () -> updatePersonService.update(id, entity);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    private static Stream<Arguments> invalidParameterWhenUpdate() {
        return Stream.of(
                arguments(1L, null),
                arguments(null, new Person("asdf", 10))
        );
    }
}
