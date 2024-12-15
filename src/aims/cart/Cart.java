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
            System.out.println("Giỏ hàng đã đầy, không thể thêm xe mới.");
            return;
        }
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] == null) {
                carsInCart[i] = newCar;
                carCount++;
                System.out.printf("Xe '%s' đã được thêm vào giỏ hàng.\n", newCar.getName());
                break;
            }
        }
    }

    // Thêm một danh sách xe vào giỏ hàng
    public void addCarsToCart(Car[] carList) {
        if (carList.length + carCount > MAX_CART_SIZE) {
            System.out.println("Giỏ hàng không đủ chỗ để thêm tất cả các xe.");
            return;
        }
        for (Car car : carList) {
            addCarToCart(car);
        }
    }

    // Thêm hai xe cùng một lúc vào giỏ hàng
    public void addCarsToCart(Car car1, Car car2) {
        if (carCount + 2 > MAX_CART_SIZE) {
            System.out.println("Giỏ hàng không đủ chỗ để thêm hai xe.");
            return;
        }
        addCarToCart(car1);
        addCarToCart(car2);
    }

    // Xóa một xe khỏi giỏ hàng
    public void removeCarFromCart(Car carToRemove) {
        if (carCount == 0) {
            System.out.println("Giỏ hàng trống, không có xe để xóa.");
            return;
        }
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] != null && carsInCart[i].equals(carToRemove)) {
                System.out.printf("Xe '%s' đã được xóa khỏi giỏ hàng.\n", carsInCart[i].getName());
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
            System.out.println("Giỏ hàng hiện tại đang trống.");
            return;
        }
        System.out.println("---------- Danh sách xe trong giỏ hàng ----------");
        for (int i = 0; i < MAX_CART_SIZE; i++) {
            if (carsInCart[i] != null) {
                System.out.printf("%d. %s\n", i + 1, carsInCart[i]);
            }
        }
        System.out.printf("Tổng chi phí: %.2f\n", calculateTotalCost());
    }

    // Tìm kiếm xe theo mã số xe
    public void searchCarById(int carId) {
        for (Car car : carsInCart) {
            if (car != null && car.getCarId() == carId) {
                System.out.printf("Tìm thấy xe: %s\n", car);
                return;
            }
        }
        System.out.println("Không tìm thấy xe với mã số yêu cầu.");
    }

    // Tìm kiếm xe theo tên
    public void searchCarByName(String name) {
        for (Car car : carsInCart) {
            if (car != null && car.matchesName(name)) {
                System.out.printf("Tìm thấy xe: %s\n", car);
                return;
            }
        }
        System.out.println("Không tìm thấy xe với tên yêu cầu.");
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


// package aims.cart;

// import aims.car.Car;

// public class Cart {
//     public static final int MAX_NUMBERS_ORDERED = 5;   //Biến tĩnh cho thấy lượng xe nhiều nhất có thể thêm vào giỏ hàng cho thuê
//     private Car itemsOrdered[] = new Car[MAX_NUMBERS_ORDERED];  // Những chiếc xe được thêm vào
//     private int qtyOrdered;   // Số lượng xe mà người dùng đang cân nhắc

//     public int getQtyOrdered() {
//         return qtyOrdered;
//     }

//     // Thêm xe vào giỏ hàng
//     public void addCar(Car xe_moi) {
//         if (this.qtyOrdered == MAX_NUMBERS_ORDERED) {
//             System.out.println("Sorry, your cart is full");
//         }
//         else {
//             for (int i = 0; i < MAX_NUMBERS_ORDERED; i++) {
//                 if (this.itemsOrdered[i] == null) {
//                     this.itemsOrdered[i] = xe_moi;
//                     this.qtyOrdered += 1;
//                     break;
//                 }
//             }
//         }
//     }

//     //Thêm 1 list xe
//     public void addCar(Car[] carList) {
//         if (carList.length + this.getQtyOrdered() > MAX_NUMBERS_ORDERED) {
//             System.out.println("Sorry, your cart does not have enough space");
//         }
//         for (Car i: carList) {
//             addCar(i);      // Gọi phương thức thêm 1 car bên trên.
//         }
//     }

//     // Thêm 1 lúc 2 xe
//     public  void  addCar(Car xe1, Car xe2) {
//         if (this.getQtyOrdered() + 2 > MAX_NUMBERS_ORDERED) {
//             System.out.println("Sorry, your cart does not have enough space");
//         }
//         addCar(xe1);
//         addCar(xe2);
//     }

//     // Xóa đi chiếc xe
//     public void removeCar(Car xoa_xe_khoi_gio) {
//         if (this.qtyOrdered == 0) {
//             System.out.println("Your cart is empty");
//         }
//         else {
//             int i;
//             for (i = 0; i < MAX_NUMBERS_ORDERED; i++) {
//                 if (this.itemsOrdered[i].equals(xoa_xe_khoi_gio)) {
//                     this.itemsOrdered[i] = null;
//                     this.qtyOrdered -= 1;
//                     break;
//                 }
//             }
//         }
//     } 

//     // Tính tổng tiền (Sẽ update lên dựa theo số ngày/số giờ thuê sau)
//     public float totalCost() {
//         float res = 0;
//         for (Car itemsOrdered1 : this.itemsOrdered) {
//             if (itemsOrdered1 != null) {
//                 res += itemsOrdered1.getCost();
//             }
//         }
//         return res;
//     }

//     public boolean checkremove() {
//         int oldqty = this.qtyOrdered;
//         Car car1 = new Car(20);
//         this.addCar(car1);
//         int newqty = this.qtyOrdered;
//         return (newqty - oldqty == 1);
//     }

//     public void print() {
//         float totalCost = 0;
//         for (Car vehicle: this.itemsOrdered) {
//             if (vehicle == null) {continue;}
//             System.out.println(vehicle.toString());
//             totalCost += vehicle.getCost();
//         }
//         System.out.printf("The total cost is: %f\n", totalCost);
//     }

//     public void searchCar_id(int no) {
//         // Tìm kiếm Car theo số thứ tự
//         boolean flag = false;
//         Car found = null;
//         for (Car veh : this.itemsOrdered) {
//             if (veh == null) {continue;}
//             if (veh.getNo() == no) {
//                 flag = true;
//                 found = veh;
//                 break;
//             }
//         }
//         if (flag) {
//             System.out.println(found.toString());
//         }
//         else {
//             System.out.println("Sorry, none of disks have the N.o you want tho search");
//         }
//     }

//     public void searchCar_name(String name) {
//         boolean flag = false;
//         Car found = null;
//         for (Car veh : this.itemsOrdered) {
//             if(veh == null){continue;}
//             if (veh.isMatch(name)) {
//                 flag = true;
//                 found = veh;
//                 break;
//             }
//         }
//         if (flag) {
//             System.out.println(found.toString());
//         }
//         else {
//             System.out.println("Sorry, none of disks have the N.o you want tho search");
//         }
//     }
    
//     public static void main(String[] args) {
//         Car item1 = new Car(35);
//         Car item2 = new Car(40);
    
//         Cart cart = new Cart();
//         Car[] carList = new Car[]{item1, item2};
//         cart.addCar(carList);
//         cart.searchCar_id(2);
//         System.out.println(item1.getNo());
//     }
// }


