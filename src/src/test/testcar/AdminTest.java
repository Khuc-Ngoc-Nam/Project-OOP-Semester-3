package src.test.testcar;

import aims.users.Admin;
import aims.users.Customer;
import aims.users.CarOwner;
import aims.store.Store;
import aims.car.Car;

import java.util.Scanner;

public class AdminTest {
    public static void main(String[] args) {
        // Tạo đối tượng Store
        Store store = new Store();

        // Thêm một số xe mẫu vào cửa hàng
        Car car1 = new Car("Toyota Camry", "ID001", "Toyota", "Sedan", 2020, 500.0f);
        Car car2 = new Car("Honda CR-V", "ID002", "Honda", "SUV", 2021, 600.0f);
        Car car3 = new Car("Ford Ranger", "ID003", "Ford", "Truck", 2022, 700.0f);

        store.addCar(car1);
        store.addCar(car2);
        store.addCar(car3);

        // Tạo Admin
        Admin admin = new Admin("admin", "admin123", store);

        // Thêm một số khách hàng và người bán mẫu
        Customer customer1 = new Customer("customer1", "password1", "Alice", "0123456789", "123456789");
        Customer customer2 = new Customer("customer2", "password2", "Bob", "0987654321", "987654321");

        CarOwner carOwner1 = new CarOwner("carowner1", "ownerpass1", "John", "0456789123");
        CarOwner carOwner2 = new CarOwner("carowner2", "ownerpass2", "Jane", "0765432198");

        admin.getCustomers().add(customer1);
        admin.getCustomers().add(customer2);

        admin.getCarOwners().add(carOwner1);
        admin.getCarOwners().add(carOwner2);

        // Mô phỏng đăng nhập
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        if (admin.login(username, password)) {
            System.out.println("Đăng nhập thành công! Xin chào, Admin.");
            admin.displayMenu();
        } else {
            System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản hoặc mật khẩu.");
        }

        // Kết thúc chương trình
        System.out.println("Chương trình kết thúc.");
    }
}

        // Tạo một số tài khoản người mua và người bán
        Customer customer1 = new Customer("alice", "pass1", "Alice", "0123456789", "123456789");
        Customer customer2 = new Customer("bob", "pass2", "Bob", "0987654321", "987654321");

        CarOwner carOwner1 = new CarOwner("john", "ownerpass1", "John", "0456789123");
        CarOwner carOwner2 = new CarOwner("jane", "ownerpass2", "Jane", "0765432198");

        // Thêm người mua và người bán vào Admin
        admin.customers.add(customer1);
        admin.customers.add(customer2);

        admin.carOwners.add(carOwner1);
        admin.carOwners.add(carOwner2);

        // Hiển thị menu cho Admin
        admin.displayMenu();
    }
}

