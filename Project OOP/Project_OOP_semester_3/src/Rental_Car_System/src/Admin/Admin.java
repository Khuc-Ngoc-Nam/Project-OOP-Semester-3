package Rental_Car_System.src.Admin;

import java.util.Map;

import Rental_Car_System.src.Person;

public class Admin extends Person{
    public Admin(String firstName, String lastName, String gender, String location, String contact) {
        super(firstName, lastName, gender, location, contact);
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
