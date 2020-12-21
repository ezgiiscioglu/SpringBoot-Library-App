package com.library.libraryProject.api;

import com.library.libraryProject.model.Book;
import com.library.libraryProject.service.BookService;
import com.library.libraryProject.service.imp.AuthorServiceImp;
import com.library.libraryProject.service.imp.BookServiceImp;
import com.library.libraryProject.service.imp.PublisherServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookServiceImp bookServiceImp;
    private final AuthorServiceImp authorServiceImp;
    private final PublisherServiceImp publisherServiceImp;
    private final BookService bookService;

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "/api/book/listBook";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id", required = true) Long id, Model model) {
        Book book = bookServiceImp.getById(id);
        model.addAttribute("book", book);
        return "/api/book/listBook";
    }

    @GetMapping("/addBookForm")
    public String newCreateForm(Book book, Model model) {
        model.addAttribute("authors", authorServiceImp.getAll());
        model.addAttribute("publishers", publisherServiceImp.getAll());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String createBook(Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();
        }
        bookServiceImp.save(book);
        model.addAttribute("book", bookServiceImp.save(book));
        return "redirect:/api/book/list";
    }
    @PostMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            book.setBook_id(id);
            return "updateBook";
        }
        bookServiceImp.update(id,book);
        model.addAttribute("book", bookServiceImp.getAll());
        return "redirect:/api/book/list";
    }

    @GetMapping("/updateBookForm/{id}")
    public String newUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookServiceImp.getById(id);
        model.addAttribute("book",book);
        return "updateBook";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
        model.addAttribute("author", bookServiceImp.getAll());
        bookServiceImp.delete(id);
        return "redirect:/api/book/list";
    }
}
