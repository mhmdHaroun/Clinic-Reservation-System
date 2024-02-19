package com.example.CRSbackend.clinicadministration;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientService;
import com.example.CRSbackend.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "clinics")
public class ClinicAdministrationController {
    private ClinicAdministrationService clinicAdministrationService;
    @Autowired
    public ClinicAdministrationController(ClinicAdministrationService clinicAdministrationService){
        this.clinicAdministrationService = clinicAdministrationService;
    }

    @GetMapping
    public List<ClinicAdministration> getClinics(){
        return clinicAdministrationService.getClinics();
    }
    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody ClinicAdministration clinicAdministration) throws IOException {
        return clinicAdministrationService.signUp(clinicAdministration);
    }
    @PostMapping("logIn")
    public ResponseEntity<String> logIn(@RequestBody ClinicAdministration clinicAdministration) throws IOException {
        return clinicAdministrationService.logIn(clinicAdministration);
    }
    @PutMapping("updateClinic/{id}")
    public boolean updateClinic(@PathVariable int id
            , @RequestBody ClinicAdministration clinicAdministration){
        return clinicAdministrationService.updateClinic(id, clinicAdministration);
    }
    @GetMapping("getReservations/{id}")
    public List<Reservation> getReservations(@PathVariable int id){
        return clinicAdministrationService.getReservations(id);
    }
}
