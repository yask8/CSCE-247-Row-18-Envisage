package envisage;

/**
 * @author Yasmine Kennedy (yask8)
 */
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UniversityPartnersController {

    @FXML
    private Label AboutLabel;

    @FXML
    private Label FAQlabel;

    @FXML
    private Label UniversityPartnersLabel;

    @FXML
    private ImageView clemsonLogo;

    @FXML
    private Label loginLabel;

    @FXML
    private ImageView texasLogo;

    @FXML
    private AnchorPane univeristyPartners;

    @FXML
    private ImageView uscLogo;

    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void setStageAbout(MouseEvent event) throws IOException{
        App.setRoot("About");
    }

    //FAQ needs to be made

}
