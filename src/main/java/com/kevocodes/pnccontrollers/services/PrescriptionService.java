package com.kevocodes.pnccontrollers.services;

import java.util.UUID;

import com.kevocodes.pnccontrollers.domain.entities.Prescription;

public interface PrescriptionService {

    void createPresctriprion(Prescription prescription);
    void deletePrescription(UUID prescriptionid) throws Exception;
}