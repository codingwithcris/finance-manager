import java.util.ArrayList;
import java.util.Scanner;

class PersonalFinanceManager {
    public double balance;
    public ArrayList<Double> incomeAmount = new ArrayList<>();
    public ArrayList<String> incomeDescription = new ArrayList<>();
    public ArrayList<Double> expenseAmount = new ArrayList<>();
    public ArrayList<String> expenseDescription = new ArrayList<>();

    public PersonalFinanceManager() {
        this.balance = 0;
    } 

    public void addIncome(String type, double amount) {
        incomeAmount.add(amount);
        incomeDescription.add(type);

        System.out.printf("Income added: %s - $%.2f%n", type, amount);
    }

    public void addExpense(String type, double amount) {
        expenseAmount.add(amount);
        expenseDescription.add(type);

        System.out.printf("Expense added: %s - $%.2f%n", type, amount);
    }

    public void calculateBalance() {
        double totalIncome = sumTotal(incomeAmount);
        double totalExpenses = sumTotal(expenseAmount);

        this.balance = totalIncome - totalExpenses;
    }

    public void clearAllTransactions() {
        incomeAmount.clear();
        incomeDescription.clear();
        expenseAmount.clear();
        expenseDescription.clear();
        calculateBalance();
        System.out.println("All transactions have been cleared");
    }

    public double sumTotal(ArrayList<Double> transactionType) {
        double totalAmount = 0;

        for (int i = 0; i < transactionType.size(); i++) {
            double transactionAmount = transactionType.get(i);
            totalAmount += transactionAmount;
        }

        return totalAmount;
    }

    public void viewTotals() {
        System.out.printf("Balance: $%.2f%n", this.balance);
        System.out.printf("Income: $%.2f%n", sumTotal(incomeAmount));
        System.out.printf("Expense: $%.2f%n", sumTotal(expenseAmount));
    }
    
    public void viewAllTransactions() {
        double amount;
        String type;

        System.out.println("----- INCOME LOG -----");
        if (incomeAmount.size() == 0) {
            System.out.println("No income recorded");
        } else {
            for (int i = 0; i < incomeAmount.size(); i++) {
                amount = incomeAmount.get(i);
                type = incomeDescription.get(i);
                
                System.out.printf("%s: $%.2f%n", type, amount);
            }
        }

        System.out.println("----- EXPENSES LOG -----");
        if (expenseAmount.size() == 0) {
            System.out.println("No expenses recorded");
        } else {
            for (int i = 0; i < expenseAmount.size(); i++) {
                amount = expenseAmount.get(i);
                type = expenseDescription.get(i);
                
                System.out.printf("%s: $%.2f%n", type, amount);
            }
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFinanceManager manager = new PersonalFinanceManager();
        String userResponse;  
        String incomeType; 
        String expenseType;
        
        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Add income");
            System.out.println("2. Add expense");
            System.out.println("3. View totals");
            System.out.println("4. View all transactions");
            System.out.println("5. Clear all transactions");
            System.out.print("Select an option or enter 'EXIT': ");

            userResponse = scanner.nextLine().trim();

            if (userResponse.equals("1")) {
                System.out.println("Select the income type:");
                System.out.println("1. Salary");
                System.out.println("2. Gift");
                System.out.println("3. Scholarship/Grant");
                System.out.println("4. Freelance");
                System.out.println("5. Other");
                System.out.print("Choose (1-5): ");

                userResponse = scanner.nextLine().trim();

                if (userResponse.equals("1")) {
                    incomeType = "Salary";
                } else if (userResponse.equals("2")) {
                    incomeType = "Gift";
                } else if (userResponse.equals("3")) {
                    incomeType = "Scholarship/Grant";
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
                
                if (incomeAmount < 0) {
                    System.out.println("The income amount cannot be a negative number");
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
                
                if (expenseAmount < 0) {
                    System.out.println("The expense amount cannot be a negative number");
                    continue;
                }

                manager.addExpense(expenseType, expenseAmount);
                manager.calculateBalance();
                userResponse = "";
            }

            if (userResponse.equals("3")) manager.viewTotals();

            if (userResponse.equals("4")) manager.viewAllTransactions();

            if (userResponse.equals("5")) manager.clearAllTransactions();

        } while (!userResponse.equalsIgnoreCase("exit"));
        scanner.close();
    }
}