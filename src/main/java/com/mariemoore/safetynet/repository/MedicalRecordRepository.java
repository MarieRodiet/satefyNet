package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    List<MedicalRecord> medicalRecords;

    JsonDataGetter jsonDataGetter;

    public MedicalRecordRepository(JsonDataGetter jsonDataGetter) throws IOException {
        this.jsonDataGetter = jsonDataGetter;
        this.medicalRecords = this.jsonDataGetter.getMedicalRecordsData();
    }

    public List<MedicalRecord> findAll(){
        return this.medicalRecords;
    }

    public MedicalRecord save(MedicalRecord medicalRecord){
        this.medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord update(MedicalRecord toUpdate){
        for(MedicalRecord m: this.medicalRecords){
            if(m.getFirstName().equals(toUpdate.getFirstName()) && m.getLastName().equals(toUpdate.getLastName())){
                m.setMedications(toUpdate.getMedications());
                m.setAllergies(toUpdate.getAllergies());
                m.setBirthdate(toUpdate.getBirthdate());
            }
        }
        return toUpdate;
    }

    public MedicalRecord delete(MedicalRecord toDelete){
        List<MedicalRecord> toKeep = new ArrayList<>();
        for(MedicalRecord m: this.medicalRecords){
            if(!m.getFirstName().equals(toDelete.getFirstName()) && !m.getLastName().equals(toDelete.getLastName())){
                toKeep.add(m);
            }
        }
        this.medicalRecords = toKeep;
        return toDelete;
    }
}
