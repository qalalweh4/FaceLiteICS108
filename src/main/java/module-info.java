module com.example.faceliteics108 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.faceliteics108 to javafx.fxml;
    exports com.example.faceliteics108;
}