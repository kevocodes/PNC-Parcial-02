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
public class RecordDTO {
    @NotBlank
    @NotNull
    @NotEmpty
    private UUID idRecord;

    @NotBlank
    @NotNull
    @NotEmpty
    private UUID patient;

    @NotBlank
    @NotNull
    @NotEmpty
    private String comments;
}
