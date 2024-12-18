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

public class CreateCarOwner extends Application {

    private Stage previousStage;  // Tham chiếu đến cửa sổ trước đó
    private TextField usernameField, passwordField, nameField, phoneNumberField;

    public CreateCarOwner(Stage previousStage) {
        this.previousStage = previousStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Tiêu đề
        Label titleLabel = new Label("Create a new Car Owner");
        titleLabel.setLayoutX(159);
        titleLabel.setLayoutY(32);
        titleLabel.setFont(new Font("System Bold", 25));
        titleLabel.setStyle("-fx-text-fill: #c91818;");

        // Các ô nhập liệu
        usernameField = createTextField(226, 123);
        passwordField = createTextField(226, 160);
        nameField = createTextField(226, 200);
        phoneNumberField = createTextField(226, 236);

        // Nhãn
        root.getChildren().addAll(
                createLabel("Username", 163, 127),
                createLabel("Password", 165, 164),
                createLabel("Name", 174, 204),
                createLabel("Phone Number", 131, 240)
        );

        // Nút Back
        Button backButton = new Button("Back");
        backButton.setLayoutX(25);
        backButton.setLayoutY(352);
        backButton.setOnAction(e -> {
            primaryStage.close();
            previousStage.show();
        });

        // Nút Accept
        Button acceptButton = new Button("Accept");
        acceptButton.setLayoutX(506);
        acceptButton.setLayoutY(352);
        acceptButton.setOnAction(e -> handleAccept());

        root.getChildren().addAll(titleLabel, usernameField, passwordField, nameField, phoneNumberField, backButton, acceptButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Create New Car Owner");
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

    private void handleAccept() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String name = nameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill in all fields!");
            return;
        }

        if (saveToCSV(username, password, name, phoneNumber)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Car Owner added successfully!");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save Car Owner.");
        }
    }

    private boolean saveToCSV(String username, String password, String name, String phoneNumber) {
        String filePath = "Project-OOP-Semester-3\\src\\aims\\carOwners.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format("%s,%s,%s,%s", username, password, name, phoneNumber));
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
