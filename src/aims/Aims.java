// Aims.java
package aims;

import aims.store.Store;
import aims.users.Admin;
import aims.users.CarOwner;
import aims.users.Customer;
import aims.users.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store(); // Quản lý kho xe

        // Load dữ liệu tài khoản từ tệp CSV
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<CarOwner> carOwners = new ArrayList<>();

        try {
            loadAccounts(admins, customers, carOwners);
        } catch (IOException e) {
            System.out.println("Không thể tải dữ liệu tài khoản: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tài khoản: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        // Xác thực tài khoản
        Person loggedInPerson = authenticate(username, password, admins, customers, carOwners);
        if (loggedInPerson != null) {
            loggedInPerson.displayMenu(store);
        } else {
            System.out.println("Tài khoản hoặc mật khẩu không đúng.");
        }
    }

    private static void loadAccounts(ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) throws IOException {
        String basePath = "D:/Git của Nam/RentedCarByNam/src/aims/";

        BufferedReader adminReader = new BufferedReader(new FileReader(basePath + "admins.csv"));
        String line;
        while ((line = adminReader.readLine()) != null) {
            String[] parts = line.split(",");
            admins.add(new Admin(parts[0], parts[1], new Store()));
        }
        adminReader.close();

        BufferedReader customerReader = new BufferedReader(new FileReader(basePath + "customers.csv"));
        while ((line = customerReader.readLine()) != null) {
            String[] parts = line.split(",");
            customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]));
        }
        customerReader.close();

        BufferedReader carOwnerReader = new BufferedReader(new FileReader(basePath + "carOwners.csv"));
        while ((line = carOwnerReader.readLine()) != null) {
            String[] parts = line.split(",");
            carOwners.add(new CarOwner(parts[0], parts[1], parts[2], parts[3]));
        }
        carOwnerReader.close();
    }

    private static Person authenticate(String username, String password, ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) {
        for (Admin admin : admins) {
            if (admin.login(username, password)) {
                return admin;
            }
        }

        for (Customer customer : customers) {
            if (customer.login(username, password)) {
                return customer;
            }
        }

        for (CarOwner carOwner : carOwners) {
            if (carOwner.login(username, password)) {
                return carOwner;
            }
        }

        return null;
    }
}
