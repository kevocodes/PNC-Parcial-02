package com.kevocodes.pnccontrollers.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.kevocodes.pnccontrollers.domain.entities.Appointment;

public interface AppointmentService {

    void requestAppointment(Appointment requestedAppointment) throws Exception;
    List<Appointment> getAllAppointments();
    void changeAppointmentStatus(UUID appointmentId)throws Exception; 
    List<Appointment> getAllByDate(Date thisDate);
    void finishAppointment (UUID appointmentId) throws Exception;
}
