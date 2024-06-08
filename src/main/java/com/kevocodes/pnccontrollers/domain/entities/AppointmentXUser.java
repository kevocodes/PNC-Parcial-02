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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class AppointmentXUser {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAppointmentXUser;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_appointment", nullable = false, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_APPOINTMENT"))
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "FK_APPOINTMENTXUSER_SPECIALTY"))
    private Specialty specialty;
}
