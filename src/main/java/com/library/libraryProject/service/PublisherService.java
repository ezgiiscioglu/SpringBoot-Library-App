package com.library.libraryProject.service;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher getById(Long id);
    Publisher update(Long id, Publisher publisher);
    Publisher save(Publisher publisher) throws BadResourceException, ResourceAlreadyExistsException;
    void delete(Long id);
}
