import java.util.Arrays;
import java.util.Scanner;

class PersonalFinanceManager {
    public double balance;
    public double[] income;
    public double[] expenses;
    public int incomeCount = 0;
    public int expensesCount = 0;

    public PersonalFinanceManager() {
        this.balance = 0;
        this.income = new double[0];
        this.expenses = new double[0];
    } 

    public void addTransaction(String type, double amount) {
        if (type.equalsIgnoreCase("income")) {
            this.income = Arrays.copyOf(this.income, income.length + 1);
            this.income[incomeCount] = amount;
            incomeCount++;
        }

        if (type.equalsIgnoreCase("expense")) {
            this.expenses = Arrays.copyOf(this.expenses, expenses.length + 1);
            this.expenses[expensesCount] = amount;
            expensesCount++;
        }
    }

    public double sumTotal(double[] array) {
        double total = 0;

        for (double element : array) {
            total += element;
        }

        return total;
    }

    public void calculateBalance() {
        double totalIncome = sumTotal(income);
        double totalExpenses = sumTotal(expenses);

        this.balance = totalIncome - totalExpenses;
    }

    public double getBalance() {
        return this.balance;
    }

    public void viewTotals() {
        System.out.printf("Balance: $%.2f%n", this.balance);
        System.out.printf("Income: $%.2f%n", sumTotal(income));
        System.out.printf("Expense: $%.2f%n", sumTotal(expenses));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFinanceManager manager = new PersonalFinanceManager();
        String userResponse;   
        
        do {
            System.out.println("What would you like to do:");
            System.out.println("1. Add a transaction");
            System.out.println("2. View totals");
            System.out.println("3. Quit");

            userResponse = scanner.nextLine().trim();

            if (userResponse.equals("1")) {

                System.out.printf("What is the type for transaction (income/expense): ");
                String type = scanner.nextLine().trim();
                
                System.out.print("What is the amount for transaction: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number");
                    continue;
                }
                    
                double amount = scanner.nextDouble();
                scanner.nextLine();
                
                if (amount < 0) {
                    System.out.println("Amount cannot be a negative number");
                    continue;
                }
                
                manager.addTransaction(type, amount);
                manager.calculateBalance();

                System.out.printf("Balance: $%.2f%n", manager.getBalance());
            }

            if (userResponse.equals("2")) manager.viewTotals();

        } while (!userResponse.equals("3"));
        scanner.close();
    }
}