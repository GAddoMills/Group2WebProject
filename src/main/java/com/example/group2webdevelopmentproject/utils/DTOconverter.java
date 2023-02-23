package com.example.group2webdevelopmentproject.utils;

import com.example.group2webdevelopmentproject.dtos.FilmDTO;
import com.example.group2webdevelopmentproject.entities.Film;

public class DTOconverter {
    public static FilmDTO toFilmAllDTO(Film film){
        FilmDTO fadto = new FilmDTO();
        fadto.setId(film.getId());
        fadto.setTitle(film.getTitle());
        fadto.setDescription(film.getDescription());
        fadto.setReleaseYear(film.getReleaseYear());
        fadto.setLength(film.getLength());
        fadto.setLanguage(film.getLanguage().getName());
        fadto.setRentalDuration(film.getRentalDuration());
        fadto.setRentalRate(film.getRentalRate());
        fadto.setReplacementCost(film.getReplacementCost());
        fadto.setRating(film.getRating());
        fadto.setSpecialFeatures(film.getSpecialFeatures());

        return fadto;
    }
}
