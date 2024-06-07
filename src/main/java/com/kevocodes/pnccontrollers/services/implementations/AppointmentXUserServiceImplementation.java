package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import com.kevocodes.pnccontrollers.repositories.AppointmentXUserRepository;
import com.kevocodes.pnccontrollers.services.AppointmentXUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentXUserServiceImplementation implements AppointmentXUserService {

    private final AppointmentXUserRepository repository;

    @Override
    public void create(AppointmentXUser info) {
        repository.save(info);
    }

    @Override
    public AppointmentXUser findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<AppointmentXUser> findAllByDoctorId(UUID id) {
        return repository.findAllByDoctorId(id);
    }

    @Override
    public List<AppointmentXUser> findAll() {
        return repository.findAll();
    }


}
