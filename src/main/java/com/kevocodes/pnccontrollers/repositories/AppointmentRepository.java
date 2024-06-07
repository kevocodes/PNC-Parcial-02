package com.kevocodes.pnccontrollers.repositories;

import java.util.Date;
import java.util.UUID;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{

    /*@Query(value = "SELECT * from Appointment a WHERE a.endDate = :date")
    List<Appointment> getAppointmentsByDate(@Param("date") Date thisDate);*/
    
}