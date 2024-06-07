package com.kevocodes.pnccontrollers.services;

import java.util.UUID;

import com.kevocodes.pnccontrollers.domain.entities.Prescription;

public interface PrescriptionService {

    void createPrescription(Prescription prescription) throws Exception;
    void deletePrescription(UUID prescriptionId) throws Exception;
}