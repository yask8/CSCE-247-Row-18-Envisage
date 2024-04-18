package envisage;

/**
 * Controller class for About Page
 * @author Row 18
 */
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AboutController {
    /**
     * FXML injected variables
     */
    @FXML
    private AnchorPane About;

    @FXML
    private Label AboutLabel;

    @FXML
    private Label faqLabel;

    @FXML
    private Label UniversityPartnersLabel;

    @FXML
    private Label loginLabel;

    /**
     * MouseEvent Method that sets the stage to the Login screen
     * @param event representing the action
     * @throws IOException if an IO error occurs when setting the root
     */
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    /**
     * MouseEvent Method that sets the stage to the  University Partners screen
     * @param event representing the action
     * @throws IOException if an IO error occurs when setting the root
     */
    @FXML
    void setStageUniversityPartners(MouseEvent event) throws IOException{
        App.setRoot("universityPartners");
    }

    /**
     * MouseEvent Method that sets the stage to the FAQ screen
     * @param event representing the action
     * @throws IOException if an IO error occurs when setting the root
     */
    @FXML
    void setStageFAQ(MouseEvent event) throws IOException{
        App.setRoot("faqPage");
    }

}

