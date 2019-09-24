package com.jullierme.restfuldemo.business.person.help;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {
    private Long id;
    private String name;
    private Integer age;

    public PersonRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public PersonRequest(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
