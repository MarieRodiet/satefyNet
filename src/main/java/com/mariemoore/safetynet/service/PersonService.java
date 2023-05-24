package com.mariemoore.safetynet.service;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person deletePerson(String lastname, String firstname){
        return personRepository.deletePerson(lastname, firstname);
    }

    public Person addPerson(Person person){
        Person addedPerson = personRepository.save(person);
        return addedPerson;
    }

    public Person updatePerson(Person person) {
        Person updatedPerson = personRepository.update(person);
        return updatedPerson;
    }

    public List<Person> getHouseholdOfChild(String firstName, String lastName) {
        return personRepository.getHouseholdOfChild(firstName, lastName);
    }

    public List<Person> findPersonsByAddress(String address) {
        return this.personRepository.findPersonsByAddress(address);
    }
}
