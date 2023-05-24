package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.dto.ChildWithHouseholdDTO;
import com.mariemoore.safetynet.dto.PersonAgeDTO;
import com.mariemoore.safetynet.dto.PersonAndFirestationDTO;
import com.mariemoore.safetynet.dto.PersonPhoneDTO;
import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.FirestationService;
import com.mariemoore.safetynet.service.MedicalRecordService;
import com.mariemoore.safetynet.service.PersonService;
import com.mariemoore.safetynet.utils.Calculations;
import com.mariemoore.safetynet.utils.CustomFilters;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PersonAndFirestationDTO> getPersonsAttachedToStation(@RequestParam(value = "stationNumber") Integer stationNumber){
        List<String> stationAddresses = this.firestationService.findAddressesOfFirestation(stationNumber);
        List<PersonPhoneDTO> personsDTO = null;

        if (stationAddresses.size() > 0) {
            personsDTO = this.personService.getPersons().stream()
                    .filter(person -> stationAddresses.stream().anyMatch(a -> Objects.equals(person.getAddress(), a)))
                    .map(p-> new PersonPhoneDTO(p.getFirstName(), p.getLastName(), p.getPhone())).collect(Collectors.toList());
        }

        HashMap<String, Integer> nbOfAdultsAndChildren = Calculations.countAdultsAndChildren(personsDTO , this.medicalRecordService.getMedicalRecords());

        PersonAndFirestationDTO result = new PersonAndFirestationDTO(personsDTO, nbOfAdultsAndChildren);
        logger.info("getting all persons attached to firestation number");
        return ResponseEntity.ok().body(result);
    }

    @ResponseBody
    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildWithHouseholdDTO>> getChildrenWithHouseholdAtAddress(@RequestParam(value = "address") String address){
        //get all people living at this address
        List<Person> peopleLivingAtAddress = this.personService.getPersons()
                .stream()
                .filter(person -> Objects.equals(person.getAddress(), address))
                .collect(Collectors.toList());

        //get all children out of this list with their age
        List<PersonAgeDTO> children = CustomFilters.getChildrenFromList(peopleLivingAtAddress, this.medicalRecordService.getMedicalRecords());

        //get all children with their household
        List<ChildWithHouseholdDTO> childrenWithAge = new ArrayList<>();
        childrenWithAge = children.stream()
                        .map(child -> new ChildWithHouseholdDTO(
                                child.getFirstName(),
                                child.getLastName(),
                                child.getAge(),
                                personService.getHouseholdOfChild(
                                        child.getFirstName(),
                                        child.getLastName())
                        ))
                        .collect(Collectors.toList());
        logger.info("getting all children and their household attached to address");
        return ResponseEntity.ok().body(childrenWithAge);
    }

    @ResponseBody
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumbersAttachedToFirestationNumber(@RequestParam(value="firestation") Integer stationNumber){
        List<String> stationAddresses = this.firestationService.findAddressesOfFirestation(stationNumber);

        List<String> phoneNumbers = this.personService.getPersons().stream()
                .filter(person -> stationAddresses.stream().anyMatch(address -> Objects.equals(person.getAddress(), address)))
                .map(person -> person.getPhone())
                .collect(Collectors.toList());
        logger.info("getting phone numbers of everybody attached to firestation number");
        return ResponseEntity.ok().body(phoneNumbers);
    }

    /*
    http://localhost:8080/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée
    ainsi que le numéro de la caserne de pompiers la desservant.
    La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents médicaux
    (médicaments, posologie et allergies) de chaque personne.*/
    @ResponseBody
    @GetMapping("/fire")
    public ResponseEntity<String> getPeopleLivingAtAddress(@RequestParam(value="address") String address){
        //DTO (firstname, lastname, phone, age, medications, allergies, stationNumber
        System.out.println(address);

        logger.info("getting people living at address");
        return ResponseEntity.ok().body(address);
    }
}
