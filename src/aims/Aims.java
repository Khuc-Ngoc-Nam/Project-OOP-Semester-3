// package aims;

// import aims.car.Car;
// import aims.cart.Cart;

// public class Aims {
//     public static void main(String[] args) {
//         Cart anOrder = new Cart();
//         Car car1 = new Car("Land Cruiser LC300", "17A-56158", "Toyota", "SUV", 2023, 499);
//         anOrder.addCar(car1);
//         Car car2 = new Car("Lexus LX600", "18B-28565", "Toyota", "SUV", 2023, 799);
//         anOrder.addCar(car2);
//         Car car3 = new Car("Maybach GLS680", "17A-56159", "Mercedes", "SUV", 2023, 989);
//         anOrder.addCar(car3);
//         Car car4 = new Car("BMW Z4", "17A-08090", "BMW", "Couple", 2023, 399);
//         anOrder.addCar(car4);

//         System.out.println("Total Cost is: ");
//         System.out.println(anOrder.totalCost());
//         System.out.println(anOrder.getQtyOrdered());

//         anOrder.print();

//         anOrder.removeCar(car3);
//         System.out.println(anOrder.getQtyOrdered());

//         anOrder.print();
//     }
// }

package aims;

import aims.users.Admin;
import aims.users.Customer;
import aims.users.CarOwner;
import aims.store.Store;

import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        // Tạo dữ liệu mẫu
        Store store = new Store(); // Quản lý kho xe

        Admin admin = new Admin("admin", "admin123", store);
        Customer customer = new Customer("alice", "password", "Alice", "0123456789", "123456789");
        CarOwner carOwner = new CarOwner("john", "password123", "John", "0456789123");

        // Chạy chương trình chính
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tài khoản: ");
        String username = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = sc.nextLine();

        if (admin.login(username, password)) {
            admin.displayMenu();
        } else if (customer.login(username, password)) {
            customer.displayMenu(store);
        } else if (carOwner.login(username, password)) {
            carOwner.displayMenu(store);
        } else {
            System.out.println("Tài khoản hoặc mật khẩu không đúng.");
        }
    }
}

