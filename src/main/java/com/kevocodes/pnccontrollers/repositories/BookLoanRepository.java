package com.kevocodes.pnccontrollers.repositories;

import com.kevocodes.pnccontrollers.domain.entities.Book;
import com.kevocodes.pnccontrollers.domain.entities.BookLoan;
import com.kevocodes.pnccontrollers.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {
    boolean existsByBookAndReturnDateIsNull(Book book);
    Optional<BookLoan> findByBookAndReturnDateIsNull(Book book);
    List<BookLoan> findLoanByBook(Book book);
    List<BookLoan> findAllByUser(User user);
    List<BookLoan> findAllByReturnDateIsNull();
}
