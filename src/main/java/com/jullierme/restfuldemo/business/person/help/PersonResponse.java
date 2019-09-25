package com.jullierme.restfuldemo.business.person.help;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {
    private Long id;
    private String name;
    private Integer age;
}
