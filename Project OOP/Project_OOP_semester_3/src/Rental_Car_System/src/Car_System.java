package Rental_Car_System.src;
import Rental_Car_System.src.Car.Car;
import Rental_Car_System.src.CarOwner.CarOwner;
import Rental_Car_System.src.Customer.Customer;
import Rental_Car_System.src.Store.Store;

public class Car_System {
    public static void main(String[] args) {
        // Create a store
        Store store = new Store();

        // Create a car owner and add cars to the store
        CarOwner owner = new CarOwner("Alice", "Smith", "Female", "New York", "123-456-7890");
        Car car1 = new Car("SUV", "Toyota Highlander", 35000.0, true, null);
        Car car2 = new Car("Sedan", "Honda Accord", 25000.0, true, null);
        owner.addCar(car1, store);
        owner.addCar(car2, store);

        // Create a customer
        Customer customer = new Customer("Bob", "Johnson", "Male", "Los Angeles", "987-654-3210");

        // View available cars
        customer.viewAvailableCars(store);

        // Rent a car
        customer.rentCar(car1, store);

        // View rental history
        customer.viewRentalHistory();

        // View available cars after renting
        customer.viewAvailableCars(store);
    }
}
