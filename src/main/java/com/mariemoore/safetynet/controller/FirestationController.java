package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.Firestation;
import com.mariemoore.safetynet.service.FirestationService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    @Autowired
    FirestationService firestationService;
    private static final Logger logger = getLogger(FirestationController.class);

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }


    @GetMapping("/all")
    public List<Firestation> getFirestations(){
        logger.info("getting all firestations");
        return firestationService.getFirestations();
    }



    @ResponseBody
    @PostMapping
    public Firestation addFirestation(@RequestBody Firestation firestation){
        Firestation addedFirestation = firestationService.addFirestation(firestation.getStation(), firestation.getAddress());
        if(Objects.isNull(addedFirestation)){
            logger.error("could not add firestation");
            return null;
        }
        logger.info("firestation added successfully");
        return addedFirestation;
    }




    @ResponseBody
    @DeleteMapping
    public Firestation deleteFirestation(@RequestBody Firestation firestation){
        Firestation deletedFirestation = firestationService.deleteFirestation(firestation);
        if(Objects.isNull(deletedFirestation)){
            logger.error("could not delete firestation");
            return null;
        }
        logger.info("firestation deleted successfully");
        return deletedFirestation;
    }

    @ResponseBody
    @PutMapping
    public Firestation updateFirestation(@RequestBody Firestation firestation){
        Firestation updatedFirestation = firestationService.updateFirestation(firestation);
        if(Objects.isNull(updatedFirestation)){
            logger.error("could not update firestation");
            return null;
        }
        logger.info("firestation updated successfully");
        return updatedFirestation;
    }

}
