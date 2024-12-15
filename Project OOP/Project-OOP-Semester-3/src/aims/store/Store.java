package aims.store;

import aims.car.Car;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Store {
    private HashMap<Car, Integer> carInventory = new HashMap<>();

    // Get inventory size (unique cars)
    public int getInventorySize() {
        return carInventory.size();
    }

    // Add car to the store
    public void addCar(Car car) {
        carInventory.put(car, carInventory.getOrDefault(car, 0) + 1);
        System.out.printf("Car '%s' has been added to the store inventory. Total count: %d\n", car.getName(), carInventory.get(car));
    }

    // Remove car from the store
    public void removeCar(Car car) {
        if (carInventory.containsKey(car)) {
            int count = carInventory.get(car);
            if (count > 1) {
                carInventory.put(car, count - 1);
                System.out.printf("Car '%s' count decreased to %d.\n", car.getName(), carInventory.get(car));
            } else {
                carInventory.remove(car);
                System.out.printf("Car '%s' has been removed from the store inventory.\n", car.getName());
            }
        } else {
            System.out.println("Car does not exist in the inventory.");
        }
    }

    // Display all cars in the store
    public void displayCars() {
        if (carInventory.isEmpty()) {
            System.out.println("The store inventory is currently empty.");
            return;
        }
        System.out.println("---------- Store Inventory ----------");
        int index = 1;
        for (Map.Entry<Car, Integer> entry : carInventory.entrySet()) {
            Car car = entry.getKey();
            int count = entry.getValue();
            System.out.printf("%d. %s (Count: %d, Rented: %s)\n", index++, car, count, car.isRented() ? "Yes" : "No");
        }
    }

    // Get the inventory as a Map (for other operations if needed)
    public HashMap<Car, Integer> getCarInventory() {
        return carInventory;
    }
}
