package com.library.libraryProject.service.impl;

import com.library.libraryProject.exception.BadResourceException;
import com.library.libraryProject.exception.ResourceAlreadyExistsException;
import com.library.libraryProject.model.Book;
import com.library.libraryProject.repository.BookRepository;
import com.library.libraryProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElse(null);
        if (book==null) {
            throw new ResourceNotFoundException("Cannot find book with id: " + id);
        }
        else return book;
    }

    @Override
    public Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(book.getBook_name())) {
            if (book.getBook_id() != null && existsById(book.getBook_id())) {
                throw new ResourceAlreadyExistsException("Book with id: " + book.getBook_id() + " already exists");
            }
            return bookRepository.save(book);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save book");
            exc.addErrorMessage("Book is null or empty");
            throw exc;
        }
    }

    @Override
    public Book update(Long id, Book book) {
        Book bookUpdate = bookRepository.getOne(id);
        if(bookUpdate==null){
            throw new ResourceNotFoundException("Cannot find book with id: " + id);
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
    public void delete(Long id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find book with id: " + id);
        }
        else {
            bookRepository.deleteById(id);
        }
    }
}
