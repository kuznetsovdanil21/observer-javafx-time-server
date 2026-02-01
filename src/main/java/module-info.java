module org.example.pi1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pi1 to javafx.fxml;
    exports org.example.pi1;
}