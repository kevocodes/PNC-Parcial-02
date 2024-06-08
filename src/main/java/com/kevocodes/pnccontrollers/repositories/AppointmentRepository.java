package com.kevocodes.pnccontrollers.repositories;

import java.util.UUID;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :userId")
    List<Appointment> readAllPatientAppointments(@Param("userId") UUID patientId);
    
}