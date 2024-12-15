package aims.users;

import aims.car.Car;
import aims.cart.Cart;
import aims.store.Store;
import java.io.*;
import java.util.Scanner;

public class Customer extends Person {
    private String fullName;
    private String phoneNumber;
    private String idCard;
    private Cart cart;

    private static final String CARS_FILE = "C:\\Users\\MY PC\\Project OOP\\Project-OOP-Semester-3\\src\\aims\\Car.csv";

    public Customer(String username, String password, String fullName, String phoneNumber, String idCard) {
        super(username, password);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.cart = new Cart();
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
            System.out.println("--- Customer Menu ---");
            System.out.println("1. View cars in store");
            System.out.println("2. View your order");
            System.out.println("3. Log out");
            System.out.print("Your option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    viewCart(scanner);
                    break;
                case 3:
                    System.out.println("You've logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 3);
    }

    private void viewStore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CARS_FILE))) {
            String line;
            System.out.println("--- Cars Available in Store ---");
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6) {
                    System.out.printf("Name: %s, License Plate: %s, Brand: %s, Type: %s, Year: %s, Price: %s\n",
                            attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading cars file: " + e.getMessage());
        }
    }

    private void viewCart(Scanner scanner) {
        cart.displayCartDetails();
        System.out.println("1. Remove car out of your cart");
        System.out.println("2. View rental payment");
        System.out.println("3. Return");
        System.out.print("Your option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter the serial number of the car you want to remove: ");
                int carIndex = scanner.nextInt();
                scanner.nextLine();
                if (carIndex > 0 && carIndex <= cart.getCarCount()) {
                    cart.removeCarFromCartByIndex(carIndex - 1);
                    System.out.println("The car has been removed!");
                } else {
                    System.out.println("Invalid serial number! Try again.");
                }
                break;
            case 2:
                System.out.printf("Total amount of payment: %.2f\n", cart.calculateTotalCost());
                break;
            case 3:
                System.out.println("Returned to the main menu.");
                break;
            default:
                System.out.println("Invalid option! Try again.");
        }
    }

    public void addCar(Car car) {
        cart.addCarToCart(car);
        System.out.println("Car added to your cart: " + car.getName());
    }

    public void addMultipleCars(Car[] cars) {
        cart.addCarsToCart(cars);
        System.out.println("Multiple cars have been added to your cart.");
    }

    public void addTwoCars(Car car1, Car car2) {
        cart.addCarsToCart(car1, car2);
        System.out.println("Two cars have been added to your cart.");
    }
}
