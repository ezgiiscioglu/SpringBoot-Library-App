package com.library.libraryProject.service;

import com.library.libraryProject.dto.AuthorDto;
import com.library.libraryProject.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    AuthorDto save(AuthorDto authorDto);
    Author update(Long id, Author author);
    Author getById(Long id);
    Boolean delete(Long id);
}
