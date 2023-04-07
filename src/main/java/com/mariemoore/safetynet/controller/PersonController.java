package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    public Iterable<Person> getPersons(){
        return personService.getPersons();
    }
}
