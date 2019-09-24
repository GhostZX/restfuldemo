package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test suite of the class FindPersonService")
class FindPersonServiceUTest {
    @Mock
    private PersonRepository personRepository;

    private FindPersonService findPersonService;

    @BeforeEach
    void setUp() {
        findPersonService = new PersonServiceImpl(personRepository);
    }

    @Test
    void givenId_whenFind_thenShouldFind() {
        //given
        Long id = 1L;

        when(personRepository.findById(id))
                .thenReturn(Optional.of(new Person(id, "name", 10)));

        //when
        Person entity = findPersonService.find(id).orElse(null);

        //then
        assertNotNull(entity);

        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void givenInvalidId_whenFind_thenShouldThrowException() {
        //given
        Long id = null;

        //when
        Executable executable = () -> findPersonService.find(id);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }
}
