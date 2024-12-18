package aims.AdminMethods;

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

public class AdminViewCustomer extends Application {

    private Stage previousStage;  // Tham chiếu cửa sổ trước đó
    private TableView<CustomerTemp> tableView;  // Bảng hiển thị dữ liệu
    private ObservableList<CustomerTemp> customerList;  // Danh sách khách hàng

    public AdminViewCustomer(Stage previousStage) {
        this.previousStage = previousStage;  // Gán tham chiếu cửa sổ trước đó
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Tiêu đề
        Label titleLabel = new Label("Your Customers");
        titleLabel.setLayoutX(210);
        titleLabel.setLayoutY(25);
        titleLabel.setFont(new Font("System Bold", 24));
        titleLabel.setStyle("-fx-text-fill: #d42222;");

        // TableView
        tableView = new TableView<>();
        tableView.setLayoutX(35);
        tableView.setLayoutY(74);
        tableView.setPrefSize(531, 242);

        TableColumn<CustomerTemp, String> colNo = new TableColumn<>("No");
        colNo.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        colNo.setPrefWidth(32);

        TableColumn<CustomerTemp, String> colUsername = new TableColumn<>("Username");
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colUsername.setPrefWidth(75);

        TableColumn<CustomerTemp, String> colPassword = new TableColumn<>("Password");
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colPassword.setPrefWidth(92);

        TableColumn<CustomerTemp, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colName.setPrefWidth(102);

        TableColumn<CustomerTemp, String> colPhoneNumber = new TableColumn<>("Phone Number");
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colPhoneNumber.setPrefWidth(122);

        TableColumn<CustomerTemp, String> colIdCard = new TableColumn<>("ID Card");
        colIdCard.setCellValueFactory(cellData -> cellData.getValue().idCardProperty());
        colIdCard.setPrefWidth(106);

        tableView.getColumns().addAll(colNo, colUsername, colPassword, colName, colPhoneNumber, colIdCard);

        // Load data from customers.csv
        customerList = loadDataFromCSV("Project-OOP-Semester-3\\src\\aims\\customers.csv");
        tableView.setItems(customerList);

        // Nút điều hướng
        Button backButton = new Button("Back");
        backButton.setLayoutX(514);
        backButton.setLayoutY(338);
        backButton.setOnAction(e -> handleBackButton(primaryStage));

        Button prevPageButton = new Button("Prev Page");
        prevPageButton.setLayoutX(35);
        prevPageButton.setLayoutY(325);

        Button nextPageButton = new Button("Next Page");
        nextPageButton.setLayoutX(116);
        nextPageButton.setLayoutY(326);

        root.getChildren().addAll(titleLabel, tableView, backButton, prevPageButton, nextPageButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("View Customers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Load dữ liệu từ file customers.csv
    private ObservableList<CustomerTemp> loadDataFromCSV(String filePath) {
        ObservableList<CustomerTemp> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int idx = 1; // Chỉ số bắt đầu từ 1
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {  // Đảm bảo dữ liệu hợp lệ
                    data.add(new CustomerTemp(String.valueOf(idx), parts[0], parts[1], parts[2], parts[3], parts[4]));
                    idx++;
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
        return data;
    }

    private void handleBackButton(Stage currentStage) {
        currentStage.close();  // Đóng cửa sổ hiện tại
        if (previousStage != null) {
            previousStage.show();  // Hiển thị cửa sổ trước đó
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Class tạm lưu thông tin Customer
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
