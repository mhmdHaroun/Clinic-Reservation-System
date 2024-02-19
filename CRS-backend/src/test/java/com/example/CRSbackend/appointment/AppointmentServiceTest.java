package com.example.CRSbackend.appointment;

import com.example.CRSbackend.clinicadministration.ClinicAdministration;
import com.example.CRSbackend.clinicadministration.ClinicAdministrationRepository;
import com.example.CRSbackend.clinicadministration.ClinicAdministrationService;
import com.example.CRSbackend.reservation.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private ClinicAdministrationRepository clinicAdministrationRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    private AppointmentService appointmentService;
    @BeforeEach
    void setUp() {
        this.appointmentService = new AppointmentService(this.appointmentRepository, this.clinicAdministrationRepository);
    }

    @Test
    void addAppointment_return400BadRequest_ifClinicIdNotExists(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20
        );
        Mockito.when(clinicAdministrationRepository.existsById(1)).thenReturn(false);
        assertEquals("400 BAD_REQUEST", appointmentService.addAppointment(appointDTO).getStatusCode().toString());
    }
    @Test
    void addAppointment_return200Ok_ifClinicIdExists(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20
        );
        ClinicAdministration clinicAdministration = new ClinicAdministration(1, new ArrayList<Appointment>());
        Mockito.when(clinicAdministrationRepository.existsById(1)).thenReturn(true);
        Mockito.when(clinicAdministrationRepository.findById(1)).thenReturn(java.util.Optional.of(clinicAdministration));
//        System.out.println(appointmentService.addAppointment(appointDTO).getStatusCode().toString());
        assertEquals("200 OK", appointmentService.addAppointment(appointDTO).getStatusCode().toString());
    }
    @Test
    void deleteAppointment_returnAppointmentNotExist_IfIDNotExists(){
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(false);
        assertEquals("Appointment does not exist", appointmentService.deleteAppointment(1).getBody());
    }
    @Test
    void deleteAppointment_returnDeletedSuccessfully_IfIDExists(){
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        assertEquals("deleted successfully", appointmentService.deleteAppointment(1).getBody());
    }
    @Test
    void updateAppointment_returnAppointmentNotExist_IfIDNotExists(){
        AppointDTO appointDTO = new AppointDTO(
                1
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(false);
        assertEquals("Appointment does not exist", appointmentService.updateAppointment(appointDTO).getBody());
    }
    @Test
    void updateAppointment_returnUpdatedSuccessfuly_IfNewDataIsValid(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Appointment appointment = new Appointment(
                1,
                "mon",
                new Time(5,30,30),
                new Time(8, 30, 30),
                30,
                3,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        Mockito.when(appointmentRepository.getById(1)).thenReturn(appointment);
        assertEquals("updated successfully", appointmentService.updateAppointment(appointDTO).getBody());
    }
    @Test
    void updateActiveNumber_returnAppointmentNotExist_IfIDNotExists(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(false);
        assertEquals("Appointment does not exist", appointmentService.updateActiveNumber(appointDTO).getBody());
    }
    @Test
    void updateActiveNumber_returnUpdatedSuccessfuly_IfNewDataIsValid(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Appointment appointment = new Appointment(
                1,
                "mon",
                new Time(5,30,30),
                new Time(8, 30, 30),
                30,
                3,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        Mockito.when(appointmentRepository.getById(1)).thenReturn(appointment);
        assertEquals("updated successfully", appointmentService.updateActiveNumber(appointDTO).getBody());
    }
    @Test
    void updateExpTimePatient_returnAppointmentNotExist_IfIDNotExists(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(false);
        assertEquals("Appointment does not exist", appointmentService.updateExpTimePatient(appointDTO).getBody());
    }
    @Test
    void updateExpTimePatient_returnEarlyArrivingError_IfEarlyArriving(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Appointment appointment = new Appointment(
                1,
                "mon",
                new Time(5,30,30),
                new Time(8, 30, 30),
                30,
                3,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        Mockito.when(appointmentRepository.getById(1)).thenReturn(appointment);
        assertEquals("EARLY ARRIVNG ERROR !!", appointmentService.updateExpTimePatient(appointDTO).getBody());
    }
    @Test
    void updateExpTimePatient_returnUpdatedSuccessfuly_IfNewDataIsValid(){
        AppointDTO appointDTO = new AppointDTO(
                1,
                1,
                "sat",
                new Time(2,30,30),
                new Time(3, 30, 30),
                20,
                3,
                new Time(2,30,30)
        );
        Appointment appointment = new Appointment(
                1,
                "mon",
                new Time(2,30,30),
                new Time(3, 30, 30),
                30,
                3,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        Mockito.when(appointmentRepository.getById(1)).thenReturn(appointment);
        assertEquals("updated successfully", appointmentService.updateExpTimePatient(appointDTO).getBody());
    }
    @Test
    void resetRegisteredPatient_returnResetSuccessfuly_IfAppIDExists(){
        Appointment appointment = new Appointment(
                1,
                "mon",
                new Time(2,30,30),
                new Time(3, 30, 30),
                30,
                3,
                3,
                new Time(2,30,30)
        );
        Mockito.when(appointmentRepository.existsById(1)).thenReturn(true);
        Mockito.when(appointmentRepository.getById(1)).thenReturn(appointment);
        assertEquals("reset successfully", appointmentService.resetRegisteredPatients(1).getBody());
    }
}