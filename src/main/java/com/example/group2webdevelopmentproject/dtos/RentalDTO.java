package com.example.group2webdevelopmentproject.dtos;

public class RentalDTO {
    private Integer id;
    private Integer inventory;

    private int customer;

    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")

    private String rentalDate;

    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")

    private String returnDate;

    private int staffId;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
