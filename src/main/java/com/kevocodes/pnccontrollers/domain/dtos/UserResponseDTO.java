package com.kevocodes.pnccontrollers.domain.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    UUID id;
    String username;
    String email;
    boolean active;
}
