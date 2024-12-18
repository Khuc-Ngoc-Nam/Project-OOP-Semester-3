package aims;

import aims.car.Car;
import aims.users.Customer;
import javafx.application.Application;
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
        colID.setCellValueFactory(cellData -> cellData.getValue().carID);
        colID.setPrefWidth(36.67);

        TableColumn<Car, String> colName = new TableColumn<>("Car Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().name);
        colName.setPrefWidth(114.67);

        TableColumn<Car, String> colLicensePlate = new TableColumn<>("License Plate");
        colLicensePlate.setCellValueFactory(cellData -> cellData.getValue().licensePlate);
        colLicensePlate.setPrefWidth(96.67);

        TableColumn<Car, String> colBrand = new TableColumn<>("Brand");
        colBrand.setCellValueFactory(cellData -> cellData.getValue().brand);
        colBrand.setPrefWidth(75.33);

        TableColumn<Car, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(cellData -> cellData.getValue().type);
        colType.setPrefWidth(71.33);

        TableColumn<Car, String> colYear = new TableColumn<>("Manufactoring Year");
        colYear.setCellValueFactory(cellData -> Integer.toString(cellData.getValue().manufacturingYear));
        colYear.setPrefWidth(120.67);

        TableColumn<Car, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData -> Float.toString(cellData.getValue().rentalPrice) );
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

        // Find the car to remove from the cart
        for (Car car : data) {
            if (car.getLicensePlate().equals(carID)) {
                carToRemove = car;
                break;
            }
        }

        if (carToRemove != null) {
            data.remove(carToRemove);
            updateCustomerCartFile(data);  // Update the cart file after removing the car
            showAlert(Alert.AlertType.INFORMATION, "Success", "Car removed from your cart.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Car ID not found in your cart.");
        }
    }

    // Update the customer's cart file
    private void updateCustomerCartFile(ObservableList<Car> data) {
        String fileName = "Project-OOP-Semester-3\\src\\aims\\Customer_" + username + ".csv";
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
