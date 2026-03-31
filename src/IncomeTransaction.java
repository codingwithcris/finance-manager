package src;

public class IncomeTransaction extends Transaction {
    public IncomeTransaction(String category, double amount) {
        super("Income", category, amount);
    }

    @Override     
    public void displayInfo() {
        System.out.printf(
            "Income - %s: $%.2f%n",  
            this.category, 
            this.amount
        );
    }
}
