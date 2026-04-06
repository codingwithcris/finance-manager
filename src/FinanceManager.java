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
        double totalSalary = 0;
        double totalGifts = 0;
        double totalScholarships = 0;
        double totalFreelance = 0;
        double totalOther = 0;


        for (Transaction transaction : transactions) {
            if (transaction instanceof IncomeTransaction) {
                String category = transaction.getCategory();
                double amount = transaction.getAmount();

                if (category.equals("Salary")) {
                    totalSalary += amount;
                } else if (category.equals("Gift")) {
                    totalGifts += amount;
                } else if (category.equals("Scholarship")) {
                    totalScholarships += amount;
                } else if (category.equals("Freelance")) {
                    totalFreelance += amount;
                } else {
                    totalOther += amount;
                }
            }
        }

        if (totalSalary > 0) {
            System.out.println("Total Salary: $" + totalSalary);
        }

        if (totalGifts > 0) {
            System.out.println("Total Gifts: $" + totalGifts);
        }

        if (totalScholarships > 0) {
            System.out.println("Total Scholarships: $" + totalScholarships);
        }

        if (totalFreelance > 0) {
            System.out.println("Total Freelance: $" + totalFreelance);
        }

        if (totalOther > 0) {
            System.out.println("Total Other: $" + totalOther);
        }
    }

    public void displayExpenseCategoryTotal() {
        double totalRent = 0;
        double totalFood = 0;
        double totalEntertainment = 0;
        double totalUtilities = 0;
        double totalOther = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction) {
                String category = transaction.getCategory();
                double amount = transaction.getAmount();

                if (category.equals("Rent")) {
                    totalRent += amount;
                } else if (category.equals("Food")) {
                    totalFood += amount;
                } else if (category.equals("Entertainment")) {
                    totalEntertainment += amount;
                } else if (category.equals("Utilities")) {
                    totalUtilities += amount;
                } else {
                    totalOther += amount;
                }
            }
        }

        if (totalRent > 0) {
            System.out.println("Total Rent: $" + totalRent);
        }

        if (totalFood > 0) {
            System.out.println("Total Food: $" + totalFood);
        }

        if (totalEntertainment > 0) {
            System.out.println("Total Entertainment: $" + totalEntertainment);
        }

        if (totalUtilities > 0) {
            System.out.println("Total Utilities: $" + totalUtilities);
        }

        if (totalOther > 0) {
            System.out.println("Total Other: $" + totalOther);
        }
    } 
}