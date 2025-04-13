//Класс що відображає головне меню програми з можливістю пошуку, додавання ліків, перегляду кошика та виходу з облікового запису.
package org.example.pharmacy;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainScreen {

    private PharmacyController pharmacyController = new PharmacyController();
    private MedicineController medicineController = new MedicineController(pharmacyController);

    public Parent createContent(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("vbox");

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.getStyleClass().add("hbox");

        TextField searchField = new TextField();
        searchField.setPromptText("Введіть назву");
        searchField.getStyleClass().add("text-field");

        Button searchButton = new Button("Пошук");
        searchButton.getStyleClass().add("button");
        searchButton.setOnAction(event -> medicineController.searchMedicine(searchField.getText()));

        searchBox.getChildren().addAll(searchField, searchButton);

        Button addButton = new Button("Додати");
        addButton.getStyleClass().add("button");
        addButton.setOnAction(event -> medicineController.addMedicine());

        Button cartButton = new Button("Кошик");
        cartButton.getStyleClass().add("button");
        cartButton.setOnAction(event -> showCartWindow());

        Button exitButton = new Button("Вийти");
        exitButton.getStyleClass().add("button");
        exitButton.setOnAction(event -> {
            LoginScreen loginScreen = new LoginScreen();
            Scene loginScene = new Scene(loginScreen.createContent(primaryStage), 300, 190);
            loginScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
            //primaryStage.setResizable(false);
            primaryStage.setTitle("Pharmacy Client");
            primaryStage.setScene(loginScene);
        });

        HBox topMenu = new HBox(10);
        topMenu.getStyleClass().add("hbox");
        topMenu.getChildren().addAll(searchBox, addButton, cartButton, exitButton);

        layout.getChildren().addAll(topMenu, medicineController.getMedicineListView());

        return layout;
    }

    private void showCartWindow() {
        Stage cartStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("vbox");
        layout.getChildren().addAll(pharmacyController.getCartListView());

        TextArea receiptArea = new TextArea();
        pharmacyController.setReceiptArea(receiptArea);
        receiptArea.setText(pharmacyController.generateReceipt());

        Button orderButton = new Button("Замовлення");
        orderButton.getStyleClass().add("button");
        orderButton.setOnAction(event -> {
            pharmacyController.processOrder(medicineController);
            receiptArea.setText(pharmacyController.generateReceipt());
        });

        layout.getChildren().addAll(receiptArea, orderButton);
        Scene scene = new Scene(layout, 925, 555);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        cartStage.setScene(scene);
        cartStage.setTitle("Кошик");
        //cartStage.setResizable(false);
        cartStage.show();
    }
}
