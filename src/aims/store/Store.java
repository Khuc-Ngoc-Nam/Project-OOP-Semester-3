package aims.store;

import aims.car.Car;
import java.util.ArrayList;

public class Store {
    // Danh sách các xe trong cửa hàng
    private ArrayList<Car> carInventory = new ArrayList<>();

    // Lấy số lượng xe trong cửa hàng
    public int getInventorySize() {
        return carInventory.size();
    }

    // Thêm xe vào cửa hàng
    public void addCar(Car car) {
        if (!carInventory.contains(car)) {
            carInventory.add(car);
            System.out.printf("Xe '%s' đã được thêm vào kho hàng.\n", car.getName());
        } else {
            System.out.println("Xe đã tồn tại trong kho hàng.");
        }
    }

    // Kiểm tra xem một xe có trong cửa hàng không
    public boolean isCarInInventory(Car car) {
        return carInventory.contains(car);
    }

    // Xóa xe khỏi cửa hàng
    public void removeCar(Car car) {
        if (!carInventory.contains(car)) {
            System.out.println("Xe không tồn tại trong kho hàng.");
        } else {
            carInventory.remove(car);
            System.out.printf("Xe '%s' đã được xóa khỏi kho hàng.\n", car.getName());
        }
    }

    // Hiển thị danh sách xe trong cửa hàng
    public void displayCars() {
        if (carInventory.isEmpty()) {
            System.out.println("Hiện tại không có xe nào trong kho hàng.");
            return;
        }

        System.out.println("---------- Danh sách xe trong kho hàng ----------");
        for (int i = 0; i < carInventory.size(); i++) {
            Car car = carInventory.get(i);
            System.out.printf("%d. %s\n", i + 1, car);
        }
    }

    // Cập nhật tình trạng của xe (thuê hoặc trả)
    public void updateCarStatus(int index, boolean isRented) {
        if (index >= 0 && index < carInventory.size()) {
            Car car = carInventory.get(index);
            car.setRented(isRented);
            System.out.printf("Tình trạng xe '%s' đã được cập nhật thành: %s\n",
                    car.getName(),
                    isRented ? "Đã thuê" : "Có sẵn");
        } else {
            System.out.println("Số thứ tự không hợp lệ.");
        }
    }

    // Lấy danh sách tất cả các xe
    public ArrayList<Car> getCarInventory() {
        return carInventory;
    }
}


// package aims.store;

// import aims.car.Car;
// import java.util.ArrayList;

// public class Store {
//     // Sử dụng ArrayList thay vì mảng để quản lý các xe trong kho
//     private ArrayList<Car> carsInStore = new ArrayList<>();

//     // Lấy số lượng xe trong cửa hàng
//     public int getQty() {
//         return carsInStore.size();
//     }

//     // Thêm xe vào cửa hàng
//     public void addCarToStore(Car car) {
//         if (!carsInStore.contains(car)) {
//             carsInStore.add(car);
//         } else {
//             System.out.println("Xe đã có trong cửa hàng.");
//         }
//     }

//     // Kiểm tra xem một xe có trong cửa hàng không
//     public boolean checkCar(Car car) {
//         return carsInStore.contains(car);
//     }

//     // Xóa xe khỏi cửa hàng
//     public void removeCarInStore(Car car) {
//         if (!carsInStore.contains(car)) {
//             System.out.println("Sorry, the car is not in the store");
//         } else {
//             carsInStore.remove(car);
//             System.out.println("Xe đã được xóa khỏi cửa hàng.");
//         }
//     }

//     // Hiển thị danh sách xe trong cửa hàng
//     public void displayAvailableCars() {
//         if (carsInStore.isEmpty()) {
//             System.out.println("Hiện tại không có xe nào trong cửa hàng.");
//             return;
//         }

//         System.out.println("---------- Danh sách xe trong cửa hàng ----------");
//         for (int i = 0; i < carsInStore.size(); i++) {
//             Car car = carsInStore.get(i);
//             System.out.printf("%d. Tên xe: %s | Giá: %.2f | Tình trạng: %s\n",
//                     i + 1,
//                     car.getName(),
//                     car.getCost(),
//                     car.isRented() ? "Đã thuê" : "Có sẵn"
//             );
//         }
//     }

