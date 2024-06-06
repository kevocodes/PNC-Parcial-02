package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.entities.Book;
import com.kevocodes.pnccontrollers.domain.entities.BookLoan;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.repositories.BookLoanRepository;
import com.kevocodes.pnccontrollers.services.BookLoanService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookLoanServiceImp implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;

    public BookLoanServiceImp(BookLoanRepository bookLoanRepository) {
        this.bookLoanRepository = bookLoanRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(Book book, User user, int loanDays) {
        BookLoan bookLoan = new BookLoan();

        bookLoan.setBook(book);
        bookLoan.setUser(user);

        bookLoan.setLoanDate(Date.from(Instant.now()));
        bookLoan.setDueDate(Date.from(Instant.now().plusSeconds((long) loanDays * 24 * 60 * 60)));

        bookLoanRepository.save(bookLoan);
    }

    @Override
    public boolean isLoaned(Book book) {
        return bookLoanRepository.existsByBookAndReturnDateIsNull(book);
    }

    @Override
    public BookLoan findActiveByBook(Book book) {
        return bookLoanRepository.findByBookAndReturnDateIsNull(book).orElse(null);
    }

    @Override
    public BookLoan findById(UUID id) {
        return bookLoanRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookLoan> findAll() {
        return bookLoanRepository.findAll();
    }

    @Override
    public List<BookLoan> findAllByBook(Book book) {
        return bookLoanRepository.findLoanByBook(book);
    }

    @Override
    public List<BookLoan> findAllByUser(User user) {
        return bookLoanRepository.findAllByUser(user);
    }

    @Override
    public List<BookLoan> findAllActive() {
        return bookLoanRepository.findAllByReturnDateIsNull();
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void returnBook(BookLoan bookLoan) {
        bookLoan.setReturnDate(new Date());
        bookLoanRepository.save(bookLoan);
    }
}
