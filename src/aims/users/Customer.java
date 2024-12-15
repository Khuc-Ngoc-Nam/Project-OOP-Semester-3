// Customer.java
package aims.users;

import aims.car.Car;
import aims.cart.Cart;
import aims.store.Store;
import java.util.Scanner;

public class Customer extends Person {
    private String fullName;
    private String phoneNumber;
    private String idCard;
    private Cart cart;

    public Customer(String username, String password, String fullName, String phoneNumber, String idCard) {
        super(username, password);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.cart = new Cart();
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public void displayMenu(Store store) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------ Chào mừng " + fullName + " ------------");
            System.out.println("1. Xem danh sách xe trong cửa hàng");
            System.out.println("2. Xem giỏ hàng");
            System.out.println("3. Đăng xuất");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStore(store, scanner);
                    break;
                case 2:
                    viewCart(scanner);
                    break;
                case 3:
                    System.out.println("Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 3);
    }

    private void viewStore(Store store, Scanner scanner) {
        store.displayCars();
        System.out.print("Nhập số thứ tự của xe muốn thêm vào giỏ hàng (hoặc nhập 0 để quay lại): ");
        int carIndex = scanner.nextInt();
        scanner.nextLine();

        if (carIndex > 0 && carIndex <= store.getInventorySize()) {
            Car car = store.getCarInventory().get(carIndex - 1);
            if (!car.isRented()) {
                cart.addCarToCart(car);
                System.out.println("Xe đã được thêm vào giỏ hàng.");
            } else {
                System.out.println("Xe đã được thuê, không thể thêm vào giỏ hàng.");
            }
        } else {
            System.out.println("Quay lại menu chính.");
        }
    }

    private void viewCart(Scanner scanner) {
        cart.displayCartDetails();
        System.out.println("1. Xóa xe khỏi giỏ hàng");
        System.out.println("2. Hiển thị tổng số tiền phải trả");
        System.out.println("3. Quay lại");
        System.out.print("Lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập số thứ tự của xe muốn xóa: ");
                int carIndex = scanner.nextInt();
                scanner.nextLine();
                if (carIndex > 0 && carIndex <= cart.getCarCount()) {
                    cart.removeCarFromCartByIndex(carIndex - 1);
                    System.out.println("Xe đã được xóa khỏi giỏ hàng.");
                } else {
                    System.out.println("Số thứ tự không hợp lệ.");
                }
                break;
            case 2:
                System.out.printf("Tổng số tiền phải trả: %.2f\n", cart.calculateTotalCost());
                break;
            case 3:
                System.out.println("Quay lại menu chính.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }
}
