package aims;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import aims.AdminMethods.CarOwnerApp;
import aims.users.Admin;
import aims.users.CarOwner;
import aims.users.Customer;

public class LoginForm extends Application {

    // Class-level variable to store the primary stage
    private Stage primaryStage;

    // GUI components
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Label welcomeLabel = new Label("Welcome to Unlimited Team's Renting Car System");
    private Label usernameLabel = new Label("Username:");
    private Label passwordLabel = new Label("Password:");

    @Override
    public void start(Stage primaryStage) {
        // Assign the primary stage to the class-level variable
        this.primaryStage = primaryStage;

        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        // Set positions and styles for the GUI components
        welcomeLabel.setLayoutX(20);
        welcomeLabel.setLayoutY(27);
        welcomeLabel.setFont(new Font("System Bold", 24));
        welcomeLabel.setStyle("-fx-text-fill: #3ba362;");

        usernameLabel.setLayoutX(190);
        usernameLabel.setLayoutY(135);

        passwordLabel.setLayoutX(190);
        passwordLabel.setLayoutY(179);

        usernameField.setLayoutX(269);
        usernameField.setLayoutY(131);

        passwordField.setLayoutX(269);
        passwordField.setLayoutY(175);

        loginButton.setLayoutX(322);
        loginButton.setLayoutY(215);

        // Handle login button click
        loginButton.setOnAction(this::handleLogin);

        // Add components to the pane
        pane.getChildren().addAll(welcomeLabel, usernameLabel, passwordLabel, usernameField, passwordField, loginButton);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Unlimited Team's Renting Car System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<CarOwner> carOwners = new ArrayList<>();

        String alert = null;

        try {
            // Load Admin Accounts
            loadAccounts("Project-OOP-Semester-3\\src\\aims\\admins.csv", admins, null, null);
            // Load Customer Accounts
            loadAccounts("Project-OOP-Semester-3\\src\\aims\\customers.csv", null, customers, null);
            // Load CarOwner Accounts
            loadAccounts("Project-OOP-Semester-3\\src\\aims\\carOwners.csv", null, null, carOwners);

            for (Admin admin : admins) {
                if (admin.login(username, password)) {
                    alert = "Admin";
                    break;
                }
            }

            for (Customer customer : customers) {
                if (customer.login(username, password)) {
                    alert = "Customer";
                    break;
                }
            }

            for (CarOwner carOwner : carOwners) {
                if (carOwner.login(username, password)) {
                    alert = "CarOwner";
                    break;
                }
            }

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user data: " + e.getMessage());
            return;
        }

        if (alert != null) {
            if (alert.equals("Admin")) {
                switchToCarOwnerApp();
            } 
            else if(alert.equals("CarOwner")){
                switchToCarOwnerOption();
            }
            else if(alert.equals("Customer")){
                switchToCusLogin();
            }
            else {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + alert + " " + username + "!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    private void switchToCarOwnerOption(){
        primaryStage.close();

        // Open the CarOwnerApp stage
        CarOwnerOption carOwnerOption = new CarOwnerOption();
        carOwnerOption.username = usernameField.getText();
        Stage carOwnerStage = new Stage();
        try {
            carOwnerOption.start(carOwnerStage);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch CarOwnerApp: " + e.getMessage());
        }
    }

    private void switchToCarOwnerApp() {
        // Close the current stage
        primaryStage.close();

        // Open the CarOwnerApp stage
        CarOwnerApp carOwnerApp = new CarOwnerApp();
        Stage carOwnerStage = new Stage();
        try {
            carOwnerApp.start(carOwnerStage);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to launch CarOwnerApp: " + e.getMessage());
        }
    }
    private void switchToCusLogin() {
        primaryStage.close();

        // Open the CusLogin stage
        CusLogin cusLogin = new CusLogin();
        cusLogin.username =  usernameField.getText();
        Stage cusLoginStage = new Stage();
        cusLogin.start(cusLoginStage);
    }

    private void loadAccounts(String filePath, ArrayList<Admin> admins, ArrayList<Customer> customers, ArrayList<CarOwner> carOwners) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (admins != null && parts.length >= 2) {
                    admins.add(new Admin(parts[0], parts[1]));
                } else if (customers != null && parts.length >= 5) {
                    customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]));
                } else if (carOwners != null && parts.length >= 4) {
                    carOwners.add(new CarOwner(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        }
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
