module com.example.chapter4challenge1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.chapter4challenge1 to javafx.fxml;
    exports com.example.chapter4challenge1;
}