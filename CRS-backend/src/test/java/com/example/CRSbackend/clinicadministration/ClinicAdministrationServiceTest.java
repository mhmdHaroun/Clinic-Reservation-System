package com.example.CRSbackend.clinicadministration;

import com.example.CRSbackend.patient.Patient;
import com.example.CRSbackend.reservation.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClinicAdministrationServiceTest {
    @Mock
    private ClinicAdministrationRepository clinicAdministrationRepository;
    @Mock
    private ReservationRepository reservationRepository;
    private ClinicAdministrationService clinicAdministrationService;
    @BeforeEach
    void setUp() {
        this.clinicAdministrationService = new ClinicAdministrationService(this.clinicAdministrationRepository, this.reservationRepository);
    }

    @Test
    void logIn_returnTrue_ifEmailAndPasswordCorrect() {
        ClinicAdministration clinic1 = new ClinicAdministration("mohamed@gmail.com", "elite");
        Mockito.when(clinicAdministrationRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        Mockito.when(clinicAdministrationRepository.findClinicByEmail("mohamed@gmail.com")).thenReturn(java.util.Optional.of(clinic1));
        assertEquals(Integer.toString(0), clinicAdministrationService.logIn(clinic1).getBody());
    }
    @Test
    void logIn_returnFalse_ifEmailCorrectAndPasswordNotCorrect() {
        ClinicAdministration clinic1 = new ClinicAdministration("mohamed@gmail.com", "elite");
        ClinicAdministration clinic2 = new ClinicAdministration("mohamed@gmail.com", "garden");
        Mockito.when(clinicAdministrationRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        Mockito.when(clinicAdministrationRepository.findClinicByEmail("mohamed@gmail.com")).thenReturn(java.util.Optional.of(clinic1));
        assertEquals("Invalid email or password", clinicAdministrationService.logIn(clinic2).getBody());
    }
    @Test
    void logIn_returnFalse_ifEmailNotCorrectAndPasswordNotCorrect() {
        ClinicAdministration clinic1 = new ClinicAdministration("mohamed@gmail.com", "elite");
        Mockito.when(clinicAdministrationRepository.existsByEmail("mohamed@gmail.com")).thenReturn(false);
        assertEquals("Invalid email or password", clinicAdministrationService.logIn(clinic1).getBody());
    }
    @Test
    void signUp_returnTrue_ifEmailIsUnique(){
        ClinicAdministration clinic1 = new ClinicAdministration("elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        Mockito.when(clinicAdministrationRepository.existsByEmail("mohamed@gmail.com")).thenReturn(false);
        assertEquals(Integer.toString(0), clinicAdministrationService.signUp(clinic1).getBody());
    }
    @Test
    void signUp_returnFalse_ifEmailIsNotUnique(){
        ClinicAdministration clinic1 = new ClinicAdministration("elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        Mockito.when(clinicAdministrationRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        assertEquals("This email is used before", clinicAdministrationService.signUp(clinic1).getBody());
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsNotChanged(){
        ClinicAdministration clinic1 = new ClinicAdministration(
                "elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        ClinicAdministration clinic2 = new ClinicAdministration("elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        Mockito.when(clinicAdministrationRepository.findById(1)).thenReturn(java.util.Optional.of(clinic1));
        assertTrue(clinicAdministrationService.updateClinic(1, clinic2));
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsChangedButUnique(){
        ClinicAdministration clinic1 = new ClinicAdministration(
                "elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        ClinicAdministration clinic2 = new ClinicAdministration("elite",
                "mohamed",
                "ahmed",
                "omar@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        Mockito.when(clinicAdministrationRepository.findById(1)).thenReturn(java.util.Optional.of(clinic1));
        Mockito.when(clinicAdministrationRepository.existsByEmail("omar@gmail.com")).thenReturn(false);
        assertTrue(clinicAdministrationService.updateClinic(1, clinic2));
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsChangedButNotUnique(){
        ClinicAdministration clinic1 = new ClinicAdministration(
                "elite",
                "mohamed",
                "ahmed",
                "mohamed@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        ClinicAdministration clinic2 = new ClinicAdministration("elite",
                "mohamed",
                "ahmed",
                "omar@gmail.com",
                "elite",
                "dentist",
                "M",
                "blablabla");
        Mockito.when(clinicAdministrationRepository.findById(1)).thenReturn(java.util.Optional.of(clinic1));
        Mockito.when(clinicAdministrationRepository.existsByEmail("omar@gmail.com")).thenReturn(true);
        assertFalse(clinicAdministrationService.updateClinic(1, clinic2));
    }
}