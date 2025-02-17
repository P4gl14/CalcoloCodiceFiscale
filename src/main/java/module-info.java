module com.example.codicefiscale {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.codicefiscale to javafx.fxml;
    exports com.example.codicefiscale;
}