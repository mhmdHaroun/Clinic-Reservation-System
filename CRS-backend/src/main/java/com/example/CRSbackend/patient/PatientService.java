package com.example.CRSbackend.patient;

import com.example.CRSbackend.reservation.Reservation;
import com.example.CRSbackend.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final ReservationRepository reservationRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder = null;
    @Autowired
    public PatientService(PatientRepository studentRepository, ReservationRepository reservationRepository) {
        this.patientRepository = studentRepository;
        this.reservationRepository = reservationRepository;
        //this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
    public ResponseEntity<String> signUp(Patient patient) {
        if(this.patientRepository.existsByEmail(patient.getEmail())){
            return ResponseEntity.badRequest().body("This email is used before");
        }
        // encode password
        //String encodedPassword = this.bCryptPasswordEncoder.encode(patient.getPassword());
        //patient.setPassword(encodedPassword);
        patient.setPassword(patient.getPassword());
        this.patientRepository.save(patient);
        return ResponseEntity.ok().body(String.valueOf(patient.getPatientId()));
    }
    public ResponseEntity<String> logIn(Patient patient){
        if(this.patientRepository.existsByEmail(patient.getEmail())){
//            if(this.patientRepository.findPatientByEmail(patient.getEmail())
//                    .get().getPassword().equals(patient.getPassword())){
//                return true;
//            }
            Optional<Patient> patient1 = this.patientRepository.findPatientByEmail(patient.getEmail());
            String savedPassword = patient1.get().getPassword();
            String rawPassword = patient.getPassword();
            //boolean isMatched = this.bCryptPasswordEncoder.matches(rawPassword, savedPassword);
            boolean isMatched =  savedPassword.equals(rawPassword);
            if (isMatched){
                return ResponseEntity.ok().body(String.valueOf(patient1.get().getPatientId()));
            }
        }
        return ResponseEntity.badRequest().body("Invalid email or password");
    }
    @Transactional
    public boolean updatePatient(int id, Patient newPatient){
        Patient oldPatient = patientRepository.findById(id).orElse(null);
        if(oldPatient.getEmail() != newPatient.getEmail()
                && patientRepository.existsByEmail(newPatient.getEmail())){
            return false;
        }
        oldPatient.setFirstName(newPatient.getFirstName());
        oldPatient.setLastName(newPatient.getLastName());
        oldPatient.setBirthday((Date) newPatient.getBirthday());
        oldPatient.setEmail(newPatient.getEmail());
        oldPatient.setPassword(newPatient.getPassword());
        oldPatient.setGender(newPatient.getGender());
        oldPatient.setDetails(newPatient.getDetails());
        return true;
    }
    public List<Reservation> getReservations(int id) {
        return this.reservationRepository.findAllByPatient(new Patient(id));
    }
}
