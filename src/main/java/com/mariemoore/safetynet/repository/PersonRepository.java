package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.Person;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository{
    List<Person> persons;
    JsonDataGetter jsonDataGetter;

    public PersonRepository(JsonDataGetter jsonDataGetter) throws Exception {
        this.jsonDataGetter = jsonDataGetter;
        //Person p = new Person("John", "Boyd", "123 address", "my City", "165 my zipcode", "123456", "email@gmail.com");
        //this.persons.add(p);
        this.persons = this.jsonDataGetter.getPersonsData();
    }
    void saveAll(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> findAll() {
        return this.persons;
    }

    public void deleteById(Long id) {
        //delete Person
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(this.persons.get(0));
    }

    public Person save(Person person) {
        this.persons.add(person);
        return person;
    }
}
