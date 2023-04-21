package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.Firestation;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {
    List<Firestation> firestations;
    JsonDataGetter jsonDataGetter;

    public FirestationRepository(List<Firestation> firestations, JsonDataGetter jsonDataGetter) throws IOException {
        this.jsonDataGetter = jsonDataGetter;
        this.firestations = this.jsonDataGetter.getFirestationsData();
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public Firestation saveFirestation(Firestation toAdd){
        this.firestations.add(toAdd);
        return toAdd;
    }

    public Firestation updateFirestation(Firestation toUpdate){
        for(Firestation f: this.firestations){
            if(f.getAddress().equals(toUpdate.getAddress())){
                f.setStation(toUpdate.getStation());
            }
        }
        return toUpdate;
    }

    public Firestation deleteFirestation(Firestation toDelete){
        List<Firestation> toKeep = new ArrayList<>();
        for(Firestation f: this.firestations){
            if(!f.getAddress().equals(toDelete.getAddress())){
                toKeep.add(f);
            }
        }
        this.firestations = toKeep;
        return toDelete;
    }
}
