package src;

public class Transaction {
    protected String type;
    protected String category;
    protected double amount;

    public Transaction(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public String getCategory() {
        return this.category;
    }

    public double getAmount() {
        return this.amount;
    }

    public void displayInfo() {
        System.out.printf(
            "%s - %s: $%.2f%n", 
            this.type, 
            this.category, 
            this.amount
        );
    }
}

