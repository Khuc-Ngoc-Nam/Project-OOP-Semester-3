package Rental_Car_System.src.Store;

import Rental_Car_System.src.Car.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the centralized car store containing all cars in the system.
 */
public class Store {
    private List<Car> cars; // List of all cars in the store

    // Constructor
    public Store() {
        this.cars = new ArrayList<>();
    }

    /**
     * Adds a car to the store's car list.
     *
     * @param car the car to add
     */
    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Car added to the store: " + car);
    }

    /**
     * Displays all cars in the store.
     */
    public void displayAllCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars are available in the store.");
            return;
        }
        System.out.println("Cars in the store:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Displays all available cars for customers to rent.
     */
    public void displayAvailableCars() {
        boolean found = false;
        System.out.println("Available cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars are available for rent.");
        }
    }

    /**
     * Returns the list of all cars in the store.
     *
     * @return List of cars
     */
    public List<Car> getCars() {
        return cars;
    }
}
