package com.kevocodes.pnccontrollers.controllers;

import com.kevocodes.pnccontrollers.domain.dtos.CreateBookLoanDTO;
import com.kevocodes.pnccontrollers.domain.dtos.GeneralResponse;
import com.kevocodes.pnccontrollers.domain.entities.Book;
import com.kevocodes.pnccontrollers.domain.entities.BookLoan;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.BookLoanService;
import com.kevocodes.pnccontrollers.services.BookService;
import com.kevocodes.pnccontrollers.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book-loans")
public class BookLoanController {
    private final BookLoanService bookLoanService;
    private final UserService userService;
    private final BookService bookService;

    public BookLoanController(BookLoanService bookLoanService, UserService userService, BookService bookService) {
        this.bookLoanService = bookLoanService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponse> loanBook(@RequestBody @Valid CreateBookLoanDTO info) {
        User user = userService.findByIdentifier(info.getUsername(), false);
        if (user == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Book book = bookService.findByIsbn(info.getIsbn());
        if (book == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        if (bookLoanService.isLoaned(book)) {
            return GeneralResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("Book already in a loan!")
                    .build();
        }

        bookLoanService.create(book, user, info.getLoanDays());

        return GeneralResponse.builder()
                .message("Loan created!")
                .build();
    }

    @PostMapping("/return-book/{isbn}")
    public ResponseEntity<GeneralResponse> returnBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        if (!bookLoanService.isLoaned(book)) {
            return GeneralResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("Book is not loaned!")
                    .build();
        }

        BookLoan bookLoan = bookLoanService.findActiveByBook(book);
        bookLoanService.returnBook(bookLoan);

        return GeneralResponse.builder()
                .message("Book returned!")
                .build();
    }

    @GetMapping("/")
    public ResponseEntity<GeneralResponse> findAllLoans() {
        return GeneralResponse.builder()
                .data(bookLoanService.findAll())
                .build();
    }

    @GetMapping("/actives")
    public ResponseEntity<GeneralResponse> findAllActiveLoans() {
        return GeneralResponse.builder()
                .data(bookLoanService.findAllActive())
                .build();
    }
}
