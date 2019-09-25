package com.jullierme.restfuldemo.business.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jullierme.restfuldemo.business.person.help.PersonRequest;
import com.jullierme.restfuldemo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerUTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreatePersonService createPersonService;

    @Autowired
    private DeletePersonService deletePersonService;

    @BeforeEach
    void setUp() {
        deletePersonService.deleteAll();
    }

    @Test
    void givenParameters_whenCreate_thenShouldCreate() throws Exception {
        String name = "Jullierme";
        Integer age = 34;

        PersonRequest request = new PersonRequest(name, age);

        mockMvc
                .perform(post("/person")
                        .content(toJson(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    void givenParameters_whenFind_thenShouldFind() throws Exception {
        String name = "Jullierme";
        Integer age = 34;

        Person p = createPersonService.create(name, age);

        mockMvc
                .perform(get("/person/{id}", p.getId())
                        .accept(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(p.getId().intValue())))
                .andExpect(jsonPath("$.name", is(p.getName())))
                .andExpect(jsonPath("$.age", is(p.getAge())));
    }

    @Test
    void givenId_whenDelete_thenShouldNotExist() throws Exception {
        String name = "Jullierme";
        Integer age = 34;

        Person p = createPersonService.create(name, age);

        mockMvc
                .perform(delete("/person/{id}", p.getId())
                        .accept(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

    @Test
    void givenParameters_whenUpdate_thenShouldUpdate() throws Exception {
        String name = "Jullierme";
        Integer age = 34;
        String newName = "Silva";
        Integer newAge = 38;

        Person p = createPersonService.create(name, age);

        PersonRequest request = new PersonRequest(p.getId(), newName, newAge);

        mockMvc
                .perform(put("/person/{id}", p.getId())
                        .content(toJson(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(p.getId().intValue())))
                .andExpect(jsonPath("$.name", is(newName)))
                .andExpect(jsonPath("$.age", is(newAge)));
    }

    static String toJson(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
