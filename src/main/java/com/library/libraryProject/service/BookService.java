package com.library.libraryProject.service;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getById(Long id);
    Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException;
    Book update(Long id, Book book);
    Boolean delete(Long id);
    // List<Book> searchBooks(String book);
}
