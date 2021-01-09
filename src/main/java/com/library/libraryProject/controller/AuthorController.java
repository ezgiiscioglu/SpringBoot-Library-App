package com.library.libraryProject.api;

import com.library.libraryProject.dto.AuthorDto;
import com.library.libraryProject.model.Author;
import com.library.libraryProject.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id", required = true) Long id, Model model) {
        Author author= authorServiceImpl.getById(id);
        model.addAttribute("author", author);
        return "listAuthor";
    }

    @GetMapping("/list")
    public String getAll(Model model)  {
        List<Author> authors = authorServiceImpl.getAll();
        model.addAttribute("authors", authors);
        return "listAuthor";
    }

    @GetMapping("/addAuthorForm")
    public String newCreateForm(AuthorDto authorDto) {
        return "addAuthor";
    }

    @PostMapping("/updateAuthor/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            author.setAuthor_id(id);
            return "updateAuthor";
        }
        authorServiceImpl.update(id,author);
        model.addAttribute("author", authorServiceImpl.getAll());
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
        return "updateAuthor";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
        model.addAttribute("author", authorServiceImpl.getAll());
        authorServiceImpl.delete(id);
        return "redirect:/api/author/list";
    }
}