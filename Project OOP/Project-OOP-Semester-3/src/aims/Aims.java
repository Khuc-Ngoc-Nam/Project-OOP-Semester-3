package aims;

import aims.store.Store;
import aims.users.Admin;
import aims.users.CarOwner;
import aims.users.Customer;
import aims.users.Person;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Aims {
    private static final String CARS_FILE = "Project-OOP-Semester-3\\src\\aims\\Car.csv";

    public static void main(String[] args) {
        Store store = new Store();

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

        Person loggedInPerson = authenticate(username, password, admins, customers, carOwners);
        if (loggedInPerson != null) {
            if (loggedInPerson instanceof Customer) {
                ((Customer) loggedInPerson).displayMenu(store);
            } else {
                loggedInPerson.displayMenu(store);
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loadAccounts(ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) throws IOException {
        // Load Admin Accounts
        String adminFile = "Project-OOP-Semester-3\\src\\aims\\admins.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(adminFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    admins.add(new Admin(parts[0], parts[1]));
                }
            }
        }

        // Load Customer Accounts
        String customerFile = "Project-OOP-Semester-3\\src\\aims\\customers.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        }

        // Load Car Owner Accounts
        String carOwnerFile = "Project-OOP-Semester-3\\src\\aims\\carOwners.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(carOwnerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
