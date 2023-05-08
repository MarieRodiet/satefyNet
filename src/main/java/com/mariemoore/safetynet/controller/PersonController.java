package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    private static final Logger logger = getLogger(PersonController.class);

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getPersons(){
        logger.info("getting all persons");
        return personService.getPersons();
    }

    @ResponseBody
    @PostMapping
    public Person addPerson(@RequestBody Person person){
        Person addedPerson = personService.addPerson(person);
        if(Objects.isNull(addedPerson)){
            logger.error("could not add person");
            return null;
        }
        logger.info("person added successfully");
        return addedPerson;
    }

    @ResponseBody
    @DeleteMapping
    public Person deletePerson(@RequestBody Person person){
        Person deletedPerson = personService.deletePerson(person);
        if(Objects.isNull(deletedPerson)){
            logger.error("could not delete person");
            return null;
        }
        logger.info("person deleted successfully");
        return deletedPerson;
    }

    @ResponseBody
    @PutMapping
    public Person updatePerson(@RequestBody Person person){
        Person updatedPerson = personService.updatePerson(person);
        if(Objects.isNull(updatedPerson)){
            logger.error("could not modify person");
            return null;
        }
        logger.info("person updated successfully");
        return updatedPerson;
    }

}
