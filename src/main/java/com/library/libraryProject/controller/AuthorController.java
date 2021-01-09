package com.library.libraryProject.controller;

import com.library.libraryProject.dto.AuthorDto;
import com.library.libraryProject.model.Author;
import com.library.libraryProject.service.impl.AuthorServiceImpl;
import com.library.libraryProject.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(ApiPaths.AuthorCtrl.CTRL)

public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id", required = true) Long id, Model model) {
        Author author= authorServiceImpl.getById(id);
        model.addAttribute("author", author);
        return "list-authors";
    }

    @GetMapping("/list")
    public String getAll(Model model)  {
        List<Author> authors = authorServiceImpl.getAllAuthors();
        model.addAttribute("authors", authors);
        return "list-authors";
    }

    @GetMapping("/addAuthorForm")
    public String newCreateForm(AuthorDto authorDto) {
        return "add-author";
    }

    @PostMapping("/updateAuthor/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            author.setAuthor_id(id);
            return "update-author";
        }
        authorServiceImpl.update(id,author);
        model.addAttribute("author", authorServiceImpl.getAllAuthors());
        return "redirect:/api/author/list";
    }

    @RequestMapping("/addAuthor")
    public String createAuthor(AuthorDto authorDto, Model model) {
        authorServiceImpl.save(authorDto);
        return "redirect:/api/author/list";
    }

    @GetMapping("/updateAuthorForm/{id}")
    public String newUpdateForm(@PathVariable("id") Long id, Model model) {
        Author author = authorServiceImpl.getById(id);
        model.addAttribute("author", author);
        return "update-author";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
        model.addAttribute("author", authorServiceImpl.getAllAuthors());
        authorServiceImpl.delete(id);
        return "redirect:/api/author/list";
    }
}