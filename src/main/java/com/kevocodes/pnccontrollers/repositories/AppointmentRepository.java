package com.kevocodes.pnccontrollers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{
    
}