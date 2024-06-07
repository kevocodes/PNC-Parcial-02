package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.Specialty;

import java.util.List;

public interface SpecialtyService {
    void addSpecialty(Specialty info) throws Exception;
    void deleteSpecialty(Specialty info) throws Exception;
    List<Specialty> findAll();
}
