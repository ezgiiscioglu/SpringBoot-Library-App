package com.library.libraryProject.service.imp;

import com.library.libraryProject.dto.PublisherDto;
import com.library.libraryProject.model.Publisher;
import com.library.libraryProject.repository.PublisherRepository;
import com.library.libraryProject.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImp implements PublisherService {
    private final ModelMapper modelMapper;
    private final PublisherRepository publisherRepository;

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getById(Long id) {
        return publisherRepository.getOne(id);
    }

    @Override
    public PublisherDto save(PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisher = publisherRepository.save(publisher);
        publisherDto.setPublisher_id(publisher.getPublisher_id());
        return publisherDto;
    }

    @Override
    public Publisher update(Long id, Publisher publisher) {
        Publisher publisherUpdate = publisherRepository.getOne(id);
        if (publisherUpdate == null) { throw new IllegalArgumentException("Publisher does not exist with this id"); }
        publisherUpdate.setPublisher_name(publisher.getPublisher_name());
        publisherUpdate.setDescription(publisher.getDescription());
        return publisherRepository.save(publisherUpdate);
    }

    public Boolean delete(Long id) {
        publisherRepository.deleteById(id);
        return null;
    }
}
