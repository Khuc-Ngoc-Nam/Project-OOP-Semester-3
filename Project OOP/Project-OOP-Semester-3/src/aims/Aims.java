package aims;

import aims.store.Store;
import aims.users.Admin;
import aims.users.CarOwner;
import aims.users.Customer;
import aims.users.Person;
import aims.car.Car;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Aims {
    private static final String CARS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\Car.csv";
    private static final String ADMINS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\admins.csv";
    private static final String CUSTOMERS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\customers.csv";
    private static final String CAR_OWNERS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\carOwners.csv";

    public static void main(String[] args) {
        Store store = new Store(); // Manage the store

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
                displayCustomerMenu((Customer) loggedInPerson, store, scanner);
            } else {
                loggedInPerson.displayMenu(store);
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loadAccounts(ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) throws IOException {
        // Load Admins
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    admins.add(new Admin(parts[0], parts[1], new Store()));
                }
            }
        }

        // Load Customers
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        }

        // Load Car Owners
        try (BufferedReader reader = new BufferedReader(new FileReader(CAR_OWNERS_FILE))) {
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

    private static void displayCustomerMenu(Customer customer, Store store, Scanner scanner) {
        int choice;
        do {
            System.out.println("------------ Welcome " + customer.getFullName() + " ------------");
            System.out.println("1. View cars in store");
            System.out.println("2. Add a car to your cart");
            System.out.println("3. View your cart");
            System.out.println("4. Log out");
            System.out.print("Your option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    customer.viewStore();
                    break;
                case 2:
                    customer.viewStore();
                    addCarToCart(customer, scanner);
                    break;
                case 3:
                    customer.viewCart(scanner);
                    break;
                case 4:
                    System.out.println("You've logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 4);
    }

    private static void addCarToCart(Customer customer, Scanner scanner) {
        System.out.print("Enter car name: ");
        String name = scanner.nextLine();
        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter manufacturing year: ");
        int year = scanner.nextInt();
        System.out.print("Enter rental price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();

        Car car = new Car(name, licensePlate, brand, type, year, price);
        customer.addCar(car);
    }
}
