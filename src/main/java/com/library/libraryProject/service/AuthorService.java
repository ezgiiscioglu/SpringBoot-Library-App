package com.library.libraryProject.service;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author save(Author author) throws BadResourceException, ResourceAlreadyExistsException;;
    Author update(Long id, Author author);
    Author getById(Long id);
    void delete(Long id);
}
