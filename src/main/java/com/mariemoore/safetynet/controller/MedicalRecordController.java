package com.mariemoore.safetynet.controller;

import com.mariemoore.safetynet.model.MedicalRecord;
import com.mariemoore.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/all")
    public List<MedicalRecord> getMedicalRecords(){
        return medicalRecordService.getMedicalRecords();
    }


    @ResponseBody
    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @ResponseBody
    @DeleteMapping
    public MedicalRecord deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.deleteMedicalRecord(medicalRecord);
    }

    @ResponseBody
    @PutMapping
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.updateMedicalRecord(medicalRecord);
    }
}
