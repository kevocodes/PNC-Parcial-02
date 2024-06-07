package com.kevocodes.pnccontrollers.services.implementations;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public void requestAppointment(Appointment requestedAppointment) {
        repository.save(requestedAppointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    @Override
    public void changeAppointmentStatus(UUID appointmentId) throws Exception{
        Appointment appointment = repository.findById(appointmentId).orElseThrow();
        appointment.setApproved(!appointment.getApproved());
        repository.save(appointment);
    }

    @Override
    public List<Appointment> getAllByDate(Date thisDate) {
        return repository.getAppointmentsByDate(thisDate);
    }

    @Override
    public void finishAppointment(UUID appointmentId) throws Exception{
        Appointment appointment = repository.findById(appointmentId).orElseThrow();

        appointment.setEndDate(Date.from(Instant.now()));
        repository.save(appointment);
    }
}
