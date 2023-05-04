package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getPersons(){
        return personService.getPersons();
    }


    @ResponseBody
    @PostMapping
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @ResponseBody
    @DeleteMapping
    public Person deletePerson(@RequestBody Person person){
        return personService.deletePerson(person);
    }

    @ResponseBody
    @PutMapping
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

}
