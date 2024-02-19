package com.example.CRSbackend.clinicadministration;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientRepository;
import com.example.CRSbackend.reservation.Reservation;
import com.example.CRSbackend.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicAdministrationService {
    private final ClinicAdministrationRepository clinicAdministrationRepository;
    private final ReservationRepository reservationRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder = null;
    @Autowired
    public ClinicAdministrationService(ClinicAdministrationRepository clinicAdministrationRepository, ReservationRepository reservationRepository) {
        this.clinicAdministrationRepository = clinicAdministrationRepository;
        this.reservationRepository = reservationRepository;
        //this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public List<ClinicAdministration> getClinics(){
        return clinicAdministrationRepository.findAll();
    }
    public ResponseEntity<String> signUp(ClinicAdministration clinicAdministration) {
        if(this.clinicAdministrationRepository.existsByEmail(clinicAdministration.getEmail())){
            return ResponseEntity.badRequest().body("This email is used before");
        }
        // encode password
        //String encodedPassword = this.bCryptPasswordEncoder.encode(clinicAdministration.getPassword());
        //clinicAdministration.setPassword(encodedPassword);
        clinicAdministration.setPassword(clinicAdministration.getPassword());
        this.clinicAdministrationRepository.save(clinicAdministration);
        return ResponseEntity.ok().body(String.valueOf(clinicAdministration.getClinicId()));
    }

    public ResponseEntity<String> logIn(ClinicAdministration clinicAdministration){
        if(this.clinicAdministrationRepository.existsByEmail(clinicAdministration.getEmail())){
            Optional<ClinicAdministration> clinic = this.clinicAdministrationRepository.findClinicByEmail(clinicAdministration.getEmail());
            String SavedPassword = clinic.get().getPassword();
            String rawPassword = clinicAdministration.getPassword();
            //boolean isMatched = this.bCryptPasswordEncoder.matches(rawPassword, SavedPassword);
            boolean isMatched = SavedPassword.equals(rawPassword);
            if (isMatched){
                return ResponseEntity.ok().body(String.valueOf(clinic.get().getClinicId()));
            }
        }
        return ResponseEntity.badRequest().body("Invalid email or password");
    }
    @Transactional
    public boolean updateClinic(int id, ClinicAdministration newClinic) {
        ClinicAdministration oldClinic = clinicAdministrationRepository.findById(id).orElse(null);
        if(oldClinic.getEmail() != newClinic.getEmail()
                && clinicAdministrationRepository.existsByEmail(newClinic.getEmail())){
            return false;
        }
        oldClinic.setClinicName(newClinic.getClinicName());
        oldClinic.setDrFirstName(newClinic.getDrFirstName());
        oldClinic.setDrLastName(newClinic.getDrLastName());
        oldClinic.setEmail(newClinic.getEmail());
        oldClinic.setPassword(newClinic.getPassword());
        oldClinic.setSpeciality(newClinic.getSpeciality());
        oldClinic.setLocation(newClinic.getLocation());
        oldClinic.setDetails(newClinic.getDetails());
        return true;
    }
    public List<Reservation> getReservations(int id) {
        return this.reservationRepository.findAllByClinicAdministration(new ClinicAdministration(id));
    }
}
