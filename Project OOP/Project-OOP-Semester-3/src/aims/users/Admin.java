package aims.users;

import aims.store.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<CarOwner> carOwners = new ArrayList<>();
    private Store store;

    private static final String CUSTOMERS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\customers.csv";
    private static final String CAR_OWNERS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\carOwners.csv";

    public Admin(String username, String password, Store store) {
        super(username, password);
        this.store = store;
    }

    @Override
    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--- Admin Menu ---");
            System.out.println("1. Manage store");
            System.out.println("2. Manage customer accounts");
            System.out.println("3. Manage car owner accounts");
            System.out.println("4. Log out");
            System.out.print("Your option: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStore();
                    break;
                case 2:
                    manageCustomers(sc);
                    break;
                case 3:
                    manageCarOwners(sc);
                    break;
                case 4:
                    System.out.println("You've logged out!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 4);
    }

    private void manageStore() {
        store.displayCars();
    }

    private void manageCustomers(Scanner sc) {
        int choice;
        do {
            System.out.println("--- Manage Customer Accounts ---");
            System.out.println("1. Add customer");
            System.out.println("2. Delete customer");
            System.out.println("3. Return");
            System.out.print("Your option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCustomer(sc);
                    break;
                case 2:
                    deleteCustomer(sc);
                    break;
                case 3:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 3);
    }

    private void addCustomer(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter full name: ");
        String fullName = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter ID card: ");
        String idCard = sc.nextLine();

        Customer newCustomer = new Customer(username, password, fullName, phoneNumber, idCard);
        customers.add(newCustomer);
        saveToCSV(newCustomer, CUSTOMERS_FILE);
        System.out.println("Customer added successfully.");
    }

    private void deleteCustomer(Scanner sc) {
        System.out.print("Enter username of the customer to delete: ");
        String username = sc.nextLine();
        Customer toRemove = null;
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                toRemove = customer;
                break;
            }
        }
        if (toRemove != null) {
            customers.remove(toRemove);
            updateCSV(customers, CUSTOMERS_FILE);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private void manageCarOwners(Scanner sc) {
        int choice;
        do {
            System.out.println("--- Manage Car Owner Accounts ---");
            System.out.println("1. Add car owner");
            System.out.println("2. Delete car owner");
            System.out.println("3. Return");
            System.out.print("Your option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCarOwner(sc);
                    break;
                case 2:
                    deleteCarOwner(sc);
                    break;
                case 3:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 3);
    }

    private void addCarOwner(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter full name: ");
        String fullName = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        CarOwner newCarOwner = new CarOwner(username, password, fullName, phoneNumber);
        carOwners.add(newCarOwner);
        saveToCSV(newCarOwner, CAR_OWNERS_FILE);
        System.out.println("Car owner added successfully.");
    }

    private void deleteCarOwner(Scanner sc) {
        System.out.print("Enter username of the car owner to delete: ");
        String username = sc.nextLine();
        CarOwner toRemove = null;
        for (CarOwner carOwner : carOwners) {
            if (carOwner.getUsername().equals(username)) {
                toRemove = carOwner;
                break;
            }
        }
        if (toRemove != null) {
            carOwners.remove(toRemove);
            updateCSV(carOwners, CAR_OWNERS_FILE);
            System.out.println("Car owner deleted successfully.");
        } else {
            System.out.println("Car owner not found.");
        }
    }

    private void saveToCSV(Person person, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getFullName() + "," + customer.getPhoneNumber() + "," + customer.getIdCard() + "\n");
            } else if (person instanceof CarOwner) {
                CarOwner carOwner = (CarOwner) person;
                writer.write(carOwner.getUsername() + "," + carOwner.getPassword() + "," + carOwner.getName() + "," + carOwner.getPhone() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void updateCSV(ArrayList<? extends Person> persons, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : persons) {
                if (person instanceof Customer) {
                    Customer customer = (Customer) person;
                    writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getFullName() + "," + customer.getPhoneNumber() + "," + customer.getIdCard() + "\n");
                } else if (person instanceof CarOwner) {
                    CarOwner carOwner = (CarOwner) person;
                    writer.write(carOwner.getUsername() + "," + carOwner.getPassword() + "," + carOwner.getName() + "," + carOwner.getPhone() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
}
