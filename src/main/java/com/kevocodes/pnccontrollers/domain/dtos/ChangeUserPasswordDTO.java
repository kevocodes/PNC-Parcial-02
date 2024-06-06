package com.kevocodes.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeUserPasswordDTO {
    @NotBlank
    String oldPassword;
    @NotBlank
    String newPassword;
}
