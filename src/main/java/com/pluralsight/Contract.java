package com.pluralsight;

public class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public double getTotalPrice() {
        return vehicleSold.getPrice();
    }

    public double getMonthlyPayment() {
        return 0.0; // Default implementation, will be overridden in subclasses
    }
}