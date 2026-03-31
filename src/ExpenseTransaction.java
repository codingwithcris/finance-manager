package src;

public class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(String category, double amount) {
        super("Expense", category, amount);
    }

    @Override
    public void displayInfo() {
        System.out.printf(
            "Expense - %s: $%.2f%n",  
            this.category, 
            this.amount
        );
    }
}
