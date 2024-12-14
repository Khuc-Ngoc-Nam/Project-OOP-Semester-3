package aims.cart;

import aims.car.Car;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 5;   //Biến tĩnh cho thấy lượng xe nhiều nhất có thể thêm vào giỏ hàng cho thuê
    private Car itemsOrdered[] = new Car[MAX_NUMBERS_ORDERED];  // Những chiếc xe được thêm vào
    private int qtyOrdered;   // Số lượng xe mà người dùng đang cân nhắc

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    // Thêm xe vào giỏ hàng
    public void addCar(Car xe_moi) {
        if (this.qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("Sorry, your cart is full");
        }
        else {
            for (int i = 0; i < MAX_NUMBERS_ORDERED; i++) {
                if (this.itemsOrdered[i] == null) {
                    this.itemsOrdered[i] = xe_moi;
                    this.qtyOrdered += 1;
                    break;
                }
            }
        }
    }

    //Thêm 1 list xe
    public void addCar(Car[] carList) {
        if (carList.length + this.getQtyOrdered() > MAX_NUMBERS_ORDERED) {
            System.out.println("Sorry, your cart does not have enough space");
        }
        for (Car i: carList) {
            addCar(i);      // Gọi phương thức thêm 1 car bên trên.
        }
    }

    // Thêm 1 lúc 2 xe
    public  void  addCar(Car xe1, Car xe2) {
        if (this.getQtyOrdered() + 2 > MAX_NUMBERS_ORDERED) {
            System.out.println("Sorry, your cart does not have enough space");
        }
        addCar(xe1);
        addCar(xe2);
    }

    // Xóa đi chiếc xe
    public void removeCar(Car xoa_xe_khoi_gio) {
        if (this.qtyOrdered == 0) {
            System.out.println("Your cart is empty");
        }
        else {
            int i;
            for (i = 0; i < MAX_NUMBERS_ORDERED; i++) {
                if (this.itemsOrdered[i].equals(xoa_xe_khoi_gio)) {
                    this.itemsOrdered[i] = null;
                    this.qtyOrdered -= 1;
                    break;
                }
            }
        }
    } 

    // Tính tổng tiền (Sẽ update lên dựa theo số ngày/số giờ thuê sau)
    public float totalCost() {
        float res = 0;
        for (Car itemsOrdered1 : this.itemsOrdered) {
            if (itemsOrdered1 != null) {
                res += itemsOrdered1.getCost();
            }
        }
        return res;
    }

    public boolean checkremove() {
        int oldqty = this.qtyOrdered;
        Car car1 = new Car(20);
        this.addCar(car1);
        int newqty = this.qtyOrdered;
        return (newqty - oldqty == 1);
    }

    public void print() {
        float totalCost = 0;
        for (Car vehicle: this.itemsOrdered) {
            if (vehicle == null) {continue;}
            System.out.println(vehicle.toString());
            totalCost += vehicle.getCost();
        }
        System.out.printf("The total cost is: %f\n", totalCost);
    }

    public void searchCar_id(int no) {
        // Tìm kiếm Car theo số thứ tự
        boolean flag = false;
        Car found = null;
        for (Car veh : this.itemsOrdered) {
            if (veh == null) {continue;}
            if (veh.getNo() == no) {
                flag = true;
                found = veh;
                break;
            }
        }
        if (flag) {
            System.out.println(found.toString());
        }
        else {
            System.out.println("Sorry, none of disks have the N.o you want tho search");
        }
    }

    public void searchCar_name(String name) {
        boolean flag = false;
        Car found = null;
        for (Car veh : this.itemsOrdered) {
            if(veh == null){continue;}
            if (veh.isMatch(name)) {
                flag = true;
                found = veh;
                break;
            }
        }
        if (flag) {
            System.out.println(found.toString());
        }
        else {
            System.out.println("Sorry, none of disks have the N.o you want tho search");
        }
    }
    
    public static void main(String[] args) {
        Car item1 = new Car(35);
        Car item2 = new Car(40);
    
        Cart cart = new Cart();
        Car[] carList = new Car[]{item1, item2};
        cart.addCar(carList);
        cart.searchCar_id(2);
        System.out.println(item1.getNo());
    }
}


