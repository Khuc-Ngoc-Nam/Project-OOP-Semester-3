package aims.car;

public class Car {
    private String name;                // Tên xe (ví dụ: Audi R8, Lamborghini Urus)
    private String licensePlate;        // Biển số xe
    private String brand;               // Hãng xe (ví dụ: Ferrari, Lamborghini)
    private String type;                // Loại xe (ví dụ: Sedan, SUV)
    private int manufacturingYear;      // Năm sản xuất
    private float rentalPrice;          // Giá thuê xe
    private static int carCounter = 0;  // Bộ đếm xe để tự động tạo mã số xe
    private int carId;                  // Mã số xe duy nhất
    private boolean rented;             // Tình trạng xe đã thuê hay chưa

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getCarId() {
        return carId;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID: %d | Name: %s | License Plate: %s | Brand: %s | Type: %s | Year: %d | Rental Price: %.2f | Status: %s",
            this.getCarId(),
            this.getName(),
            this.getLicensePlate(),
            this.getBrand(),
            this.getType(),
            this.getManufacturingYear(),
            this.getRentalPrice(),
            this.isRented() ? "Rented" : "Available"
        );
    }

    public boolean matchesName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    // Constructors
    public Car(String name) {
        this.name = name;
        this.carId = ++carCounter;
    }

    public Car(float rentalPrice) {
        this.rentalPrice = rentalPrice;
        this.carId = ++carCounter;
    }

    public Car(String name, String licensePlate, String type, float rentalPrice) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.type = type;
        this.rentalPrice = rentalPrice;
        this.carId = ++carCounter;
    }

    public Car(String name, String licensePlate, String brand, String type, int manufacturingYear, float rentalPrice) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.rentalPrice = rentalPrice;
        this.carId = ++carCounter;
    }

    // Main để test
    public static void main(String[] args) {
        Car car1 = new Car("Ferrari LaFerrari");
        Car car2 = new Car("Lamborghini Aventador");
        System.out.println(car1);
        System.out.println(car2);
    }
}


// package aims.car;
// public class Car {
//     private String name;    // Tên xe (Audi R8, Lamborghini urus)
//     private String id;      // Biển số xe
//     private String brand;   // Hãng xe (Ferrari, Lamborghini)
//     private String type;    // Loại xe (Sedan, SUV)
//     private int manufactoring_year; // Năm sản xuất
//     private float cost;    // Giá thuê
//     private static int nbCars = 0;
//     private int no;
//     private boolean isRented;
// // Getter, setter
//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getBranch() {
//         return brand;
//     }

//     public void setBranch(String brand) {
//         this.brand = brand;
//     }

//     public String getType() {
//         return type;
//     }

//     public void setType(String type) {
//         this.type = type;
//     }

//     public int getManufactoring_year() {
//         return manufactoring_year;
//     }

//     public void setManufactoring_year(int manufactoring_year) {
//         this.manufactoring_year = manufactoring_year;
//     }

//     public float getCost() {
//         return cost;
//     }

//     public void setCost(float cost) {
//         this.cost = cost;
//     }

//     public int getNo() {
//         return no;
//     }
//     public boolean isRented() {
//         return isRented;
//     }

//     public void setRented(boolean rented) {
//         isRented = rented;
//     }

//     public String toString() {
//         return String.format(
//             "No: %d, Name: %s, Id: %s, Brand: %s, Type: %s, Manufacturing Year: %d, Cost: %.2f, Status: %s",
//             this.getNo(),
//             this.getName(),
//             this.getId(),
//             this.getBranch(),
//             this.getType(),
//             this.getManufactoring_year(),
//             this.getCost(),
//             this.isRented() ? "Đã thuê" : "Có sẵn" // Kiểm tra trạng thái thuê
//         );
//     }

//     public boolean isMatch(String name) {
//         return this.name.equals(name);
//     }

    
    

// //Constructor

//     public Car(String name) {
//         this.name = name;
//         nbCars += 1;
//         this.no = nbCars;
//     }

//     public Car(float cost) {
//         this.cost = cost;
//         nbCars += 1;
//         this.no = nbCars;
//     }

//     public Car(String name, String id, String type, float cost) {
//         this.name = name;
//         this.id = id;
//         this.type = type;
//         this.cost = cost;
//         nbCars += 1;
//         this.no = nbCars;
//     }

//     public Car(String name, String id, String brand, String type, int manufactoring_year, float cost) {
//         this.name = name;
//         this.id = id;
//         this.brand = brand;
//         this.type = type;
//         this.manufactoring_year = manufactoring_year;
//         this.cost = cost;
//         nbCars += 1;
//         this.no = nbCars;
//     }

//     public 

//     public static void main(String[] args) {
//         Car car1 = new Car("Ferrari La Ferrari");
//         Car car2 = new Car("Lamborghini Aventador");
//         System.out.println(car1.getNo());
//         System.out.println(car2.getNo());
//     }


// }


