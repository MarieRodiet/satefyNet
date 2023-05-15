package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.dto.FirestationAndPersonDTO;
import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.FirestationService;
import com.mariemoore.safetynet.service.MedicalRecordService;
import com.mariemoore.safetynet.service.PersonService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public Integer getPersonsAttachedToStation(@RequestParam(value = "stationNumber") Integer stationNumber){
        //return 1. List<Person> living in the area of the stationNumber
        //return 2. Object {"adults": 20, "children": 10} of that list
        //Person: firstname, lastname, address, phone number
        List<String> stationAddresses = new ArrayList<>();
        stationAddresses = this.firestationService.findAddressesOfFirestation(stationNumber);

        System.out.println(stationAddresses);
        return 1;
    }

    {

    }
}
