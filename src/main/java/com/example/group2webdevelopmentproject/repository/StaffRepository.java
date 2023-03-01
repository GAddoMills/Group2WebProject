package com.example.group2webdevelopmentproject.repository;

import com.example.group2webdevelopmentproject.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Short> {
}