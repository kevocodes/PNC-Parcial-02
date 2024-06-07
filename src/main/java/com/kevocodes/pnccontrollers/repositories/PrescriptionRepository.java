package com.kevocodes.pnccontrollers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevocodes.pnccontrollers.domain.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID>{
    
}
