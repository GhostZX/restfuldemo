package com.jullierme.restfuldemo.business.person.help;

import com.jullierme.restfuldemo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonRequest request);

//    PersonRequest toRequest(Person person);

   PersonResponse toResponse(Person person);
}
