package Rental_Car_System.src;
import Rental_Car_System.src.Admin.Admin;
import Rental_Car_System.src.Customer.Customer;
import Rental_Car_System.src.CarOwner.CarOwner;

public class Car_System {
    public static void main(String[] args) {
        Customer customer = new Customer("Alice", "001");
        Admin admin = new Admin("Bob", "002");
        CarOwner carOwner = new CarOwner("Charlie", "003");

        // Customer login
        if (customer.logIn("customer1", "password123", CredentialStore.getCustomerCredentials())) {
            System.out.println("Welcome, " + customer.getName());
        }

        // Admin login
        if (admin.logIn("admin1", "adminPass", CredentialStore.getAdminCredentials())) {
            System.out.println("Welcome, " + admin.getName());
        }

        // Car Owner login
        if (carOwner.logIn("owner1", "wrongPass", CredentialStore.getCarOwnerCredentials())) {
            System.out.println("Welcome, " + carOwner.getName());
        }
    
    }
}
