package com.kevocodes.pnccontrollers.domain.entities;

import java.time.LocalDateTime;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Appointment {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAppointment;

    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_APPOINTMENT_USER"))
    private User user;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.MERGE)
    List<AppointmentXUser> appointmentDetails;

    @Column(nullable = false)
    private Boolean approved;

    @Column(nullable = false)
    @Length(min = 2, max =  9999)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = true)
    private LocalDateTime appointmentEndDateTime;

}
