package aims;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddCar extends Application {

    private Stage previousStage; // Store reference to the previous stage
    private TextField nameField, licensePlateField, brandField, typeField, yearField, priceField;
    String username;

    // Constructor accepting previousStage and username
    public AddCar(Stage previousStage, String username) {
        this.previousStage = previousStage;  // Store the reference of the previous stage
        this.username = username;  // Store the username of the logged-in CarOwner
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Title Label
        Label titleLabel = new Label("Please type your car's information");
        titleLabel.setLayoutX(115);
        titleLabel.setLayoutY(33);
        titleLabel.setFont(new Font("System Bold", 23));
        titleLabel.setStyle("-fx-text-fill: #b11111");

        // Input fields
        nameField = createTextField(226, 84);
        licensePlateField = createTextField(226, 127);
        brandField = createTextField(226, 167);
        typeField = createTextField(226, 208);
        yearField = createTextField(226, 245);
        priceField = createTextField(226, 284);

        // Labels for input fields
        root.getChildren().addAll(
                createLabel("Name", 142, 88),
                createLabel("License Plate", 124, 131),
                createLabel("Brand", 142, 171),
                createLabel("Type", 145, 212),
                createLabel("Manufacturing Year", 113, 249),
                createLabel("Price", 145, 288)
        );

        // Buttons
        Button backButton = new Button("Back");
        backButton.setLayoutX(34);
        backButton.setLayoutY(341);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        Button addCarButton = new Button("Add Car");
        addCarButton.setLayoutX(485);
        addCarButton.setLayoutY(341);
        addCarButton.setOnAction(e -> handleAddCarButton());

        // Add components to the Pane
        root.getChildren().addAll(titleLabel, nameField, licensePlateField, brandField, typeField, yearField, priceField, backButton, addCarButton);

        // Scene and Stage setup
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add a Car");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper methods to create TextField and Label
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

    // Handle the Back button action (return to the previous stage)
    private void handleBackButton(Stage currentStage) {
        currentStage.close();
        if (previousStage != null) {
            previousStage.show();
        }
    }

    // Handle Add Car button action (save car information)
    private void handleAddCarButton() {
        String name = nameField.getText().trim();
        String licensePlate = licensePlateField.getText().trim();
        String brand = brandField.getText().trim();
        String type = typeField.getText().trim();
        String year = yearField.getText().trim();
        String price = priceField.getText().trim();

        if (name.isEmpty() || licensePlate.isEmpty() || brand.isEmpty() || type.isEmpty() || year.isEmpty() || price.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill in all fields!");
            return;
        }


        // Save the car data to the file
        String userCarFilePath = "CarOwner_" + username + ".csv";
        String globalCarFilePath = "Project-OOP-Semester-3\\src\\aims\\Car.csv"; // This is the global car file

        // Save the car data to the CarOwner's file and global car file
        boolean isAddedToCarOwnerFile = saveCarToCSV(userCarFilePath, name, licensePlate, brand, type, year, price, false);
        boolean isAddedToGlobalCarFile = saveCarToCSV(globalCarFilePath, name, licensePlate, brand, type, year, price, true);

        if (isAddedToCarOwnerFile && isAddedToGlobalCarFile) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Car added successfully to both your personal and global car lists!");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add the car.");
        }
    }

    // Save car data to CSV
    private boolean saveCarToCSV(String filePath, String name, String licensePlate, String brand, String type, String year, String price, boolean includeStatus) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (includeStatus) {
                // Write with "available" status for global file
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s", name, licensePlate, brand, type, year, price, "available"));
            } else {
                // Write without "available" status for individual CarOwner file
                writer.write(String.format("%s,%s,%s,%s,%s,%s", name, licensePlate, brand, type, year, price));
            }
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Clear the input fields
    private void clearFields() {
        nameField.clear();
        licensePlateField.clear();
        brandField.clear();
        typeField.clear();
        yearField.clear();
        priceField.clear();
    }

    // Show alert method
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
