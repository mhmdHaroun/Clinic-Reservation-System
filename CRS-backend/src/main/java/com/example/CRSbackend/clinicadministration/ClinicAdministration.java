package com.example.CRSbackend.clinicadministration;

import com.example.CRSbackend.appointment.Appointment;
import com.example.CRSbackend.medicalrecord.MedicalRecord;
import com.example.CRSbackend.reservation.Reservation;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class ClinicAdministration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clinicId;
    @Column
    private String clinicName;
    @Column
    private String drFirstName;
    @Column
    private String drLastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String speciality;
    @Column
    private String location;
    @Column
    private String details;

    @OneToMany(cascade = CascadeType.ALL)
    @Autowired
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinicId")
    private List<Appointment> appointments;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "clinic_id", referencedColumnName = "clinicId")
    @OneToMany(mappedBy = "clinicAdministration", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinicId")
    private List<MedicalRecord> medicalRecords;

    public ClinicAdministration() {
    }
    public ClinicAdministration(int clinicId) {
        this.clinicId = clinicId;
    }

    public ClinicAdministration(int clinicId, List<Appointment> appointments) {
        this.clinicId = clinicId;
        this.appointments = appointments;
    }

    public ClinicAdministration(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public ClinicAdministration(String clinicName, String drFirstName, String drLastName, String email, String password, String speciality, String location, String details) {
        this.clinicName = clinicName;
        this.drFirstName = drFirstName;
        this.drLastName = drLastName;
        this.email = email;
        this.password = password;
        this.speciality = speciality;
        this.location = location;
        this.details = details;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDrFirstName() {
        return drFirstName;
    }

    public void setDrFirstName(String drFirstName) {
        this.drFirstName = drFirstName;
    }

    public String getDrLastName() {
        return drLastName;
    }

    public void setDrLastName(String drLastName) {
        this.drLastName = drLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }
}
