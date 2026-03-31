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

class ExpenseTransaction extends Transaction {
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
            System.out.println("4. View by type");
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
                userResponse = "";
            } else if (userResponse.equals("3")) {
                manager.displaySummary();
            } else if (userResponse.equals("4")) {
                System.out.println("Select the type:");
                System.out.println("1. Income");
                System.out.println("2. Expense");
                System.out.print("Enter choice: ");

                userResponse = scanner.nextLine().trim();

                if (userResponse.equals("1")) {
                    manager.displayTransactionsByType("Income");
                } else if (userResponse.equals("2")) {
                    manager.displayTransactionsByType("Expense");
                } else {
                    System.out.println("Invalid option. Choose 1 or 2.");
                    continue;
                }

                userResponse = "";
            } else if (userResponse.equals("5")) {
                manager.clearAllRecords();
            } else {
                System.out.println("Invalid option. Try again please.");
            }

        } while (!userResponse.equalsIgnoreCase("exit"));
        scanner.close();
    }
}