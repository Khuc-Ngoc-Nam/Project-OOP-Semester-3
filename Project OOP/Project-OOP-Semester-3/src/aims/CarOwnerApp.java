package aims;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CarOwnerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
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
        Button addCarOwner = createButton("Add Car Owner", 230, 130, "#5711f0");
        Button deleteCarOwner = createButton("Delete Car Owner", 230, 180, "#000000");
        Button viewCustomers = createButton("View Customers", 230, 230, "#16d00c");
        Button addCustomer = createButton("Add Customer", 230, 280, "#baa807");
        Button deleteCustomer = createButton("Delete Customer", 230, 330, "#ff05d9");
        Button logOut = createButton("Log Out", 230, 380, "#0da6ae");

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

    public static void main(String[] args) {
        launch(args);
    }
}
