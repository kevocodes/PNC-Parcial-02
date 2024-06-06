package com.kevocodes.pnccontrollers.controllers;

import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.dtos.LoginDTO;
import com.kevocodes.pnccontrollers.domain.dtos.TokenDTO;
import com.kevocodes.pnccontrollers.domain.dtos.UserRegisterDTO;
import com.kevocodes.pnccontrollers.domain.entities.Token;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginDTO info) throws Exception {
        User user = userService.findByIdentifier(info.getIdentifier(), true);

        if (user == null) {
            return GeneralResponse.builder()
                    .message("User not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        if (!userService.checkPassword(user, info.getPassword())) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND) // Este status puede ser un 401 unauthorized
                    .message("Incorrect password")
                    .build();
        }

        // Create token
        Token token = userService.registerToken(user);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .message("User logged in")
                .data(new TokenDTO(token))
                .build();
    }

    @PostMapping("/register")
    public  ResponseEntity<GeneralResponse> register(@RequestBody @Valid UserRegisterDTO info) {
        User user = userService.findByUsernameOrEmail(info.getUsername(), info.getEmail(), true);

        if (user != null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("User already exists")
                    .build();
        }

        userService.register(info);

        return GeneralResponse.builder()
                .message("User registered successfully")
                .status(HttpStatus.CREATED)
                .build();
    }
}
