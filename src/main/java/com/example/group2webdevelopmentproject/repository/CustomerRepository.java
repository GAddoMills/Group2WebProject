package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}