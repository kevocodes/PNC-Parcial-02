package com.kevocodes.pnccontrollers.controllers;

import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.dtos.PrescriptionDTO;
import com.kevocodes.pnccontrollers.domain.entities.Appointment;
import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import com.kevocodes.pnccontrollers.domain.entities.Prescription;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.AppointmentService;
import com.kevocodes.pnccontrollers.services.PrescriptionService;
import com.kevocodes.pnccontrollers.services.UserService;
import com.kevocodes.pnccontrollers.utils.GetAuthenticatedUserNameHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final AppointmentService appointmentService;
    private final UserService userService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('doctor')")
    public ResponseEntity<GeneralResponse> addPrescription(@RequestBody @Valid PrescriptionDTO obj) throws Exception{

        User user = userService.findByIdentifier(GetAuthenticatedUserNameHelper.getUserName(), false);

        List<AppointmentXUser> AXU = appointmentService.readAllApointmentsForDoctors(user.getIdUser());

        boolean exists = false;

        for( AppointmentXUser appointment : AXU) {
            if (appointment.getAppointment().getIdAppointment().equals(obj.getIdAppointment())){
               exists = true;
               break;
            }
        }

        if(exists) {
            prescriptionService.createPrescription(modelMapper.map(obj, Prescription.class));
            return GeneralResponse.builder().message("Success").build();
        }
        else return GeneralResponse.builder().status(HttpStatus.UNAUTHORIZED).build();
    }
}
