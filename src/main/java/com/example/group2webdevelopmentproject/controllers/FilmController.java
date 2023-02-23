package com.example.group2webdevelopmentproject.controllers;

import com.example.group2webdevelopmentproject.dtos.FilmDTO;
import com.example.group2webdevelopmentproject.entities.Film;
import com.example.group2webdevelopmentproject.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("films/edit/{id}")
    public String displayFilmEditForm(@PathVariable int id, Model model){
        Optional<Film> optFilm = Filmrepo.findById(id);
        if (optFilm.isEmpty()) {
            // todo: log an error, redirect to error message with reason.
            return "errorPage";
        } else {
            Film film = optFilm.get();
            model.addAttribute("filmToEdit", film);
            return "filmEditForm";
        }
    }
    @PostMapping("films/update")
    public String updateFilm(@ModelAttribute("filmToEdit") Film film){
        film.setLastUpdate(Instant.now());
        Filmrepo.saveAndFlush(film);
        return "editSuccess";
    }

}
