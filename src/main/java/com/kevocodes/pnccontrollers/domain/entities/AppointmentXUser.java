package com.kevocodes.pnccontrollers.domain.entities;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
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
public class AppointmentXUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAppointmentXUser;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_USER"))
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "id_appointment", nullable = false, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_APPOINTMENT"))
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_SPECIALTY"))
    private Specialty specialty;

    @Column(nullable = false)
    @Length(min = 2, max =  9999)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = true)
    private LocalDateTime appointmentEndDateTime;
}
