module com.example.week10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    requires pokeapi;
    requires com.google.gson;


    opens com.example.week10 to javafx.fxml;
    exports com.example.week10;
}