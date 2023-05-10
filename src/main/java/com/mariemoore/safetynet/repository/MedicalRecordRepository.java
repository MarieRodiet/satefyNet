package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.utils.Validation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MedicalRecordRepository {

    List<MedicalRecord> medicalRecords;
    JsonDataGetter jsonDataGetter;
    Validation validation;

    public MedicalRecordRepository(JsonDataGetter jsonDataGetter, Validation validation) {
        this.jsonDataGetter = jsonDataGetter;
        this.medicalRecords = this.jsonDataGetter.getMedicalRecordsData();
        this.validation = validation;
    }

    public List<MedicalRecord> findAll(){
        return this.medicalRecords;
    }

    public MedicalRecord save(MedicalRecord medicalRecord){
        MedicalRecord alreadyExists = this.medicalRecords.stream().filter( m ->
                m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
                .findAny().orElse(null);
        if(this.validation.isMedicalRecordInvalid(medicalRecord) || Objects.nonNull(alreadyExists)){
            return null;
        }
        this.medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord update(MedicalRecord toUpdate){
        Boolean isFoundAndValid = false;
        for(MedicalRecord m: this.medicalRecords){
            if(m.getFirstName().equals(toUpdate.getFirstName()) && m.getLastName().equals(toUpdate.getLastName()) && !this.validation.isMedicalRecordInvalid(toUpdate)){
                isFoundAndValid = true;
                m.setMedications(toUpdate.getMedications());
                m.setAllergies(toUpdate.getAllergies());
                m.setBirthdate(toUpdate.getBirthdate());
            }
        }
        return isFoundAndValid ? toUpdate : null;
    }

    public MedicalRecord delete(MedicalRecord toDelete){
        for(MedicalRecord m: this.medicalRecords){
            if(m.getFirstName().equals(toDelete.getFirstName()) && m.getLastName().equals(toDelete.getLastName())){
                m.setMedications(new ArrayList<>());
                m.setAllergies(new ArrayList<>());
                return m;
            }
        }
        return null;
    }
}
