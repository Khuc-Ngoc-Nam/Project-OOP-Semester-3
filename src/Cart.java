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

    public static void main(String[] args) {
        Car item1 = new Car(35);
        Cart cart = new Cart();
        cart.addCar(item1);
        System.out.println(cart.totalCost());
        System.out.println(cart.checkremove());
    }

    public void print() {
        System.out.println("List of Cars in the cart:");
        for (int i = 0; i < MAX_NUMBERS_ORDERED; i++) {
            if (itemsOrdered[i] != null) {
                System.out.println("Car " + (i + 1) + ": " + itemsOrdered[i].getName() +
                    " - Brand: " + itemsOrdered[i].getBranch() + 
                    " - Type: " + itemsOrdered[i].getType() +
                    " - Year of Manufacture: " + itemsOrdered[i].getManufactoring_year() +
                    " - Cost: " + itemsOrdered[i].getCost());
            }
        }
        System.out.println("Total items in cart: " + qtyOrdered);
    }
    
}
