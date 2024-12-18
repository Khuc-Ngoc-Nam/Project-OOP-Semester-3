package aims;

import aims.car.Car;
import aims.cart.Cart;
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
import java.util.Scanner;

public class ViewAndAdd extends Application {

    private Stage previousStage;  // Reference to the previous stage
    String username;

    // Default constructor
    public ViewAndAdd() {}

    // Constructor that accepts a previousStage
    public ViewAndAdd(Stage previousStage) {
        this.previousStage = previousStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(636, 400);

        // Title Label
        Label titleLabel = new Label("Our Store");
        titleLabel.setLayoutX(259);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold", 25));

        // TableView for displaying cars
        TableView<CarTemp> tableView = new TableView<>();
        tableView.setLayoutX(12);
        tableView.setLayoutY(50);
        tableView.setPrefSize(608, 249);

        // Define Table Columns
        TableColumn<CarTemp, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colID.setPrefWidth(75);

        TableColumn<CarTemp, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(75);

        TableColumn<CarTemp, String> colLicensePlate = new TableColumn<>("License Plate");
        colLicensePlate.setCellValueFactory(cellData -> cellData.getValue().licensePlateProperty());
        colLicensePlate.setPrefWidth(75);

        TableColumn<CarTemp, String> colBrand = new TableColumn<>("Brand");
        colBrand.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        colBrand.setPrefWidth(75);

        TableColumn<CarTemp, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colType.setPrefWidth(75);

        TableColumn<CarTemp, String> colYear = new TableColumn<>("Year");
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        colYear.setPrefWidth(75);

        TableColumn<CarTemp, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        colPrice.setPrefWidth(75);

        TableColumn<CarTemp, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        colStatus.setPrefWidth(79);

        tableView.getColumns().addAll(colID, colName, colLicensePlate, colBrand, colType, colYear, colPrice, colStatus);

        // Load data from CSV
        ObservableList<CarTemp> data = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\Car.csv");
        tableView.setItems(data);

        // Input Field for Car ID
        TextField carIDField = new TextField();
        carIDField.setLayoutX(432);
        carIDField.setLayoutY(310);

        // Label for Car ID Input
        Label enterIDLabel = new Label("Enter ID");
        enterIDLabel.setLayoutX(372);
        enterIDLabel.setLayoutY(313);
        enterIDLabel.setFont(new Font("System Bold Italic", 14));

        // Rent Button to Add Car to Cart
        Button rentButton = new Button("Rent");
        rentButton.setLayoutX(540);
        rentButton.setLayoutY(349);
        rentButton.setOnAction(e -> handleRentButton(carIDField.getText()));

        // Back Button
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(361);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        root.getChildren().addAll(titleLabel, tableView, carIDField, enterIDLabel, rentButton, backButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("View and Add Car");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Load data from the car CSV file
    private ObservableList<CarTemp> loadDataFromCSV(String filePath) {
        ObservableList<CarTemp> data = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String idx = "1";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {  // Đảm bảo rằng có đủ 7 cột trong mỗi dòng
                    // Tạo một đối tượng Car với 7 giá trị từ file CSV
                    data.add(new CarTemp(idx, parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));  // Gửi đủ 7 tham số
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

    // Handle the Rent button click (Add the car to the cart)
    private void handleRentButton(String carID) {
        // Assuming Cart and Customer classes have relevant methods to add a car to the cart
        Cart cart = new Cart();  // Assuming Cart object
        Customer customer = new Customer(username, "password", "phone", "email", "id");  // Assuming Customer object

        // Step 1: Find the car in Car.csv and update its status to "rented"
        CarTemp carToRent = findCarInStore(carID);
        if (carToRent == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Car not found or already rented.");
            return;
        }
        Car c = new Car(carToRent.name, carToRent.licensePlate, carToRent.brand, carToRent.type, Integer.parseInt(carToRent.year), Float.parseFloat(carToRent.price));

        // Step 2: Add the car to the customer's cart
        cart.addCarToCart(c);  // Add the car to cart

        // Step 3: Update the car's status in the store and in the file
        updateCarStatusInFile(carID, "rented");

        // Step 4: Add the car to the customer's file
        addCarToCustomerFile(customer, carToRent);

        // Show success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Car added to your cart and rented.");
    }

    // Find the car in Car.csv based on the carID
    private CarTemp findCarInStore(String carID) {
        File carFile = new File("Project-OOP-Semester-3\\src\\aims\\Car.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(carFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[6].equals("available")) {
                    return new CarTemp(carID, attributes[0], attributes[1], attributes[2], attributes[3],
                            attributes[4], attributes[5], attributes[6]);

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading car data: " + e.getMessage());
        }
        return null;
    }

    // Update the car status in Car.csv (from available to rented)
    private void updateCarStatusInFile(String carID, String status) {
        File originalFile = new File("Project-OOP-Semester-3\\src\\aims\\Car.csv");
        File tempFile = new File("Project-OOP-Semester-3\\src\\aims\\Car.csv.tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7 && attributes[1].equals(carID)) {
                    attributes[6] = status;  // Update car status
                }
                writer.write(String.join(",", attributes) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating car status in file: " + e.getMessage());
            return;
        }

        // Finalize the update by replacing the original file
        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            System.out.println("Error finalizing car file update.");
        }
    }

    // Add the car to the customer's file
    private void addCarToCustomerFile(Customer customer, CarTemp car) {
        String fileName = "Project-OOP-Semester-3\\src\\aims\\Customer_" + customer.getUsername() + ".csv";
        File customerFile = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile, true))) {
            // Check if the file doesn't exist, create it
            if (!customerFile.exists()) {
                customerFile.createNewFile();
            }

            // Add car details to the customer's file
            writer.write(String.format("%s,%s,%s,%s,%s,%s",
                    car.name, car.licensePlate, car.brand, car.type,
                    car.year, car.price));
        } catch (IOException e) {
            System.out.println("Error saving car to customer file: " + e.getMessage());
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

    // Main class to run the program
    public static void main(String[] args) {
        launch(args);
    }

    // Car class representing a car in the store
    public class CarTemp {
        private String id;
        private String name;
        private String licensePlate;
        private String brand;
        private String type;
        private String year;
        private String price;
        private String status;
    
        // Constructor nhận 7 tham số
        public CarTemp(String id, String name, String licensePlate, String brand, String type, String year, String price, String status) {
            this.id = id;
            this.name = name;
            this.licensePlate = licensePlate;
            this.brand = brand;
            this.type = type;
            this.year = year;
            this.price = price;
            this.status = status;
        }
    
        // Property methods cho TableView binding
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
}        