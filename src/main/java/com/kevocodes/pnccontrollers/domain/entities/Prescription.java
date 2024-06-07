package com.kevocodes.pnccontrollers.domain.entities;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPrescription;

    @ManyToOne
    @JoinColumn(name = "id_appointment", nullable = false, foreignKey = @ForeignKey(name = "FK_PRESCRIPTION_APPOINTMENT"))
    private Appointment appointment;

    @Length(min = 4, max = 50)
    private String medicine;

    @Length(min = 4, max = 50)
    private String dose;

}
