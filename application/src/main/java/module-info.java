module envisage {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens envisage to javafx.fxml;
    exports envisage;

    opens AdvisingSoftware to javafx.fmxl;

    exports AdvisingSoftware;
}
