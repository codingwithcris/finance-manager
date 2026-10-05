package src;

public class Transaction {
    private String type;
    private double amount;
    private String category;
    private String description;

    public Transaction(
        double amount,
        String type, 
        String category, 
        String description
    ) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getType() { return this.type; }
    public String getCategory() { return this.category; }
    public double getAmount() { return this.amount; }
    public String getDescription() { return this.description; }
}