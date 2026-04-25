package src;

public class IncomeTransaction extends Transaction {
    public IncomeTransaction(String category, double amount) {
        super("Income", category, amount);
    }

    @Override     
    public void displayInfo() {
        System.out.printf(
            "%s - %s: $%.2f%n",  
            getType(),
            getCategory(),
            getAmount()
        );
    }
}
