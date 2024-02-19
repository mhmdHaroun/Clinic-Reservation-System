package com.example.CRSbackend.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    boolean existsById(Integer integer);
    void delete(Appointment entity);
    Optional<Appointment> findById(Integer integer);

    Appointment getById(Integer integer);

    @Query(value = "SELECT count(*) FROM Appointment a WHERE a.clinic_id = :clinicId AND a.day = :day AND :startTime >= a.start_time AND :startTime < a.end_time", nativeQuery = true)
    long findAppointsInSameTime(@Param("clinicId") int clinicId, @Param("day") String day, @Param("startTime") Time startTime);

    @Query(value = "SELECT count(*) FROM Appointment a WHERE a.clinic_id = :clinicId AND a.day = :day AND a.app_id != :appId AND :startTime >= a.start_time AND :startTime < a.end_time", nativeQuery = true)
    long findAppointsInSameTime(@Param("clinicId") int clinicId, @Param("appId") int appId, @Param("day") String day, @Param("startTime") Time startTime);

    @Query(value = "SELECT * FROM Appointment a WHERE a.clinic_id = :clinicId", nativeQuery = true)
    List<Appointment> findAllByClinicID(@Param("clinicId") int clinicId);


}
