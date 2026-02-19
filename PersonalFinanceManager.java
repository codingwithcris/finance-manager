import java.util.Scanner;

class PersonalFinanceManager {
    public double income;
    public double expense;

    public PersonalFinanceManager() {
        income = 0;
    } 

    public PersonalFinanceManager(double income) {
        this.income = income;
    }

    public void addTransaction(String type, double amount) {
        if (type.equalsIgnoreCase("expense")) {
            this.expense += amount;
        } 
        
        if (type.equalsIgnoreCase("deposit")) {
            this.income += amount;
        }
    }

    public double calculateBalance() {
        if (this.expense == 0) {
            return this.income;
        }

        return this.income - this.expense;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFinanceManager manager = new PersonalFinanceManager();
        String userResponse;
        String type;
        double amount;    
        
        do {
            System.out.println("What would you like to do:");
            System.out.println("1. Add a transaction");
            System.out.println("2. Quit");

            userResponse = scanner.nextLine();

            if (userResponse.equals("1")) {
                System.out.print("What is the amount for the transaction: ");
                userResponse = scanner.nextLine();
                amount = Double.valueOf(userResponse);
                
                System.out.print("What is the type of transaction: ");
                userResponse = scanner.nextLine();
                type = userResponse;

                manager.addTransaction(type, amount);

                System.out.printf("Balance: $%.2f%n", manager.calculateBalance());
            }
        } while (!userResponse.equalsIgnoreCase("2"));
        scanner.close();
    }
}