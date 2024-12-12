public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();
        Car car1 = new Car("Land Cruiser LC300", "17A-56158", "Toyota", "SUV", 2023, 499);
        anOrder.addCar(car1);
        Car car2 = new Car("Lexus LX600", "18B-28565", "Toyota", "SUV", 2023, 799);
        anOrder.addCar(car2);
        Car car3 = new Car("Maybach GLS680", "17A-56159", "Mercedes", "SUV", 2023, 989);
        anOrder.addCar(car3);
        Car car4 = new Car("BMW Z4", "17A-08090", "BMW", "Couple", 2023, 399);
        anOrder.addCar(car4);

        System.out.println("Total Cost is: ");
        System.out.println(anOrder.totalCost());
        System.out.println(anOrder.getQtyOrdered());

        anOrder.print();

        anOrder.removeCar(car3);
        System.out.println(anOrder.getQtyOrdered());

        anOrder.print();
    }
}
