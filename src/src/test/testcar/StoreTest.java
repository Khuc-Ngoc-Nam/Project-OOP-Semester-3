package src.test.testcar;

import aims.car.Car;
import aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        // Tạo một số xe
        Car car1 = new Car("Ferrari SF90", "17A-12345", "Ferrari", "SUV", 2023, 500);
        Car car2 = new Car("Lamborghini Aventador", "18B-54321", "Lamborghini", "Coupe", 2022, 700);
        Car car3 = new Car("Porsche 911", "19C-67890", "Porsche", "Convertible", 2021, 400);

        // Thêm xe vào cửa hàng
        store.addCar(car1);
        store.addCar(car2);
        store.addCar(car3);

        // Hiển thị danh sách xe
        System.out.println("\nDanh sách xe trong kho hàng:");
        store.displayCars();

        // Cập nhật tình trạng xe (Thuê xe)
        System.out.println("\nCập nhật tình trạng xe:");
        store.updateCarStatus(0, true);
        store.updateCarStatus(1, true);

        // Hiển thị danh sách xe sau khi cập nhật
        System.out.println("\nDanh sách xe sau khi cập nhật:");
        store.displayCars();

        // Xóa xe khỏi cửa hàng
        System.out.println("\nXóa xe khỏi cửa hàng:");
        store.removeCar(car2);

        // Hiển thị danh sách xe sau khi xóa
        System.out.println("\nDanh sách xe sau khi xóa:");
        store.displayCars();

        // Kiểm tra xe có trong kho hàng không
        System.out.println("\nKiểm tra xe có trong kho:");
        System.out.println("Car1 có trong kho: " + store.isCarInInventory(car1));
        System.out.println("Car2 có trong kho: " + store.isCarInInventory(car2));
    }
}
