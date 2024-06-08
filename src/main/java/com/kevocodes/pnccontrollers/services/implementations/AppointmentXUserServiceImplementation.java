package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import com.kevocodes.pnccontrollers.repositories.AppointmentXUserRepository;
import com.kevocodes.pnccontrollers.services.AppointmentXUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentXUserServiceImplementation implements AppointmentXUserService {

    private final AppointmentXUserRepository repo;

    @Override
    public void create(AppointmentXUser aux) {
        repo.save(aux);
    }
}
