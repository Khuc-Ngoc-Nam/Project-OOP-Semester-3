package aims.users;

import aims.car.Car;
import aims.store.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Person {
    private String fullName;
    private String phoneNumber;
    private String idCard;
    private static final String CARS_FILE = "Project-OOP-Semester-3\\src\\aims\\Car.csv";

    public Customer(String username, String password, String fullName, String phoneNumber, String idCard) {
        super(username, password);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
    }
   
    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    @Override
public void displayMenu(Store store) {
    Scanner scanner = new Scanner(System.in);
    int choice;
    do {
        System.out.println("------------ Welcome " + fullName + " ------------");
        System.out.println("1. View cars in store");
        System.out.println("2. Add a car to your cart");
        System.out.println("3. View your cart");
        System.out.println("4. Remove a car from your cart");
        System.out.println("5. Log out");
        System.out.print("Your option: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewStore();
                break;
            case 2:
                addCar(scanner);
                break;
            case 3:
                viewCart(scanner);
                break;
            case 4:
                removeCar(scanner);
                break;
            case 5:
                System.out.println("You've logged out successfully!");
                break;
            default:
                System.out.println("Invalid option! Try again.");
        }
    } while (choice != 5);
}


    public void addCar(Scanner scanner) {
        ArrayList<Car> availableCars = new ArrayList<>();

        // Read available cars from the store file
        try (BufferedReader reader = new BufferedReader(new FileReader(CARS_FILE))) {
            String line;
            System.out.println("--- Cars Available in Store ---");
            int index = 1;

            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[6].equalsIgnoreCase("available")) {
                    Car car = new Car(attributes[0], attributes[1], attributes[2], attributes[3],
                            Integer.parseInt(attributes[4]), Float.parseFloat(attributes[5]));
                    availableCars.add(car);
                    System.out.printf("%d. Name: %s, License Plate: %s, Brand: %s, Type: %s, Year: %s, Price: %.2f%n",
                            index++, attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                            Float.parseFloat(attributes[5]));
                }
            }

            if (availableCars.isEmpty()) {
                System.out.println("No cars are currently available in the store.");
                return;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading cars file: " + e.getMessage());
            return;
        }

        // Prompt the user to select a car by index
        System.out.print("Enter the index of the car you want to rent: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (carIndex < 0 || carIndex >= availableCars.size()) {
            System.out.println("Invalid index! Please try again.");
            return;
        }

        Car selectedCar = availableCars.get(carIndex);

        // Update the store file and add the car to the customer's list
        boolean carAdded = false;
        File tempFile = new File(CARS_FILE + ".tmp");
        File originalFile = new File(CARS_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[1].equals(selectedCar.getLicensePlate())) {
                    attributes[6] = "rented";
                    carAdded = true;
                }
                writer.write(String.join(",", attributes) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating store file: " + e.getMessage());
            return;
        }

        if (carAdded) {
            if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
                System.out.println("Error finalizing store update.");
            } else {
                addCarToCustomerList(selectedCar);
                updateCarOwnerFile(selectedCar, "rented");
                System.out.println("The car has been added to your cart!");
            }
        } else {
            tempFile.delete();
            System.out.println("Failed to add the car to your cart.");
        }
    }

    private void addCarToCustomerList(Car car) {
        String fileName = String.format("Customer_%s.csv", this.getUsername());
        File customerFile = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile, true))) {
            writer.write(String.format("%s,%s,%s,%s,%d,%.2f%n",
                    car.getName(), car.getLicensePlate(), car.getBrand(), car.getType(),
                    car.getManufacturingYear(), car.getRentalPrice()));
        } catch (IOException e) {
            System.out.println("Error saving to customer's list: " + e.getMessage());
        }
    }

    private void updateCarOwnerFile(Car car, String status) {
        String ownerFileName = String.format("CarOwner_%s.csv", car.getBrand());
        File tempFile = new File(ownerFileName + ".tmp");
        File originalFile = new File(ownerFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6 && attributes[1].equals(car.getLicensePlate())) {
                    attributes[5] = status;
                }
                writer.write(String.join(",", attributes) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating owner's file: " + e.getMessage());
        }

        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            System.out.println("Error finalizing owner file update.");
        }
    }

    public void viewCart(Scanner scanner) {
        String fileName = String.format("Customer_%s.csv", this.getUsername());
        File customerFile = new File(fileName);

        if (!customerFile.exists()) {
            System.out.println("Your cart is empty.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            System.out.println("--- Your Cart ---");
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6) {
                    System.out.printf("Name: %s, License Plate: %s, Brand: %s, Type: %s, Year: %s, Price: %.2f%n",
                            attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                            Float.parseFloat(attributes[5]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading your cart: " + e.getMessage());
        }
    }

    public void viewStore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CARS_FILE))) {
            String line;
            System.out.println("--- Cars Available in Store ---");
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[6].equalsIgnoreCase("available")) {
                    found = true;
                    System.out.printf("Name: %s, License Plate: %s, Brand: %s, Type: %s, Year: %s, Price: %.2f%n",
                            attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                            Float.parseFloat(attributes[5]));
                }
            }

            if (!found) {
                System.out.println("No cars available in the store.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading cars file: " + e.getMessage());
        }
    }

    public void removeCar(Scanner scanner) {
        String fileName = String.format("Customer_%s.csv", this.getUsername());
        File customerFile = new File(fileName);

        if (!customerFile.exists()) {
            System.out.println("Your cart is empty.");
            return;
        }

        ArrayList<Car> cartCars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            System.out.println("--- Your Cart ---");
            int index = 1;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6) {
                    Car car = new Car(attributes[0], attributes[1], attributes[2], attributes[3],
                            Integer.parseInt(attributes[4]), Float.parseFloat(attributes[5]));
                    cartCars.add(car);
                    System.out.printf("%d. Name: %s, License Plate: %s, Brand: %s, Type: %s, Year: %s, Price: %.2f%n",
                            index++, attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                            Float.parseFloat(attributes[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading your cart: " + e.getMessage());
            return;
        }

        if (cartCars.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.print("Enter the index of the car you want to remove: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (carIndex < 0 || carIndex >= cartCars.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Car carToRemove = cartCars.get(carIndex);

        // Update cart file
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6 && !attributes[1].equals(carToRemove.getLicensePlate())) {
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating cart: " + e.getMessage());
            return;
        }

        // Finalize cart update
        File tempFile = new File(fileName + ".tmp");
        if (!customerFile.delete() || !tempFile.renameTo(customerFile)) {
            System.out.println("Error finalizing cart update.");
            return;
        }

        // Update store and owner's list
        updateCarStatus(carToRemove, "available");
        System.out.println("The car has been removed from your cart.");
    }

    private void updateCarStatus(Car car, String status) {
        updateFile(CARS_FILE, car, status);
        String ownerFileName = String.format("CarOwner_%s.csv", car.getBrand());
        updateFile(ownerFileName, car, status);
    }

    private void updateFile(String fileName, Car car, String status) {
        File tempFile = new File(fileName + ".tmp");
        File originalFile = new File(fileName);
    
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length >= 7 && attributes[1].equals(car.getLicensePlate())) {
                    attributes[6] = status; // Correct column index for availability
                }
                writer.write(String.join(",", attributes) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            return;
        }
    
        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            System.out.println("Error finalizing file update.");
        }
    }
}    
