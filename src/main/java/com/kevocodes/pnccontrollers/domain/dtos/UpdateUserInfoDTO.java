package com.kevocodes.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserInfoDTO {
    private String username;

    @Email
    private String email;
}
