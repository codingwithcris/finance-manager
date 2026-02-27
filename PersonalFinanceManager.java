import java.util.Scanner;

class PersonalFinanceManager {
    public double income;
    public double expense;
    public double balance;

    public PersonalFinanceManager() {
        this.income = 0;
        this.expense = 0;
        this.balance = 0;
    } 

    public PersonalFinanceManager(double income) {
        this.income = income;
        this.balance = income;
        this.expense = 0;
    }

    public void addTransaction(String type, double amount) {
        if (type.equalsIgnoreCase("expense")) {
            this.expense += amount;
        } 
        
        if (type.equalsIgnoreCase("income")) {
            this.income += amount;
        }

        calculateBalance();
    }

    public void calculateBalance() {
        this.balance = this.income - this.expense;
    }

    public double getBalance() {
        return this.balance;
    }

    public void viewTotals() {
        System.out.printf("Balance: $%.2f%n", this.balance);
        System.out.printf("Income: $%.2f%n", this.income);
        System.out.printf("Expense: $%.2f%n", this.expense);
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
                System.out.print("What is the type for this transaction (income/expense): ");
                userResponse = scanner.nextLine().trim();
                String type = userResponse;

                
                System.out.print("What is the amount for this transaction: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount. Please enter a number");
                    continue;
                }
                    
                double amount = scanner.nextDouble();
                scanner.nextLine();
                    
                if (amount < 0) {
                    System.out.println("Amount cannot be negative");
                    continue;
                }

                manager.addTransaction(type, amount);

                System.out.printf("Balance: $%.2f%n", manager.getBalance());
            }

            if (userResponse.equals("2")) manager.viewTotals();

        } while (!userResponse.equals("3"));
        scanner.close();
    }
}