package src.test.testcar;

import aims.car.Car;
import aims.store.Store;

public class TestCarStatus {
    public static void main(String[] args) {
        Store store = new Store();

        // Tạo một số xe mẫu
        Car car1 = new Car("Toyota Camry", "ID001", "Toyota", "Sedan", 2020, 500.0f);
        Car car2 = new Car("Honda CR-V", "ID002", "Honda", "SUV", 2021, 600.0f);
        Car car3 = new Car("Ford Ranger", "ID003", "Ford", "Truck", 2022, 700.0f);

        // Thêm xe vào cửa hàng
        store.addCarToStore(car1);
        store.addCarToStore(car2);
        store.addCarToStore(car3);

        // Hiển thị danh sách xe ban đầu
        System.out.println("Trước khi cập nhật tình trạng:");
        store.displayAvailableCars();

        // Cập nhật tình trạng xe
        store.setCarStatus(1, true); // Đánh dấu xe thứ 2 là "Đã thuê"
        store.setCarStatus(0, true); // Đánh dấu xe đầu tiên là "Đã thuê"

        // Hiển thị danh sách xe sau khi cập nhật
        System.out.println("\nSau khi cập nhật tình trạng:");
        store.displayAvailableCars();
    }
}