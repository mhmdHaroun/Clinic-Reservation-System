package com.example.CRSbackend.patient;

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
class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ReservationRepository reservationRepository;
    private PatientService patientService;
    @BeforeEach
    void setUp() {
        this.patientService = new PatientService(this.patientRepository, this.reservationRepository);
    }

//    @Test
//    void test(){
//        System.out.println(1);
//    }
    @Test
    void logIn_returnTrue_ifEmailAndPasswordCorrect() {
        Patient patient1 = new Patient("mohamed@gmail.com", "elite");
        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        Mockito.when(patientRepository.findPatientByEmail("mohamed@gmail.com")).thenReturn(java.util.Optional.of(patient1));
        assertEquals(Integer.toString(0), patientService.logIn(patient1).getBody());
    }
    @Test
    void logIn_returnFalse_ifEmailCorrectAndPasswordNotCorrect() {
        Patient patient1 = new Patient("mohamed@gmail.com", "elite");
        Patient patient2 = new Patient("mohamed@gmail.com", "garden");
        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        Mockito.when(patientRepository.findPatientByEmail("mohamed@gmail.com")).thenReturn(java.util.Optional.of(patient1));
        assertEquals("Invalid email or password", patientService.logIn(patient2).getBody());
    }
    @Test
    void logIn_returnFalse_ifEmailNotCorrectAndPasswordNotCorrect() {
        Patient patient1 = new Patient("mohamed@gmail.com", "elite");
        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(false);
        assertEquals("Invalid email or password", patientService.logIn(patient1).getBody());
    }
    @Test
    void signUp_returnTrue_ifEmailIsUnique(){
        Patient patient1 = new Patient("mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "elite",
                "M",
                "blablabla");
        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(false);
        assertEquals(Integer.toString(0), patientService.signUp(patient1).getBody());
    }
    @Test
    void signUp_returnFalse_ifEmailIsNotUnique(){
        Patient patient1 = new Patient("mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "elite",
                "M",
                "blablabla");
        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        assertEquals("This email is used before", patientService.signUp(patient1).getBody());
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsNotChanged(){
        Patient patient1 = new Patient(1,
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "elite",
                "M",
                "blablabla");
        Patient patient2 = new Patient(
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "stars",
                "M",
                "blablabla");
        Mockito.when(patientRepository.findById(1)).thenReturn(java.util.Optional.of(patient1));
        assertTrue(patientService.updatePatient(1, patient2));
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsChangedButUnique(){
        Patient patient1 = new Patient(1,
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "elite",
                "M",
                "blablabla");
        Patient patient2 = new Patient(
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "omar@gmail.com",
                "stars",
                "M",
                "blablabla");
        Mockito.when(patientRepository.findById(1)).thenReturn(java.util.Optional.of(patient1));
        Mockito.when(patientRepository.existsByEmail("omar@gmail.com")).thenReturn(false);
//        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        assertTrue(patientService.updatePatient(1, patient2));
    }
    @Test
    void updatePatient_returnTrue_ifEmailIsChangedButNotUnique(){
        Patient patient1 = new Patient(1,
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "mohamed@gmail.com",
                "elite",
                "M",
                "blablabla");
        Patient patient2 = new Patient(
                "mohamed",
                "ahmed",
                new Date(2000, 2, 2),
                "omar@gmail.com",
                "stars",
                "M",
                "blablabla");
        Mockito.when(patientRepository.findById(1)).thenReturn(java.util.Optional.of(patient1));
        Mockito.when(patientRepository.existsByEmail("omar@gmail.com")).thenReturn(true);
//        Mockito.when(patientRepository.existsByEmail("mohamed@gmail.com")).thenReturn(true);
        assertFalse(patientService.updatePatient(1, patient2));
    }

}