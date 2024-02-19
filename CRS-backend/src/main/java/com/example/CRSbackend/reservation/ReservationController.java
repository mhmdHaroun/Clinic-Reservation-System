package com.example.CRSbackend.reservation;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "reservations")
public class ReservationController {
    private ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }
//    @PostMapping("addReservation/{patientId}/{appId}")
//    public boolean addReservation(
//            @PathVariable int patientId,
//            @PathVariable int appId,
//            @RequestBody Reservation reservation
//    ){
//        return this.reservationService.addReservation(patientId, appId, reservation);
//    }

    @PostMapping("addReservation/{clinicId}/{patientId}/{appId}")
    public int addReservation(
            @PathVariable int clinicId,
            @PathVariable int patientId,
            @PathVariable int appId,
            @RequestBody Reservation reservation
    ){
        return this.reservationService.addReservation(clinicId, patientId, appId, reservation);
    }

//    @PostMapping("addReservation")
//    public boolean addReservation(
//            @RequestParam int cid,
//            @RequestParam int pid,
//            @RequestParam int aid,
//            @RequestBody Reservation reservation
//    ){
//        return this.reservationService.addReservation(cid, pid, aid, reservation);
//    }
}
