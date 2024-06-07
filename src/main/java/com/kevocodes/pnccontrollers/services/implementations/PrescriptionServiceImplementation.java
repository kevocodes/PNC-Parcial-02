package com.kevocodes.pnccontrollers.services.implementations;

import java.util.UUID;

import com.kevocodes.pnccontrollers.handlers.ModelNotFoundException;
import org.springframework.stereotype.Service;

import com.kevocodes.pnccontrollers.domain.entities.Prescription;
import com.kevocodes.pnccontrollers.repositories.PrescriptionRepository;
import com.kevocodes.pnccontrollers.services.PrescriptionService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PrescriptionServiceImplementation implements PrescriptionService{
    
    private final PrescriptionRepository repository;

    @Override
    public void createPrescription(Prescription prescription) {
        repository.save(prescription);
    }

    @Override
    public void deletePrescription(UUID prescriptionId) throws Exception{
        repository.findById(prescriptionId).orElseThrow(() -> new ModelNotFoundException("Appointment not found"));
        repository.deleteById(prescriptionId);
    }  
}