//Класс, що керує ліками, реалізація функцій пошуку, додавання, редагування, видалення та додавання до кошика
package org.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class MedicineController {
    private ObservableList<Medicine> medicines = FXCollections.observableArrayList();
    private ListView<HBox> medicineListView = new ListView<>();
    private PharmacyController pharmacyController;

    public MedicineController(PharmacyController pharmacyController) {
        this.pharmacyController = pharmacyController;
        // Тестові дані
        medicines.add(new Medicine("Парацетамол", 10.0, 50));
        medicines.add(new Medicine("Аспірін", 8.0, 27));
        medicines.add(new Medicine("Ібупрофан", 27.0, 16));
        medicines.add(new Medicine("Дуфалак", 21.0, 3));
        medicines.add(new Medicine("Абробене", 15.0, 6));
        medicines.add(new Medicine("док. Мом", 100.0, 7));
        updateListView();
    }

    public ListView<HBox> getMedicineListView() {
        return medicineListView;
    }

    public ObservableList<Medicine> getMedicines() {
        return medicines;
    }

    public void searchMedicine(String query) {
        ObservableList<Medicine> filteredList = FXCollections.observableArrayList();
        for (Medicine medicine : medicines) {
            if (medicine.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(medicine);
            }
        }
        updateListView(filteredList);
    }

    public void addMedicine() {
        Stage addStage = new Stage();
        VBox layout = new VBox(10);
        addStage.setTitle("Додати товар");
        layout.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        nameField.setPromptText("Назва");
        TextField priceField = new TextField();
        priceField.setPromptText("Ціна");
        TextField quantityField = new TextField();
        quantityField.setPromptText("К-сть");
        Button addButton = new Button("Додати");

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String priceText = priceField.getText();
            String quantityText = quantityField.getText();

            if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                showAlert("Помилка", "Всі поля повинні бути заповненими.");
                return;
            }

            double price;
            int quantity;

            try {
                price = Double.parseDouble(priceText);
                if (price < 0) {
                    showAlert("Помилка", "Ціна не може бути від'ємною.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Ціна має бути дійсним числом.");
                return;
            }

            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity < 0) {
                    showAlert("Помилка", "Кількість не може бути від’ємною.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Кількість має бути дійсним цілим числом.");
                return;
            }

            medicines.add(new Medicine(name, price, quantity));
            updateListView();
            addStage.close();
        });

        layout.getChildren().addAll(nameField, priceField, quantityField, addButton);
        Scene scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        addStage.setScene(scene);
        addStage.show();
    }

    public void updateListView() {
        updateListView(medicines);
    }

    public void updateListView(ObservableList<Medicine> list) {
        ObservableList<HBox> items = FXCollections.observableArrayList();
        for (Medicine medicine : list) {
            HBox item = createMedicineItem(medicine);
            items.add(item);
        }
        medicineListView.setItems(items);
    }

    private HBox createMedicineItem(Medicine medicine) {
        HBox item = new HBox(10);
        item.getStyleClass().add("hbox");
        TextField nameField = new TextField(medicine.getName());
        nameField.getStyleClass().add("text-field");
        TextField priceField = new TextField(String.valueOf(medicine.getPrice()));
        priceField.getStyleClass().add("text-field");
        TextField quantityField = new TextField(String.valueOf(medicine.getQuantity()));
        quantityField.getStyleClass().add("text-field");
        Button editButton = new Button("Редагувати");
        editButton.getStyleClass().add("button");
        Button deleteButton = new Button("Видалити");
        deleteButton.getStyleClass().add("button");
        Button addToCartButton = new Button("Додати в кошик");
        addToCartButton.getStyleClass().add("button");

        editButton.setOnAction(event -> {
            String name = nameField.getText();
            String priceText = priceField.getText();
            String quantityText = quantityField.getText();

            if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                showAlert("Помилка", "Усі поля мають бути заповнені.");
                return;
            }

            double price;
            int quantity;

            try {
                price = Double.parseDouble(priceText);
                if (price < 0) {
                    showAlert("Помилка", "Ціна не може бути від'ємною.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Ціна має бути дійсним числом.");
                return;
            }

            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity < 0) {
                    showAlert("Помилка", "Кількість не може бути від’ємною.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Кількість має бути дійсним цілим числом.");
                return;
            }

            medicine.setName(name);
            medicine.setPrice(price);
            medicine.setQuantity(quantity);
            updateListView();
        });

        deleteButton.setOnAction(event -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Підтвердження видалення");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Ви впевнені, що хочете видалити цей товар?");
            confirmationAlert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                medicines.remove(medicine);
                updateListView();
            }
        });

        addToCartButton.setOnAction(event -> {
            if (medicine.getQuantity() <= 0) {
                showAlert("Помилка", "Неможливо додати в кошик. Ліків немає в наявності.");
                return;
            }

            int currentCartQuantity = pharmacyController.getCartQuantity(medicine.getName());
            if (currentCartQuantity >= medicine.getQuantity()) {
                showAlert("Помилка", "Неможливо додати більше до кошика. Недостатньо запасів.");
                return;
            }

            pharmacyController.addToCart(new Medicine(medicine.getName(), medicine.getPrice(), 1));
            updateListView();
        });

        item.getChildren().addAll(nameField, priceField, quantityField, editButton, deleteButton, addToCartButton);
        return item;
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
