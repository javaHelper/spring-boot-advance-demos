package com.example.controller;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import com.example.service.FlywayService;
import org.flywaydb.core.api.MigrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FlywayService flywayService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<MigrationInfo> migrations = flywayService.getAllMigrations();
        model.addAttribute("migrations", migrations);
        return "home";
    }

    @GetMapping("/persons")
    public String listPersons(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "5") int size) {

        try {
            Page<Person> personPage = personRepository.findAll(PageRequest.of(page - 1, size));

            model.addAttribute("personPage", personPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", personPage.getTotalPages());
            model.addAttribute("totalItems", personPage.getTotalElements());
            model.addAttribute("pageSize", size);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "persons";
    }
}