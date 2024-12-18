package aims;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CarOwnerOption extends Application {

    String username; // Store the username of the logged-in CarOwner
    private Stage primaryStage;  // Store reference to the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Assign the primaryStage

        // Create a Pane for the layout
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Label for the greeting
        Label greetingLabel = new Label("Welcome to your Car space! Please choose these following options");
        greetingLabel.setLayoutX(26);
        greetingLabel.setLayoutY(21);
        greetingLabel.setFont(new Font("System Bold", 19));
        greetingLabel.setTextFill(Color.web("#d71414"));

        // Buttons
        Button viewCarOwners = createButton("View your cars", 111, 90, "#dd1a82");
        Button removeCarOwner = createButton("Remove a car from your list", 324, 90, "#c01f1f");
        Button addCarOwner = createButton("Add a car to the store", 111, 242, "#d70e0e");
        Button logOutButton = createButton("Log out", 484, 352, "#0da6ae");

        // Set actions for buttons
        viewCarOwners.setOnAction(e -> switchToViewOwnerCar()); // Switch to ViewOwnerCar when "View your cars" is clicked
        removeCarOwner.setOnAction(e -> switchToDeleteOwnerCar()); // Switch to DeleteOwnerCar when "Remove a car from your list" is clicked
        addCarOwner.setOnAction(e -> switchToAddCar()); // Switch to AddCar when "Add a car to the store" is clicked
        logOutButton.setOnAction(e -> handleLogout());

        // Add components to the Pane
        root.getChildren().addAll(
                greetingLabel,
                viewCarOwners,
                removeCarOwner,
                addCarOwner,
                logOutButton
        );

        // Create the Scene and add it to the Stage
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Car Owner Options");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create buttons
    private Button createButton(String text, double x, double y, String color) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(Font.font("System", 15));
        button.setTextFill(Color.web(color));
        return button;
    }

    // Method to switch to ViewOwnerCar
    private void switchToViewOwnerCar() {
        try {
            // Pass the username to the ViewOwnerCar screen
            ViewOwnerCar viewOwnerCar = new ViewOwnerCar(this.primaryStage);
            viewOwnerCar.username = this.username;
            Stage viewOwnerCarStage = new Stage();
            viewOwnerCar.start(viewOwnerCarStage);
            this.primaryStage.hide(); // Hide the current stage
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch ViewOwnerCar: " + e.getMessage());
        }
    }

    // Method to switch to AddCar
    private void switchToAddCar() {
        try {
            // Pass the username to AddCar screen
            AddCar addCar = new AddCar(this.primaryStage, this.username);
            Stage addCarStage = new Stage();
            addCar.start(addCarStage);
            this.primaryStage.hide(); // Hide the current stage
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch AddCar: " + e.getMessage());
        }
    }

    // Method to switch to DeleteOwnerCar
    private void switchToDeleteOwnerCar() {
        try {
            // Pass the username to DeleteOwnerCar
            DeleteOwnerCar deleteOwnerCar = new DeleteOwnerCar(this.primaryStage);
            deleteOwnerCar.username = this.username;  // Pass the CarOwner's username
            Stage deleteOwnerCarStage = new Stage();
            deleteOwnerCar.start(deleteOwnerCarStage);
            this.primaryStage.hide();  // Hide the current stage
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch DeleteOwnerCar: " + e.getMessage());
        }
    }

    // Handle Log out action
    private void handleLogout() {
        // Switch to the login form
        switchToLogin();
    }

    // Switch to the Login screen
    private void switchToLogin() {
        try {
            LoginForm loginForm = new LoginForm();
            Stage loginStage = new Stage();
            loginForm.start(loginStage);
            this.primaryStage.close();  // Close the CarOwnerOption stage
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch LoginForm: " + e.getMessage());
        }
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
