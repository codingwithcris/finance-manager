import java.util.Scanner;

class PersonalFinanceManager {
    public double income;
    public double expense;
    public String transactions[];

    // default
    public PersonalFinanceManager() {
        income = 0;
    } 

    // user can begin with a specific income
    public PersonalFinanceManager(double income) {
        this.income = income;
    }

    public void addExpense(double amount) {
        this.expense += amount;
    }

    public void addIncome(double amount) {
        this.income += amount;
    }

    public double calculateBalance() {
        if (this.expense == 0) {
            return this.income;
        }

        return Math.floor(this.income - this.expense);
    }

    // public String viewTransactions() {
    //     for (String transaction: transactions) {
    //         System.out.println(transaction);
    //     }
    // }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFinanceManager manager = new PersonalFinanceManager();
        String userResponse;
        double amount;
        
        do {
            System.out.println("What would you like to do:");
            System.out.println("1. Add a transaction");
            System.out.println("2. View all transactions");
            System.out.println("3. View balance");
            System.out.println("4. Quit");

            userResponse = scanner.nextLine();

            if (userResponse.equals("1")) {
                System.out.print("What is the amount for the transaction: ");
                userResponse = scanner.nextLine();
                amount = Double.valueOf(userResponse);
                
                System.out.print("How is this transaction categorized: ");
                userResponse = scanner.nextLine();
                if (userResponse.equalsIgnoreCase("expense")) {
                    manager.addExpense(amount);
                } else {
                    manager.addIncome(amount);
                }
            }

            if (userResponse.equals("3")) System.out.println(manager.calculateBalance());

        } while (!userResponse.equalsIgnoreCase("4"));
        scanner.close();
    }
}
