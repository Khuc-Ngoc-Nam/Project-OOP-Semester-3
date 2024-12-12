public class Car {
    private String name;    // Tên xe (Audi R8, Lamborghini urus)
    private String id;      // Biển số xe
    private String brand;   // Hãng xe (Ferrari, Lamborghini)
    private String type;    // Loại xe (Sedan, SUV)
    private int manufactoring_year; // Năm sản xuất
    private float cost;    // Giá thuê
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

    public void setID(String id) {
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

//Constructor

    public Car(String name) {
        this.name = name;
    }

    public Car(float cost) {
        this.cost = cost;
    }

    public Car(String name, String id, String type, float cost) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.cost = cost;
    }

    public Car(String name, String id, String brand, String type, int manufactoring_year, float cost) {
        this.name = name;
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.manufactoring_year = manufactoring_year;
        this.cost = cost;
    }


}
