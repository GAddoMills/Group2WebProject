package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}