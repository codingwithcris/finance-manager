package src;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Main extends Application {
    private static final int WIDTH = 500;
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

        // Inputs
        TextArea transactionLog = new TextArea();
        transactionLog.setEditable(false);
        transactionLog.setPromptText("No recent activity ...");
        transactionLog.setPrefHeight(200);
        transactionLog.setMaxWidth(460);
        transactionLog.setStyle("-fx-background-color: #ececec;");
        transactionLog.setFont(mozillaTextRegular(14));

        TextField filterCategoryField = new TextField();
        filterCategoryField.setPromptText("Filter by category");
        filterCategoryField.setPrefWidth(180);
        filterCategoryField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        filterCategoryField.setFont(mozillaTextRegular(11));

        TextField filterAmountField = new TextField();
        filterAmountField.setPromptText("Filter by amount");
        filterAmountField.setPrefWidth(180);
        filterAmountField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        filterAmountField.setFont(mozillaTextRegular(11));

        TextField amountField = new TextField();
        amountField.setPromptText("0.00");
        amountField.setMaxWidth(280);
        amountField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        amountField.setFont(mozillaTextRegular(11));

        TextField descriptionField = new TextField();
        descriptionField.setPromptText(
            "e.g. Groceries, books, transportation"
        );
        descriptionField.setMaxWidth(280);
        descriptionField.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-background-color: #ececec;" +
            "-fx-padding: 7 8;"
        );
        descriptionField.setFont(mozillaTextRegular(11));
        
        // ComboBox
        ComboBox<String> categoryDropDown  = new ComboBox<>();
        categoryDropDown.getItems().addAll(
            "Salary",
            "Gift",
            "Freelance",
            "Scholarship",
            "Rent",
            "Food",
            "Utilities",
            "Entertainment",
            "Other"
        );
        categoryDropDown.setStyle(
            "-fx-background-color: #ececec;"
        );

        /* BUTTONS */
        Button viewAllButton = new Button("All");
        viewAllButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" + 
            "-fx-background-color: #0072b1;" +
            "-fx-text-fill: #ffffff;"  +
            "-fx-padding: 6 18;"
        );
        viewAllButton.setFont(mozillaTextBold(12));

        Button viewIncomeButton = new Button("Income");
        viewIncomeButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" +
            "-fx-background-color: #26a5ea;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 15;"
        );
        viewIncomeButton.setFont(mozillaTextRegular(12));

        Button viewExpensesButton = new Button("Expenses");
        viewExpensesButton.setStyle(
            "-fx-background-radius: 14;" + 
            "-fx-border-radius: 14;" +
            "-fx-background-color: #26a5ea;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 15;"
        );
        viewExpensesButton.setFont(mozillaTextRegular(12));

        Button searchButton = new Button("Search");
        searchButton.setStyle(
            "-fx-background-radius: 4;" + 
            "-fx-border-radius: 4;" + 
            "-fx-background-color: #377550;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 20;"
        );
        searchButton.setFont(mozillaTextBold(12));

        Button insertButton = new Button("Insert");
        insertButton.setStyle(
            "-fx-background-radius: 4;" + 
            "-fx-border-radius: 4;" + 
            "-fx-background-color: #e57e00;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 6 20;"
        );
        insertButton.setFont(mozillaTextBold(12));

        Button clearAllButton = new Button("Clear All");
        clearAllButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #ffffff;" + 
            "-fx-padding: 6 0;"
        );
        clearAllButton.setFont(mozillaTextBold(12));

        ToggleGroup transactionTypeGroup = new ToggleGroup();
        RadioButton incomeRadioButton = new RadioButton("Income");
        incomeRadioButton.setToggleGroup(transactionTypeGroup);
        incomeRadioButton.setStyle("-fx-text-fill: #ffffff");
        incomeRadioButton.setFont(mozillaTextRegular(12));

        RadioButton expenseRadioButton = new RadioButton("Expense");
        expenseRadioButton.setToggleGroup(transactionTypeGroup);
        expenseRadioButton.setStyle("-fx-text-fill: #ffffff");
        expenseRadioButton.setFont(mozillaTextRegular(12));
        
        // LAYOUT
        VBox root = new VBox(20);
        root.setStyle(
            "-fx-background-color: #2f466b;" +
            "-fx-padding: 20;"
        );
        
        VBox header = new VBox(5);
        header.getChildren().addAll(
            balanceLabel,
            balanceAmount
        );

        HBox controlWrapper = new HBox(10);
        controlWrapper.getChildren().addAll(
            filterCategoryField,
            filterAmountField,
            searchButton
        );

        HBox viewButtonsWrapper = new HBox(10);
        viewButtonsWrapper.getChildren().addAll(
            viewAllButton,
            viewIncomeButton,
            viewExpensesButton
        );
        viewButtonsWrapper.setAlignment(Pos.CENTER);

        VBox transactionContainer = new VBox(10);
        transactionContainer.getChildren().addAll(
            headingOne,
            controlWrapper,
            transactionLog,
            viewButtonsWrapper
        );

        HBox radioButtonGroup = new HBox(10);
        radioButtonGroup.getChildren().addAll(
            incomeRadioButton,
            expenseRadioButton
        );

        VBox categoryGroup = new VBox(10);
        categoryGroup.getChildren().addAll(
            categoryLabel,
            categoryDropDown
        );

        HBox formGroup = new HBox(15);
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
            new HBox(12, insertButton, clearAllButton)
        );

        VBox body = new VBox(25);
        body.getChildren().addAll(
            transactionContainer,
            formContainer
        );

        root.getChildren().addAll(
            header,
            body
        );

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.setTitle("Finance Manager");
        stage.show();
    }
}