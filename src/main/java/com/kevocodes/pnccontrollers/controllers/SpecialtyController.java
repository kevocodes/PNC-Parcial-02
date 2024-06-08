package com.kevocodes.pnccontrollers.controllers;

import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.dtos.SpecialtyDTO;
import com.kevocodes.pnccontrollers.domain.entities.Specialty;
import com.kevocodes.pnccontrollers.services.SpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('SYSADMIN')")
    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createSpecialty(@RequestBody @Valid SpecialtyDTO obj) throws Exception{
        service.addSpecialty(modelMapper.map(obj, Specialty.class));

        return  GeneralResponse.builder()
                .message("Success")
                .build();
    }
}
