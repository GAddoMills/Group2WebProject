package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}