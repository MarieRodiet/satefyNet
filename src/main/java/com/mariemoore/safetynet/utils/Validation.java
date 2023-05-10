package com.mariemoore.safetynet.utils;

import com.mariemoore.safetynet.model.Firestation;
import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.model.Person;
import org.springframework.stereotype.Service;

@Service
public class Validation {
    public static boolean isStringInvalid(String s) {
        return s == null || s.isBlank();
    }

    public static boolean isPersonInvalid(Person toValidate){
        if(
            isStringInvalid(toValidate.getFirstName()) ||
            isStringInvalid(toValidate.getLastName()) ||
            isStringInvalid(toValidate.getAddress()) ||
            isStringInvalid(toValidate.getCity()) ||
            isStringInvalid(toValidate.getZip()) ||
            isStringInvalid(toValidate.getPhone()) ||
            isStringInvalid(toValidate.getEmail())
        ){
            return true;
        }
        else return false;
    }

    public static boolean isFireStationInvalid(Firestation toValidate){
        if (
                isStringInvalid(toValidate.getStation().toString()) ||
                isStringInvalid(toValidate.getAddress())
        ) {
            return true;
        }
        else return false;
    }

    public static boolean isMedicalRecordInvalid(MedicalRecord toValidate){
        if (
                isStringInvalid(toValidate.getFirstName()) ||
                isStringInvalid(toValidate.getLastName()) ||
                isStringInvalid(toValidate.getBirthdate()) ||
                toValidate.getMedications() == null ||
                toValidate.getAllergies() == null
        ) {
            return true;
        }
        else return false;
    }
}
