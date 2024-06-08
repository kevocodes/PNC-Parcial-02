package com.kevocodes.pnccontrollers.services;

import java.util.List;
import java.util.UUID;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;
import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;

public interface AppointmentService {

    void createAppointment(Appointment object);
    List<Appointment> readAllAppointmentsForPatient(UUID patiendId);
    List<AppointmentXUser> readAppointmentXUserForPatient(UUID patientId);
    List<AppointmentXUser> readAllApointmentsForDoctors(UUID doctorId);
    void aproveAppointment(UUID appointmentId)throws Exception;
}
