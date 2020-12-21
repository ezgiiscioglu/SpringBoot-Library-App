package com.library.libraryProject.service.imp;

import com.library.libraryProject.dto.AuthorDto;
import com.library.libraryProject.model.Author;
import com.library.libraryProject.repository.AuthorRepository;
import com.library.libraryProject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        author = authorRepository.save(author);
        authorDto.setAuthor_id(author.getAuthor_id());
        return authorDto;
    }

    @Override
    public Author update(Long id, Author author) {
        Author authorUpdate = authorRepository.getOne(id);
        if(authorUpdate==null){ throw  new IllegalArgumentException("Author does not exist with this id"); }
        authorUpdate.setAuthor_name(author.getAuthor_name());
        authorUpdate.setDescription(author.getDescription());
        return authorRepository.save(authorUpdate);
    }

    public Boolean delete(Long id) {
        authorRepository.deleteById(id);
        return null;
    }
}
