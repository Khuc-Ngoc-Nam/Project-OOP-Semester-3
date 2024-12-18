package aims.AdminMethods;

import aims.LoginForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CarOwnerApp extends Application {

    private Stage primaryStage;  // Store reference to the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Assign the primaryStage

        // Create a Pane for the layout
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Label for the greeting
        Label greetingLabel = new Label("Hello Unlimited Team, please choose one of these following options");
        greetingLabel.setLayoutX(20);
        greetingLabel.setLayoutY(20);
        greetingLabel.setFont(Font.font("System", 18));
        greetingLabel.setTextFill(Color.web("#2605ff"));

        // Buttons
        Button viewCarOwners = createButton("View Car Owners", 230, 80, "#e21818");
        viewCarOwners.setOnAction(e -> switchToAdminViewOwnerList());

        Button addCarOwner = createButton("Add Car Owner", 230, 130, "#5711f0");
        addCarOwner.setOnAction(e -> switchToCreateCarOwner());

        Button deleteCarOwner = createButton("Delete Car Owner", 230, 180, "#000000");
        deleteCarOwner.setOnAction(e -> switchToDeleteCarOwner());

        Button viewCustomers = createButton("View Customers", 230, 230, "#16d00c");
        viewCustomers.setOnAction(e -> switchToAdminViewCustomer());

        Button addCustomer = createButton("Add Customer", 230, 280, "#baa807");
        addCustomer.setOnAction(e -> switchToAddCustomer());

        Button deleteCustomer = createButton("Delete Customer", 230, 330, "#ff05d9");
        deleteCustomer.setOnAction(e -> switchToDeleteCustomer());

        Button logOut = createButton("Log Out", 230, 380, "#0da6ae");
        logOut.setOnAction(e -> handleLogout());

        // Add components to the Pane
        root.getChildren().addAll(
                greetingLabel,
                viewCarOwners,
                addCarOwner,
                deleteCarOwner,
                viewCustomers,
                addCustomer,
                deleteCustomer,
                logOut
        );

        // Create the Scene and add it to the Stage
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Car Owner Management");
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

    // Method to switch to AdminViewOwnerList
    private void switchToAdminViewOwnerList() {
        try {
            AdminViewOwnerList adminViewOwnerList = new AdminViewOwnerList(this.primaryStage);
            Stage adminViewOwnerStage = new Stage();
            adminViewOwnerList.start(adminViewOwnerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch AdminViewOwnerList: " + e.getMessage());
        }
    }

    // Method to switch to CreateCarOwner
    private void switchToCreateCarOwner() {
        try {
            CreateCarOwner createCarOwner = new CreateCarOwner(this.primaryStage);
            Stage createCarOwnerStage = new Stage();
            createCarOwner.start(createCarOwnerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch CreateCarOwner: " + e.getMessage());
        }
    }

    // Method to switch to DeleteCarOwner
    private void switchToDeleteCarOwner() {
        try {
            DeleteCarOwner deleteCarOwner = new DeleteCarOwner(this.primaryStage);
            Stage deleteCarOwnerStage = new Stage();
            deleteCarOwner.start(deleteCarOwnerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch DeleteCarOwner: " + e.getMessage());
        }
    }

    // Method to switch to AdminViewCustomer
    private void switchToAdminViewCustomer() {
        try {
            AdminViewCustomer adminViewCustomer = new AdminViewCustomer(this.primaryStage);
            Stage adminViewCustomerStage = new Stage();
            adminViewCustomer.start(adminViewCustomerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch AdminViewCustomer: " + e.getMessage());
        }
    }

    // Method to switch to AddCustomer
    private void switchToAddCustomer() {
        try {
            AddCustomer addCustomer = new AddCustomer(this.primaryStage);
            Stage addCustomerStage = new Stage();
            addCustomer.start(addCustomerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch AddCustomer: " + e.getMessage());
        }
    }

    // Method to switch to DeleteCustomer
    private void switchToDeleteCustomer() {
        try {
            DeleteCustomer deleteCustomer = new DeleteCustomer(this.primaryStage);
            Stage deleteCustomerStage = new Stage();
            deleteCustomer.start(deleteCustomerStage);
            this.primaryStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch DeleteCustomer: " + e.getMessage());
        }
    }

    // Method to handle Logout
    private void handleLogout() {
        showAlert(Alert.AlertType.INFORMATION, "Logged Out", "You have been logged out successfully.");
        switchToLogin();  // Quay lại màn hình đăng nhập
    }

    // Switch to Login screen
    private void switchToLogin() {
        try {
            LoginForm loginForm = new LoginForm();
            Stage loginStage = new Stage();
            loginForm.start(loginStage);
            this.primaryStage.close();
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
