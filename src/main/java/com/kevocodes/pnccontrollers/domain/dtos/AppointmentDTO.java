package com.kevocodes.pnccontrollers.domain.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
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

    @NotNull
    @Length(min = 2, max = 9999)
    private String reason;

    @JsonManagedReference
    @NotNull
    private List<AppointmentXUserDTO> appointmentDetails;

    @NotNull
    private LocalDateTime appointmentDateTime;
}
