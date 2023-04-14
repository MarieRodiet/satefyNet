package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;
    public PersonController(PersonService personService){
        this.personService = personService;
    }
    @Autowired


    @GetMapping()
    public List<Person> getPersons(){
        return personService.getPersons();
    }
}
