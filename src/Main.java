package src;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Main extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 720;
    public static void main(String[] args) {
        launch(args);
    }

    public static Font mozillaTextRegular(double size) {
        return Font.loadFont(
            Main.class.getResourceAsStream("../resources/fonts/MozillaText-Regular.ttf"),
            size
        );
    }

    public static Font mozillaTextBold(double size) {
        return Font.loadFont(
            Main.class.getResourceAsStream("../resources/fonts/MozillaText-Bold.ttf"),
            size
        );
    }

    @Override
    public void start(Stage stage) {
        FinanceManager manager = new FinanceManager();

        // TEXT
        Label balanceAmount = new Label("$0.00");
        balanceAmount.setStyle("-fx-text-fill: #ffffff;");
        balanceAmount.setFont(mozillaTextBold(16));

        Label headingOne = new Label("TRANSACTIONS");
        headingOne.setStyle("-fx-text-fill: #ffffff;");
        headingOne.setFont(mozillaTextBold(18));
       
        Label headingTwo = new Label("ENTER A NEW TRANSACTION");
        headingTwo.setStyle("-fx-text-fill: #ffffff;");
        headingTwo.setFont(mozillaTextBold(18));

        Label balanceLabel = new Label("CURRENT BALANCE");
        balanceLabel.setStyle("-fx-text-fill: #ffffff;");
        balanceLabel.setFont(mozillaTextBold(12));

        Label amountLabel = new Label("Amount *");
        amountLabel.setStyle("-fx-text-fill: #ffffff;");
        amountLabel.setFont(mozillaTextRegular(14));

        Label typeLabel = new Label("Type *");
        typeLabel.setStyle("-fx-text-fill: #ffffff;");
        typeLabel.setFont(mozillaTextRegular(14));

        Label categoryLabel = new Label("Category *");
        categoryLabel.setStyle("-fx-text-fill: #ffffff;");
        categoryLabel.setFont(mozillaTextRegular(14));

        Label descriptionLabel = new Label("Description *");
        descriptionLabel.setStyle("-fx-text-fill: #ffffff;");
        descriptionLabel.setFont(mozillaTextRegular(14));

        // INPUTS
        TextField filterCategoryField = new TextField();
        filterCategoryField.setPromptText("Filter by category");
        filterCategoryField.setPrefWidth(160);
        filterCategoryField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        filterCategoryField.setFont(mozillaTextRegular(11));
        filterCategoryField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                filterCategoryField.setText(oldValue);
            }
        });

        TextField filterAmountField = new TextField();
        filterAmountField.setPromptText("Filter by amount");
        filterAmountField.setPrefWidth(160);
        filterAmountField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        filterAmountField.setFont(mozillaTextRegular(11));
        filterAmountField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                filterAmountField.setText(oldValue);
            }
        });

        TextField amountField = new TextField();
        amountField.setPromptText("0.00");
        amountField.setMaxWidth(380);
        amountField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        amountField.setFont(mozillaTextRegular(11));
        amountField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                amountField.setText(oldValue);
                manager.showStatusMessage(
                    "ERROR", 
                    "The amount cannot contain letters or symbols"
                );
            }            
            
            if (newValue.contains(".")) {
                String[] amountParts = newValue.split("\\.");                
                if (amountParts.length > 1) {
                    String cents = amountParts[1];
                    if (cents.length() > 2) {
                        manager.showStatusMessage(
                            "ERROR", 
                            "The cents cannot exceed two decimal places"
                        );
                    } else {
                        manager.hideStatusMessage();
                    }
                }
            }

        });

        TextField descriptionField = new TextField();
        descriptionField.setPromptText(
            "e.g. Groceries, books, transportation"
        );
        descriptionField.setMaxWidth(380);
        descriptionField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        descriptionField.setFont(mozillaTextRegular(11));

        // COMBOBOX
        ComboBox<String> categoryDropDown  = new ComboBox<>();
        categoryDropDown.setStyle(
            "-fx-background-color: #ececec;" +
            "-fx-padding: 2"
        );
        categoryDropDown.setPrefWidth(140);

        ComboBox<String> inequalityDropDown = new ComboBox<>();
        inequalityDropDown.getItems().addAll(
            "Is less than",
            "Exactly",
            "Is greater than"
        );
        inequalityDropDown.setStyle(
            "-fx-background-color: #ececec;" +
            "-fx-padding: 1;"
        );
        inequalityDropDown.setValue("Exactly");

        // BUTTONS 
        Button viewAllButton = new Button("All");
        viewAllButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" + 
            "-fx-background-color: #0072b1;" +
            "-fx-text-fill: #ffffff;"  +
            "-fx-padding: 6 18;"
        );
        viewAllButton.setFont(mozillaTextBold(12));
        viewAllButton.setOnAction(event -> {
            manager.filterTransactionsByType(
                viewAllButton.getText()
            );
        });

        Button viewIncomeButton = new Button("Income");
        viewIncomeButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" +
            "-fx-background-color: #26a5ea;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 15;"
        );
        viewIncomeButton.setFont(mozillaTextRegular(12));
        viewIncomeButton.setOnAction(event -> {
            manager.filterTransactionsByType(
                viewIncomeButton.getText()
            );
        });

        Button viewExpenseButton = new Button("Expense");
        viewExpenseButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" +
            "-fx-background-color: #26a5ea;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 15;"
        );
        viewExpenseButton.setFont(mozillaTextRegular(12));
        viewExpenseButton.setOnAction(event -> {
            manager.filterTransactionsByType(
                viewExpenseButton.getText()
            );
        });

        Button searchButton = new Button("Search");
        searchButton.setStyle(
            "-fx-background-radius: 4;" + 
            "-fx-border-radius: 4;" + 
            "-fx-background-color: #377550;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 20;"
        );
        searchButton.setFont(mozillaTextBold(12));
        // searchButton.setOnAction(event -> {

        // });

        ToggleGroup transactionTypeGroup = new ToggleGroup();

        RadioButton incomeRadioButton = new RadioButton("Income");
        incomeRadioButton.setToggleGroup(transactionTypeGroup);
        incomeRadioButton.setStyle(
            "-fx-text-fill: #ffffff;" +
            "-fx-border-width: 1;" + 
            "-fx-border-color: #ececec;" +
            "-fx-padding:  7 26 7 8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;"
        );
        incomeRadioButton.setFont(mozillaTextRegular(12));

        RadioButton expenseRadioButton = new RadioButton("Expense");
        expenseRadioButton.setToggleGroup(transactionTypeGroup);
        expenseRadioButton.setStyle(
            "-fx-text-fill: #ffffff;" +
            "-fx-border-width: 1;" + 
            "-fx-border-color: #ececec;" +
            "-fx-padding:  7 26 7 8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;"
        );
        expenseRadioButton.setFont(mozillaTextRegular(12));
        
        // 
        transactionTypeGroup.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
            categoryDropDown.getItems().clear();
            if (newValue == incomeRadioButton) {
                categoryDropDown.getItems().addAll(
                    "Salary",
                    "Gift",
                    "Freelance",
                    "Scholarship"
                );
                categoryDropDown.setValue("Salary");
            } else if (newValue == expenseRadioButton) {
                categoryDropDown.getItems().addAll(
                    "Rent",
                    "Food",
                    "Utilities",
                    "Entertainment",
                    "Memberships",
                    "Pet care"
                );
                categoryDropDown.setValue("Rent");
            }
        });

        categoryDropDown.disableProperty().bind(
            transactionTypeGroup.selectedToggleProperty().isNull()
        );

        Button insertButton = new Button("Insert");
        insertButton.setStyle(
            "-fx-background-radius: 4;" + 
            "-fx-border-radius: 4;" + 
            "-fx-background-color: #e57e00;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 20;"
        );
        insertButton.setFont(mozillaTextBold(12));
        insertButton.disableProperty().bind(
            amountField.textProperty().isEmpty()
            .or(transactionTypeGroup.selectedToggleProperty().isNull())
            .or(categoryDropDown.itemsProperty().isNull())
            .or(descriptionField.textProperty().isEmpty())
        );
        insertButton.setOnAction(event -> {          
            manager.hideStatusMessage();    
            
            Toggle selectedToggle = transactionTypeGroup.getSelectedToggle();
            String typeValue = ((RadioButton) selectedToggle).getText();    
            String amountStr = amountField.getText().trim();
            String categoryValue = categoryDropDown.getValue();
            String descriptionValue = descriptionField.getText().trim();

            if (descriptionValue.isEmpty()) {
                manager.showStatusMessage(
                    "ERROR", 
                    "description field cannot be blank"
                );
                return;
            }

            if (descriptionValue.length() > 75) {
                manager.showStatusMessage(
                    "ERROR", 
                    "description cannot exceed 75 characters"
                );
                return;
            }

            try {
                Double amountValue = Double.parseDouble(amountStr);
                manager.validateAmount(amountValue);
                Transaction transaction = new Transaction(
                    amountValue, 
                    typeValue, 
                    categoryValue, 
                    descriptionValue
                );

                manager.showStatusMessage(
                    "SUCCESSFUL", 
                    "Transaction submitted"
                );
                manager.saveTransaction(transaction);
            } catch (NumberFormatException e) {
                manager.showStatusMessage(
                    "ERROR", 
                    "invalid number. Please try again"
                );
            } catch (IllegalArgumentException e) {
                manager.showStatusMessage("ERROR", e.getMessage());
            } 

            // amountField.clear();
            // transactionTypeGroup.selectToggle(null);
            // categoryDropDown.getSelectionModel().clearSelection();
            // descriptionField.clear();
        });

        Button clearFormButton = new Button("Clear Form");
        clearFormButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #ffffff;" + 
            "-fx-padding: 6 0;"
        );
        clearFormButton.setFont(mozillaTextBold(12));
        clearFormButton.setOnAction(event -> {
            manager.hideStatusMessage();
            amountField.clear();
            transactionTypeGroup.selectToggle(null);
            categoryDropDown.getSelectionModel().clearSelection();
            descriptionField.clear();
        });

        // LAYOUT        
        VBox header = new VBox(5);
        header.setStyle("-fx-padding: 18 0 0 18;");
        header.getChildren().addAll(
            balanceLabel, balanceAmount
        );

        HBox controlWrapper = new HBox(10);
        controlWrapper.getChildren().addAll(
            filterCategoryField,
            filterAmountField,
            inequalityDropDown,
            searchButton
        );

        HBox viewButtonsWrapper = new HBox(10);
        viewButtonsWrapper.getChildren().addAll(
            viewAllButton,
            viewIncomeButton,
            viewExpenseButton
        );
        viewButtonsWrapper.setAlignment(Pos.CENTER);

        VBox transactionContainer = new VBox(10);
        transactionContainer.getChildren().addAll(
            headingOne,
            controlWrapper,
            manager.createTransactionsLog(),
            viewButtonsWrapper
        );

        HBox radioButtonGroup = new HBox(8);
        radioButtonGroup.getChildren().addAll(
            incomeRadioButton, expenseRadioButton
        );

        VBox categoryGroup = new VBox(10);
        categoryGroup.getChildren().addAll(
            categoryLabel, categoryDropDown
        );

        HBox formGroup = new HBox(18);
        formGroup.getChildren().addAll(
            new VBox(10, typeLabel, radioButtonGroup),
            categoryGroup
        );

        VBox formContainer = new VBox(12);
        formContainer.getChildren().addAll(
            headingTwo,
            amountLabel,
            amountField,
            formGroup,
            descriptionLabel,
            descriptionField,
            new HBox(12, insertButton, clearFormButton)
        );

        VBox body = new VBox(25);
        body.setStyle("-fx-padding: 19;");
        body.getChildren().addAll(
            transactionContainer,
            formContainer
        );

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #2f466b;");
        root.getChildren().addAll(
            manager.createStatusBar(),
            header,
            body
        );

        manager.loadTransactions();

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.setTitle("Finance Manager");
        stage.show();
    }
}