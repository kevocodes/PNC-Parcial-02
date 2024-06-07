package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;

import java.util.List;
import java.util.UUID;

public interface AppointmentXUserService {
    void create(AppointmentXUser info)throws Exception;
    AppointmentXUser findById(UUID id)throws Exception;
    List<AppointmentXUser> findAllByDoctorId(UUID id);
    List<AppointmentXUser> findAll();
}
