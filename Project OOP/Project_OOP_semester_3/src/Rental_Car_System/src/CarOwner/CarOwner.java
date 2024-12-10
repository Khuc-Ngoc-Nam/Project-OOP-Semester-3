package Rental_Car_System.src.CarOwner;

import java.util.Map;

import Rental_Car_System.src.Person;

public class CarOwner extends Person{
    public CarOwner(String name, String id) {
        super(name, id);
    }
    @Override
    public boolean logIn(String username, String password, Map<String, String> credentials) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Car Owner logged in successfully!");
            return true;
        }
        System.out.println("Car Owner login failed.");
        return false;
    }
}
