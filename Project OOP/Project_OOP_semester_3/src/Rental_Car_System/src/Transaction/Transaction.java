package Rental_Car_System.src.Transaction;

import Rental_Car_System.src.Car.Car;
import Rental_Car_System.src.Customer.Customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

public class Transaction {

    private static final Map<String, Integer> MONTH_MAP = Map.ofEntries(
    Map.entry("Jan", 1),
    Map.entry("Feb", 2),
    Map.entry("Mar", 3),
    Map.entry("Apr", 4),
    Map.entry("May", 5),
    Map.entry("Jun", 6),
    Map.entry("Jul", 7),
    Map.entry("Aug", 8),
    Map.entry("Sep", 9),
    Map.entry("Oct", 10),
    Map.entry("Nov", 11),
    Map.entry("Dec", 12)
);

    private Customer customer;
    private Car car;

    private LocalDate currentDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private LocalDate birthDate;

    public Transaction() {
        this.customer = new Customer();
        this.car = new Car();
        this.currentDate = LocalDate.now();
        this.pickupDate = null;
        this.returnDate = null;
        this.birthDate = null;
    }

    public void setTransaction(Customer customer, Car car, LocalDate currentDate,
                                LocalDate pickupDate, LocalDate returnDate, LocalDate birthDate) {
        this.customer = customer;
        this.car = car;
        this.currentDate = currentDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.birthDate = birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, currentDate).getYears();
    }

    public int getRentalDays() {
        return Period.between(pickupDate, returnDate).getDays();
    }

    public double getRentalPrice() {
        return getRentalDays() * car.getCarPrice();
    }

    public String validateDates() {
        if (pickupDate.isAfter(returnDate)) {
            return "FALSE: Pickup date is after return date.";
        }
        if (pickupDate.isBefore(currentDate)) {
            return "FALSE: Pickup date is in the past.";
        }
        return "TRUE";
    }

    public String validateAge() {
        return getAge() >= 20 ? "TRUE" : "FALSE: Customer is under 20 years old.";
    }

    public String toStringMrMrs() {
        return customer.getGender().equalsIgnoreCase("M") ? "Mr." : "Ms.";
    }

    public String toStringReceipt() {
        return String.format(
            "                Car Rental\n" +
            "             RENTAL TRANSACTION\n" +
            "-------------------------------------------\n" +
            "Customer: %s %s\n" +
            "Car: %s\n" +
            "Rental Days: %d\n" +
            "Rental Price Per Day: %.2f\n" +
            "Total Payment: %.2f\n",
            toStringMrMrs(), customer.getFullName(), car.getCar(),
            getRentalDays(), car.getCarPrice(), getRentalPrice()
        );
    }

    // Helper method to convert month names to numerical values (if needed)
    private static int getMonthValue(String month) {
        return MONTH_MAP.getOrDefault(month, -1);
    }
}
