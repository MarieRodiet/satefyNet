package com.mariemoore.safetynet.repository;

import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.utils.Validation;
import org.springframework.stereotype.Repository;
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

    public MedicalRecord save(MedicalRecord toAdd){
        MedicalRecord exists = this.validation.medicalRecordExists(this.medicalRecords, toAdd.getFirstName(), toAdd.getLastName());
        //will only add if medical record is valid and that person's medical record does not already exists
        if(this.validation.isMedicalRecordInvalid(toAdd) || Objects.nonNull(exists)){
            return null;
        }
        this.medicalRecords.add(toAdd);
        return toAdd;
    }

    public MedicalRecord update(MedicalRecord toUpdate){
        MedicalRecord exists = this.validation.medicalRecordExists(this.medicalRecords, toUpdate.getFirstName(), toUpdate.getLastName());
        //will only update if medical record already exists
        if(Objects.isNull(exists)){
            return null;
        }
        exists.setMedications(toUpdate.getMedications());
        exists.setAllergies(toUpdate.getAllergies());
        exists.setBirthdate(toUpdate.getBirthdate());
        return toUpdate;
    }

    public MedicalRecord delete(MedicalRecord toDelete){
        MedicalRecord exists = this.validation.medicalRecordExists(this.medicalRecords, toDelete.getFirstName(), toDelete.getLastName());
        //will only delete if medical record already exists
        if(Objects.isNull(exists)){
            return null;
        }
        this.medicalRecords.stream().filter((m -> !m.equals(exists)));
        return toDelete;
    }
}
