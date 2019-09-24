package com.jullierme.restfuldemo.business.person.controller;

import com.jullierme.restfuldemo.business.person.CreatePersonService;
import com.jullierme.restfuldemo.business.person.DeletePersonService;
import com.jullierme.restfuldemo.business.person.FindPersonService;
import com.jullierme.restfuldemo.business.person.UpdatePersonService;
import com.jullierme.restfuldemo.business.person.help.PersonMapper;
import com.jullierme.restfuldemo.business.person.help.PersonRequest;
import com.jullierme.restfuldemo.business.person.help.PersonResponse;
import com.jullierme.restfuldemo.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {

    private PersonMapper personMapper;
    private CreatePersonService createPersonService;
    private FindPersonService findPersonService;
    private DeletePersonService deletePersonService;
    private UpdatePersonService updatePersonService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PersonRequest request) {
        Person entity = createPersonService.create(personMapper.toPerson(request));

        return ResponseEntity
                .created(getLocationPath(entity.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable("id") Long id,
                                                 @RequestBody PersonRequest request) {
        Person entity = updatePersonService.update(id, personMapper.toPerson(request));

        return ResponseEntity.ok(personMapper.toResponse(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> find(@PathVariable("id") Long id) {
        Person entity = findPersonService.find(id).orElse(null);

        if (entity == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(personMapper.toResponse(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deletePersonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI getLocationPath(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
