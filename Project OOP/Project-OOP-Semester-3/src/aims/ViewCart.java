package aims;

import aims.car.Car;
import aims.users.Customer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ViewCart extends Application {

    private Stage previousStage;
    private String username;

    // Default constructor
    public ViewCart() {}

    // Constructor that accepts a previousStage
    public ViewCart(Stage previousStage, String username) {
        this.previousStage = previousStage;
        this.username = username;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Title Label
        Label titleLabel = new Label("Your Cart has these following cars");
        titleLabel.setLayoutX(123.0);
        titleLabel.setLayoutY(14.0);
        titleLabel.setTextFill(javafx.scene.paint.Color.web("#797d23"));
        titleLabel.setFont(new Font("Agency FB Bold", 30));

        // TableView for displaying cars
        TableView<Car> tableView = new TableView<>();
        tableView.setLayoutX(4.0);
        tableView.setLayoutY(51.0);
        tableView.setPrefSize(593.0, 223.0);

        // Define Table Columns
        TableColumn<Car, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(tableView.getItems().indexOf(cellData.getValue()) + 1)));
        colID.setPrefWidth(36.67);

        TableColumn<Car, String> colName = new TableColumn<>("Car Name");
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colName.setPrefWidth(114.67);

        TableColumn<Car, String> colLicensePlate = new TableColumn<>("License Plate");
        colLicensePlate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLicensePlate()));
        colLicensePlate.setPrefWidth(96.67);

        TableColumn<Car, String> colBrand = new TableColumn<>("Brand");
        colBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        colBrand.setPrefWidth(75.33);

        TableColumn<Car, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        colType.setPrefWidth(71.33);

        TableColumn<Car, String> colYear = new TableColumn<>("Manufacturing Year");
        colYear.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getManufacturingYear())));
        colYear.setPrefWidth(120.67);

        TableColumn<Car, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRentalPrice())));
        colPrice.setPrefWidth(78.67);

        tableView.getColumns().addAll(colID, colName, colLicensePlate, colBrand, colType, colYear, colPrice);

        // Load data from customer's cart file
        ObservableList<Car> data = loadCustomerCart("Customer_" + username + ".csv");
        tableView.setItems(data);

        // Input Field for Car ID
        TextField carIDField = new TextField();
        carIDField.setLayoutX(391.0);
        carIDField.setLayoutY(301.0);

        // Label for Car ID Input
        Label enterIDLabel = new Label("Enter ID");
        enterIDLabel.setLayoutX(326.0);
        enterIDLabel.setLayoutY(301.0);
        enterIDLabel.setFont(new Font("System Bold", 16));

        // Remove Car Button
        Button removeCarButton = new Button("Remove Car");
        removeCarButton.setLayoutX(466.0);
        removeCarButton.setLayoutY(338.0);
        removeCarButton.setFont(new Font("System Bold", 15));
        removeCarButton.setOnAction(e -> handleRemoveCarButton(carIDField.getText(), tableView));

        // Back Button
        Button backButton = new Button("Back");
        backButton.setLayoutX(14.0);
        backButton.setLayoutY(338.0);
        backButton.setFont(new Font("System Bold", 15));
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        root.getChildren().addAll(titleLabel, tableView, carIDField, enterIDLabel, removeCarButton, backButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("View Cart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Load data from the customer's cart file
    private ObservableList<Car> loadCustomerCart(String filePath) {
        ObservableList<Car> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 6) {
                    data.add(new Car(attributes[0], attributes[1], attributes[2], attributes[3],
                            Integer.parseInt(attributes[4]), Float.parseFloat(attributes[5])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customer cart: " + e.getMessage());
        }
        return data;
    }

    // Handle the Remove Car button click (Remove car from the cart)
    private void handleRemoveCarButton(String carID, TableView<Car> tableView) {
        ObservableList<Car> data = tableView.getItems();
        Car carToRemove = null;

        try {
            int index = Integer.parseInt(carID) - 1; // Convert to zero-based index
            if (index >= 0 && index < data.size()) {
                carToRemove = data.get(index);
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid ID format. Please enter a valid number.");
            return;
        }

        if (carToRemove != null) {
            data.remove(carToRemove);
            updateCustomerCartFile(data);  // Update the cart file after removing the car
            updateCarStatusInFile(carToRemove.getLicensePlate(), "available"); // Update the status in Car.csv
            showAlert(Alert.AlertType.INFORMATION, "Success", "Car removed from your cart.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Car ID not found in your cart.");
        }
    }

    // Update the customer's cart file
    private void updateCustomerCartFile(ObservableList<Car> data) {
        String fileName = "Customer_" + username + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : data) {
                writer.write(String.format("%s,%s,%s,%s,%d,%.2f%n",
                        car.getName(), car.getLicensePlate(), car.getBrand(), car.getType(),
                        car.getManufacturingYear(), car.getRentalPrice()));
            }
        } catch (IOException e) {
            System.out.println("Error saving updated cart: " + e.getMessage());
        }
    }

    // Update the status of the car in Car.csv
    private void updateCarStatusInFile(String licensePlate, String status) {
        File carFile = new File("Project-OOP-Semester-3\\src\\aims\\Car.csv");
        File tempFile = new File("Project-OOP-Semester-3\\src\\aims\\Car_temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(carFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[1].equals(licensePlate)) {
                    attributes[6] = status; // Update the status to "available"
                }
                writer.write(String.join(",", attributes) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating car status: " + e.getMessage());
        }

        // Replace original file with the updated file
        if (!carFile.delete() || !tempFile.renameTo(carFile)) {
            System.out.println("Error replacing car file.");
        }
    }

    // Handle Back button action (return to the previous stage)
    private void handleBackButton(Stage currentStage) {
        currentStage.close();
        if (previousStage != null) {
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
}
