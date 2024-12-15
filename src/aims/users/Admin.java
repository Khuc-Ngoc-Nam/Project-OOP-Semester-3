package aims.users;

import aims.car.Car;
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
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---------- Admin Menu ----------");
            System.out.println("0. Xem cửa hàng xe");
            System.out.println("1. Xem người mua");
            System.out.println("2. Xem người bán");
            System.out.println("3. Exit");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng mới

            switch (choice) {
                case 0:
                    viewStore();
                    break;
                case 1:
                    manageCustomers(sc);
                    break;
                case 2:
                    manageCarOwners(sc);
                    break;
                case 3:
                    System.out.println("Đăng xuất Admin.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 3);
    }

    private void viewStore() {
        if (store.getInventorySize() == 0) {
            System.out.println("Cửa hàng hiện không có xe nào.");
            return;
        }
        System.out.println("---------- Danh sách xe trong cửa hàng ----------");
        for (Car car : store.getCarInventory()) {
            System.out.printf("Tên xe: %s | Giá thuê: %.2f | Tình trạng: %s%n",
                    car.getName(),
                    car.getRentalPrice(),
                    car.isRented() ? "Đã thuê" : "Có sẵn");
        }
    }

    private void manageCustomers(Scanner sc) {
        int choice;
        do {
            System.out.println("---------- Quản lý người mua ----------");
            for (int i = 0; i < customers.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, customers.get(i).getFullName());
            }
            System.out.println("0. Xóa người mua");
            System.out.println("1. Thêm tài khoản cho người mua");
            System.out.println("2. Exit");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng mới

            switch (choice) {
                case 0:
                    System.out.print("Nhập số thứ tự để xóa: ");
                    int customerIndex = sc.nextInt();
                    sc.nextLine();
                    if (customerIndex > 0 && customerIndex <= customers.size()) {
                        customers.remove(customerIndex - 1);
                        System.out.println("Đã xóa người mua.");
                    } else {
                        System.out.println("Số thứ tự không hợp lệ.");
                    }
                    break;
                case 1:
                    System.out.print("Nhập tên đăng nhập: ");
                    String username = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String password = sc.nextLine();
                    System.out.print("Nhập tên người dùng: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = sc.nextLine();
                    System.out.print("Nhập số căn cước công dân: ");
                    String idCard = sc.nextLine();
                    customers.add(new Customer(username, password, name, phone, idCard));
                    System.out.println("Đã thêm tài khoản người mua.");
                    break;
                case 2:
                    System.out.println("Thoát quản lý người mua.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 2);
    }

    private void manageCarOwners(Scanner sc) {
        int choice;
        do {
            System.out.println("---------- Quản lý người bán ----------");
            for (int i = 0; i < carOwners.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, carOwners.get(i).getName());
            }
            System.out.println("0. Xóa người bán");
            System.out.println("1. Thêm người bán");
            System.out.println("2. Exit");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng mới

            switch (choice) {
                case 0:
                    System.out.print("Nhập số thứ tự để xóa: ");
                    int carOwnerIndex = sc.nextInt();
                    sc.nextLine();
                    if (carOwnerIndex > 0 && carOwnerIndex <= carOwners.size()) {
                        carOwners.remove(carOwnerIndex - 1);
                        System.out.println("Đã xóa người bán.");
                    } else {
                        System.out.println("Số thứ tự không hợp lệ.");
                    }
                    break;
                case 1:
                    System.out.print("Nhập tên đăng nhập: ");
                    String username = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String password = sc.nextLine();
                    System.out.print("Nhập tên người bán: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = sc.nextLine();
                    carOwners.add(new CarOwner(username, password, name, phone));
                    System.out.println("Đã thêm tài khoản người bán.");
                    break;
                case 2:
                    System.out.println("Thoát quản lý người bán.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 2);
    }
}
