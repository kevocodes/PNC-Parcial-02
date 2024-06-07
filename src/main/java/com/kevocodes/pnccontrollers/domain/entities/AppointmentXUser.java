package com.kevocodes.pnccontrollers.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(
        name = "entity1_entity2",
        joinColumns = @JoinColumn(name = "entity1_id"),
        inverseJoinColumns = @JoinColumn(name = "entity2_id")
    )
    private List<Specialty> specialties;
}
