package src.test.testcar;

import aims.users.CarOwner;
import aims.car.Car;

import java.util.Scanner;

public class CarOwnerTest {
    public static void main(String[] args) {
        // Tạo đối tượng CarOwner
        CarOwner carOwner = new CarOwner("owner1", "password123", "John Smith", "0123456789");

        // Thêm một số xe mẫu vào danh sách
        Car car1 = new Car("Toyota Camry", "ID001", "Toyota", "Sedan", 2020, 500.0f);
        Car car2 = new Car("Honda CR-V", "ID002", "Honda", "SUV", 2021, 600.0f);
        carOwner.addCar(car1);
        carOwner.addCar(car2);

        // Mô phỏng đăng nhập
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        if (carOwner.login(username, password)) {
            System.out.println("Đăng nhập thành công! Xin chào, " + carOwner.getName());
            // Hiển thị menu của CarOwner
            carOwner.displayMenu();
        } else {
            System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản hoặc mật khẩu.");
        }

        // Kết thúc chương trình
        System.out.println("Chương trình kết thúc.");
    }
}
