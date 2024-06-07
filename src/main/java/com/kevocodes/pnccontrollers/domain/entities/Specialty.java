package com.kevocodes.pnccontrollers.domain.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Specialty {
    @Id
    @Length(min = 4, max = 4)
    private String idSpecialty;

    @Column(nullable = false)
    @Length(min = 2, max = 30)
    private String specialtyName;
}
