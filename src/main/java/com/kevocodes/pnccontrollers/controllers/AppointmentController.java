package com.kevocodes.pnccontrollers.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevocodes.pnccontrollers.domain.dtos.AppointmentDTO;
import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.entities.Appointment;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.AppointmentService;
import com.kevocodes.pnccontrollers.services.UserService;
import com.kevocodes.pnccontrollers.utils.GetAuthenticatedUserNameHelper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PostMapping("/create-appointment")
    public ResponseEntity<GeneralResponse> createAppointment(@RequestBody @Valid AppointmentDTO object){
        User user = userService.findByIdentifier(GetAuthenticatedUserNameHelper.getUserName(), false);
        object.setIdUser(user.getIdUser());

        appointmentService.createAppointment(modelMapper.map(object, Appointment.class));

        return new GeneralResponse.Builder().build();
    }

    @GetMapping("/get-all-appointments")
    public ResponseEntity<GeneralResponse> getAllAppointments(){
        return new GeneralResponse.Builder().data(appointmentService.readAllAppointments()).build();
    }

}
