package com.jullierme.restfuldemo.config;

import com.jullierme.restfuldemo.business.person.help.PersonMapper;
import com.jullierme.restfuldemo.business.person.help.PersonMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PersonMapper personMapper() {
        return new PersonMapperImpl();
    }
}
