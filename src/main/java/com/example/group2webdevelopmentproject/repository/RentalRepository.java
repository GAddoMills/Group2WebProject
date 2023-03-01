package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}