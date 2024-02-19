package com.example.CRSbackend.medicalrecord;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;
//    @Column
//    private int patientId;
//    @Column
//    private int clinicId;
    @Column
    private String record;
    @Column
    private Date recordDate;

    public MedicalRecord() {
    }

//    public MedicalRecord(int recordId, int patientId, int clinicId, String record, Date recordDate) {
//        this.recordId = recordId;
//        this.patientId = patientId;
//        this.clinicId = clinicId;
//        this.record = record;
//        this.recordDate = recordDate;
//    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

//    public int getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(int patientId) {
//        this.patientId = patientId;
//    }

//    public int getClinicId() {
//        return clinicId;
//    }
//
//    public void setClinicId(int clinicId) {
//        this.clinicId = clinicId;
//    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
