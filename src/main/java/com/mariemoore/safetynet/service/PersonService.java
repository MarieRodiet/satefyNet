package com.mariemoore.safetynet.service;

import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public void deletePersonById(Long id){
        personRepository.deleteById(id);
    }

    public Person addPerson(Person person){
        Person addedPerson = personRepository.save(person);
        return addedPerson;
    }


}
