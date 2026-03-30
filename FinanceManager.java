import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    protected String type;
    protected String category;
    protected double amount;

    public Transaction() {
        this.type = "unknown";
        this.category = "unknown";
        this.amount = 0;
    }

    public Transaction(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public String getCategory() {
        return this.category;
    }

    public double getAmount() {
        return this.amount;
    }

    public void displayInfo() {
        System.out.printf(
            "%s - %s: $%.2f%n", 
            this.type, 
            this.category, 
            this.amount
        );
    }
}

class IncomeTransaction extends Transaction {
    public IncomeTransaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public void displayInfo() {
        System.out.printf(
            "Income - %s: $%.2f%n",  
            this.category, 
            this.amount
        );
    }
}

class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public void displayInfo() {
        System.out.printf(
            "Expense - %s: $%.2f%n",  
            this.category, 
            this.amount
        );
    }
}

class FinanceManager {
    private double balance;
    private ArrayList<IncomeTransaction> incomeRecords;
    private ArrayList<ExpenseTransaction> expenseRecords;

    public FinanceManager() {
        this.balance = 0;
        incomeRecords = new ArrayList<>();
        expenseRecords = new ArrayList<>();
    } 

    public void addIncome(IncomeTransaction income) {
        incomeRecords.add(income);
        System.out.printf(
            "Income added: %s - $%.2f%n", 
            income.getCategory(), 
            income.getAmount()
        );
    }

    public void addExpense(ExpenseTransaction expense) {
        expenseRecords.add(expense);
        System.out.printf(
            "Expense added: %s - $%.2f%n", 
            expense.getCategory(), 
            expense.getAmount()
        );
    }

    public void calculateBalance() {
        double totalIncome = getTotalIncome();
        double totalExpenses = getTotalExpenses();
        
        this.balance = totalIncome - totalExpenses;
    }

    public void clearAllRecords() {
        incomeRecords.clear();
        expenseRecords.clear();
        calculateBalance();
        System.out.println("All records have been cleared");
    }
    
    public void displaySummary() {
        System.out.println("------ SUMMARY ------");
        System.out.printf("Balance: $%.2f%n", getBalance());
        System.out.printf("Income: $%.2f%n", getTotalIncome());
        System.out.printf("Expenses: $%.2f%n", getTotalExpenses());
    }

    public void displayAllRecords() {
        System.out.println("------ INCOME ------");
        if (incomeRecords.size() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < incomeRecords.size(); i++) {
                IncomeTransaction income = incomeRecords.get(i);
                System.out.print(i + 1 + ". ");
                income.displayInfo();
            }
        }

        System.out.println("------ EXPENSES ------");
        if (expenseRecords.size() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < expenseRecords.size(); i++) {
                ExpenseTransaction expense = expenseRecords.get(i);
                System.out.print(i + 1 + ". ");
                expense.displayInfo();
            }
        }
    }

    public double getTotalIncome() {
        double totalIncome = 0;

        for (IncomeTransaction income : incomeRecords) {
            totalIncome += income.getAmount();
        }

        return totalIncome;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0;

        for (ExpenseTransaction expense : expenseRecords) {
            totalExpenses += expense.getAmount();
        }

        return totalExpenses;
    }

    public double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();
        String userResponse;  
        String category;
        double amount;
        
        do {
            System.out.println("------ MENU ------");
            System.out.println("1. Add income");
            System.out.println("2. Add expense");
            System.out.println("3. View balance");
            System.out.println("4. View all records");
            System.out.println("5. Clear all records");
            System.out.print("Select an option or enter 'EXIT': ");

            userResponse = scanner.nextLine().trim();

            if (userResponse.equals("1")) {
                System.out.println("Select the income type:");
                System.out.println("1. Salary");
                System.out.println("2. Gift");
                System.out.println("3. Scholarship");
                System.out.println("4. Freelance");
                System.out.println("5. Other");
                System.out.print("Choose (1-5): ");

                userResponse = scanner.nextLine().trim();

                if (userResponse.equals("1")) {
                    category = "Salary";
                } else if (userResponse.equals("2")) {
                    category = "Gift";
                } else if (userResponse.equals("3")) {
                    category = "Scholarship";
                } else if (userResponse.equals("4")) {
                    category = "Freelance";
                } else if (userResponse.equals("5")) {
                    category = "Other";
                } else {
                    System.out.println("Invalid option. Choose (1-5).");
                    continue;
                }

                System.out.print("Enter the amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number");
                    scanner.nextLine();
                    continue;
                }
                    
                amount = scanner.nextDouble();
                scanner.nextLine();
                
                if (amount <= 0) {
                    System.out.println("The amount cannot be a zero or negative number");
                    continue;
                }

                IncomeTransaction income = new IncomeTransaction(category, amount);
                manager.addIncome(income);
                manager.calculateBalance();
                userResponse = "";
            } else if (userResponse.equals("2")) {
                System.out.println("Select the expense type:");
                System.out.println("1. Rent");
                System.out.println("2. Food");
                System.out.println("3. Entertainment");
                System.out.println("4. Utilities");
                System.out.println("5. Other");
                System.out.print("Choose (1-5): ");

                userResponse = scanner.nextLine().trim();

                if (userResponse.equals("1")) {
                    category = "Rent";
                } else if (userResponse.equals("2")) {
                    category = "Food";
                } else if (userResponse.equals("3")) {
                    category = "Entertainment";
                } else if (userResponse.equals("4")) {
                    category = "Utilities";
                } else if (userResponse.equals("5")) {
                    category = "Other";
                } else {
                    System.out.println("Invalid option: Choose (1-5).");
                    continue;
                }

                System.out.print("Enter the amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }
                    
                amount = scanner.nextDouble();
                scanner.nextLine();
                
                if (amount <= 0) {
                    System.out.println("The amount cannot be a zero or negative number.");
                    continue;
                }

                ExpenseTransaction expense = new ExpenseTransaction(category, amount);
                manager.addExpense(expense);
                manager.calculateBalance();
                userResponse = "";
            } else if (userResponse.equals("3")) {
                manager.displaySummary();
            } else if (userResponse.equals("4")) {
                manager.displayAllRecords();
            } else if (userResponse.equals("5")) {
                manager.clearAllRecords();
            } else {
                System.out.println("Invalid option. Try again please.");
            }

        } while (!userResponse.equalsIgnoreCase("exit"));
        scanner.close();
    }
}