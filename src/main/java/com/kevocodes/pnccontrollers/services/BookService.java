package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.dtos.SaveBookDTO;
import com.kevocodes.pnccontrollers.domain.entities.Book;
import com.kevocodes.pnccontrollers.domain.entities.Category;

import java.util.List;

public interface BookService {
    void save(SaveBookDTO info, Category category);
    List<Book> findAll();
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
    List<Book> findByCategory(Category category);
}
