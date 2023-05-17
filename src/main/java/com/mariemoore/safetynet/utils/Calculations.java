package com.mariemoore.safetynet.utils;

import com.mariemoore.safetynet.dto.PersonDTO;
import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.model.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Calculations {

    public static HashMap<String, Integer> countAdultsAndChildren(List<Person> personList, List<MedicalRecord> medicalRecords){
        HashMap<String, Integer> result = new HashMap<>();
        Integer adultCount = 0;
        Integer childCount = 0;
        result.put("adultCount", adultCount);
        result.put("childCount", childCount);
        List<PersonDTO> personDTOS = new ArrayList<>();
        for(Person p: personList){
            //Get birthdate from medical record of that person
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecords.stream()
                    .filter(m -> Objects.equals(m.getFirstName(), p.getFirstName()) && Objects.equals(m.getLastName(), p.getLastName()))
                    .findAny().orElse(null);
            personDTOS.add(new PersonDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord.getBirthdate()));
            //calculate birthdate
            if(calculateAge(medicalRecord.getBirthdate()) > 18){
                Integer count = result.get("adultCount");
                result.put("adultCount", count + 1);
            }
            else {
                Integer count = result.get("childCount");
                result.put("childCount", count + 1);
            }
        }
        return result;
    }

    public static long calculateAge (String birthDate) {
        String[] str = birthDate.split("/");
        LocalDate start = LocalDate.of(parseInt(str[2]), parseInt(str[0]), parseInt(str[1]));
        LocalDate end = LocalDate.now();

        return ChronoUnit.YEARS.between(start, end);
    }
}
