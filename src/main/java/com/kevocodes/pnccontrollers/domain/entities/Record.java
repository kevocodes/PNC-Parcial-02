package com.kevocodes.pnccontrollers.domain.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Record {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRecord;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_RECORD_USER"))
    private User patient;

    @Column(nullable = false)
    @Length(min = 2, max = 999)
    private String comments;
}
