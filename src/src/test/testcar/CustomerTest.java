package src.test.testcar;

import aims.users.Customer;
import aims.users.CarOwner;
import aims.car.Car;
import aims.store.Store;

import java.util.Scanner;

public class CustomerTest {
    public static void main(String[] args) {
        // Tạo một kho xe
        Store store = new Store();

        // Thêm một số xe vào kho
        Car car1 = new Car("Toyota Camry", "ID001", "Toyota", "Sedan", 2020, 500.0f);
        Car car2 = new Car("Honda CR-V", "ID002", "Honda", "SUV", 2021, 600.0f);
        Car car3 = new Car("Ford Ranger", "ID003", "Ford", "Truck", 2022, 700.0f);
        store.addCar(car1);
        store.addCar(car2);
        store.addCar(car3);

        // Tạo khách hàng
        Customer customer = new Customer("alice", "password123", "Alice", "0123456789", "123456789");

        // Giao diện kiểm tra chức năng của Customer
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tài khoản: ");
        String username = sc.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = sc.nextLine();

        // Xác thực đăng nhập
        if (customer.login(username, password)) {
            System.out.println("Đăng nhập thành công!");
            customer.displayMenu(store);
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu!");
        }
    }
}
