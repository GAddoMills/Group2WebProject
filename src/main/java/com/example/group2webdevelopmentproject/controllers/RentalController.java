package com.example.group2webdevelopmentproject.controllers;

import com.example.group2webdevelopmentproject.dtos.RentalDTO;
import com.example.group2webdevelopmentproject.entities.*;
import com.example.group2webdevelopmentproject.repository.CustomerRepository;
import com.example.group2webdevelopmentproject.repository.InventoryRepository;
import com.example.group2webdevelopmentproject.repository.RentalRepository;
import com.example.group2webdevelopmentproject.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class RentalController {

    @Autowired
    private RentalRepository rentalRepo;
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private InventoryRepository inventRepo;

    @Autowired
    private StaffRepository staffRepo;

    @GetMapping("index")
    public String index(){
        return "index";
    }

    //SHOW ALL RENTALS
    @GetMapping("rentals")
    public String getRental(Model model){
        List<Rental> rentalList = rentalRepo.findAll();
        model.addAttribute("rentals", rentalList);
        return "rentals";
    }
//SHOW THE CUSTOMER DETAILS
    @GetMapping("customer/{id}")
    public String getCustomerDetails(@PathVariable int id, Model model){
        Customer customer = customerRepo.findById(id).get();
        model.addAttribute("customer", customer);
        return "customer";
    }
//DELETE RENTALS
    @GetMapping("rentals/delete/{id}")
    public String deleteRental(@PathVariable int id){
        Optional<Rental> optRental =  rentalRepo.findById(id);
        if (optRental.isPresent()) {
            rentalRepo.delete(rentalRepo.findById(id).get());
            return "deleteRental";
        } else {
            // TODO logging
            return "errorPage";
        }
    }
//EDIT RENTALS
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
//CREATE RENTALS
    @GetMapping("rentals/create")
    public String createRental(Model model){
        RentalDTO rental = new RentalDTO();
        model.addAttribute("rentalToCreate", rental);
        return "rentalCreateForm";
    }

    @Transactional
    @PostMapping("rentals/add")
    public String createdRental(@ModelAttribute("rentalToCreate") RentalDTO dto){
        Rental rental =new Rental();

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  // Specify locale to determine human language and cultural norms used in translating that input string.
        LocalDateTime rentalDate = LocalDateTime.parse(dto.getRentalDate(), f);
        LocalDateTime returnDate = LocalDateTime.parse(dto.getReturnDate(), f);

        ZoneId zoneId = ZoneId.of("UTC-0");
        ZonedDateTime zonedrentalDate = rentalDate.atZone(zoneId);
        ZonedDateTime zonedreturnDate = returnDate.atZone(zoneId);

        Optional<Inventory> inventory = inventRepo.findById(dto.getInventory());
        Optional <Customer> customer = customerRepo.findById(dto.getCustomer());
        Optional<Staff> staff = staffRepo.findById((short)dto.getStaffId());

        rental.setRentalDate(zonedrentalDate.toInstant());
        if (inventory.isPresent()) {
            rental.setInventory(inventory.get());
        } else {
            // TODO log warning and redirect to error
        }
        if (customer.isPresent()) {
            rental.setCustomer(customer.get());
        } else {
            // TODO log warning and redirect to error
        }

        rental.setReturnDate(zonedreturnDate.toInstant());
        if (staff.isPresent()) {
            rental.setStaff(staff.get());
        } else {
            // TODO log warning and redirect to error
        }
        rental.setLastUpdate(Instant.now());

        rentalRepo.saveAndFlush(rental);
        return "rentalCreatedSuccess";
    }







}
