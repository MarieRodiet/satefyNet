package com.mariemoore.safetynet.repository;

import com.jsoniter.JsonIterator;
import com.mariemoore.safetynet.model.Person;
import org.springframework.stereotype.Component;
import com.jsoniter.any.Any;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Component
public class JsonDataGetter{
    List<Person> persons = new ArrayList<>();
    String filePath = "src/main/resources/data.json";

    private static Any buffer;

    public JsonDataGetter() throws IOException {
        byte[] fileContentArray = Files.readAllBytes(new File(filePath).toPath());
        JsonIterator iterator = JsonIterator.parse(fileContentArray);
        buffer = iterator.readAny();
    }

    public List<Person> getPersonsData() throws IOException {
        Any personsData = buffer.get("persons");
        personsData.forEach(a -> {
            Person temp = new Person(
                    a.get("firstName").toString(),
                    a.get("lastName").toString(),
                    a.get("phone").toString(),
                    a.get("email").toString(),
                    a.get("address").toString(),
                    a.get("city").toString(),
                    a.get("zip").toString());

                    this.persons.add(temp);
                }
        );
        return this.persons;
    }
}
