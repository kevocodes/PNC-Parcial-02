package com.kevocodes.pnccontrollers.domain.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    
    private UUID idAppointment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID idUser;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean approved = false;

    @JsonManagedReference
    @NotNull
    private List<AppointmentXUserDTO> appointmentDetails;


}
