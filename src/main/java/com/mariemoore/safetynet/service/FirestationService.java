package com.mariemoore.safetynet.service;

import com.mariemoore.safetynet.model.Firestation;
import com.mariemoore.safetynet.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public List<Firestation> getFirestations(){ return firestationRepository.getFirestations();}

    public Firestation addFirestation(Firestation firestation){
        return this.firestationRepository.saveFirestation(firestation);
    }

    public Firestation updateFirestation(Firestation firestation){
        return this.firestationRepository.updateFirestation(firestation);
    }

    public Firestation deleteFirestation(Firestation firestation){
        return this.firestationRepository.deleteFirestation(firestation);
    }
}
