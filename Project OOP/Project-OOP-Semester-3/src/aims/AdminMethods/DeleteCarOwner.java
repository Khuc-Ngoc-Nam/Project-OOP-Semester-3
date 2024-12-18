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
import java.util.stream.Collectors;

public class DeleteCarOwner extends Application {

    private Stage previousStage; // Tham chiếu cửa sổ trước đó
    private TableView<CarOwnerTemp> tableView;
    private TextField indexField;
    private ObservableList<CarOwnerTemp> carOwnerList;

    public DeleteCarOwner(Stage previousStage) {
        this.previousStage = previousStage;  // Lưu tham chiếu đến cửa sổ trước
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Tiêu đề
        Label titleLabel = new Label("Delete a Car Owner");
        titleLabel.setLayoutX(181);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold", 26));
        titleLabel.setStyle("-fx-text-fill: #d40f0f;");

        // TableView
        tableView = new TableView<>();
        tableView.setLayoutX(11);
        tableView.setLayoutY(52);
        tableView.setPrefSize(575, 243);

        TableColumn<CarOwnerTemp, String> colNo = new TableColumn<>("No");
        colNo.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        colNo.setPrefWidth(86);

        TableColumn<CarOwnerTemp, String> colUsername = new TableColumn<>("Username");
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colUsername.setPrefWidth(106);

        TableColumn<CarOwnerTemp, String> colPassword = new TableColumn<>("Password");
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colPassword.setPrefWidth(104);

        TableColumn<CarOwnerTemp, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(124);

        TableColumn<CarOwnerTemp, String> colPhoneNumber = new TableColumn<>("Phone Number");
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colPhoneNumber.setPrefWidth(152);

        tableView.getColumns().addAll(colNo, colUsername, colPassword, colName, colPhoneNumber);

        // Load data into TableView
        carOwnerList = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\carOwners.csv");
        tableView.setItems(carOwnerList);

        // TextField và Label
        indexField = new TextField();
        indexField.setLayoutX(383);
        indexField.setLayoutY(316);

        Label indexLabel = new Label("Type a number");
        indexLabel.setLayoutX(299);
        indexLabel.setLayoutY(320);

        // Nút điều hướng
        Button backButton = new Button("Back");
        backButton.setLayoutX(40);
        backButton.setLayoutY(352);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        Button agreeButton = new Button("Agree");
        agreeButton.setLayoutX(506);
        agreeButton.setLayoutY(352);
        agreeButton.setOnAction(e -> handleAgreeButton());

        root.getChildren().addAll(titleLabel, tableView, indexField, indexLabel, backButton, agreeButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Delete Car Owner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<CarOwnerTemp> loadDataFromCSV(String filePath) {
        ObservableList<CarOwnerTemp> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int idx = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    data.add(new CarOwnerTemp(String.valueOf(idx), parts[0], parts[1], parts[2], parts[3]));
                    idx++;
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
        return data;
    }

    private void handleAgreeButton() {
        String input = indexField.getText().trim();
        if (!input.matches("\\d+") || Integer.parseInt(input) <= 0 || Integer.parseInt(input) > carOwnerList.size()) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "Please enter a valid row number!");
            return;
        }

        int indexToDelete = Integer.parseInt(input) - 1;
        carOwnerList.remove(indexToDelete);

        // Write the updated list to CSV
        saveUpdatedDataToCSV("Project-OOP-Semester-3\\src\\aims\\carOwners.csv", carOwnerList);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Car Owner deleted successfully!");
    }

    private void saveUpdatedDataToCSV(String filePath, List<CarOwnerTemp> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (CarOwnerTemp owner : data) {
                writer.write(String.format("%s,%s,%s,%s", owner.getUsername(), owner.getPassword(), owner.getName(), owner.getPhoneNumber()));
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

    public static class CarOwnerTemp {
        private String no, username, password, name, phoneNumber;

        public CarOwnerTemp(String no, String username, String password, String name, String phoneNumber) {
            this.no = no;
            this.username = username;
            this.password = password;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public javafx.beans.property.SimpleStringProperty noProperty() { return new javafx.beans.property.SimpleStringProperty(no); }
        public javafx.beans.property.SimpleStringProperty usernameProperty() { return new javafx.beans.property.SimpleStringProperty(username); }
        public javafx.beans.property.SimpleStringProperty passwordProperty() { return new javafx.beans.property.SimpleStringProperty(password); }
        public javafx.beans.property.SimpleStringProperty nameProperty() { return new javafx.beans.property.SimpleStringProperty(name); }
        public javafx.beans.property.SimpleStringProperty phoneNumberProperty() { return new javafx.beans.property.SimpleStringProperty(phoneNumber); }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getName() { return name; }
        public String getPhoneNumber() { return phoneNumber; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
