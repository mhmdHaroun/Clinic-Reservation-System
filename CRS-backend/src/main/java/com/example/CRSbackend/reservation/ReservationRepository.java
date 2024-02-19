package com.example.CRSbackend.reservation;

import com.example.CRSbackend.appointment.Appointment;
import com.example.CRSbackend.clinicadministration.ClinicAdministration;
import com.example.CRSbackend.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByAppointment(Appointment appointment);
    List<Reservation> findAllByPatient(Patient patient);
    List<Reservation> findAllByClinicAdministration(ClinicAdministration clinicAdministration);
    List<Reservation> findAllByPatientAndClinicAdministration(Patient patient, ClinicAdministration clinicAdministration);
}
