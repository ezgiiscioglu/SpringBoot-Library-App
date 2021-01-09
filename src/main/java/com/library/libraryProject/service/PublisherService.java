package com.library.libraryProject.service;

import com.library.libraryProject.dto.PublisherDto;
import com.library.libraryProject.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher getById(Long id);
    Publisher update(Long id, Publisher publisher);
    PublisherDto save(PublisherDto publisherDto);
    Boolean delete(Long id);
}
