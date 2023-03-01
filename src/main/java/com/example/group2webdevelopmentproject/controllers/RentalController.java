package com.example.group2webdevelopmentproject.controllers;

import com.example.group2webdevelopmentproject.entities.Customer;
import com.example.group2webdevelopmentproject.entities.Film;
import com.example.group2webdevelopmentproject.entities.Rental;
import com.example.group2webdevelopmentproject.repository.CustomerRepository;
import com.example.group2webdevelopmentproject.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.List;

@Controller
public class RentalController {

    @Autowired
    private RentalRepository rentalRepo;
    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("rentals")
    public String getRental(Model model){
        List<Rental> rentalList = rentalRepo.findAll();
        model.addAttribute("rentals", rentalList);
        return "rentals";
    }

    @GetMapping("customer/{id}")
    public String getCustomerDetails(@PathVariable int id, Model model){
        Customer customer = customerRepo.findById(id).get();
        model.addAttribute("customer", customer);
        return "customer";
    }

    @GetMapping("rentals/delete/{id}")
    public String deleteRental(@PathVariable int id){
        rentalRepo.delete(rentalRepo.findById(id).get());
        return "deleteRental";
    }

    @GetMapping("rentals/edit/{id}")
    public String viewRentalbyId(@PathVariable int id, Model model){
        Rental rental = rentalRepo.findById(id).get();
        model.addAttribute("rentalToEdit", rental);
        return "rentalEditForm";
    }

    @PostMapping("rentals/update")
    public String updateRental(@ModelAttribute("rentalToEdit") Rental rental){
        rental.setReturnDate(Instant.now());
        rental.setRentalDate(Instant.now());
        rental.setLastUpdate(Instant.now());
        rentalRepo.saveAndFlush(rental);
        return "editRentalSuccess";
    }







}
