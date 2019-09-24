package com.jullierme.restfuldemo.business.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test suite of the class DeletePersonService")
class DeletePersonServiceUTest {

    @Mock
    private PersonRepository personRepository;

    private DeletePersonService deletePersonService;

    @BeforeEach
    void setUp() {
        deletePersonService = new PersonServiceImpl(personRepository);
    }

    @Test
    @DisplayName("should delete a person")
    void givenId_whenDelete_thenShouldDelete() {
        //given
        Long id = 1L;

        doNothing()
                .when(personRepository).deleteById(id);
        //when
        deletePersonService.delete(id);

        //then
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("should delete all persons")
    void whenDeleteAll_thenShouldDeleteAllRecords() {
        doNothing()
                .when(personRepository).deleteAll();
        //when
        deletePersonService.deleteAll();

        //then
        verify(personRepository, times(1)).deleteAll();
    }

    @Test
    @DisplayName("should throw exception with invalid id")
    void givenInvalidId_whenDelete_thenShouldThrowException() {
        //given
        Long id = null;

        //when
        Executable executable = () -> deletePersonService.delete(id);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }
}
