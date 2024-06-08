package com.kevocodes.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {

    private UUID idPrescription;

    @NotNull
    @NotBlank
    @NotEmpty
    private String medicine;

    @NotNull
    @NotBlank
    @NotEmpty
    private String dose;


    @NotNull
    @NotBlank
    @NotEmpty
    private UUID idAppointment;
}
