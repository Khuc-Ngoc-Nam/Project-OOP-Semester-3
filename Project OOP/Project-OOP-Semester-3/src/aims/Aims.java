package aims;

import aims.store.Store;
import aims.users.Admin;
import aims.users.CarOwner;
import aims.users.Customer;
import aims.users.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store(); // Manage the store

        // Load account data from CSV files
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<CarOwner> carOwners = new ArrayList<>();

        try {
            loadAccounts(admins, customers, carOwners);
        } catch (IOException e) {
            System.out.println("Error loading account data: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate account
        Person loggedInPerson = authenticate(username, password, admins, customers, carOwners);
        if (loggedInPerson != null) {
            loggedInPerson.displayMenu(store);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loadAccounts(ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) throws IOException {
        String adminFilePath = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\admins.csv"; // Replace with the full path to admins.csv
        String customerFilePath = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\customers.csv"; // Replace with the full path to customers.csv
        String carOwnerFilePath = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\carOwners.csv"; // Replace with the full path to carOwners.csv

        // Load admin accounts
        try (BufferedReader adminReader = new BufferedReader(new FileReader(adminFilePath))) {
            String line;
            while ((line = adminReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    admins.add(new Admin(parts[0], parts[1], new Store()));
                }
            }
        }

        // Load customer accounts
        try (BufferedReader customerReader = new BufferedReader(new FileReader(customerFilePath))) {
            String line;
            while ((line = customerReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        }

        // Load car owner accounts
        try (BufferedReader carOwnerReader = new BufferedReader(new FileReader(carOwnerFilePath))) {
            String line;
            while ((line = carOwnerReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    carOwners.add(new CarOwner(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        }
    }

    private static Person authenticate(String username, String password, ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) {
        for (Admin admin : admins) {
            if (admin.login(username, password)) {
                return admin;
            }
        }

        for (Customer customer : customers) {
            if (customer.login(username, password)) {
                return customer;
            }
        }

        for (CarOwner carOwner : carOwners) {
            if (carOwner.login(username, password)) {
                return carOwner;
            }
        }

        return null;
    }
}
