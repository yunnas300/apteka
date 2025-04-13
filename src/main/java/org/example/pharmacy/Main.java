// Мірошниченко Б. Міщенко К. Некроєнко О. Луніка О.
// ООП на мові Java
// Проєкт на тему "ПЗ для формування замовлень в аптеці"
// 28 травня 2024
// Години : 100+ годин
// Ми визнаємо, що це наша командна робота

package org.example.pharmacy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen();
        Scene scene = new Scene(loginScreen.createContent(primaryStage), 300, 190);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pharmacy Client");
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
