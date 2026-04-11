package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();
        String userResponse;  
        String category;
        double amount;
        
        do {
            System.out.println("====== MENU ======");
            System.out.println("1. Add income");
            System.out.println("2. Add expense");
            System.out.println("3. View balance");
            System.out.println("4. View income category totals");
            System.out.println("5. View expense category totals");
            System.out.println("6. Clear all transactions");
            System.out.print("Select an option or enter 'EXIT': ");

            userResponse = scanner.nextLine().trim();

            if (userResponse.equals("1")) {
                System.out.println("Select the income category:");
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

                IncomeTransaction income = new IncomeTransaction(category, amount);
                manager.addIncome(income);
                userResponse = "";
            } else if (userResponse.equals("2")) {
                System.out.println("Select the expense category:");
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
                manager.displayIncomeCategoryTotal();
            } else if (userResponse.equals("5")) {
                manager.displayExpenseCategoryTotal();
            } else if (userResponse.equals("6")) {
                manager.clearAllTransactions();
            } else {
                System.out.println("Invalid menu option. Please try again.");
                continue;
            }
        } while (!userResponse.equalsIgnoreCase("exit"));
        scanner.close();
    }
}
