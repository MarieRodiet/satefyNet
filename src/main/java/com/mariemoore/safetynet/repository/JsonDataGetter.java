package com.mariemoore.safetynet.repository;

import com.jsoniter.JsonIterator;
import com.mariemoore.safetynet.model.Firestation;
import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.model.Person;
import org.springframework.stereotype.Component;
import com.jsoniter.any.Any;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class JsonDataGetter{
    List<Person> persons = new ArrayList<>();
    List<Firestation> firestations = new ArrayList<>();

    List<MedicalRecord> medicalRecords = new ArrayList<>();
    String filePath = "src/main/resources/data.json";

    private static Any buffer;

    public JsonDataGetter() throws IOException {
        Any deserialized =  JsonIterator.deserialize(Files.readAllBytes(new File("src/main/resources/data.json").toPath()));
        buffer = deserialized;
    }

    public class Persons{
        public List<Person> persons;
    }

    public void getPersonDataFromJson() throws IOException {
        Any something = JsonIterator.deserialize(Files.readAllBytes(new File("src/main/resources/data.json").toPath()));
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

    public List<Firestation> getFirestationsData() throws IOException{
        Any firestationsData = buffer.get("firestations");
        firestationsData.forEach(f -> {
            Firestation temp = new Firestation(
                    f.get("station").toInt(),
                    f.get("address").toString());
            this.firestations.add(temp);
        });
        return this.firestations;
    }

    public List<MedicalRecord> getMedicalRecordsData() throws IOException{
        Any medicalRecordsData = buffer.get("medicalrecords");
        medicalRecordsData.forEach(f -> {
            MedicalRecord temp = new MedicalRecord(
                    f.get("firstName").toString(),
                    f.get("lastName").toString(),
                    f.get("birthdate").toString(),
                    Collections.singletonList(f.get("medications").toString()),
                    Collections.singletonList(f.get("allergies").toString())
            );

            this.medicalRecords.add(temp);
        });
        return this.medicalRecords;
    }
}
