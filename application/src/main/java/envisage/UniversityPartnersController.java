package envisage;

/**
 * Controller class for the University Partners page in the Envisage application.
 * This class handles the interaction and logic for displaying university partners.
 * It contains methods for initializing the UI components and handling mouse events.
 * 
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
    private Label faqLabel;

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

    /**
     * Handles mouse click event on the login label.
     * Switches the scene to the Login page.
     * 
     * @param event The mouse event
     * @throws IOException If an error occurs while loading the FXML file
     */
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    /**
     * Handles mouse click event on the About label.
     * Switches the scene to the About page.
     * 
     * @param event The mouse event
     * @throws IOException If an error occurs while loading the FXML file
     */
    @FXML
    void setStageAbout(MouseEvent event) throws IOException{
        App.setRoot("About");
    }

    /**
     * Handles mouse click event on the FAQ label.
     * Switches the scene to the FAQ page.
     * 
     * @param event The mouse event
     * @throws IOException If an error occurs while loading the FXML file
     */
    @FXML
    void setStageFAQ(MouseEvent event) throws IOException{
        App.setRoot("faqPage");
    }

}