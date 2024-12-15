package aims.users;

import aims.car.Car;
import java.util.ArrayList;
import java.util.Scanner;

public class CarOwner extends Person {
    private String name; // CarOwner's name
    private String phone; // Phone number
    private ArrayList<Car> ownedCars = new ArrayList<>(); // List of cars owned by the CarOwner

    // Constructor
    public CarOwner(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------- Hello " + name + " ----------");
            System.out.println("0. View your car list.");
            System.out.println("1. Edit your car list.");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    viewOwnedCars();
                    break;
                case 1:
                    editOwnedCars(sc);
                    break;
                case 2:
                    System.out.println("Exiting. See you next time!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 2);
    }

    private void viewOwnedCars() {
        if (ownedCars.isEmpty()) {
            System.out.println("You don't have any cars listed.");
            return;
        }

        System.out.println("---------- Your Car List ----------");
        for (int i = 0; i < ownedCars.size(); i++) {
            Car car = ownedCars.get(i);
            System.out.printf(
                "%d. Name: %s | Rental Price: %.2f | Status: %s%n",
                i + 1,
                car.getName(),
                car.getRentalPrice(),
                car.isRented() ? "Rented" : "Available"
            );
        }
    }

    private void editOwnedCars(Scanner sc) {
        int choice;
        do {
            System.out.println("---------- Edit Your Car List ----------");
            System.out.println("0. Add car");
            System.out.println("1. Delete car");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    addCar(sc);
                    break;
                case 1:
                    removeCar(sc);
                    break;
                case 2:
                    System.out.println("Exiting car list editing.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 2);
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
        sc.nextLine(); // Consume newline

        Car newCar = new Car(name, licensePlate, brand, type, year, price);
        ownedCars.add(newCar);
        System.out.println("Car has been added to your list.");
    }

    private void removeCar(Scanner sc) {
        if (ownedCars.isEmpty()) {
            System.out.println("No cars to remove.");
            return;
        }

        System.out.print("Enter the number of the car to remove: ");
        int index = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (index > 0 && index <= ownedCars.size()) {
            ownedCars.remove(index - 1);
            System.out.println("Car has been removed from your list.");
        } else {
            System.out.println("Invalid car number.");
        }
    }
}
