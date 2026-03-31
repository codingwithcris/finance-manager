package src;
import java.util.ArrayList;

class FinanceManager {
    private ArrayList<Transaction> transactions;

    public FinanceManager() {
        transactions = new ArrayList<>();
    } 

    public void addIncome(Transaction income) {
        transactions.add(income);
        System.out.printf(
            "Income added: %s - $%.2f%n", 
            income.getCategory(), 
            income.getAmount()
        );
    }

    public void addExpense(Transaction expense) {
        transactions.add(expense);
        System.out.printf(
            "Expense added: %s - $%.2f%n", 
            expense.getCategory(), 
            expense.getAmount()
        );
    }

    public void clearAllRecords() {
        transactions.clear();
        System.out.println("All records have been cleared");
    }
    
    public void displaySummary() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (Transaction transaction: transactions) {
            if (transaction.getType().equals("Expense")) {
                totalExpenses += transaction.getAmount();
            } else if (transaction.getType().equals("Income")) {
                totalIncome += transaction.getAmount();
            }
        }

        double balance = totalIncome - totalExpenses;

        System.out.println("------ SUMMARY ------");
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Income: $%.2f%n", totalIncome);
        System.out.printf("Expenses: $%.2f%n", totalExpenses);
    }

    public void displayTransactionsByType(String type) {
        boolean transactionFound = false;
        
        System.out.println("----- " + type.toUpperCase() + " -----");
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(type)) {
                transaction.displayInfo();
                transactionFound = true;
            } 
        }

        if (!transactionFound) {
            System.out.println("No " + type.toLowerCase() + " transactions found.");
        }
    }
}