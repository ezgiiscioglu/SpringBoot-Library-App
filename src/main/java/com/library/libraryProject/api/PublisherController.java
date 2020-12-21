package com.library.libraryProject.api;

import com.library.libraryProject.dto.PublisherDto;
import com.library.libraryProject.model.Publisher;
import com.library.libraryProject.service.PublisherService;
import com.library.libraryProject.service.imp.PublisherServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherServiceImp publisherServiceImp;
    private final PublisherService publisherService;

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Publisher> publishers = publisherService.getAll();
        model.addAttribute("publishers", publishers);
        return "listPublisher";
    }

    @RequestMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Publisher publisher = publisherServiceImp.getById(id);

        model.addAttribute("publisher", publisher);
        return "listPublisher";
    }

    @PostMapping("/updatePublisher/{id}")
    public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            publisher.setPublisher_id(id);
            return "updatePublisher";
        }
        publisherServiceImp.update(id, publisher);
        model.addAttribute("publisher", publisherServiceImp.getAll());
        return "redirect:/api/publisher/list";
    }

    @RequestMapping("/addPublisher")
    public String createPublisher(PublisherDto publisherDto, Model model) {
        publisherServiceImp.save(publisherDto);
        return "redirect:/api/publisher/list";
    }

    @GetMapping("/addPublisherForm")
    public String newCreateForm(PublisherDto publisherDto) {
        return "addPublisher";
    }

    @GetMapping("/updatePublisherForm/{id}")
    public String newUpdateForm(@PathVariable("id") Long id, Model model) {
        Publisher publisher = publisherServiceImp.getById(id);
        model.addAttribute("publisher", publisher);
        return "updatePublisher";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id,Model model){
        model.addAttribute("publisher", publisherServiceImp.getAll());
        publisherServiceImp.delete(id);
        return "redirect:/api/publisher/list";
    }
}
