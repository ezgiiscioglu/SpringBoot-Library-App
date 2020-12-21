package com.library.libraryProject.service.imp;

import com.library.libraryProject.model.Book;
import com.library.libraryProject.repository.BookRepository;
import com.library.libraryProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        List<Book> data = bookRepository.findAll();
        return Arrays.asList(modelMapper.map(data, Book[].class));
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    //public Book getById(Long id) { return bookRepository.getOne(id); }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book bookUpdate = bookRepository.getOne(id);
        if(bookUpdate==null){
            throw  new IllegalArgumentException("Book does not exist with this id");
        }
        bookUpdate.setBook_name(book.getBook_name());
        bookUpdate.setIsbn(book.getIsbn());
        bookUpdate.setDescription(book.getDescription());
        bookUpdate.setBook_serial_name(book.getBook_serial_name());
        bookUpdate.setBook_sub_name(book.getBook_sub_name());
        return bookRepository.save(bookUpdate);
    }
/*
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String book) {
        return bookRepository.findAll();
    }
*/
    @Override
    public Boolean delete(Long id) {
        bookRepository.deleteById(id);
        return null;
    }
}
