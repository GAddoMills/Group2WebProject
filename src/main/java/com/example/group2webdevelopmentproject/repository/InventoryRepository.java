package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Inventory;
import com.example.group2webdevelopmentproject.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}