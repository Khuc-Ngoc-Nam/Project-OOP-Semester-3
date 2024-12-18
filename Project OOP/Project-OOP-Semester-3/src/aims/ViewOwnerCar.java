package aims;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewOwnerCar extends Application {

    private Stage previousStage;  // Reference to the previous stage
    String username;

    // Default constructor
    public ViewOwnerCar() {}

    // Constructor that accepts a previousStage
    public ViewOwnerCar(Stage previousStage) {
        this.previousStage = previousStage;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create Pane for layout
        Pane root = new Pane();
        root.setPrefSize(737, 400);

        // Title Label
        Label titleLabel = new Label("There are your cars in our App");
        titleLabel.setLayoutX(180);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold Italic", 26));
        titleLabel.setStyle("-fx-text-fill: #2211d9");

        // TableView for displaying cars
        TableView<Car> tableView = new TableView<>();
        tableView.setLayoutX(5);
        tableView.setLayoutY(67);
        tableView.setPrefSize(727, 244);

        // Define Table Columns
        TableColumn<Car, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colID.setPrefWidth(46);

        TableColumn<Car, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(84);

        TableColumn<Car, String> colLicensePlate = new TableColumn<>("License Plate");
        colLicensePlate.setCellValueFactory(cellData -> cellData.getValue().licensePlateProperty());
        colLicensePlate.setPrefWidth(109);

        TableColumn<Car, String> colBrand = new TableColumn<>("Brand");
        colBrand.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        colBrand.setPrefWidth(83);

        TableColumn<Car, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colType.setPrefWidth(67);

        TableColumn<Car, String> colYear = new TableColumn<>("Manufacturing Year");
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        colYear.setPrefWidth(123);

        TableColumn<Car, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        colPrice.setPrefWidth(72);

        TableColumn<Car, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        colStatus.setPrefWidth(138);

        // Add columns to TableView
        tableView.getColumns().addAll(colID, colName, colLicensePlate, colBrand, colType, colYear, colPrice, colStatus);

        // Load data from CSV
        ObservableList<Car> data = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\cars.csv");
        tableView.setItems(data);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setLayoutX(514);
        backButton.setLayoutY(338);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        // Add components to the Pane
        root.getChildren().addAll(titleLabel, tableView, backButton);

        // Scene and Stage setup
        Scene scene = new Scene(root);
        primaryStage.setTitle("Car Owner Cars");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle Back button action (return to the previous stage)
    private void handleBackButton(Stage currentStage) {
        currentStage.close();
        if (previousStage != null) {
            previousStage.show();
        }
    }

    // Load car data from CSV
    private ObservableList<Car> loadDataFromCSV(String filePath) {
        ObservableList<Car> data = FXCollections.observableArrayList();

        // Simulate getting the current CarOwner's username
        String currentCarOwnerUsername = this.username;  // Example username, should be from the actual logged-in CarOwner

        // Construct the file path for the CarOwner's cars data
        String userCarFilePath = "CarOwner_" + currentCarOwnerUsername + ".csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(userCarFilePath))) {
            String line;
            String idx = "1";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {  // Validate data
                    data.add(new Car(idx, parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    int id = Integer.parseInt(idx);
                    id++;
                    idx = Integer.toString(id);
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load car data: " + e.getMessage());
        }

        return data;
    }

    // Show alert method
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Car class representing a car owned by the CarOwner
    public static class Car {
        private String id;
        private String name;
        private String licensePlate;
        private String brand;
        private String type;
        private String year;
        private String price;
        private String status;

        public Car(String id, String name, String licensePlate, String brand, String type, String year, String price) {
            this.id = id;
            this.name = name;
            this.licensePlate = licensePlate;
            this.brand = brand;
            this.type = type;
            this.year = year;
            this.price = price;
            this.status = "available";  // Default status
        }

        // Property methods for TableView binding
        public javafx.beans.property.SimpleStringProperty idProperty() {
            return new javafx.beans.property.SimpleStringProperty(id);
        }

        public javafx.beans.property.SimpleStringProperty nameProperty() {
            return new javafx.beans.property.SimpleStringProperty(name);
        }

        public javafx.beans.property.SimpleStringProperty licensePlateProperty() {
            return new javafx.beans.property.SimpleStringProperty(licensePlate);
        }

        public javafx.beans.property.SimpleStringProperty brandProperty() {
            return new javafx.beans.property.SimpleStringProperty(brand);
        }

        public javafx.beans.property.SimpleStringProperty typeProperty() {
            return new javafx.beans.property.SimpleStringProperty(type);
        }

        public javafx.beans.property.SimpleStringProperty yearProperty() {
            return new javafx.beans.property.SimpleStringProperty(year);
        }

        public javafx.beans.property.SimpleStringProperty priceProperty() {
            return new javafx.beans.property.SimpleStringProperty(price);
        }

        public javafx.beans.property.SimpleStringProperty statusProperty() {
            return new javafx.beans.property.SimpleStringProperty(status);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
