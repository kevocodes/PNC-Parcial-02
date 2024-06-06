package com.kevocodes.pnccontrollers.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "sec01_book_loans")
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //Fechas
    private Date loanDate;
    private Date returnDate; // Este campo determina si el libro está o no prestado
    private Date dueDate;

    @ManyToOne(optional = false)
    private Book book;
    @ManyToOne(optional = false)
    private User user;
}
