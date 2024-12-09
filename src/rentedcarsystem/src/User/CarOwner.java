package src.User;

import java.util.ArrayList;
import java.util.List;

public class CarOwner {
    // Thuộc tính của lớp CarOwner
    private String name;
    private String id;
    private String phoneNumber;
    private List<Car> carsOwned;  // Danh sách xe sở hữu
    private String location;
    private double rating;  // Số sao được đánh giá

    // Constructor
    public CarOwner(String name, String id, String phoneNumber, String location) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.carsOwned = new ArrayList<>();
        this.rating = 0.0;  // Mặc định là 0 sao
    }

    // Phương thức thêm xe
    public void addCar(Car car) {
        this.carsOwned.add(car);
        System.out.println("Xe " + car.getName() + " đã được thêm vào danh sách xe sở hữu.");
    }

    // Phương thức chat với User hoặc Admin
    public void chatWithUserOrAdmin(String message) {
        System.out.println("Chủ xe gửi tin nhắn: " + message);
    }

    // Phương thức xem lịch sử cho thuê
    public void viewRentalHistory() {
        System.out.println("Lịch sử cho thuê của " + this.name + ":");
        // Giả sử mỗi xe đều có lịch sử cho thuê riêng.
        for (Car car : carsOwned) {
            car.viewRentalHistory();
        }
    }

    // Phương thức cập nhật thông tin xe
    public void updateCarInfo(String carID, String newName, String newLocation) {
        for (Car car : carsOwned) {
            if (car.getId().equals(carID)) {
                car.setName(newName);
                car.setLocation(newLocation);
                System.out.println("Thông tin xe " + carID + " đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy xe với ID: " + carID);
    }

    // Phương thức kiểm tra tình trạng các xe còn lại
    public void checkAvailableCars() {
        System.out.println("Danh sách xe còn lại của " + this.name + ":");
        for (Car car : carsOwned) {
            if (car.isAvailable()) {
                System.out.println(car.getName() + " - Available");
            } else {
                System.out.println(car.getName() + " - Not Available");
            }
        }
    }

    // Phương thức xóa xe
    public void removeCar(String carID) {
        for (Car car : carsOwned) {
            if (car.getId().equals(carID)) {
                carsOwned.remove(car);
                System.out.println("Xe " + carID + " đã bị xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy xe với ID: " + carID);
    }

    // Các phương thức getter và setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Car> getCarsOwned() {
        return carsOwned;
    }

    public void setCarsOwned(List<Car> carsOwned) {
        this.carsOwned = carsOwned;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

class Car {
    private String id;
    private String name;
    private String location;
    private boolean available;
    private List<String> rentalHistory;

    // Constructor
    public Car(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.available = true;
        this.rentalHistory = new ArrayList<>();
    }

    // Phương thức xem lịch sử cho thuê
    public void viewRentalHistory() {
        System.out.println("Lịch sử cho thuê của xe " + this.name + ":");
        if (rentalHistory.isEmpty()) {
            System.out.println("Chưa có giao dịch cho thuê.");
        } else {
            for (String history : rentalHistory) {
                System.out.println(history);
            }
        }
    }

    // Các phương thức getter và setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }
}
