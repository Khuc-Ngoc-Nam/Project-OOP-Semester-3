package src.Car;

public class Car {
  private String carId; // Biển số xe
  private String carName; // Tên xe
  private String brand; // Hãng xe (thay đổi từ "make" thành "brand")
  private String carType; // Loại xe (SUV, Sedan, etc.)
  private int year; // Năm sản xuất
  private float rentalPrice; // Giá thuê xe mỗi ngày (dùng một giá duy nhất)
  private boolean availability; // Trạng thái xe (có sẵn hay không, true = có sẵn, false = không có sẵn)

  // Constructor
  public Car(String carId, String carName, String brand, String carType, int year, float rentalPrice,
      boolean availability) {
    this.carId = carId; // Biển số xe
    this.carName = carName;
    this.brand = brand; // Hãng xe
    this.carType = carType;
    this.year = year;
    this.rentalPrice = rentalPrice; // Giá thuê xe chung
    this.availability = availability; // Trạng thái có sẵn (true = có, false = không)
  }

  // Hiển thị thông tin chi tiết của xe
  public void displayCarInfo() {
    System.out.println("Car ID (License Plate): " + carId); // In ra biển số xe
    System.out.println("Car Name: " + carName);
    System.out.println("Brand: " + brand); // In ra hãng xe
    System.out.println("Car Type: " + carType);
    System.out.println("Year: " + year);
    System.out.println("Rental Price: " + rentalPrice); // In ra giá thuê xe
    System.out.println("Availability: " + (availability ? "Available" : "Unavailable")); // In ra trạng thái có sẵn
  }

  // Kiểm tra trạng thái xe (có sẵn hay không)
  public boolean isAvailable() {
    return availability; // Trả về true nếu có sẵn, false nếu không có sẵn
  }

  // Cập nhật trạng thái xe (có sẵn hay không)
  public void updateAvailability(boolean newStatus) {
    this.availability = newStatus; // Cập nhật trạng thái xe
  }

  // Tính giá thuê xe tùy vào ngày hay giờ thuê
  public float calculateRentalPrice(int rentalDays, int rentalHours) {
    float totalPrice = 0.0f;

    // Tính giá thuê theo ngày
    if (rentalDays > 0) {
      totalPrice = rentalDays * rentalPrice;
    }

    // Tính giá thuê theo giờ (giả sử 1 ngày = 24 giờ)
    if (rentalHours > 0) {
      totalPrice = rentalHours * (rentalPrice / 24); // Tính theo giờ
    }

    return totalPrice; // Trả về giá thuê xe
  }

  // Getters and Setters
  public String getCarId() {
    return carId;
  }

  public void setCarId(String carId) {
    this.carId = carId;
  }

  public String getCarName() {
    return carName;
  }

  public void setCarName(String carName) {
    this.carName = carName;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCarType() {
    return carType;
  }

  public void setCarType(String carType) {
    this.carType = carType;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public float getRentalPrice() {
    return rentalPrice;
  }

  public void setRentalPrice(float rentalPrice) {
    this.rentalPrice = rentalPrice;
  }

  public boolean isAvailability() {
    return availability;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  // To String method for car information display
  @Override
  public String toString() {
    return carId + " (" + carName + " " + brand + " " + carType + " " + year + ") - " + rentalPrice
        + " - Availability: " + (availability ? "Available" : "Unavailable");
  }
}
