package aims.AdminMethods;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class DeleteCustomer extends Application {

    private Stage previousStage;  // Tham chiếu cửa sổ trước đó
    private TableView<CustomerTemp> tableView;
    private TextField indexField;
    private ObservableList<CustomerTemp> customerList;

    // Constructor mặc định
    public DeleteCustomer() {
    }

    // Constructor với tham số cho previousStage
    public DeleteCustomer(Stage previousStage) {
        this.previousStage = previousStage;
    }

    // Setter cho previousStage nếu cần
    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Tiêu đề
        Label titleLabel = new Label("Delete a Customer");
        titleLabel.setLayoutX(192);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold", 25));
        titleLabel.setStyle("-fx-text-fill: #c51717;");

        // TableView hiển thị Customer
        tableView = new TableView<>();
        tableView.setLayoutX(28);
        tableView.setLayoutY(59);
        tableView.setPrefSize(544, 241);

        TableColumn<CustomerTemp, String> colNo = new TableColumn<>("No");
        colNo.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        colNo.setPrefWidth(42);

        TableColumn<CustomerTemp, String> colUsername = new TableColumn<>("Username");
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colUsername.setPrefWidth(88);

        TableColumn<CustomerTemp, String> colPassword = new TableColumn<>("Password");
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colPassword.setPrefWidth(92);

        TableColumn<CustomerTemp, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(78);

        TableColumn<CustomerTemp, String> colPhoneNumber = new TableColumn<>("Phone Number");
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colPhoneNumber.setPrefWidth(132);

        TableColumn<CustomerTemp, String> colIdCard = new TableColumn<>("ID Card");
        colIdCard.setCellValueFactory(cellData -> cellData.getValue().idCardProperty());
        colIdCard.setPrefWidth(114);

        tableView.getColumns().addAll(colNo, colUsername, colPassword, colName, colPhoneNumber, colIdCard);

        // Load dữ liệu từ customers.csv
        customerList = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\customers.csv");
        tableView.setItems(customerList);

        // TextField và Label
        indexField = new TextField();
        indexField.setLayoutX(352);
        indexField.setLayoutY(340);

        Label indexLabel = new Label("Enter the index of customer you want to delete");
        indexLabel.setLayoutX(282);
        indexLabel.setLayoutY(314);
        indexLabel.setFont(new Font("System Bold", 13));

        // Nút Back
        Button backButton = new Button("Back");
        backButton.setLayoutX(28);
        backButton.setLayoutY(352);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        // Nút Delete
        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(501);
        deleteButton.setLayoutY(365);
        deleteButton.setOnAction(e -> handleDeleteButton());

        root.getChildren().addAll(titleLabel, tableView, indexField, indexLabel, backButton, deleteButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Delete Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<CustomerTemp> loadDataFromCSV(String filePath) {
        ObservableList<CustomerTemp> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int idx = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    data.add(new CustomerTemp(String.valueOf(idx), parts[0], parts[1], parts[2], parts[3], parts[4]));
                    idx++;
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
        return data;
    }

    private void handleDeleteButton() {
        String input = indexField.getText().trim();
        if (!input.matches("\\d+") || Integer.parseInt(input) <= 0 || Integer.parseInt(input) > customerList.size()) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "Please enter a valid row number!");
            return;
        }

        int indexToDelete = Integer.parseInt(input) - 1;
        customerList.remove(indexToDelete);

        // Lưu dữ liệu cập nhật vào file CSV
        saveUpdatedDataToCSV("Project-OOP-Semester-3\\src\\aims\\customers.csv", customerList);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully!");
    }

    private void saveUpdatedDataToCSV(String filePath, List<CustomerTemp> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (CustomerTemp customer : data) {
                writer.write(String.format("%s,%s,%s,%s,%s", customer.getUsername(), customer.getPassword(), customer.getName(), customer.getPhoneNumber(), customer.getIdCard()));
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void handleBackButton(Stage currentStage) {
        currentStage.close();
        if (previousStage != null) {
            previousStage.show();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static class CustomerTemp {
        private String no, username, password, name, phoneNumber, idCard;

        public CustomerTemp(String no, String username, String password, String name, String phoneNumber, String idCard) {
            this.no = no;
            this.username = username;
            this.password = password;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.idCard = idCard;
        }

        public javafx.beans.property.SimpleStringProperty noProperty() { return new javafx.beans.property.SimpleStringProperty(no); }
        public javafx.beans.property.SimpleStringProperty usernameProperty() { return new javafx.beans.property.SimpleStringProperty(username); }
        public javafx.beans.property.SimpleStringProperty passwordProperty() { return new javafx.beans.property.SimpleStringProperty(password); }
        public javafx.beans.property.SimpleStringProperty nameProperty() { return new javafx.beans.property.SimpleStringProperty(name); }
        public javafx.beans.property.SimpleStringProperty phoneNumberProperty() { return new javafx.beans.property.SimpleStringProperty(phoneNumber); }
        public javafx.beans.property.SimpleStringProperty idCardProperty() { return new javafx.beans.property.SimpleStringProperty(idCard); }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getName() { return name; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getIdCard() { return idCard; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
