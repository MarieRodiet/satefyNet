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

    public static HashMap<String, Integer> countAdultsAndChildren(List<PersonDTO> personsDTO, List<MedicalRecord> medicalRecords){
        HashMap<String, Integer> adultChildrenCounts = new HashMap<>();
        Integer adultCount = 0;
        Integer childCount = 0;
        adultChildrenCounts.put("adultCount", adultCount);
        adultChildrenCounts.put("childCount", childCount);
        for(PersonDTO p: personsDTO){
            //Get birthdate from medical record of that person
            String birthdate = medicalRecords.stream()
                    .filter(m -> Objects.equals(m.getFirstName(), p.getFirstName()) && Objects.equals(m.getLastName(), p.getLastName()))
                    .map(e-> e.getBirthdate())
                    .findAny().orElse(null);
            //calculate birthdate
            if(calculateAge(birthdate) > 18){
                Integer count = adultChildrenCounts.get("adultCount");
                adultChildrenCounts.put("adultCount", count + 1);
            }
            else {
                Integer count = adultChildrenCounts.get("childCount");
                adultChildrenCounts.put("childCount", count + 1);
            }
        }
        return adultChildrenCounts;
    }

    public static long calculateAge (String birthdate) {
        String[] str = birthdate.split("/");
        LocalDate start = LocalDate.of(parseInt(str[2]), parseInt(str[0]), parseInt(str[1]));
        LocalDate end = LocalDate.now();
        return ChronoUnit.YEARS.between(start, end);
    }


}