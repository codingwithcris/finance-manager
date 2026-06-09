package src;

public class Transaction {
    protected String type;
    protected Double amount;
    protected String category;
    protected String description;

    public Transaction(
        Double amount,
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

    public Double getAmount() { return this.amount; }

    public String getDescription() { return this.description; }
}