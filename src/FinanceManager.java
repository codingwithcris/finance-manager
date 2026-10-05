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
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

class FinanceManager {
    private ArrayList<Transaction> transactionsList;
    private static final String FILE_PATH = "data/transactions.txt";
    private HBox statusBar = new HBox(4);
    private Label statusTypeLabel = new Label("");
    private Label statusMessageLabel = new Label("");
    private TextArea transactionLog = new TextArea();
    private double balance = 0;

    public FinanceManager() {
        transactionsList = new ArrayList<>();
    }

    public void validateAmount(double amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(
                "The amount cannot be equal to zero. Please try again"
            );
        }
    }

    public TextArea createTransactionsLog() {
        transactionLog.setEditable(false);
        transactionLog.setPromptText("No recent activity ...");
        transactionLog.setPrefHeight(200);
        transactionLog.setMaxWidth(580);
        transactionLog.setStyle("-fx-background-color: #ececec;");
        transactionLog.setFont(Main.mozillaTextRegular(12));

        return transactionLog;
    }

    public void calculateBalance() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : transactionsList) {
            String type = transaction.getType();
            double amount = transaction.getAmount();

            if (type.equalsIgnoreCase("Income")) {
                totalIncome += amount;
            } else if (type.equalsIgnoreCase("Expense")) {
                totalExpense += amount;
            }
        }

       this.balance = totalIncome - totalExpense;
    }

    public double getBalance() { return this.balance; }

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

    public void saveTransaction(Transaction newTransaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.printf(
                "%.2f,%s,%s,%s\n", 
                newTransaction.getAmount(), 
                newTransaction.getType(),
                newTransaction.getCategory(),
                newTransaction.getDescription()
            );
        } catch (IOException e) {
            showStatusMessage(
                "ERROR", 
                "cannot save transaction into file"
            );
        } 
    }   

    public String formatTransaction(Transaction transaction) {
        return String.format(
            "Amount: $%.2f; Type: %s; Category: %s; Description: %s\n", 
            transaction.getAmount(), 
            transaction.getType(),
            transaction.getCategory(),
            transaction.getDescription()
        );
    }

    public void loadTransactions() {
        StringBuilder sb = new StringBuilder();

        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
           while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] transactionParts = line.split("\\,");

                Transaction transaction = new Transaction(
                    Double.parseDouble(transactionParts[0]), 
                    transactionParts[1], 
                    transactionParts[2],
                    transactionParts[3]
                );

                transactionsList.add(transaction);

                sb.append(formatTransaction(transaction));
           }

           transactionLog.setText(sb.toString());
        } catch (FileNotFoundException e) {
            showStatusMessage(
                "ERROR", 
                "cannot load transactions because file does not exist"
            );
        } catch (NumberFormatException e) {
            showStatusMessage(
                "ERROR",
                "cannot load invalid numbers from transactions"
            );
        }  
    }

    public void filterTransactionsByType(String query) {
        StringBuilder sb = new StringBuilder(); 
        boolean foundTransactionType = false;

        if (query.equalsIgnoreCase("all")) {
            if (transactionsList.size() != 0) {
                for (Transaction transaction : transactionsList) {
                    sb.append(formatTransaction(transaction));
                    transactionLog.setText(sb.toString());
                }
            } else {
                transactionLog.setPromptText("No transactions available");
            }
            
            return;
        } 

        for (Transaction transaction : transactionsList) {
            String type = transaction.getType();

            if (type.equalsIgnoreCase(query)) {
                sb.append(formatTransaction(transaction));
                foundTransactionType = true;
            } 
            
            transactionLog.setText(sb.toString());
        }

        if (!foundTransactionType == true) {
            transactionLog.setPromptText(
                "Cannot find " + query.toLowerCase() + " transactions"
            );
        }
    }

    public void filterTransactionByCategory(String query) {
        StringBuilder sb = new StringBuilder();
        boolean foundTransactionCategory = false;

        for (Transaction transaction : transactionsList) {
            String category = transaction.getCategory().toLowerCase();

            if (category.contains(query.toLowerCase())) {
                sb.append(formatTransaction(transaction));
                foundTransactionCategory = true;
            }
        }
        transactionLog.setText(sb.toString());

        if (!foundTransactionCategory == true) {
            transactionLog.setPromptText(
                "Cannot find transactions under " + query.toLowerCase()
            );
        }
    }

    public void updateTransactionLog(Transaction newTransaction) {
        StringBuilder sb = new StringBuilder();    
        transactionsList.add(newTransaction);

        for (Transaction transaction : transactionsList) {
            sb.append(formatTransaction(transaction));
            transactionLog.setText(sb.toString());
        }
    }
}