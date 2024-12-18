package aims;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CusLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Title Label
        Label titleLabel = new Label("Welcome to Unlimited Team Store. Please choose one option");
        titleLabel.setLayoutX(42);
        titleLabel.setLayoutY(36);
        titleLabel.setTextFill(Color.web("#ff0bff"));
        titleLabel.setFont(new Font("System Bold", 18));

        // Buttons for "View Cars" and "View Cart"
        Button viewCarsButton = new Button("View Cars");
        viewCarsButton.setLayoutX(42);
        viewCarsButton.setLayoutY(127);
        viewCarsButton.setPrefHeight(178);
        viewCarsButton.setPrefWidth(242);
        viewCarsButton.setTextFill(Color.web("#ff0303"));
        viewCarsButton.setFont(new Font("System Bold", 30));

        Button viewCartButton = new Button("View Cart");
        viewCartButton.setLayoutX(317);
        viewCartButton.setLayoutY(127);
        viewCartButton.setPrefHeight(178);
        viewCartButton.setPrefWidth(242);
        viewCartButton.setTextFill(Color.web("#1b11d9"));
        viewCartButton.setFont(new Font("System Bold", 30));

        // Log out button
        Button logOutButton = new Button("Log out");
        logOutButton.setLayoutX(479);
        logOutButton.setLayoutY(339);
        logOutButton.setTextFill(Color.web("#30c72a"));
        logOutButton.setFont(new Font("System Bold", 16));
        logOutButton.setOnAction(e -> handleLogout(primaryStage)); // Use this action to return to LoginForm

        // Add components to the Pane
        root.getChildren().addAll(titleLabel, viewCarsButton, viewCartButton, logOutButton);

        // Set action for "View Cars" button to navigate to the ViewAndAdd screen
        viewCarsButton.setOnAction(e -> {
            ViewAndAdd viewAndAdd = new ViewAndAdd(primaryStage);  // Passing current stage to retain window context
            Stage viewAndAddStage = new Stage();
            viewAndAdd.start(viewAndAddStage);  // Open the ViewAndAdd screen
            primaryStage.close();  // Close the current CusLogin screen
        });

        // Set action for "View Cart" button to navigate to the ViewCart screen
        viewCartButton.setOnAction(e -> {
            // Assuming the customer is already logged in, pass the username to load the customer's cart
            ViewCart viewCart = new ViewCart(primaryStage, "customerUsername");  // Replace "customerUsername" with actual username
            Stage viewCartStage = new Stage();
            viewCart.start(viewCartStage);  // Open the ViewCart screen
            primaryStage.close();  // Close the current CusLogin screen
        });

        // Scene and Stage setup
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Customer Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle Log out action (return to the LoginForm)
    private void handleLogout(Stage primaryStage) {
        primaryStage.close(); // Close current window

        // Show LoginForm screen
        LoginForm loginForm = new LoginForm();
        Stage loginStage = new Stage();
        loginForm.start(loginStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
