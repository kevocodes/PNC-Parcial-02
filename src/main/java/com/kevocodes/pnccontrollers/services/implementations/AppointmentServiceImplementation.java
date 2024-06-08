package com.kevocodes.pnccontrollers.services.implementations;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;
import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import com.kevocodes.pnccontrollers.handlers.ModelNotFoundException;
import com.kevocodes.pnccontrollers.repositories.AppointmentRepository;
import com.kevocodes.pnccontrollers.repositories.AppointmentXUserRepository;
import com.kevocodes.pnccontrollers.services.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImplementation implements AppointmentService{

    private final AppointmentRepository repository;
    private final AppointmentXUserRepository repositoryAXU;

    @Override
    public void createAppointment(Appointment object) {
        repository.save(object);
    }

    @Override
    public List<Appointment> readAllAppointmentsForPatient(UUID patiendId) {
        return repository.readAllPatientAppointments(patiendId);
    }

    @Override
    public List<AppointmentXUser> readAppointmentXUserForPatient(UUID patientId) {
        List<Appointment> appointments = readAllAppointmentsForPatient(patientId);

        return appointments.stream().flatMap(appointment->appointment.getAppointmentDetails().stream()).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentXUser> readAllApointmentsForDoctors(UUID doctorId) {
        return repositoryAXU.findAllByDoctorId(doctorId);
    }

    @Override
    public void aproveAppointment(UUID appointmentId) throws ModelNotFoundException{
        Appointment appointment = repository.findById(appointmentId).orElseThrow(() -> new ModelNotFoundException("Appointment Cannot Be Found"));
        appointment.setApproved(true);
    }

    @Override
    public List<Appointment> readAllAppointments() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

}
