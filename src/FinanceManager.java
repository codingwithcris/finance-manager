package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

class FinanceManager {
    private ArrayList<Transaction> transactions;
    private static final String FILE_PATH = "data/transactions.txt";
    private HBox statusBar = new HBox(4);
    private Label statusTypeLabel = new Label("");
    private Label statusMessageLabel = new Label("");

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

    public void loadTransactions() {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(FILE_PATH));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] transactionDetails = line.split(",");
                String type = transactionDetails[0];
                String category = transactionDetails[1];
                double amount = Double.parseDouble(transactionDetails[2]);
                
                if (type.equals("Income")) {
                    transactions.add(new IncomeTransaction(category, amount));
                } else if (type.equals("Expense")) {
                    transactions.add(new ExpenseTransaction(category, amount));
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
            System.out.println("Failed to save transactions: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public void validateAmount(double amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(
                "The amount cannot be equal to zero. Please try again"
            );
        }
    }

    public HBox createStatusBar() {
        statusBar.getChildren().addAll(
            statusTypeLabel, statusMessageLabel
        );
        statusBar.setManaged(false);

        statusTypeLabel.setStyle("-fx-text-fill: #ffffff;");
        statusTypeLabel.setFont(Main.mozillaTextBold(13));

        statusMessageLabel.setStyle("-fx-text-fill: #ffffff;");
        statusMessageLabel.setFont(Main.mozillaTextRegular(13));

        return statusBar;
    }

    public void showStatusMessage(String statusType, String message) {
        if (statusType.equalsIgnoreCase("ERROR")) {
            statusBar.setStyle("-fx-background-color: #e11818");
        } else if (statusType.equalsIgnoreCase("SUCCESSFUL")) {
            statusBar.setStyle("-fx-background-color: #377550");
        }

        statusBar.setManaged(true);
        statusBar.setPadding(new Insets(15));
        statusTypeLabel.setText(statusType + ":");
        statusMessageLabel.setText(message);
    }

    public void hideStatusMessage() {
        statusBar.setManaged(false);
        statusBar.setStyle("-fx-background-color: transparent;");
        statusTypeLabel.setText("");
        statusMessageLabel.setText("");
    }
}