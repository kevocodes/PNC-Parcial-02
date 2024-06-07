package com.kevocodes.pnccontrollers.services.implementations;

import org.springframework.stereotype.Service;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;
import com.kevocodes.pnccontrollers.repositories.AppointmentRepository;
import com.kevocodes.pnccontrollers.services.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentSeriviceImplementation implements AppointmentService{

    private final AppointmentRepository repository;

    @Override
    public void RequestAppointment(Appointment requestedAppointment) {
        repository.save(requestedAppointment);
    }

    
    
}
