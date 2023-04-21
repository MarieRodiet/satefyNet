package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository{
    List<Person> persons;
    JsonDataGetter jsonDataGetter;

    public PersonRepository(JsonDataGetter jsonDataGetter) throws Exception {
        this.jsonDataGetter = jsonDataGetter;
        this.persons = this.jsonDataGetter.getPersonsData();
    }
    void saveAll(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> findAll() {
        return this.persons;
    }

    public Person deletePerson(Person toDelete) {
       List<Person> toKeep = new ArrayList<>();
       for(Person p : this.persons){
           if(!p.getFirstName().equals(toDelete.getFirstName().toString()) && !p.getLastName().toString().equals(toDelete.getLastName().toString())){
               toKeep.add(p);
           }
       }
       this.persons = toKeep;
        return toDelete;
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(this.persons.get(0));
    }

    public Person save(Person person) {
        this.persons.add(person);
        return person;
    }

    public Person update(Person toUpdate){
        for(Person p: this.persons){
            if(p.getFirstName().equals(toUpdate.getFirstName().toString()) && p.getLastName().toString().equals(toUpdate.getLastName().toString())){
                p.setAddress(toUpdate.getAddress());
                p.setCity(toUpdate.getCity());
                p.setZip(toUpdate.getZip());
                p.setPhone(toUpdate.getPhone());
                p.setEmail(toUpdate.getEmail());
            }
        }
        return toUpdate;
    }
}
