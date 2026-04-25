package src;

public class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(String category, double amount) {
        super("Expense", category, amount);
    }
  
    public void displayInfo() {
        System.out.printf(
            "%s - %s: $%.2f%n",  
            getType(),
            getCategory(),
            getAmount()
        );
    }
}
