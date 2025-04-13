module org.example.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pharmacy to javafx.fxml;
    exports org.example.pharmacy;
}