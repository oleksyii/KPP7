module com.example.kpp7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.kpp7 to javafx.fxml;
    exports com.example.kpp7;
}