package src.Transaction;

public class Transaction {
    private double amount;
    private String details;

    public Transaction(double amount, String details) {
        this.amount = amount;
        this.details = details;
    }

    public double getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }
}

