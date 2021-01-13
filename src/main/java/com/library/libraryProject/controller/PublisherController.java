package com.library.libraryProject.controller;

import com.library.libraryProject.model.Publisher;
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
@RequestMapping(ApiPaths.PublisherCtrl.CTRL)
public class PublisherController {

    private final PublisherServiceImpl publisherServiceImpl;

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Publisher> publishers = publisherServiceImpl.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "list-publishers";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Publisher publisher = null;
        try {
            publisher = publisherServiceImpl.getById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("publisher", publisher);
        return "publisher";
    }
    @GetMapping("/add-publisher")
    public String showAddPublisher(Model model) {
        Publisher publisher = new Publisher();
        model.addAttribute("add", true);
        model.addAttribute("publisher", publisher);
        return "add-update-publisher";
    }

    @PostMapping("/add-publisher")
    public String addBPublisher(Model model, @ModelAttribute("publisher") Publisher publisher) {
        try {
            publisherServiceImpl.save(publisher);
            return "redirect:/api/publisher/list";
        }catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "add-update-publisher";
        }
    }

    @GetMapping("/update-publisher/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Publisher publisher = null;
        try {
            publisher = publisherServiceImpl.getById(id);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Publisher not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("publisher", publisher);
        return "add-update-publisher";
    }

    @PostMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, Model model) {
        try {
            publisherServiceImpl.update(id,publisher);
            return "redirect:/api/publisher/list";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "add-update-publisher";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id,Model model){
        try{
            model.addAttribute("publisher", publisherServiceImpl.getAllPublishers());
            publisherServiceImpl.delete(id);
            return "redirect:/api/publisher/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "list-publishers";
        }
    }
}
