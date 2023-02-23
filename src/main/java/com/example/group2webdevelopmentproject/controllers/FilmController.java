package com.example.group2webdevelopmentproject.controllers;

import com.example.group2webdevelopmentproject.entities.Film;
import com.example.group2webdevelopmentproject.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FilmController {
    @Autowired
    private FilmRepository Filmrepo;
    @GetMapping("films")
    public String getActors(Model model) {
        List<Film> films = Filmrepo.findAll();
        model.addAttribute("films", films);
        return "films";
    }
}
