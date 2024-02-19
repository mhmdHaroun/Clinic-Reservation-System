package com.example.CRSbackend.reservation;

import com.example.CRSbackend.appointment.Appointment;
import com.example.CRSbackend.clinicadministration.ClinicAdministration;
import com.example.CRSbackend.patient.Patient;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;
//    @Column
//    private int patientId;
//    @Column
//    private int clinicId;
//    @Column
//    private int appId;
    @Column
    private Date reservationDate;
    @Column
    private java.sql.Date appointmentDate;
    @Column
    private int patientNum;
    @Column
    private int isConsultaion;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private ClinicAdministration clinicAdministration;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Appointment appointment;

    public Reservation() {
    }
    public Reservation(java.sql.Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public Reservation(java.sql.Date appointmentDate, int isConsultaion) {
        this.appointmentDate = appointmentDate;
        this.isConsultaion = isConsultaion;
    }

    public Reservation(Date reservationDate, java.sql.Date appointmentDate, int patientNum, int isConsultaion, ClinicAdministration clinicAdministration, Patient patient, Appointment appointment) {
        this.reservationDate = reservationDate;
        this.appointmentDate = appointmentDate;
        this.patientNum = patientNum;
        this.isConsultaion = isConsultaion;
        this.clinicAdministration = clinicAdministration;
        this.patient = patient;
        this.appointment = appointment;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

//    public int getAppId() {
//        return appId;
//    }
//
//    public void setAppId(int appId) {
//        this.appId = appId;
//    }

    public ClinicAdministration getClinicAdministration() {
        return clinicAdministration;
    }

    public void setClinicAdministration(ClinicAdministration clinicAdministration) {
        this.clinicAdministration = clinicAdministration;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public java.sql.Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(java.sql.Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(int patientNum) {
        this.patientNum = patientNum;
    }

    public int getIsConsultaion() {
        return isConsultaion;
    }

    public void setIsConsultaion(int isConsultaion) {
        this.isConsultaion = isConsultaion;
    }
}
