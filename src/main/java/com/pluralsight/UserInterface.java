package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner;
    private ContractFileManager contractFileManager;

    public UserInterface() {
        scanner = new Scanner(System.in);
        contractFileManager = new ContractFileManager();
        init();
    }

    public void display() {
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- Menu ----------");
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Sell a Vehicle");
            System.out.println("11. Lease a Vehicle");
            System.out.println("99. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    processSellRequest(); // Updated to handle selling
                    break;
                case "11":
                    processLeaseRequest(); // Updated to handle leasing
                    break;
                case "99":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        System.out.print("Enter vehicle vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        System.out.print("Enter vehicle mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Motorcycle): ");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle you wish to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
        } else {
            DealershipFileManager manager = new DealershipFileManager();
            manager.saveDealership(dealership);
        }
    }

    private void processSellRequest() {
        System.out.print("Enter the VIN of the vehicle to sell: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer's email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Enter date of contract: ");
        String date = scanner.nextLine();

        System.out.print("Is this financed? (yes/no): ");
        boolean isFinanced = scanner.nextLine().equalsIgnoreCase("yes");

        Contract contract = new SalesContract(date, customerName, customerEmail, selectedVehicle, isFinanced);
        contractFileManager.saveContract(contract);
        dealership.removeVehicle(selectedVehicle);
        System.out.println("Sale contract created and saved successfully!");
    }

    private void processLeaseRequest() {
        System.out.print("Enter the VIN of the vehicle to lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer's email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Enter date of contract: ");
        String date = scanner.nextLine();

        Contract contract = new LeaseContract(date, customerName, customerEmail, selectedVehicle);
        contractFileManager.saveContract(contract);
        dealership.removeVehicle(selectedVehicle);
        System.out.println("Lease contract created and saved successfully!");
    }
}
