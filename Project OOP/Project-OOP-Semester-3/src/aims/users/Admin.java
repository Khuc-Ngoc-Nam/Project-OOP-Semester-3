package aims.users;

import aims.store.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void displayMenu(Store store) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------ Admin Menu ------------");
            System.out.println("1. View Car Owners");
            System.out.println("2. Add Car Owner");
            System.out.println("3. Delete Car Owner");
            System.out.println("4. View Customers");
            System.out.println("5. Add Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Log Out");
            System.out.print("Your option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAccounts("Project-OOP-Semester-3\\src\\aims\\carOwners.csv");
                    break;
                case 2:
                    addAccount(scanner, "carOwner");
                    break;
                case 3:
                    deleteAccount(scanner, "Project-OOP-Semester-3\\src\\aims\\carOwners.csv");
                    break;
                case 4:
                    viewAccounts("Project-OOP-Semester-3\\src\\aims\\customers.csv");
                    break;
                case 5:
                    addAccount(scanner, "customer");
                    break;
                case 6:
                    deleteAccount(scanner, "Project-OOP-Semester-3\\src\\aims\\customers.csv");
                    break;
                case 7:
                    System.out.println("You've logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 7);
    }

    private void viewAccounts(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 1;
            System.out.println("--- Accounts ---");
            while ((line = reader.readLine()) != null) {
                System.out.printf("%d. %s%n", index++, line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addAccount(Scanner scanner, String accountType) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (accountType.equals("carOwner")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            CarOwner carOwner = new CarOwner(username, password, name, phone);
            saveToCSV(carOwner, "Project-OOP-Semester-3\\src\\aims\\carOwners.csv");
        } else if (accountType.equals("customer")) {
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter ID card: ");
            String idCard = scanner.nextLine();

            Customer customer = new Customer(username, password, fullName, phoneNumber, idCard);
            saveToCSV(customer, "Project-OOP-Semester-3\\src\\aims\\customers.csv");
        }

        System.out.println("Account added successfully!");
    }

    private void deleteAccount(Scanner scanner, String fileName) {
        ArrayList<String> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 1;
            System.out.println("--- Accounts ---");
            while ((line = reader.readLine()) != null) {
                accounts.add(line);
                System.out.printf("%d. %s%n", index++, line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (accounts.isEmpty()) {
            System.out.println("No accounts available to delete.");
            return;
        }

        System.out.print("Enter the index of the account to delete: ");
        int accountIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (accountIndex < 0 || accountIndex >= accounts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        accounts.remove(accountIndex);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String account : accounts) {
                writer.write(account);
                writer.newLine();
            }
            System.out.println("Account deleted successfully!");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    private void saveToCSV(Person person, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            StringBuilder entry = new StringBuilder();
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                entry.append(customer.getUsername()).append(",")
                     .append(customer.getPassword()).append(",")
                     .append(customer.getFullName()).append(",")
                     .append(customer.getPhoneNumber()).append(",")
                     .append(customer.getIdCard());
            } else if (person instanceof CarOwner) {
                CarOwner carOwner = new CarOwner(person.getUsername(), person.getPassword(), ((CarOwner) person).getName(), ((CarOwner) person).getPhone());
                entry.append(carOwner.getUsername()).append(",")
                     .append(carOwner.getPassword()).append(",")
                     .append(carOwner.getName()).append(",")
                     .append(carOwner.getPhone());
            }
            writer.write(entry.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
