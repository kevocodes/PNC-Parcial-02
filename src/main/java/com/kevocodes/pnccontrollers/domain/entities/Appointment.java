package com.kevocodes.pnccontrollers.domain.entities;

import java.util.UUID;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAppointment;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true, foreignKey = @ForeignKey(name = "FK_APPOINTMENT_USER"))
    private User user;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    List<AppointmentXUser> appointmentXUser;

    @Column(nullable = false)
    private Boolean approved;

}
