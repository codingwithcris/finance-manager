package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class FinanceManager {
    private ArrayList<Transaction> transactions;
    private String FILE_PATH = "data/transactions.txt";

    public FinanceManager() {
        transactions = new ArrayList<>();
    } 

    public void addIncome(Transaction income) {
        transactions.add(income);
        saveTransaction(income);
        System.out.println("Income added successfully.");
    }

    public void addExpense(Transaction expense) {
        transactions.add(expense);
        saveTransaction(expense);
        System.out.println("Expense added successfully.");
    }

    public void clearTransactionsHistory() {
        transactions.clear();
        System.out.println("Transactions history has been cleared.");
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

    public void loadTransactions() {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(FILE_PATH));

            while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] transactionFields = line.split(",");
                    String typeField = transactionFields[0];
                    String categoryField = transactionFields[1];
                    String amountStr = transactionFields[2];
                    double amountField = Double.parseDouble(amountStr);
                    
                    if (typeField.equals("Income")) {
                        transactions.add(new IncomeTransaction(categoryField, amountField));
                    } else if (typeField.equals("Expense")) {
                        transactions.add(new ExpenseTransaction(categoryField, amountField));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + FILE_PATH);
            } finally {
                if (fileScanner != null) {
                    fileScanner.close();
                }
            }
    }

    public void saveTransaction(Transaction transaction) {
        PrintWriter fileWriter = null;

        try {
            fileWriter = new PrintWriter(new FileWriter(FILE_PATH, true));
            fileWriter.printf(
                "%s, %s, %.2f%n",  
                transaction.getType(),
                transaction.getCategory(),
                transaction.getAmount()
            );
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "The amount cannot be negative number or zero."
            );
        }
    }
}