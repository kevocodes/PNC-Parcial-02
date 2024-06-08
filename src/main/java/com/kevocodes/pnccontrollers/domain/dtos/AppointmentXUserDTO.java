package com.kevocodes.pnccontrollers.domain.dtos;


import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentXUserDTO {

    @JsonBackReference
    private AppointmentDTO appointment;

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID idUser;

    @NotNull
    private String idSpecialty;

}
