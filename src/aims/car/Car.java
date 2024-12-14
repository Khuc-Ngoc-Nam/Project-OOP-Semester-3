package aims.car;
public class Car {
    private String name;    // Tên xe (Audi R8, Lamborghini urus)
    private String id;      // Biển số xe
    private String brand;   // Hãng xe (Ferrari, Lamborghini)
    private String type;    // Loại xe (Sedan, SUV)
    private int manufactoring_year; // Năm sản xuất
    private float cost;    // Giá thuê
    private static int nbCars = 0;
    private int no;
// Getter, setter
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

    public String getBranch() {
        return brand;
    }

    public void setBranch(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getManufactoring_year() {
        return manufactoring_year;
    }

    public void setManufactoring_year(int manufactoring_year) {
        this.manufactoring_year = manufactoring_year;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getNo() {
        return no;
    }

    public String toString() {
        return String.format("%d %s , %s , %s , %s , %d, %.2f", 
            this.getNo(), 
            this.getName(), 
            this.getId(), 
            this.getBranch(), 
            this.getType(), 
            this.getManufactoring_year(), 
            this.getCost());
    }

    public boolean isMatch(String name) {
        return this.name.equals(name);
    }
    

//Constructor

    public Car(String name) {
        this.name = name;
        nbCars += 1;
        this.no = nbCars;
    }

    public Car(float cost) {
        this.cost = cost;
        nbCars += 1;
        this.no = nbCars;
    }

    public Car(String name, String id, String type, float cost) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.cost = cost;
        nbCars += 1;
        this.no = nbCars;
    }

    public Car(String name, String id, String brand, String type, int manufactoring_year, float cost) {
        this.name = name;
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.manufactoring_year = manufactoring_year;
        this.cost = cost;
        nbCars += 1;
        this.no = nbCars;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Ferrari La Ferrari");
        Car car2 = new Car("Lamborghini Aventador");
        System.out.println(car1.getNo());
        System.out.println(car2.getNo());
    }


}
