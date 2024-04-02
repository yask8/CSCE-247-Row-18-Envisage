module envisage {
    requires javafx.controls;
    requires javafx.fxml;

    opens envisage to javafx.fxml;
    exports envisage;
}
