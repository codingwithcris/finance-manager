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

    public void clearAllTransactions() {
        transactions.clear();
        System.out.println("All transactions have been cleared.");
    }
    
    public void displaySummary() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (Transaction transaction: transactions) {
            if (transaction instanceof ExpenseTransaction) {
                totalExpenses += transaction.getAmount();
            } else if (transaction instanceof IncomeTransaction) {
                totalIncome += transaction.getAmount();
            }
        }

        double balance = totalIncome - totalExpenses;

        System.out.println("====== SUMMARY ======");
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Income: $%.2f%n", totalIncome);
        System.out.printf("Expenses: $%.2f%n", totalExpenses);
    }

    public void displayIncomeCategoryTotal() {
        double salaryAmount = 0;
        double giftsAmount = 0;
        double scholarshipsAmount = 0;
        double freelanceAmount = 0;
        double otherAmount = 0;
        boolean incomeCategoryFound = false;

        for (Transaction transaction : transactions) {
            if (transaction instanceof IncomeTransaction) {
                String category = transaction.getCategory();
                double amount = transaction.getAmount();
                incomeCategoryFound = true;

                if (category.equals("Salary")) {
                    salaryAmount += amount;
                } else if (category.equals("Gift")) {
                    giftsAmount += amount;
                } else if (category.equals("Scholarship")) {
                    scholarshipsAmount += amount;
                } else if (category.equals("Freelance")) {
                    freelanceAmount += amount;
                } else {
                    otherAmount += amount;
                }
            }
        }

        System.out.println("====== INCOME CATEGORY TOTALS ======");
        if (salaryAmount > 0) {
            System.out.printf("Salary: $%.2f%n", salaryAmount);
        }

        if (giftsAmount > 0) {
            System.out.printf("Gifts: $%.2f%n", giftsAmount);
        }

        if (scholarshipsAmount > 0) {
            System.out.printf("Scholarships: $%.2f%n", scholarshipsAmount);
        }

        if (freelanceAmount > 0) {
            System.out.printf("Freelance: $%.2f%n", freelanceAmount);
        }

        if (otherAmount > 0) {
            System.out.printf("Other: $%.2f%n", otherAmount);
        }

        if (!incomeCategoryFound) {
            System.out.println("No income category transactions found.");
        }
    }

    public void displayExpenseCategoryTotal() {
        double rentAmount = 0;
        double foodAmount = 0;
        double entertainmentAmount = 0;
        double utilitiesAmount = 0;
        double otherAmount = 0;
        boolean expenseCategoryFound = false;

        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction) {
                String category = transaction.getCategory();
                double amount = transaction.getAmount();
                expenseCategoryFound = true;

                if (category.equals("Rent")) {
                    rentAmount += amount;
                } else if (category.equals("Food")) {
                    foodAmount += amount;
                } else if (category.equals("Entertainment")) {
                    entertainmentAmount += amount;
                } else if (category.equals("Utilities")) {
                    utilitiesAmount += amount;
                } else {
                    otherAmount += amount;
                }
            }
        }

        System.out.println("====== EXPENSE CATEGORY TOTALS ======");
        if (rentAmount > 0) {
            System.out.printf("Rent: $%.2f%n", rentAmount);
        }

        if (foodAmount > 0) {
            System.out.printf("Food: $%.2f%n", foodAmount);
        }

        if (entertainmentAmount > 0) {
            System.out.printf("Entertainment: $%.2f%n", entertainmentAmount);
        }

        if (utilitiesAmount > 0) {
            System.out.printf("Utilities: $%.2f%n", utilitiesAmount);
        }

        if (otherAmount > 0) {
            System.out.printf("Other: $%.2f%n", otherAmount);
        }

        if (!expenseCategoryFound) {
            System.out.println("No expense category transactions found.");
        }
    } 
}