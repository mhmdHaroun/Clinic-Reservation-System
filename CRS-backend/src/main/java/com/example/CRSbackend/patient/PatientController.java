package com.example.CRSbackend.patient;

import com.example.CRSbackend.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "patients")
public class PatientController {
    private PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping
    public List<Patient> getStudents(){
        return patientService.getPatients();
    }
    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody Patient patient) throws IOException {
        return patientService.signUp(patient);
    }
    @PostMapping("logIn")
    public ResponseEntity<String> logIn(@RequestBody Patient patient) throws IOException {
        return patientService.logIn(patient);
    }
    @PutMapping("updatePatient/{id}")
    public boolean updatePatient(@PathVariable int id
            , @RequestBody Patient newPatient){
        return patientService.updatePatient(id, newPatient);
    }
    @GetMapping("getReservations/{id}")
    public List<Reservation> getReservations(@PathVariable int id){
        return patientService.getReservations(id);
    }
}
