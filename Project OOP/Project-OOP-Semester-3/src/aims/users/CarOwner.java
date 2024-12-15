package aims.users;

import aims.car.Car;
import aims.store.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CarOwner extends Person {
    private String name;
    private String phone;
    private ArrayList<Car> ownedCars;
    private static final String CARS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\Car.csv";

    public CarOwner(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.ownedCars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------ Welcome " + name + " ------------");
            System.out.println("1. View your cars");
            System.out.println("2. Add a car to the store");
            System.out.println("3. Remove a car from your list");
            System.out.println("4. Log out");
            System.out.print("Your option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewOwnedCars();
                    break;
                case 2:
                    addCarToStore(sc);
                    break;
                case 3:
                    removeCarFromList(sc);
                    break;
                case 4:
                    System.out.println("Saving your cars...");
                    saveOwnedCarsToFile();
                    System.out.println("You've logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 4);
    }

    private void viewOwnedCars() {
        if (ownedCars.isEmpty()) {
            System.out.println("You have no cars in your list.");
        } else {
            System.out.println("--- Your Cars ---");
            for (Car car : ownedCars) {
                System.out.println(car);
            }
        }
    }

    private void addCarToStore(Scanner sc) {
        System.out.print("Enter car name: ");
        String name = sc.nextLine();
        System.out.print("Enter license plate: ");
        String licensePlate = sc.nextLine();
        System.out.print("Enter brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter type: ");
        String type = sc.nextLine();
        System.out.print("Enter manufacturing year: ");
        int year = sc.nextInt();
        System.out.print("Enter rental price: ");
        float price = sc.nextFloat();
        sc.nextLine();

        Car newCar = new Car(name, licensePlate, brand, type, year, price);
        ownedCars.add(newCar);

        // Save to owner's list and store
        addCarToOwnerListAndStore(newCar);

        System.out.println("The car has been added to your list and the store.");
    }

    private void addCarToOwnerListAndStore(Car car) {
        // File name based on car owner
        String fileName = String.format("CarOwner_%s.csv", this.getUsername());
        File ownerFile = new File(fileName);

        // Add car to owner's list (create file if not exists)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ownerFile, true))) {
            writer.write(String.format("%s,%s,%s,%s,%d,%.2f%n",
                    car.getName(), car.getLicensePlate(), car.getBrand(), car.getType(),
                    car.getManufacturingYear(), car.getRentalPrice()));
        } catch (IOException e) {
            System.out.println("Error saving to owner's list: " + e.getMessage());
        }

        // Add car to the store list
        saveCarToStore(car);
    }

    private void saveCarToStore(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARS_FILE, true))) {
            writer.write(String.format("%s,%s,%s,%s,%d,%.2f,available%n",
                    car.getName(), car.getLicensePlate(), car.getBrand(), car.getType(),
                    car.getManufacturingYear(), car.getRentalPrice()));
        } catch (IOException e) {
            System.out.println("Error saving car to store: " + e.getMessage());
        }
    }

    private void removeCarFromList(Scanner sc) {
        if (ownedCars.isEmpty()) {
            System.out.println("You have no cars to remove.");
            return;
        }
    
        System.out.println("--- Your Cars ---");
        for (int i = 0; i < ownedCars.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, ownedCars.get(i));
        }
    
        System.out.print("Enter the number of the car you want to remove: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
    
        if (index >= 0 && index < ownedCars.size()) {
            Car removedCar = ownedCars.remove(index);
    
            // Remove the car from the owner's list file
            saveOwnedCarsToFile();
    
            // Remove the car from the store's list
            removeCarFromStoreFile(removedCar);
    
            System.out.println("The car has been removed from your list and the store: " + removedCar.getName());
        } else {
            System.out.println("Invalid option! Try again.");
        }
    }

    private void removeCarFromStoreFile(Car car) {
        File tempFile = new File(CARS_FILE + ".tmp");
        File originalFile = new File(CARS_FILE);
    
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                // Skip writing this car to the temp file if it's the one being removed
                if (attributes.length == 7 && attributes[1].equals(car.getLicensePlate())) {
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating store file: " + e.getMessage());
            return;
        }
    
        // Replace the original file with the updated file
        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            System.out.println("Error finalizing store update.");
        }
    }
    
    

    private void saveOwnedCarsToFile() {
        String fileName = String.format("CarOwner_%s.csv", this.getUsername());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : ownedCars) {
                writer.write(String.format("%s,%s,%s,%s,%d,%.2f%n",
                        car.getName(), car.getLicensePlate(), car.getBrand(), car.getType(),
                        car.getManufacturingYear(), car.getRentalPrice()));
            }
            System.out.println("Your car list has been saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving your car list: " + e.getMessage());
        }
    }

    public void loadOwnedCarsFromFile() {
        String fileName = String.format("CarOwner_%s.csv", this.getUsername());
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No saved car list found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6) {
                    Car car = new Car(
                            attributes[0], attributes[1], attributes[2], attributes[3],
                            Integer.parseInt(attributes[4]), Float.parseFloat(attributes[5]));
                    ownedCars.add(car);
                }
            }
            System.out.println("Your car list has been loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading your car list: " + e.getMessage());
        }
    }
}
