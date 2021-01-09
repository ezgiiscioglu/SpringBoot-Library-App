package com.library.libraryProject.controller;

import com.library.libraryProject.dto.PublisherDto;
import com.library.libraryProject.model.Publisher;
import com.library.libraryProject.service.PublisherService;
import com.library.libraryProject.service.impl.PublisherServiceImpl;
import com.library.libraryProject.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(ApiPaths.PublisherCtrl.CTRL)
public class PublisherController {

    private final PublisherServiceImpl publisherServiceImpl;
    private final PublisherService publisherService;

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "list-publishers";
    }

    @RequestMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Publisher publisher = publisherServiceImpl.getById(id);

        model.addAttribute("publisher", publisher);
        return "list-publishers";
    }

    @PostMapping("/updatePublisher/{id}")
    public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            publisher.setPublisher_id(id);
            return "update-publisher";
        }
        publisherServiceImpl.update(id, publisher);
        model.addAttribute("publisher", publisherServiceImpl.getAllPublishers());
        return "redirect:/api/publisher/list";
    }

    @RequestMapping("/addPublisher")
    public String createPublisher(PublisherDto publisherDto, Model model) {
        publisherServiceImpl.save(publisherDto);
        return "redirect:/api/publisher/list";
    }

    @GetMapping("/addPublisherForm")
    public String newCreateForm(PublisherDto publisherDto) {
        return "add-publisher";
    }

    @GetMapping("/updatePublisherForm/{id}")
    public String newUpdateForm(@PathVariable("id") Long id, Model model) {
        Publisher publisher = publisherServiceImpl.getById(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id,Model model){
        model.addAttribute("publisher", publisherServiceImpl.getAllPublishers());
        publisherServiceImpl.delete(id);
        return "redirect:/api/publisher/list";
    }
}
