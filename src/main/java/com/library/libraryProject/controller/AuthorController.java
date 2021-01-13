package com.library.libraryProject.controller;

import com.library.libraryProject.model.Author;
import com.library.libraryProject.service.impl.AuthorServiceImpl;
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
@RequestMapping(ApiPaths.AuthorCtrl.CTRL)

public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @GetMapping("/list")
    public String getAll(Model model)  {
        List<Author> authors = authorServiceImpl.getAllAuthors();
        model.addAttribute("authors", authors);
        return "list-authors";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id") Long id, Model model) {
        Author author = null;
        try {
            author = authorServiceImpl.getById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("author", author);
        return "author";
    }

    @GetMapping("/add-author")
    public String showAddAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("add", true);
        model.addAttribute("author", author);
        return "add-update-author";
    }

    @PostMapping("/add-author")
    public String addAuthor(Model model, @ModelAttribute("author") Author author) {
        try {
            authorServiceImpl.save(author);
            return "redirect:/api/author/list";
        }catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "add-update-author";
        }
    }

    @GetMapping("/update-author/{id}")
    public String showUpdateBook(@PathVariable("id") Long id, Model model) {
        Author author = null;
        try {
            author = authorServiceImpl.getById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("author", author);
        return "add-update-author";
    }

    @PostMapping("/update-author/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("author") Author author, Model model) {
        try {
            authorServiceImpl.update(id,author);
            return "redirect:/api/author/list";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "add-update-author";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        try{
            model.addAttribute("author", authorServiceImpl.getAllAuthors());
            authorServiceImpl.delete(id);
            return "redirect:/api/author/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "list-authors";
        }
    }

}