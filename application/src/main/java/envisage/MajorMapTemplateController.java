package envisage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MajorMapTemplateController {

    @FXML
    private Label majorMapLabel;

    @FXML
    private AnchorPane majrMapTemplatePane;

    @FXML
    private Button viewMajorMapButton;

    public void setMajorName(String majorName) {
        majorMapLabel.setText(majorName);
    }

}