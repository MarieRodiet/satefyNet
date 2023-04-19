package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    public PersonController(PersonService personService){
        this.personService = personService;
    }
    @Autowired


    @GetMapping("/persons")
    public List<Person> getPersons(){
        return personService.getPersons();
    }


    @ResponseBody
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    //TODO
    //put request -> mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de
    //delete request supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur
    //unique)

}
