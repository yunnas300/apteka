//Класс що відображає екран входу, перевіряє введені облікові дані та переходить в меню управління після успішного входу.
package org.example.pharmacy;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginScreen {

    public Parent createContent(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("vbox");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Логін");
        usernameField.getStyleClass().add("text-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.getStyleClass().add("password-field");

        Button loginButton = new Button("Увійти");
        loginButton.getStyleClass().add("button");
        //loginButton.isDefaultButton();
        loginButton.setOnAction(event -> handleLogin(usernameField.getText(), passwordField.getText(), primaryStage));

        layout.getChildren().addAll(usernameField, passwordField, loginButton);
        return layout;
    }

    private void handleLogin(String username, String password, Stage primaryStage) {
        if ("admin".equals(username) && "admin".equals(password)) {
            MainScreen mainScreen = new MainScreen();
            Scene scene = new Scene(mainScreen.createContent(primaryStage), 1280, 500);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
        } else {
            showAlert("Помилка входу!", "Неправильний логін або пароль. Спробуйте ще раз.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }
}
