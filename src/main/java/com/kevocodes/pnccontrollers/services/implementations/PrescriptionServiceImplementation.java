package com.kevocodes.pnccontrollers.services.implementations;

import java.util.UUID;

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
    public void createPresctriprion(Prescription prescription) {
        repository.save(prescription);
    }

    @Override
    public void deletePrescription(UUID prescriptionid) throws Exception{
        repository.findById(prescriptionid).orElseThrow();
        repository.deleteById(prescriptionid);
    }  
}