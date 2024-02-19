package com.example.CRSbackend.medicalrecord;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }
    public List<MedicalRecord> getMedicalRecords(){
        return medicalRecordRepository.findAll();
    }
}
