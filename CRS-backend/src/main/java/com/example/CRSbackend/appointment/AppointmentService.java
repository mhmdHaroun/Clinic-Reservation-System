package com.example.CRSbackend.appointment;

import com.example.CRSbackend.clinicadministration.ClinicAdministration;
import com.example.CRSbackend.clinicadministration.ClinicAdministrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointRepository;

    private final ClinicAdministrationRepository clinicAdminRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointRepository, ClinicAdministrationRepository clinicAdminRepository) {
        this.appointRepository = appointRepository;
        this.clinicAdminRepository = clinicAdminRepository;
    }
    public List<Appointment> getAppointments(int clinicID){
        List<Appointment> allByClinicID =  appointRepository.findAllByClinicID(clinicID);
        return allByClinicID;
    }

    public ResponseEntity<Integer> addAppointment(AppointDTO appointDTO){

        if (!clinicAdminRepository.existsById(appointDTO.getClinicID()) ||
                appointDTO.getStartTime().compareTo(appointDTO.getEndTime()) >= 0){
            return ResponseEntity.badRequest().build();
        }

        long countAppointments = appointRepository.findAppointsInSameTime(appointDTO.getClinicID(), appointDTO.getDay().toLowerCase(), appointDTO.getStartTime());
        System.out.println(countAppointments);
        if (countAppointments != 0){
            return ResponseEntity.badRequest().build();
        }

        Appointment appointment = new Appointment(appointDTO.getDay().toLowerCase(),
                appointDTO.getStartTime(),
                appointDTO.getEndTime(),
                appointDTO.getMaxPatients());
        appointRepository.save(appointment);
        ClinicAdministration clinicAdministration = clinicAdminRepository.findById(appointDTO.getClinicID()).get();
        clinicAdministration.addAppointment(appointment);
        clinicAdminRepository.save(clinicAdministration);

        return ResponseEntity.ok(appointment.getAppId());
    }

    public ResponseEntity<String> deleteAppointment(int appId){
        if (!appointRepository.existsById(appId)){ // appointment does not exist
            return ResponseEntity.badRequest().body("Appointment does not exist");
        }
        appointRepository.deleteById(appId);
        return ResponseEntity.ok("deleted successfully");
    }

    public ResponseEntity<String> updateAppointment(AppointDTO appointDTO){
        // appointment does not exist
        if (!appointRepository.existsById(appointDTO.getAppId())){
            return ResponseEntity.badRequest().body("Appointment does not exist");
        }
        // check if start time greater than or equal end time
        if (appointDTO.getStartTime().compareTo(appointDTO.getEndTime()) >= 0){
            return ResponseEntity.badRequest().body("invalid start and end Time");
        }
        // check if another appointment has the same time
        long count = appointRepository.findAppointsInSameTime(appointDTO.getClinicID(), appointDTO.getAppId(), appointDTO.getDay().toLowerCase(), appointDTO.getStartTime());
        if (count != 0){
            return ResponseEntity.badRequest().body("exist appointment at the same time");
        }
        ///////
        Appointment appointment = appointRepository.getById(appointDTO.getAppId());
        appointment.setDay(appointDTO.getDay().toLowerCase());
        appointment.setStartTime(appointDTO.getStartTime());
        appointment.setEndTime(appointDTO.getEndTime());
        appointment.setMaxPatients(appointDTO.getMaxPatients());
        appointRepository.save(appointment);
        return ResponseEntity.ok("updated successfully");
    }

    public ResponseEntity<String> updateActiveNumber(AppointDTO appointDTO){
        // appointment does not exist
        if (!appointRepository.existsById(appointDTO.getAppId())){
            return ResponseEntity.badRequest().body("Appointment does not exist");
        }
        Appointment appointment = appointRepository.getById(appointDTO.getAppId());
        int curr = appointDTO.getCurrentActiveNumber();
        if(curr < 0)
            return ResponseEntity.badRequest().body("WRONG PATIENT ID !!");
        else if(curr >= appointment.getMaxPatients())
            return ResponseEntity.badRequest().body("MaxPatient exeeded !!");
        else 
            appointment.setCurrentActiveNumber(curr);
        
        appointRepository.save(appointment);
        return ResponseEntity.ok("updated successfully");
    }

    public ResponseEntity<String> updateExpTimePatient(AppointDTO appointDTO){
        // appointment does not exist
        if (!appointRepository.existsById(appointDTO.getAppId())){
            return ResponseEntity.badRequest().body("Appointment does not exist");
        }
        Appointment appointment = appointRepository.getById(appointDTO.getAppId());
        Time curr = appointDTO.getExpTimeCurrentPatient();
        if(curr.before(appointment.getStartTime()))
            return ResponseEntity.badRequest().body("EARLY ARRIVNG ERROR !!");
        else if(curr.after(appointment.getEndTime()))
            return ResponseEntity.badRequest().body("LATE ARRIVNG ERROR !!");
        else
            appointment.setExpTimeCurrentPatient(curr);
        
        appointRepository.save(appointment);
        return ResponseEntity.ok("updated successfully");
    }

   

    public ResponseEntity<String> resetRegisteredPatients(int appId){
        // appointment does not exist
        if (!appointRepository.existsById(appId)){
            return ResponseEntity.badRequest().body("Appointment does not exist");
        }
        Appointment appointment = appointRepository.getById(appId);
        appointment.setNumRegPatients(0);
        appointRepository.save(appointment);
        return ResponseEntity.ok("reset successfully");
    }

}
