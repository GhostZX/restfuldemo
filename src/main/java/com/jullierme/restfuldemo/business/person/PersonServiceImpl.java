package com.jullierme.restfuldemo.business.person;

import com.jullierme.restfuldemo.model.Person;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements CreatePersonService,
        DeletePersonService,
        UpdatePersonService,
        FindPersonService {
    private PersonRepository personRepository;

    @Override
    public Person create(@NonNull String name, @NonNull Integer age) {
        Person entity = Person.builder()
                .name(name)
                .age(age)
                .build();

        return personRepository.save(entity);
    }

    @Override
    public Person create(Person entity) {
        return create(entity.getName(), entity.getAge());
    }

    @Override
    public void delete(@NonNull Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Override
    public Person update(@NonNull Long id, @NonNull Person entity) {
        validateTheSameId(id, entity);

        return personRepository.save(entity);
    }

    private void validateTheSameId(Long id, Person entity) {
        if (!id.equals(entity.getId()))
            throw new IllegalArgumentException();
    }

    @Override
    public Optional<Person> find(@NonNull Long id) {
        return personRepository.findById(id);
    }
}
