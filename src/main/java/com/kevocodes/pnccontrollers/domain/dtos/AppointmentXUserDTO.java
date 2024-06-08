package com.kevocodes.pnccontrollers.domain.dtos;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentXUserDTO {

    @JsonBackReference
    private AppointmentDTO appointment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID idUser;

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
