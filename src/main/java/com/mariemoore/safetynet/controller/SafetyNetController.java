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
        List<String> stationAddresses = this.firestationService.findAddressesOfFirestation(stationNumber);
        List<PersonDTO> personsDTO = null;

        if (stationAddresses.size() > 0) {
            personsDTO = this.personService.getPersons().stream()
                    .filter(person -> stationAddresses.stream().anyMatch(a -> Objects.equals(person.getAddress(), a)))
                    .map(p-> new PersonDTO(p.getFirstName(), p.getLastName(), p.getPhone())).collect(Collectors.toList());
        }

        HashMap<String, Integer> nbOfAdultsAndChildren = Calculations.countAdultsAndChildren(personsDTO , this.medicalRecordService.getMedicalRecords());

        PersonAndFirestationDTO result = new PersonAndFirestationDTO(personsDTO, nbOfAdultsAndChildren);
        return result;
    }

}
