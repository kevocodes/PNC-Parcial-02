package com.kevocodes.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookLoanDTO {
    @NotNull
    private int loanDays;
    @NotBlank
    private String username;
    @NotBlank
    private String isbn;
}