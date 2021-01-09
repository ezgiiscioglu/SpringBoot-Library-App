package com.library.libraryProject.controller;

import com.library.libraryProject.model.Book;
import com.library.libraryProject.service.impl.AuthorServiceImpl;
import com.library.libraryProject.service.impl.BookServiceImpl;
import com.library.libraryProject.service.impl.PublisherServiceImpl;
import com.library.libraryProject.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(ApiPaths.BookCtrl.CTRL)
public class BookController {
    private final BookServiceImpl bookServiceImpl;

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Book> books = bookServiceImpl.getAllBooks();
        model.addAttribute("books", books);
        return "list-books";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id", required = true) Long id, Model model) {
        Book book = bookServiceImpl.getById(id);
        log.info(String.valueOf(book.getBook_id()));
        model.addAttribute("book", book);
        return "list-books";
    }

    @GetMapping("/add-book")
    public String showAddBook(Model model) {
        Book book = new Book();
        model.addAttribute("add", true);
        model.addAttribute("book", book);
        return "add-update-book";
    }

    @PostMapping("/add-book")
    public String addBook(Model model, @ModelAttribute("book") Book book) {
        try {
            bookServiceImpl.save(book);
            return "redirect:/api/book/list";
        }catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "add-update-book";
        }
    }

    @GetMapping("/update-book/{id}")
    public String showUpdateBook(@PathVariable("id") Long id, Model model) {
        Book book = null;
        try {
            book = bookServiceImpl.getById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("book", book);
        return "add-update-book";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book, Model model) {
        try {
            bookServiceImpl.update(id,book);
            return "redirect:/api/book/list";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "add-update-book";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        try{
            bookServiceImpl.delete(id);
            return "redirect:/api/book/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/api/book/list";
        }
    }
}
