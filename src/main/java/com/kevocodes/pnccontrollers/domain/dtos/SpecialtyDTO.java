package com.kevocodes.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String idSpecialty;

    @NotNull
    @NotEmpty
    @NotBlank
    private String specialtyName;
}
