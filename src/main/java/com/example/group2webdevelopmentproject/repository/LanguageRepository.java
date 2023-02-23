package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Short> {
    Language findByName(String name);
}