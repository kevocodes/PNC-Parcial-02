package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.Specialty;

import java.util.List;

public interface SpecialtyService {
    void addSpecialty(Specialty info);
    void deleteSpecialty(Specialty info);
    List<Specialty> findAll();
}
