package com.kevocodes.pnccontrollers.domain.dtos;

import com.kevocodes.pnccontrollers.domain.entities.Token;
import lombok.Data;

@Data
public class TokenDTO {

    private String token;

    public TokenDTO(Token token) {
        this.token = token.getContent();
    }

}
