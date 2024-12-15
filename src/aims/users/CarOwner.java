package aims.users;

import aims.car.Car;
import aims.store.Store;
import java.util.ArrayList;
import java.util.Scanner;

public class CarOwner extends Person {
    private String name;
    private String phone;
    private ArrayList<Car> ownedCars = new ArrayList<>();

    public CarOwner(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    @Override
    public void displayMenu(Store store) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------- Xin chào " + name + " ----------");
            System.out.println("0. Xem danh sách xe của bạn");
            System.out.println("1. Chỉnh sửa danh sách xe của bạn");
            System.out.println("2. Đăng xuất");
            System.out.print("Lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    viewOwnedCars();
                    break;
                case 1:
                    editOwnedCars(sc);
                    break;
                case 2:
                    System.out.println("Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 2);
    }

    private void viewOwnedCars() {
        if (ownedCars.isEmpty()) {
            System.out.println("Bạn không có xe nào trong danh sách.");
            return;
        }

        System.out.println("Danh sách xe của bạn:");
        for (int i = 0; i < ownedCars.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, ownedCars.get(i));
        }
    }

    private void editOwnedCars(Scanner sc) {
        System.out.println("1. Thêm xe");
        System.out.println("2. Xóa xe");
        System.out.println("3. Quay lại");
        System.out.print("Lựa chọn: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addCar(sc);
                break;
            case 2:
                removeCar(sc);
                break;
            case 3:
                System.out.println("Quay lại menu chính.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void addCar(Scanner sc) {
        System.out.print("Tên xe: ");
        String name = sc.nextLine();
        System.out.print("Biển số: ");
        String id = sc.nextLine();
        System.out.print("Hãng: ");
        String brand = sc.nextLine();
        System.out.print("Loại: ");
        String type = sc.nextLine();
        System.out.print("Năm sản xuất: ");
        int year = sc.nextInt();
        System.out.print("Giá thuê: ");
        float price = sc.nextFloat();
        ownedCars.add(new Car(name, id, brand, type, year, price));
        System.out.println("Đã thêm xe thành công.");
    }

    private void removeCar(Scanner sc) {
        System.out.println("Nhập số thứ tự xe muốn xóa:");
        int index = sc.nextInt();
        sc.nextLine();

        if (index > 0 && index <= ownedCars.size()) {
            ownedCars.remove(index - 1);
            System.out.println("Đã xóa xe thành công.");
        } else {
            System.out.println("Số thứ tự không hợp lệ.");
        }
    }
}