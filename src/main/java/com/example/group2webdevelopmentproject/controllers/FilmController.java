package com.example.group2webdevelopmentproject.controllers;

import com.example.group2webdevelopmentproject.dtos.FilmDTO;
import com.example.group2webdevelopmentproject.entities.Film;
import com.example.group2webdevelopmentproject.entities.Language;
import com.example.group2webdevelopmentproject.repository.FilmRepository;
import com.example.group2webdevelopmentproject.repository.LanguageRepository;
import com.example.group2webdevelopmentproject.utils.DTOconverter;
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
    @Autowired
    private LanguageRepository languageRepository;

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
            FilmDTO dto = DTOconverter.toFilmAllDTO(film);
            model.addAttribute("filmToEdit", dto);
            return "filmEditForm";
        }
    }
    @PostMapping("films/update")
    public String updateFilm(@ModelAttribute("filmToEdit") FilmDTO dto) {
        Film film = new Film();
        Language language = languageRepository.findByName(dto.getLanguage());
        film.setDescription(dto.getDescription());
        film.setId(dto.getId());
        film.setLanguage(language);
        film.setLastUpdate(Instant.now());
        film.setLength(dto.getLength());
        film.setRating(dto.getRating());
        film.setReleaseYear(dto.getReleaseYear());
        film.setRentalDuration(dto.getRentalDuration());
        film.setRentalRate(dto.getRentalRate());
        film.setReplacementCost(dto.getReplacementCost());
        film.setSpecialFeatures(dto.getSpecialFeatures());
        film.setTitle(dto.getTitle());
        Filmrepo.saveAndFlush(film);
        return "editSuccess";
    }

}
