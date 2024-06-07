package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;

import java.util.List;
import java.util.UUID;

public interface AppointmentXUserService {
    void create(AppointmentXUser info);
    AppointmentXUser findById(UUID id);
    List<AppointmentXUser> findAllByDoctorId(UUID id);
    List<AppointmentXUser> findAll();
}
