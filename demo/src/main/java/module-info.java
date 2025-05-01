module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires javafx.graphics;

    opens com.example to javafx.fxml,com.google.gson;

    exports com.example;
}
