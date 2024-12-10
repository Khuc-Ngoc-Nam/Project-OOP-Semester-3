package Rental_Car_System.src.Customer;
import java.util.Map;

import Rental_Car_System.src.Person;
public class Customer extends Person{
    public Customer(String name, String id) {
        super(name, id);
    }
    @Override
    public boolean logIn(String username, String password, Map<String, String> credentials) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Customer logged in successfully!");
            return true;
        }
        System.out.println("Customer login failed.");
        return false;
    }
}