//     // Thay đổi tình trạng của xe
//     public void setCarStatus(int index, boolean isRented) {
//         if (index >= 0 && index < carsInStore.size()) {
//             Car car = carsInStore.get(index);
//             car.setRented(isRented);
//             System.out.printf("Tình trạng xe '%s' đã được cập nhật thành: %s\n",
//                     car.getName(),
//                     isRented ? "Đã thuê" : "Có sẵn");
//         } else {
//             System.out.println("Số thứ tự không hợp lệ.");
//         }
//     }

//     // Lấy danh sách xe
//     public ArrayList<Car> getCarsInStore() {
//         return carsInStore;
//     }
    
//     public static void main(String[] args) {
//         Car car1 = new Car(20);
//         Car car2 = new Car(20);
    
//         Store store = new Store();
//         store.addCarToStore(car1);
//         store.addCarToStore(car2);
    
//         System.out.println("Số lượng xe ban đầu: " + store.getQty());
    
//         store.displayAvailableCars();
    
//         store.removeCarInStore(car1);
//         System.out.println("Số lượng xe sau khi xóa: " + store.getQty());
    
//         store.displayAvailableCars();
//     }
// }
   
//     private Car[] itemInStore = {};
//     public int getQty() {
//         return this.itemInStore.length;
//     }
//     public void addCarToStore(Car new_car) {
//         int n = this.itemInStore.length;
//         Car newarr[] = new Car[n + 1];
//         for (int i = 0; i < n; i++) {
//             newarr[i] = this.itemInStore[i];
//         }
//         newarr[n] = new_car;
//         this.itemInStore = newarr;
//     }

//     public boolean checkCar(Car vehicle) {
//         boolean flag = false;
//         for (Car xe: this.itemInStore) {
//             if (xe == vehicle) {
//                 flag = true;
//                 break;
//             }
//         }
//         return flag;

//     }
//     public void removeCarInStore(Car vehicle) {
//         if (checkCar(vehicle) == false) {
//             System.out.println("Sorry, the car is not in the store");
//         } else {
//             int n = this.itemInStore.length;
//             Car newarr[] = new Car[n - 1];
//             int j = 0; // Chỉ số cho mảng mới
//             for (int i = 0; i < n; i++) {
//                 if (this.itemInStore[i] != vehicle) {
//                     newarr[j] = this.itemInStore[i];
//                     j++;
//                 }
//             }
//             this.itemInStore = newarr;
//         }
//     }

//     private ArrayList<Car> carsInStore = new ArrayList<>();

//     // Thêm xe vào kho
//     public void addCarToStore2(Car car) {
//         carsInStore.add(car);
//     }

//     // Hiển thị danh sách xe trong cửa hàng
//     public void displayAvailableCars() {
//         if (carsInStore.isEmpty()) {
//             System.out.println("Hiện tại không có xe nào trong cửa hàng.");
//             return;
//         }

//         System.out.println("---------- Danh sách xe trong cửa hàng ----------");
//         for (int i = 0; i < carsInStore.size(); i++) {
//             Car car = carsInStore.get(i);
//             System.out.printf("%d. Tên xe: %s | Giá: %.2f | Tình trạng: %s\n",
//                     i + 1,
//                     car.getName(),
//                     car.getCost(),
//                     car.isRented() ? "Đã thuê" : "Có sẵn"
//             );
//         }
//     }

//     // Thay đổi tình trạng của xe
//     public void setCarStatus(int index, boolean isRented) {
//         if (index >= 0 && index < carsInStore.size()) {
//             Car car = carsInStore.get(index);
//             car.setRented(isRented);
//             System.out.printf("Tình trạng xe '%s' đã được cập nhật thành: %s\n",
//                     car.getName(),
//                     isRented ? "Đã thuê" : "Có sẵn");
//         } else {
//             System.out.println("Số thứ tự không hợp lệ.");
//         }
//     }

//     // Lấy danh sách xe
//     public ArrayList<Car> getCarsInStore() {
//         return carsInStore;
//     }
    
//     public static void main(String[] args) {
//         Car car1 = new Car(20);
//         Car car2 = new Car(20);

//         Store store = new Store();
//         store.addCarToStore(car1);
//         store.addCarToStore(car2);

//         System.out.println(store.getQty());

//         store.removeCarInStore(car1);
//         System.out.println(store.getQty());
//     }

// }
