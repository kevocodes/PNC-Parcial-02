package com.kevocodes.pnccontrollers.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevocodes.pnccontrollers.domain.dtos.AppointmentDTO;
import com.kevocodes.pnccontrollers.domain.dtos.AppointmentXUserDTO;
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

        List<AppointmentDTO> appointmentList = appointmentService.readAllAppointments().stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)).toList();
        return new GeneralResponse.Builder().data(appointmentList).build();
    }

    @GetMapping("/get-my-appointments")
    public ResponseEntity<GeneralResponse> getAppointmentsAsPatient(){
        User user = userService.findByIdentifier(GetAuthenticatedUserNameHelper.getUserName(), false);

        List<AppointmentXUserDTO> myAppointments =  appointmentService
            .readAppointmentXUserForPatient(user.getIdUser())
            .stream()
            .map(a -> modelMapper.map(a, AppointmentXUserDTO.class))
            .toList();

        return new GeneralResponse.Builder()
            .data(myAppointments)
            .build();
    }

    @GetMapping("/get-my-appointments-request")
    public ResponseEntity<GeneralResponse> getAppointmentsRequestAsPatien(){
        User user = userService.findByIdentifier(GetAuthenticatedUserNameHelper.getUserName(), false);

        List<AppointmentDTO> myAppointments = appointmentService
            .readAllAppointmentsForPatient(user.getIdUser())
            .stream()
            .map(a -> modelMapper.map(a, AppointmentDTO.class))
            .toList();

        return new GeneralResponse.Builder()
            .data(myAppointments)
            .build();
    }

    @GetMapping("/get-appointments-requested-doctor")
    public ResponseEntity<GeneralResponse> getAppointmentAsDoctor(){
        User user = userService.findByIdentifier(GetAuthenticatedUserNameHelper.getUserName(), false);

        List<AppointmentXUserDTO> requestedAppointments = appointmentService
            .readAllApointmentsForDoctors(user.getIdUser())
            .stream()
            .map(a -> modelMapper.map(a, AppointmentXUserDTO.class))
            .toList();

        return new GeneralResponse.Builder()
            .data(requestedAppointments)
            .build();
    }
}
