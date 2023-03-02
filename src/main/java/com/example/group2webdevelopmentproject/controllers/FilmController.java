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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class FilmController {
    Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmRepository filmrepo;
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("films")
    public String getActors(Model model) {
        logger.debug("Get all films method");
        List<Film> films = filmrepo.findAll();
        model.addAttribute("films", films);
        return "films";
    }

    @GetMapping("films/edit/{id}")
    public String displayFilmEditForm(@PathVariable int id, Model model){
        logger.debug("Edit film requested");
        Optional<Film> optFilm = filmrepo.findById(id);
        if (optFilm.isEmpty()) {
            logger.warn("Film " + id + " not found on the database.");
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
        logger.debug("Post method received for update film: " + dto);
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
        filmrepo.saveAndFlush(film);
        return "editSuccess";
    }

    @PostMapping ("films/add")
    public String addFilm(@ModelAttribute("newFilm") FilmDTO dto){
        logger.debug("Post request for add new film");
        Film film = new Film();
        Language language = languageRepository.findByName(dto.getLanguage());
        film.setDescription(dto.getDescription());
        film.setLanguage(language);
        film.setLength(dto.getLength());
        film.setRating(dto.getRating());
        film.setReleaseYear(dto.getReleaseYear());
        film.setRentalDuration(dto.getRentalDuration());
        film.setRentalRate(dto.getRentalRate());
        film.setReplacementCost(dto.getReplacementCost());
        film.setSpecialFeatures(dto.getSpecialFeatures());
        film.setTitle(dto.getTitle());
        film.setLastUpdate(Instant.now());

        filmrepo.saveAndFlush(film);
        return "addSuccess";
    }

    @GetMapping("films/new")
    public String displayActorCreateForm(Model model) {
        logger.debug("Get method for creating a new film");
        model.addAttribute("newFilm", new Film());
        return "filmCreateForm";
    }

}
