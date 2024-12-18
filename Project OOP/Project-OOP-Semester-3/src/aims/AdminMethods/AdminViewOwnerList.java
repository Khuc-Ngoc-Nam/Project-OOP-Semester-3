package aims.AdminMethods;

import aims.users.CarOwner;  // Import CarOwner from aims.users
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminViewOwnerList extends Application {

    private Stage previousStage;  // Store reference to the previous stage

    @FXML
    private TableView<CarOwnerTemp> tableView;
    @FXML
    private TableColumn<CarOwnerTemp, String> colNo;
    @FXML
    private TableColumn<CarOwnerTemp, String> colUsername;
    @FXML
    private TableColumn<CarOwnerTemp, String> colPassword;
    @FXML
    private TableColumn<CarOwnerTemp, String> colName;
    @FXML
    private TableColumn<CarOwnerTemp, String> colPhoneNumber;

    @FXML
    private Button backButton;
    @FXML
    private Button prevPageButton;
    @FXML
    private Button nextPageButton;

    public AdminViewOwnerList(Stage previousStage) {
        this.previousStage = previousStage;  // Store the reference of the previous stage
    }

    @Override
    public void start(Stage primaryStage) {
        // Create Pane for layout
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // TableView for Car Owner Data
        tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setLayoutX(67);
        tableView.setLayoutY(53);
        tableView.setPrefSize(467, 304);

        // Define Table Columns
        colNo = new TableColumn<>("N.o");
        colNo.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        colNo.setPrefWidth(40);

        colUsername = new TableColumn<>("Username");
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colUsername.setPrefWidth(90);

        colPassword = new TableColumn<>("Password");
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colPassword.setPrefWidth(100);

        colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(98);

        colPhoneNumber = new TableColumn<>("Phone Number");
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colPhoneNumber.setPrefWidth(142);

        // Add Columns to TableView
        tableView.getColumns().addAll(colNo, colUsername, colPassword, colName, colPhoneNumber);

        // Load data from CarOwner.csv
        ObservableList<CarOwnerTemp> data = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\carOwners.csv");
        tableView.setItems(data);

        // Label for Title
        Label titleLabel = new Label("Car Owner List");
        titleLabel.setLayoutX(218);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold", 24));
        titleLabel.setStyle("-fx-text-fill: #0600c3;");

        // Buttons for navigation
        backButton = new Button("Back");
        backButton.setLayoutX(493);
        backButton.setLayoutY(361);
        backButton.setOnAction(e -> handleBackButton());  // Add event for back button

        prevPageButton = new Button("Prev page");
        prevPageButton.setLayoutX(74);
        prevPageButton.setLayoutY(361);

        nextPageButton = new Button("Next page");
        nextPageButton.setLayoutX(159);
        nextPageButton.setLayoutY(361);

        // Add all components to the Pane
                // Add all components to the Pane
                root.getChildren().addAll(tableView, titleLabel, backButton, prevPageButton, nextPageButton);

                // Scene and Stage setup
                Scene scene = new Scene(root);
                primaryStage.setTitle("Car Owner List");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        
            // Load data from carOwners.csv
            private ObservableList<CarOwnerTemp> loadDataFromCSV(String filePath) {
                ObservableList<CarOwnerTemp> data = FXCollections.observableArrayList();
        
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    String idx = "1";  // Start the "No." index from 1
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 4) {  // Ensure that the data is valid
                            data.add(new CarOwnerTemp(idx, parts[0], parts[1], parts[2], parts[3]));
                            int id = Integer.parseInt(idx);
                            id++;
                            idx = Integer.toString(id);
                        }
                    }
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data from file: " + e.getMessage());
                }
        
                return data;
            }
        
            // Handle the Back button action (return to the previous stage)
            @FXML
            private void handleBackButton() {
                if (previousStage != null) {
                    // Close the current stage (AdminViewOwnerList)
                    ((Stage) backButton.getScene().getWindow()).close();
        
                    // Show the previous stage (previous window)
                    previousStage.show();
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
        
            // A placeholder class for Car Owner Data
            public static class CarOwnerTemp {
                private String no;
                private String username;
                private String password;
                private String name;
                private String phoneNumber;
        
                public CarOwnerTemp(String no, String username, String password, String name, String phoneNumber) {
                    this.no = no;
                    this.username = username;
                    this.password = password;
                    this.name = name;
                    this.phoneNumber = phoneNumber;
                }
        
                // Properties for TableView binding
                public String getNo() {
                    return no;
                }
        
                public String getUsername() {
                    return username;
                }
        
                public String getPassword() {
                    return password;
                }
        
                public String getName() {
                    return name;
                }
        
                public String getPhoneNumber() {
                    return phoneNumber;
                }
        
                // Property methods for TableView binding
                public javafx.beans.property.SimpleStringProperty noProperty() {
                    return new javafx.beans.property.SimpleStringProperty(no);
                }
        
                public javafx.beans.property.SimpleStringProperty usernameProperty() {
                    return new javafx.beans.property.SimpleStringProperty(username);
                }
        
                public javafx.beans.property.SimpleStringProperty passwordProperty() {
                    return new javafx.beans.property.SimpleStringProperty(password);
                }
        
                public javafx.beans.property.SimpleStringProperty nameProperty() {
                    return new javafx.beans.property.SimpleStringProperty(name);
                }
        
                public javafx.beans.property.SimpleStringProperty phoneNumberProperty() {
                    return new javafx.beans.property.SimpleStringProperty(phoneNumber);
                }
            }
        }
        
