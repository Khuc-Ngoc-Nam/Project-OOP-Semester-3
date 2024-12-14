package Rental_Car_System.src.Car;

import Rental_Car_System.src.CarOwner.CarOwner;

/**
 * Represents a car in the rental system.
 */
public class Car {
    private String carType;
    private String carModel;
    private double carPrice;
    private boolean isAvailable;
    private CarOwner owner; // The owner of the car

    // Default constructor
    public Car() {
        this.carType = "";
        this.carModel = "";
        this.carPrice = 0.0;
        this.isAvailable = true; // Default to available
        this.owner = null;
    }

    // Overloaded constructor for initialization
    public Car(String carType, String carModel, double carPrice, boolean isAvailable, CarOwner owner) {
        this.carType = carType;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvailable = isAvailable;
        this.owner = owner;
    }

    // Method to update car details
    public void setCarDetails(String carType, String carModel, double carPrice, boolean isAvailable, CarOwner owner) {
        if (carPrice < 0) {
            throw new IllegalArgumentException("Car price cannot be negative.");
        }
        this.carType = carType;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvailable = isAvailable;
        this.owner = owner;
    }

	public String getCar() {
		return getCarType() + "-" + getCarModel();
	}

    // Getters
    public String getCarType() {
        return carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public CarOwner getOwner() {
        return owner;
    }

    // Set availability
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Set owner
    public void setOwner(CarOwner owner) {
        this.owner = owner;
    }

    // String representation of the car
    @Override
    public String toString() {
        return "Car [Type: " + carType + ", Model: " + carModel + ", Price: $" + carPrice +
                ", Available: " + (isAvailable ? "Yes" : "No") +
                ", Owner: " + (owner != null ? owner.getFirstName() + " " + owner.getLastName() : "No owner") + "]";
    }
}
