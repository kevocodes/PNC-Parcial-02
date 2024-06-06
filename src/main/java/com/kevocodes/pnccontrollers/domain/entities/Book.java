package com.kevocodes.pnccontrollers.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name="sec01_books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code;
    private String ISBN;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}



