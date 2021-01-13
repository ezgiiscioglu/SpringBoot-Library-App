package com.library.libraryProject.service.impl;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Publisher;
import com.library.libraryProject.repository.PublisherRepository;
import com.library.libraryProject.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final ModelMapper modelMapper;
    private final PublisherRepository publisherRepository;

    private boolean existsById(Long id) {
        return publisherRepository.existsById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher==null) {
            throw new ResourceNotFoundException("Cannot find publisher with id: " + id);
        }
        else return publisher;
    }

    @Override
    public Publisher save(Publisher publisher) throws BadResourceException, ResourceAlreadyExistsException{
        if (!StringUtils.isEmpty(publisher.getPublisher_name())) {
            if (publisher.getPublisher_id() != null && existsById(publisher.getPublisher_id())) {
                throw new ResourceAlreadyExistsException("Publisher with id: " + publisher.getPublisher_id() + " already exists");
            }
            return publisherRepository.save(publisher);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save publisher");
            exc.addErrorMessage("Publisher is null or empty");
            throw exc;
        }
    }

    @Override
    public Publisher update(Long id, Publisher publisher) {
        Publisher publisherUpdate = publisherRepository.getOne(id);
        if (publisherUpdate == null) { throw new ResourceNotFoundException("Cannot find publisher with id: " + id); }
        publisherUpdate.setPublisher_name(publisher.getPublisher_name());
        publisherUpdate.setDescription(publisher.getDescription());
        return publisherRepository.save(publisherUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find publisher with id: " + id);
        }
        else {
            publisherRepository.deleteById(id);
        }
    }
}
