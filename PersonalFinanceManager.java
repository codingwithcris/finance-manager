import java.util.ArrayList;
import java.util.Scanner;

class FinanceRecord {
    private String transactionType;
    private String transactionDescription;
    private double transactionAmount;

    public FinanceRecord(String type, 
                         String description, 
                         double amount) {
        this.transactionType = type;
        this.transactionDescription = description;
        this.transactionAmount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }
}

class PersonalFinanceManager {
    public double balance;
    public ArrayList<FinanceRecord> records = new ArrayList<>();

    public PersonalFinanceManager() {
        this.balance = 0;
    } 

    public void addIncome(String type, double amount) {
        FinanceRecord income = new FinanceRecord("Income", type, amount);
        records.add(income);

        System.out.printf("Income added: %s - $%.2f%n", type, amount);
    }

    public void addExpense(String type, double amount) {
        FinanceRecord expense = new FinanceRecord("Expense", type, amount);
        records.add(expense);

        System.out.printf("Expense added: %s - $%.2f%n", type, amount);
    }

    public void calculateBalance() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (FinanceRecord record : records) {
            if (record.getTransactionType().equalsIgnoreCase("income")) {
                totalIncome += record.getTransactionAmount();
            } else if (record.getTransactionType().equalsIgnoreCase("expense")) {
                totalExpenses += record.getTransactionAmount();
            }
        }
        
        this.balance = totalIncome - totalExpenses;
    }

    public void clearAllRecords() {
        records.clear();
        calculateBalance();
        System.out.println("All records have been cleared");
    }
    
    public void viewAllRecords() {
        if (records.size() == 0) {
            System.out.println("There are no records found");
        } else {
            System.out.println("------ RECORDS ------");
            for (FinanceRecord record : records) {
                System.out.printf(
                    "%s(%s): $%.2f%n",
                    record.getTransactionType(), 
                    record.getTransactionDescription(), 
                    record.getTransactionAmount()
                );
            }
        }
    }

    public void viewTotals() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (FinanceRecord record : records) {
            if (record.getTransactionType().equalsIgnoreCase("income")) {
                totalIncome += record.getTransactionAmount();
            } else if (record.getTransactionType().equalsIgnoreCase("expense")) {
                totalExpenses += record.getTransactionAmount();
            }
        }
        
        System.out.printf("Balance: $%.2f%n", this.balance);
        System.out.printf("Income: $%.2f%n", totalIncome);
        System.out.printf("Expenses: $%.2f%n", totalExpenses);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFinanceManager manager = new PersonalFinanceManager();
        String userResponse;  
        String incomeType; 
        String expenseType;
        
        do {
            System.out.println("------ MENU ------");
            System.out.println("1. Add income");
            System.out.println("2. Add expense");
            System.out.println("3. View totals");
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
                    incomeType = "Salary";
                } else if (userResponse.equals("2")) {
                    incomeType = "Gift";
                } else if (userResponse.equals("3")) {
                    incomeType = "Scholarship";
                } else if (userResponse.equals("4")) {
                    incomeType = "Freelance";
                } else {
                    incomeType = "Other";
                }

                System.out.print("Enter the income amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number");
                    scanner.nextLine();
                    continue;
                }
                    
                double incomeAmount = scanner.nextDouble();
                scanner.nextLine();
                
                if (incomeAmount <= 0) {
                    System.out.println("The income amount cannot be a zero or negative number");
                    continue;
                }

                manager.addIncome(incomeType, incomeAmount);
                manager.calculateBalance();
                userResponse = "";
            }

            if (userResponse.equals("2")) {
                System.out.println("Select the expense type:");
                System.out.println("1. Rent");
                System.out.println("2. Food");
                System.out.println("3. Entertainment");
                System.out.println("4. Utilities");
                System.out.println("5. Other");
                System.out.print("Choose (1-5): ");

                userResponse = scanner.nextLine().trim();

                if (userResponse.equals("1")) {
                    expenseType = "Rent";
                } else if (userResponse.equals("2")) {
                    expenseType = "Food";
                } else if (userResponse.equals("3")) {
                    expenseType = "Entertainment";
                } else if (userResponse.equals("4")) {
                    expenseType = "Utilities";
                } else {
                    expenseType = "Other";
                }

                System.out.print("Enter the expense amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number");
                    scanner.nextLine();
                    continue;
                }
                    
                double expenseAmount = scanner.nextDouble();
                scanner.nextLine();
                
                if (expenseAmount <= 0) {
                    System.out.println("The expense amount cannot be a zero or negative number");
                    continue;
                }

                manager.addExpense(expenseType, expenseAmount);
                manager.calculateBalance();
                userResponse = "";
            }

            if (userResponse.equals("3")) manager.viewTotals();

            if (userResponse.equals("4")) manager.viewAllRecords();

            if (userResponse.equals("5")) manager.clearAllRecords();

        } while (!userResponse.equalsIgnoreCase("exit"));
        scanner.close();
    }
}