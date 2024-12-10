package Rental_Car_System.src.Admin;

import java.util.Map;

import Rental_Car_System.src.Person;

public class Admin extends Person{
    public Admin(String name, String id) {
        super(name, id);
    }
    @Override
    public boolean logIn(String username, String password, Map<String, String> credentials) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Admin logged in successfully!");
            return true;
        }
        System.out.println("Admin login failed.");
        return false;
    }
}
