package com.example.CRSbackend.medicalrecord;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "medical-records")
public class MedicalRecordController {
    private MedicalRecordService medicalRecordService;
    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }
    @GetMapping
    public List<MedicalRecord> getMedicalRecords(){
        return medicalRecordService.getMedicalRecords() ;
    }
}
