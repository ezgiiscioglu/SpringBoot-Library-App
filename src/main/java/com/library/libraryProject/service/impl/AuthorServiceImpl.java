package com.library.libraryProject.service.impl;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Author;
import com.library.libraryProject.repository.AuthorRepository;
import com.library.libraryProject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) throws ResourceNotFoundException {
        Author author = authorRepository.findById(id).orElse(null);
        if (author==null) {
            throw new ResourceNotFoundException("Cannot find author with id: " + id);
        }
        else return author;
    }

    @Override
    public Author save(Author author) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(author.getAuthor_name())) {
            if (author.getAuthor_id() != null && existsById(author.getAuthor_id())) {
                throw new ResourceAlreadyExistsException("Book with id: " + author.getAuthor_id() + " already exists");
            }
            return authorRepository.save(author);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save author");
            exc.addErrorMessage("Author is null or empty");
            throw exc;
        }
    }

    @Override
    public Author update(Long id, Author author) {
        Author authorUpdate = authorRepository.getOne(id);
        if(authorUpdate==null){
            throw new ResourceNotFoundException("Cannot find author with id: " + id);
        }
        authorUpdate.setAuthor_name(author.getAuthor_name());
        authorUpdate.setDescription(author.getDescription());
        return authorRepository.save(authorUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find contact with id: " + id);
        }
        else {
            authorRepository.deleteById(id);
        }
    }
}
