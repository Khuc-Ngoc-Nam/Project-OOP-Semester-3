package aims.cart;

import aims.car.Car;

public class Cart {
    public static final int MAX_CART_SIZE = 5; // Số lượng xe tối đa có thể thêm vào giỏ hàng
    private Car[] carsInCart = new Car[MAX_CART_SIZE]; // Danh sách các xe trong giỏ hàng
    private int carCount = 0; // Số lượng xe hiện tại trong giỏ hàng

    // Lấy số lượng xe trong giỏ hàng
    public int getCarCount() {
        return carCount;
    }

    // Thêm một xe vào giỏ hàng
    public void addCarToCart(Car newCar) {
        if (carCount == MAX_CART_SIZE) {
            System.out.println("The cart is full! Cannot add new car!");
            return;
        }
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] == null) {
                carsInCart[i] = newCar;
                carCount++;
                System.out.printf("The car '%s' has been added to your cart!\n", newCar.getName());
                break;
            }
        }
    }

    // Thêm một danh sách xe vào giỏ hàng
    public void addCarsToCart(Car[] carList) {
        if (carList.length + carCount > MAX_CART_SIZE) {
            System.out.println("Your cart doesn't have enough space for this number of cars!");
            return;
        }
        for (Car car : carList) {
            addCarToCart(car);
        }
    }

    // Thêm hai xe cùng một lúc vào giỏ hàng
    public void addCarsToCart(Car car1, Car car2) {
        if (carCount + 2 > MAX_CART_SIZE) {
            System.out.println("Your cart doesn't have enough space for this number of cars!");
            return;
        }
        addCarToCart(car1);
        addCarToCart(car2);
    }

    // Xóa một xe khỏi giỏ hàng
    public void removeCarFromCart(Car carToRemove) {
        if (carCount == 0) {
            System.out.println("Your cart is empty, cannot remove!");
            return;
        }
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] != null && carsInCart[i].equals(carToRemove)) {
                System.out.printf("The car '%s' has been removed!\n", carsInCart[i].getName());
                carsInCart[i] = null;
                carCount--;
                break;
            }
        }
    }

    // Tính tổng chi phí của tất cả các xe trong giỏ hàng
    public float calculateTotalCost() {
        float totalCost = 0;
        for (Car car : carsInCart) {
            if (car != null) {
                totalCost += car.getRentalPrice();
            }
        }
        return totalCost;
    }

    // Hiển thị thông tin chi tiết của các xe trong giỏ hàng
    public void displayCartDetails() {
        if (carCount == 0) {
            System.out.println("Your cart is empty!");
            return;
        }
        System.out.println("---------- Cars in your cart ----------");
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] != null) {
                System.out.printf("%d. %s\n", i + 1, carsInCart[i]);
            }
        }
        System.out.printf("Total payment: %.2f\n", calculateTotalCost());
    }

    // Tìm kiếm xe theo mã số xe
    public void searchCarById(int carId) {
        for (Car car : carsInCart) {
            if (car != null && car.getCarId() == carId) {
                System.out.printf("Found: %s\n", car);
                return;
            }
        }
        System.out.println("Cannot find this car!");
    }

    // Tìm kiếm xe theo tên
    public void searchCarByName(String name) {
        for (Car car : carsInCart) {
            if (car != null && car.matchesName(name)) {
                System.out.printf("Found: %s\n", car);
                return;
            }
        }
        System.out.println("Cannot find this car!");
    }

    public Car getCarAtIndex(int index) {
        if (index >= 0 && index < carCount) {
            return carsInCart[index];
        }
        return null;
    }
    
    public Car removeCarFromCartByIndex(int index) {
        if (index >= 0 && index < carCount) {
            Car removedCar = carsInCart[index];
            for (int i = index; i < carCount - 1; i++) {
                carsInCart[i] = carsInCart[i + 1];
            }
            carsInCart[--carCount] = null; // Reduce car count and nullify the last slot
            System.out.printf("The car '%s' has been removed.\n", removedCar.getName());
            return removedCar;
        } else {
            System.out.println("Invalid serial number! Try again.");
            return null; // Return null if the index is invalid
        }
    }
    

    // Main để test
    public static void main(String[] args) {
        Car car1 = new Car("Ferrari", "17A-12345", "Ferrari", "SUV", 2022, 500);
        Car car2 = new Car("Lamborghini", "18B-54321", "Lamborghini", "Coupe", 2023, 700);

        Cart cart = new Cart();
        cart.addCarToCart(car1);
        cart.addCarToCart(car2);
        cart.displayCartDetails();

        cart.removeCarFromCart(car1);
        cart.displayCartDetails();

        cart.searchCarByName("Lamborghini");
        cart.searchCarById(2);
    }
}


