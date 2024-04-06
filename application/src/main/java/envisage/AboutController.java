package envisage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AboutController {

    @FXML
    private AnchorPane About;

    @FXML
    private Label AboutLabel;

    @FXML
    private Label FAQlabel;

    @FXML
    private Label UniversityPartnersLabel;

    @FXML
    private Label loginLabel;

    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

}

