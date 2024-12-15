package aims.users;

import aims.car.Car;
import aims.store.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CarOwner extends Person {
    private String name;
    private String phone;
    private ArrayList<Car> ownedCars = new ArrayList<>();

    private static final String CARS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\Car.csv";

    public CarOwner(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--- Car Owner Menu ---");
            System.out.println("1. View your cars");
            System.out.println("2. Add a car");
            System.out.println("3. Remove a car");
            System.out.println("4. Log out");
            System.out.print("Your option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewOwnedCars();
                    break;
                case 2:
                    addCar(sc);
                    break;
                case 3:
                    removeCar(sc);
                    break;
                case 4:
                    System.out.println("You've logged out!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 4);
    }

    private void viewOwnedCars() {
        if (ownedCars.isEmpty()) {
            System.out.println("You currently have no cars.");
            return;
        }
        System.out.println("--- Your Cars ---");
        for (int i = 0; i < ownedCars.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, ownedCars.get(i));
        }
    }

    private void addCar(Scanner sc) {
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
        saveCarToCSV(newCar);
        System.out.println("Car added successfully.");
    }

    private void removeCar(Scanner sc) {
        if (ownedCars.isEmpty()) {
            System.out.println("You currently have no cars to remove.");
            return;
        }
        System.out.print("Enter the car number to remove: ");
        int carIndex = sc.nextInt();
        sc.nextLine();

        if (carIndex > 0 && carIndex <= ownedCars.size()) {
            Car removedCar = ownedCars.remove(carIndex - 1);
            updateCarsCSV();
            System.out.println("Car removed successfully: " + removedCar.getName());
        } else {
            System.out.println("Invalid car number.");
        }
    }

    private void saveCarToCSV(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARS_FILE, true))) {
            writer.write(car.getName() + "," + car.getLicensePlate() + "," + car.getBrand() + "," + car.getType() + "," + car.getManufacturingYear() + "," + car.getRentalPrice() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving car to file: " + e.getMessage());
        }
    }

    private void updateCarsCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARS_FILE))) {
            for (Car car : ownedCars) {
                writer.write(car.getName() + "," + car.getLicensePlate() + "," + car.getBrand() + "," + car.getType() + "," + car.getManufacturingYear() + "," + car.getRentalPrice() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating car file: " + e.getMessage());
        }
    }
}
