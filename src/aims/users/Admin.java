package aims.users;

import aims.store.Store;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<CarOwner> carOwners = new ArrayList<>();
    private Store store;

    public Admin(String username, String password, Store store) {
        super(username, password);
        this.store = store;
    }

    @Override
    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--- Admin Menu ---");
            System.out.println("1. Quản lý cửa hàng");
            System.out.println("2. Quản lý người mua");
            System.out.println("3. Quản lý người bán");
            System.out.println("4. Đăng xuất");
            System.out.print("Lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    manageStore();
                    break;
                case 2:
                    manageCustomers(sc);
                    break;
                case 3:
                    manageCarOwners(sc);
                    break;
                case 4:
                    System.out.println("Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 4);
    }

    private void manageStore() {
        store.displayCars();
    }

    private void manageCustomers(Scanner sc) {
        System.out.println("Quản lý khách hàng:");
        // Implement customer management logic
    }

    private void manageCarOwners(Scanner sc) {
        System.out.println("Quản lý người bán:");
        // Implement car owner management logic
    }
}