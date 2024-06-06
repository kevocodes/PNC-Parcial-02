package com.kevocodes.pnccontrollers.controllers;

import com.kevocodes.pnccontrollers.domain.dtos.ChangeRolesDTO;
import com.kevocodes.pnccontrollers.domain.dtos.ChangeUserPasswordDTO;
import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.dtos.UpdateUserInfoDTO;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.UserService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<GeneralResponse> findAll() {
        List<User> users = userService.findAll(true);

        return GeneralResponse.builder()
                .data(users)
                .message("List of users!")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> findById(@PathVariable UUID id) {
        User user = userService.findById(id, true);

        if (user == null) {
            return GeneralResponse.builder()
                    .message("User not found!")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return GeneralResponse.builder()
                .data(user)
                .message("User found!")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> delete(@PathVariable UUID id) {
        User user = userService.findById(id, true);

        if (user == null) {
            return  GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        };

        userService.deleteUser(user);

        return GeneralResponse.builder()
                .message("User deactivated!")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateUserInfo(@PathVariable UUID id, @RequestBody @Valid UpdateUserInfoDTO info) {
        // Verify if user exists
        User user = userService.findById(id, true);

        if (user == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        // Validate if the new email is already exists
        User userByEmail = userService.findByIdentifier(info.getEmail(), false);
        if (userByEmail != null && !userByEmail.getId().equals(id)) {
            return GeneralResponse.builder()
                    .message("Email already exists")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        // Validate if the new username already exists
        User userByUsername = userService.findByIdentifier(info.getUsername(), false);
        if (userByUsername != null && !userByUsername.getId().equals(id)) {
            return GeneralResponse.builder()
                    .message("Username already exists")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        // If the new information is correct, update it
        userService.updateUserInfo(user, info);

        return GeneralResponse.builder()
                .message("User information updated!")
                .build();
    }

    @PatchMapping("/change-password/{id}")
    public ResponseEntity<GeneralResponse> changePassword(@PathVariable UUID id, @RequestBody @Valid ChangeUserPasswordDTO info) {
        User user = userService.findById(id, true);

        if (user == null) {
            return  GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        };

        if (!user.getPassword().equals(info.getOldPassword())) {
            return  GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        userService.changePassword(user, info.getNewPassword());

        return GeneralResponse.builder()
                .message("Password changed!")
                .build();
    }

    @PatchMapping("/toogle-active/{id}")
    public ResponseEntity<GeneralResponse> toogleActive(@PathVariable UUID id) {
        User user = userService.findById(id, false);
        if (user == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        userService.toogleActive(user);

        String message = user.isActive() ? "User activated!" : "User deactivated!";

        return GeneralResponse.builder()
                .message(message)
                .build();
    }

    @PostMapping("/change-roles")
    public ResponseEntity<GeneralResponse> changeRoles(@RequestBody @Valid ChangeRolesDTO info) {
        User user = userService.findByIdentifier(info.getIdentifier(), true);

        if (user == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        userService.changeRoles(user, info.getRoles());

        return GeneralResponse.builder()
                .message("Roles changed")
                .build();
    }
}
