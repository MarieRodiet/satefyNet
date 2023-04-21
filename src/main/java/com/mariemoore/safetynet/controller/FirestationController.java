package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Firestation;
import com.mariemoore.safetynet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    @Autowired
    FirestationService firestationService;


    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }


    @GetMapping("/all")
    public List<Firestation> getFirestations(){
        return firestationService.getFirestations();
    }


    @ResponseBody
    @PostMapping
    public Firestation addFirestation(@RequestBody Firestation firestation){
        return firestationService.addFirestation(firestation);
    }

    @ResponseBody
    @DeleteMapping
    public Firestation deleteFirestation(@RequestBody Firestation firestation){
        return firestationService.deleteFirestation(firestation);
    }

    @ResponseBody
    @PutMapping
    public Firestation updateFirestation(@RequestBody Firestation firestation){
        return firestationService.updateFirestation(firestation);
    }

}
