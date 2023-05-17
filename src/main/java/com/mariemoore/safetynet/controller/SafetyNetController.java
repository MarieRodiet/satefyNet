package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.dto.PersonAndFirestationDTO;
import com.mariemoore.safetynet.dto.PersonDTO;
import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.FirestationService;
import com.mariemoore.safetynet.service.MedicalRecordService;
import com.mariemoore.safetynet.service.PersonService;
import com.mariemoore.safetynet.utils.Calculations;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
public class SafetyNetController {
    private static final Logger logger = getLogger(MedicalRecordController.class);
    @Autowired
    private PersonService personService;
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private MedicalRecordService medicalRecordService;


    public SafetyNetController(PersonService personService, FirestationService firestationService, MedicalRecordService medicalRecordService){
        this.personService = personService;
        this.firestationService = firestationService;
        this.medicalRecordService = medicalRecordService;
    }

    @ResponseBody
    @GetMapping("/firestation")
    public PersonAndFirestationDTO getPersonsAttachedToStation(@RequestParam(value = "stationNumber") Integer stationNumber){
        //return 1. List<PersonDTO> living in the area of the stationNumber
        //return 2. Object {"adults": 20, "children": 10} of that list
        //Person: firstname, lastname, address, phone number

        List<String> stationAddresses;
        stationAddresses = this.firestationService.findAddressesOfFirestation(stationNumber);
        List<Person> personListLivingAtFirestationAddresses = null;

        if (stationAddresses.size() > 0) {
            List<Person> allPersons = this.personService.getPersons();
            personListLivingAtFirestationAddresses = allPersons.stream()
                    .filter(person -> stationAddresses.stream().anyMatch(a -> Objects.equals(person.getAddress(), a)))
                    .collect(Collectors.toList());
        }

        //get birthdays of Person to calculate their age
        List<PersonDTO> personDTOS = new ArrayList<>();
        for(Person p: personListLivingAtFirestationAddresses){
            MedicalRecord medicalRecord = new MedicalRecord();
            this.medicalRecordService.getMedicalRecords().stream()
                    .filter(m -> Objects.equals(m.getFirstName(), p.getFirstName()) && Objects.equals(m.getLastName(), p.getLastName()))
                    .findAny().orElse(null);
            personDTOS.add(new PersonDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord.getBirthdate()));
        }

        HashMap<String, Integer> nbOfAdultsAndChildren = Calculations.countAdultsAndChildren(personListLivingAtFirestationAddresses, this.medicalRecordService.getMedicalRecords());

        PersonAndFirestationDTO result = new PersonAndFirestationDTO(personListLivingAtFirestationAddresses, nbOfAdultsAndChildren);
        return result;
    }

}
