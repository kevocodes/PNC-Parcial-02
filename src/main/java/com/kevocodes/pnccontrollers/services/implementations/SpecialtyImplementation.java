package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.entities.Specialty;
import com.kevocodes.pnccontrollers.repositories.SpecialtyRepository;
import com.kevocodes.pnccontrollers.services.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyImplementation implements SpecialtyService{

    private final SpecialtyRepository repository;

    @Override
    public void addSpecialty(Specialty info) {
        repository.save(info);
    }

    @Override
    public void deleteSpecialty(Specialty info) {
        repository.delete(info);
    }

    @Override
    public List<Specialty> findAll() {
        return repository.findAll();
    }
}
