package Rental_Car_System.src.Customer;

import Rental_Car_System.src.Car.Car;
import Rental_Car_System.src.Person;
import Rental_Car_System.src.Store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a customer in the rental system.
 */
public class Customer extends Person {
    private List<Car> rentalHistory; // List of cars rented by the customer

    // Constructor with parameters
    public Customer(String firstName, String lastName, String gender, String location, String contact) {
        super(firstName, lastName, gender, location, contact);
        this.rentalHistory = new ArrayList<>();
    }

    // Default constructor
    public Customer() {
        this.rentalHistory = new ArrayList<>();
    }

    /**
     * Logs in the customer using provided credentials.
     *
     * @param username    the username
     * @param password    the password
     * @param credentials a map of valid usernames and passwords
     * @return true if login is successful, false otherwise
     */
    @Override
    public boolean logIn(String username, String password, Map<String, String> credentials) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Customer logged in successfully!");
            return true;
        }
        System.out.println("Customer login failed.");
        return false;
    }

    /**
     * Displays all available cars in the store for the customer to choose from.
     *
     * @param store the store containing all cars
     */
    public void viewAvailableCars(Store store) {
        System.out.println("Available cars:");
        store.displayAvailableCars();
    }

    /**
     * Allows the customer to rent a car from the store.
     *
     * @param car the car to rent
     * @param store the store to rent the car from
     */
    public void rentCar(Car car, Store store) {
        if (car.isAvailable()) {
            car.setAvailability(false); // Mark the car as rented
            rentalHistory.add(car); // Add to customer's rental history
            System.out.println("Car rented successfully: " + car);
        } else {
            System.out.println("Sorry, the car is not available for rent.");
        }
    }

    /**
     * Displays the customer's rental history.
     */
    public void viewRentalHistory() {
        if (rentalHistory.isEmpty()) {
            System.out.println("You haven't rented any cars yet.");
            return;
        }
        System.out.println("Your rental history:");
        for (Car car : rentalHistory) {
            System.out.println(car);
        }
    }
}
