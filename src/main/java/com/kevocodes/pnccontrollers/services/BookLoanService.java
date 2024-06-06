package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.Book;
import com.kevocodes.pnccontrollers.domain.entities.BookLoan;
import com.kevocodes.pnccontrollers.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface BookLoanService {
    void create(Book book, User user, int loanDays);

    boolean isLoaned(Book book);

    BookLoan findActiveByBook(Book book); // Devuelve un registro unico del prestamo del libro
    BookLoan findById(UUID id);

    List<BookLoan> findAll();
    List<BookLoan> findAllByBook(Book book);
    List<BookLoan> findAllByUser(User user);
    List<BookLoan> findAllActive();

    void returnBook(BookLoan bookLoan);
}
