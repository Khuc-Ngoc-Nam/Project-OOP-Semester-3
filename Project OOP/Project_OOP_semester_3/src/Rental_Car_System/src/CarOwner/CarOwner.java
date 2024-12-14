package Rental_Car_System.src.CarOwner;

import Rental_Car_System.src.Car.Car;
import Rental_Car_System.src.Store.Store;
import Rental_Car_System.src.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a car owner in the rental system.
 */
public class CarOwner extends Person {
    private List<Car> carList; // List of cars owned by the car owner

    public CarOwner(String firstName, String lastName, String gender, String location, String contact) {
        super(firstName, lastName, gender, location, contact);
        this.carList = new ArrayList<>();
    }

    /**
     * Adds a car to the owner's car list and to the store.
     *
     * @param car   the car to be added
     * @param store the store where the car should be listed
     */
    public void addCar(Car car, Store store) {
        car.setOwner(this); // Set the current CarOwner as the owner of the car
        carList.add(car);   // Add to owner's list
        store.addCar(car);  // Add to the store
        System.out.println("Car added successfully to both the owner's list and the store.");
    }

    /**
     * Displays the list of cars owned by the car owner.
     */
    public void displayCars() {
        if (carList.isEmpty()) {
            System.out.println("No cars in your list.");
            return;
        }
        System.out.println("Your cars:");
        for (Car car : carList) {
            System.out.println(car);
        }
    }

    @Override
    public boolean logIn(String username, String password, Map<String, String> credentials) {
        if (username == null || password == null) {
            System.out.println("Username or password cannot be null.");
            return false;
        }
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Car Owner logged in successfully!");
            return true;
        }
        System.out.println("Car Owner login failed.");
        return false;
    }
}
