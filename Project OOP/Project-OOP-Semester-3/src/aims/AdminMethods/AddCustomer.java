package aims.AdminMethods;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddCustomer extends Application {

    private Stage previousStage;  // Tham chiếu đến cửa sổ trước đó
    private TextField usernameField, passwordField, nameField, phoneNumberField, idCardField;

    // Constructor mặc định (bắt buộc phải có)
    public AddCustomer() {
    }

    // Constructor với tham số cho previousStage
    public AddCustomer(Stage previousStage) {
        this.previousStage = previousStage;
    }

    // Setter để gán previousStage khi cần
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Tiêu đề
        Label titleLabel = new Label("Add a Customer");
        titleLabel.setLayoutX(206);
        titleLabel.setLayoutY(27);
        titleLabel.setFont(new Font("System Bold", 25));
        titleLabel.setStyle("-fx-text-fill: #4316de;");

        // Các ô nhập liệu
        usernameField = createTextField(275, 77);
        passwordField = createTextField(275, 125);
        nameField = createTextField(275, 167);
        phoneNumberField = createTextField(275, 209);
        idCardField = createTextField(275, 250);

        // Nhãn
        root.getChildren().addAll(
                createLabel("Username", 199, 81),
                createLabel("Password", 201, 129),
                createLabel("Name", 210, 171),
                createLabel("Phone Number", 185, 213),
                createLabel("ID Card", 206, 254)
        );

        // Nút Back
        Button backButton = new Button("Back");
        backButton.setLayoutX(36);
        backButton.setLayoutY(319);
        backButton.setPrefSize(91, 25);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        // Nút Add
        Button addButton = new Button("Add");
        addButton.setLayoutX(454);
        addButton.setLayoutY(319);
        addButton.setPrefSize(81, 25);
        addButton.setOnAction(e -> handleAddButton());

        root.getChildren().addAll(titleLabel, usernameField, passwordField, nameField, phoneNumberField, idCardField, backButton, addButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Add a Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createTextField(double x, double y) {
        TextField textField = new TextField();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        return textField;
    }

    private Label createLabel(String text, double x, double y) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    private void handleBackButton(Stage currentStage) {
        currentStage.close();  // Đóng cửa sổ hiện tại
        if (previousStage != null) {
            previousStage.show();  // Hiển thị cửa sổ trước đó
        }
    }

    private void handleAddButton() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String name = nameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String idCard = idCardField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || idCard.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill in all fields!");
            return;
        }

        if (saveToCSV(username, password, name, phoneNumber, idCard)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save Customer.");
        }
    }

    private boolean saveToCSV(String username, String password, String name, String phoneNumber, String idCard) {
        String filePath = "Project-OOP-Semester-3\\src\\aims\\customers.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format("%s,%s,%s,%s,%s", username, password, name, phoneNumber, idCard));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        nameField.clear();
        phoneNumberField.clear();
        idCardField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
