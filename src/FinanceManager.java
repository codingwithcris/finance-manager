package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

class FinanceManager {
    private static final String FILE_PATH = "data/transactions.txt";
    private HBox statusBar = new HBox(4);
    private Label statusTypeLabel = new Label("");
    private Label statusMessageLabel = new Label("");

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

    public void loadTransactions(TextArea transactionLog) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
           while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] transactionParts = line.split("\\,");

                String amount = transactionParts[0];
                String type = transactionParts[1];
                String category = transactionParts[2];
                String description = transactionParts[3];
                
                sb.append(i += 1)
                  .append(". Amount: ").append(amount)
                  .append(" Type: ").append(type)
                  .append(" Category: ").append(category)
                  .append(" description: ").append(description)
                  .append("\n");
           }

           transactionLog.setText(sb.toString());
        } catch (FileNotFoundException e) {
            showStatusMessage(
                "ERROR", 
                "cannot load transactions because file does not exist"
            );
        }   
    }

    public void saveTransaction(String transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(transaction);
        } catch (IOException e) {
            showStatusMessage(
                "ERROR", 
                "cannot save transaction into file"
            );
        } 
    }   
}