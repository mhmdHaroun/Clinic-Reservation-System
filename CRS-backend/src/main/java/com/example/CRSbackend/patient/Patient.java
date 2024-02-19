package com.example.CRSbackend.patient;

import com.example.CRSbackend.medicalrecord.MedicalRecord;
import com.example.CRSbackend.reservation.Reservation;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private java.sql.Date birthday;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String gender;
    @Column
    private String details;

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private List<MedicalRecord> medicalRecords;

    public Patient() {
    }
    public Patient(int patientId) {
        this.patientId = patientId;
    }
    public Patient(String firstName, String lastName, java.sql.Date birthday, String email, String password, String gender, String details) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.details = details;
    }

    public Patient(String email, String password) {
        this.email = email;
        this.password = password;
    }
        public Patient(int patientId, String firstName, String lastName, java.sql.Date birthday, String email, String password, String gender, String details) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.details = details;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
