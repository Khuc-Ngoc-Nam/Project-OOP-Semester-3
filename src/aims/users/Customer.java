package aims.users;

import aims.car.Car;
import aims.store.Store;

import java.util.ArrayList;
import java.util.Scanner;

public class CarOwner extends Person {
    private String name; // Tên người bán
    private String phone; // Số điện thoại
    private ArrayList<Car> ownedCars = new ArrayList<>(); // Danh sách xe sở hữu

    // Constructor
    public CarOwner(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    // Getter cho tên người bán
    public String getName() {
        return name;
    }

    // Hiển thị menu chính cho CarOwner
    @Override
    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------- Xin chào " + name + " ----------");
            System.out.println("0. Xem danh sách xe của bạn");
            System.out.println("1. Chỉnh sửa danh sách xe của bạn");
            System.out.println("2. Exit");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng mới

            switch (choice) {
                case 0:
                    viewOwnedCars();
                    break;
                case 1:
                    editOwnedCars(sc);
                    break;
                case 2:
                    System.out.println("Thoát chương trình. Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 2);
    }

    // Hiển thị danh sách xe của CarOwner
    private void viewOwnedCars() {
        if (ownedCars.isEmpty()) {
            System.out.println("Hiện tại bạn chưa thêm xe nào.");
            return;
        }

        System.out.println("---------- Danh sách xe của bạn ----------");
        for (int i = 0; i < ownedCars.size(); i++) {
            Car car = ownedCars.get(i);
            System.out.printf("%d. Tên xe: %s | Giá thuê: %.2f | Tình trạng: %s\n",
                    i + 1,
                    car.getName(),
                    car.getRentalPrice(),
                    car.isRented() ? "Đã thuê" : "Có sẵn");
        }
    }

    // Chỉnh sửa danh sách xe
    private void editOwnedCars(Scanner sc) {
        int choice;
        do {
            System.out.println("---------- Chỉnh sửa danh sách xe của bạn ----------");
            System.out.println("0. Thêm xe");
            System.out.println("1. Xóa xe");
            System.out.println("2. Exit");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng mới

            switch (choice) {
                case 0:
                    addCar(sc);
                    break;
                case 1:
                    removeCar(sc);
                    break;
                case 2:
                    System.out.println("Thoát chỉnh sửa danh sách xe.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 2);
    }

    // Thêm xe vào danh sách
    private void addCar(Scanner sc) {
        System.out.print("Nhập tên xe: ");
        String name = sc.nextLine();
        System.out.print("Nhập biển số xe: ");
        String licensePlate = sc.nextLine();
        System.out.print("Nhập hãng xe: ");
        String brand = sc.nextLine();
        System.out.print("Nhập loại xe: ");
        String type = sc.nextLine();
        System.out.print("Nhập năm sản xuất: ");
        int year = sc.nextInt();
        System.out.print("Nhập giá thuê: ");
        float rentalPrice = sc.nextFloat();
        sc.nextLine(); // Đọc bỏ dòng mới

        Car newCar = new Car(name, licensePlate, brand, type, year, rentalPrice);
        ownedCars.add(newCar);
        System.out.println("Xe đã được thêm vào danh sách của bạn.");
    }

    // Xóa xe khỏi danh sách
    private void removeCar(Scanner sc) {
        if (ownedCars.isEmpty()) {
            System.out.println("Hiện tại bạn chưa có xe nào để xóa.");
            return;
        }

        System.out.print("Nhập số thứ tự của xe muốn xóa: ");
        int index = sc.nextInt();
        sc.nextLine(); // Đọc bỏ dòng mới

        if (index > 0 && index <= ownedCars.size()) {
            ownedCars.remove(index - 1);
            System.out.println("Xe đã được xóa khỏi danh sách của bạn.");
        } else {
            System.out.println("Số thứ tự không hợp lệ.");
        }
    }
}
