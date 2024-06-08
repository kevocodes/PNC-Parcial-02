package com.kevocodes.pnccontrollers.domain.dtos;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentXUserDTO {

    @JsonBackReference
    private AppointmentDTO appointment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID idDoctor;

    @NotNull
    private String idSpecialty;

    @NotNull
    @Length(min = 2, max = 9999)
    private String reason;

    @NotBlank
    @NotNull
    private LocalDateTime appointmentDateTime;

    @NotNull
    @NotBlank
    private LocalDateTime appointmentEndDateTime;

}
