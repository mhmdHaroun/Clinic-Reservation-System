package com.example.CRSbackend.appointment;

import com.example.CRSbackend.clinicadministration.ClinicAdministration;
import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @GetMapping("getAppointments/{clinicID}")
    public List<Appointment> getAppointments(@PathVariable("clinicID") int clinicID){
        return appointmentService.getAppointments(clinicID);
    }

    @PostMapping("addAppointment") // clinicID, day, startTime, endTime, maxPatients
    public ResponseEntity<Integer> addAppointment(@RequestBody AppointDTO appointDTO) throws IOException {
        return this.appointmentService.addAppointment(appointDTO);
    }
    @DeleteMapping("deleteAppointment/{appId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("appId") int appId) throws IOException {
        return this.appointmentService.deleteAppointment(appId);
    }

    @PostMapping("updateAppointment") // clinicID, appId, day, startTime, endTime, maxPatients
    public ResponseEntity<String> updateAppointment(@RequestBody AppointDTO appointDTO) throws IOException {
        return this.appointmentService.updateAppointment(appointDTO);
    }

    @PostMapping("updateActiveNumber")  // clinicID, appId, currentActiveNumber
    public ResponseEntity<String> updateActiveNumber(@RequestBody AppointDTO appointDTO) throws IOException {
        return this.appointmentService.updateActiveNumber(appointDTO);
    }

    @PostMapping("updateExpTimePatient") //  clinicID, appId, expTimeCurrentPatient
    public ResponseEntity<String> updateExpTimePatient(@RequestBody AppointDTO appointDTO) throws IOException {
        return this.appointmentService.updateExpTimePatient(appointDTO);
    }

    @GetMapping("resetRegisteredPatients/{appId}")
    public ResponseEntity<String> resetRegisteredPatients(@PathVariable("appId") int appId){
        return appointmentService.resetRegisteredPatients(appId);
    }



}
