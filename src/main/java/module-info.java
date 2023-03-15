module com.example.calculadoralp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;


    opens com.example.calculadoralp3 to javafx.fxml;
    exports com.example.calculadoralp3;
}