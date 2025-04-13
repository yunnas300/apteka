//Класс що керуэ кошиком покупок, реалізація функцій формування чека, видалення предметів з кошику
package org.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;

public class PharmacyController {
    private ObservableList<Medicine> cart = FXCollections.observableArrayList();
    private ListView<HBox> cartListView = new ListView<>();
    private TextArea receiptArea;

    public ListView<HBox> getCartListView() {
        return cartListView;
    }

    public void setReceiptArea(TextArea receiptArea) {
        this.receiptArea = receiptArea;
    }

    public int getCartQuantity(String medicineName) {
        for (Medicine item : cart) {
            if (item.getName().equals(medicineName)) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    public void addToCart(Medicine medicine) {
        for (Medicine item : cart) {
            if (item.getName().equals(medicine.getName())) {
                item.setQuantity(item.getQuantity() + 1);
                updateCartView();
                updateReceipt();
                return;
            }
        }
        cart.add(new Medicine(medicine.getName(), medicine.getPrice(), 1));
        updateCartView();
        updateReceipt();
    }

    public void updateCartView() {
        ObservableList<HBox> items = FXCollections.observableArrayList();
        for (Medicine medicine : cart) {
            HBox item = createCartItem(medicine);
            items.add(item);
        }
        cartListView.setItems(items);
    }

    private HBox createCartItem(Medicine medicine) {
        HBox item = new HBox(10);
        item.getStyleClass().add("hbox");
        TextField nameField = new TextField(medicine.getName());
        nameField.getStyleClass().add("text-field");
        TextField priceField = new TextField(String.valueOf(medicine.getPrice()));
        priceField.getStyleClass().add("text-field");
        TextField quantityField = new TextField(String.valueOf(medicine.getQuantity()));
        quantityField.getStyleClass().add("text-field");
        Button removeButton = new Button("Видалити");
        removeButton.getStyleClass().add("button");

        removeButton.setOnAction(event -> {
            cart.remove(medicine);
            updateCartView();
            updateReceipt();
        });

        item.getChildren().addAll(nameField, priceField, quantityField, removeButton);
        return item;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        double total = 0;
        for (Medicine medicine : cart) {
            receipt.append(medicine.getName())
                    .append(" - ")
                    .append(medicine.getQuantity())
                    .append(" pcs - ₴")
                    .append(medicine.getPrice() * medicine.getQuantity())
                    .append("\n");
            total += medicine.getPrice() * medicine.getQuantity();
        }
        receipt.append("\nВсього: ₴").append(total);
        return receipt.toString();
    }

    public void processOrder(MedicineController medicineController) {
        for (Medicine cartItem : cart) {
            for (Medicine medicine : medicineController.getMedicines()) {
                if (medicine.getName().equals(cartItem.getName())) {
                    if (cartItem.getQuantity() > medicine.getQuantity()) {
                        showAlert("Помилка замовлення", "Недостатньо доступних запасів для " + cartItem.getName());
                        return;
                    }
                }
            }
        }

        for (Medicine cartItem : cart) {
            for (Medicine medicine : medicineController.getMedicines()) {
                if (medicine.getName().equals(cartItem.getName())) {
                    medicine.setQuantity(medicine.getQuantity() - cartItem.getQuantity());
                }
            }
        }
        cart.clear();
        updateCartView();
        updateReceipt();
        medicineController.updateListView();
    }

    private void updateReceipt() {
        if (receiptArea != null) {
            receiptArea.setText(generateReceipt());
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
