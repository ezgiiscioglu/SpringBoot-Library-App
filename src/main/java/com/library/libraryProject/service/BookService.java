package com.library.libraryProject.service;

import com.library.libraryProject.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    List<Book> getAll();
    Book getById(Long id);
    Book save(Book book);
    Book update(Long id, Book book);
    Boolean delete(Long id);
    // List<Book> searchBooks(String book);
}
