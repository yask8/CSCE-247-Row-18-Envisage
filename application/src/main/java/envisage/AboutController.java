package envisage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the About page.
 * Controls navigation and actions related to the About page UI elements.
 */
public class AboutController {

    // FXML elements injected from the corresponding FXML file
    @FXML
    private AnchorPane About; // AnchorPane containing the About page content

    @FXML
    private Label AboutLabel; // Label for the About section

    @FXML
    private Label faqLabel; // Label for the FAQ section

    @FXML
    private Label UniversityPartnersLabel; // Label for the University Partners section

    @FXML
    private Label loginLabel; // Label for the Login section

    /**
     * Switches the scene to the Login page when the loginLabel is clicked.
     *
     * @param event Mouse click event on the loginLabel
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn"); // Switches the scene to the Login page
    }

    /**
     * Switches the scene to the University Partners page when the UniversityPartnersLabel is clicked.
     *
     * @param event Mouse click event on the UniversityPartnersLabel
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageUniversityPartners(MouseEvent event) throws IOException {
        App.setRoot("universityPartners"); // Switches the scene to the University Partners page
    }

    /**
     * Switches the scene to the FAQ page when the faqLabel is clicked.
     *
     * @param event Mouse click event on the faqLabel
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageFAQ(MouseEvent event) throws IOException {
        App.setRoot("faqPage"); // Switches the scene to the FAQ page
    }
}

