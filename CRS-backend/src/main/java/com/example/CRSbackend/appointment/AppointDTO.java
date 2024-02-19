package com.example.CRSbackend.appointment;


import lombok.Getter;
import lombok.Setter;

import javax.xml.transform.sax.SAXResult;
import java.sql.Time;

@Getter
@Setter
public class AppointDTO {
    private int clinicID;
    private int appId;
    private String day;
    private Time startTime;
    private Time endTime;
    private int maxPatients;
    private int currentActiveNumber;
    private Time expTimeCurrentPatient;

    public AppointDTO(int clinicID, String day, Time startTime, Time endTime, int maxPatients) {
        this.clinicID = clinicID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatients = maxPatients;
    }

    public AppointDTO(int appId) {
        this.appId = appId;
    }

    public AppointDTO(int clinicID, int appId, String day, Time startTime, Time endTime, int maxPatients, int currentActiveNumber, Time expTimeCurrentPatient) {
        this.clinicID = clinicID;
        this.appId = appId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatients = maxPatients;
        this.currentActiveNumber = currentActiveNumber;
        this.expTimeCurrentPatient = expTimeCurrentPatient;
    }
}

