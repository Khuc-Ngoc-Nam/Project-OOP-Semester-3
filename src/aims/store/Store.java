package aims.store;
import aims.car.Car;

public class Store {
    private Car[] itemInStore = {};
    public int getQty() {
        return this.itemInStore.length;
    }
    public void addCarToStore(Car new_car) {
        int n = this.itemInStore.length;
        Car newarr[] = new Car[n + 1];
        for (int i = 0; i < n; i++) {
            newarr[i] = this.itemInStore[i];
        }
        newarr[n] = new_car;
        this.itemInStore = newarr;
    }

    public boolean checkCar(Car vehicle) {
        boolean flag = false;
        for (Car xe: this.itemInStore) {
            if (xe == vehicle) {
                flag = true;
                break;
            }
        }
        return flag;

    }
    public void removeCarInStore(Car vehicle) {
        if (checkCar(vehicle) == false) {
            System.out.println("Sorry, the car is not in the store");
        } else {
            int n = this.itemInStore.length;
            Car newarr[] = new Car[n - 1];
            int j = 0; // Chỉ số cho mảng mới
            for (int i = 0; i < n; i++) {
                if (this.itemInStore[i] != vehicle) {
                    newarr[j] = this.itemInStore[i];
                    j++;
                }
            }
            this.itemInStore = newarr;
        }
    }
    
    public static void main(String[] args) {
        Car car1 = new Car(20);
        Car car2 = new Car(20);

        Store store = new Store();
        store.addCarToStore(car1);
        store.addCarToStore(car2);

        System.out.println(store.getQty());

        store.removeCarInStore(car1);
        System.out.println(store.getQty());
    }

}
