package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {


    public void saveContract(Contract contract) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contracts.csv", true))) {


            StringBuilder contractData = new StringBuilder();


            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;

                contractData.append("SALE|")
                        .append(salesContract.getDate()).append("|")
                        .append(salesContract.getCustomerName()).append("|")
                        .append(salesContract.getCustomerEmail()).append("|")
                        .append(salesContract.getVehicleSold().getVin()).append("|")
                        .append(salesContract.getVehicleSold().getYear()).append("|")
                        .append(salesContract.getVehicleSold().getMake()).append("|")
                        .append(salesContract.getVehicleSold().getModel()).append("|")
                        .append(salesContract.getVehicleSold().getVehicleType()).append("|")
                        .append(salesContract.getVehicleSold().getColor()).append("|")
                        .append(salesContract.getVehicleSold().getOdometer()).append("|")
                        .append(salesContract.getTotalPrice()).append("|")
                        .append(salesContract.getSalesTaxAmount()).append("|")
                        .append(salesContract.getRecordingFee()).append("|")
                        .append(salesContract.getProcessingFee()).append("|")
                        .append(salesContract.isFinanceOption() ? "YES" : "NO").append("|")
                        .append(salesContract.getMonthlyPayment());
            }


            else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;


                contractData.append("LEASE|")
                        .append(leaseContract.getDate()).append("|")
                        .append(leaseContract.getCustomerName()).append("|")
                        .append(leaseContract.getCustomerEmail()).append("|")
                        .append(leaseContract.getVehicleSold().getVin()).append("|")
                        .append(leaseContract.getVehicleSold().getYear()).append("|")
                        .append(leaseContract.getVehicleSold().getMake()).append("|")
                        .append(leaseContract.getVehicleSold().getModel()).append("|")
                        .append(leaseContract.getVehicleSold().getVehicleType()).append("|")
                        .append(leaseContract.getVehicleSold().getColor()).append("|")
                        .append(leaseContract.getVehicleSold().getOdometer()).append("|")
                        .append(leaseContract.getTotalPrice()).append("|")
                        .append(leaseContract.getExpectedEndingValue()).append("|")
                        .append(leaseContract.getLeaseFee()).append("|")
                        .append(leaseContract.getMonthlyPayment());
            }

            bw.write(contractData.toString());

            bw.newLine();

            System.out.println("Contract saved successfully.");

        } catch (IOException e) {
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }
}
