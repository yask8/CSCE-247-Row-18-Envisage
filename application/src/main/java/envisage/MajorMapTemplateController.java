package envisage;

import AdvisingSoftware.Facade;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the MajorMapTemplate.fxml file.
 * This class handles the display and actions related to a major map template.
 */
public class MajorMapTemplateController {

    Facade facade = Facade.getInstance();

    @FXML
    private Label majorMapLabel;

    @FXML
    private AnchorPane majrMapTemplatePane;

    @FXML
    private Button viewMajorMapButton;

    /**
     * Handles the action event for setting the major map.
     * If the major name is undeclared or empty, navigates to the major list.
     * Otherwise, sets the selected major name and navigates to the MajorMap view.
     * 
     * @param event The action event that occurred.
     * @throws IOException If an error occurs while loading the FXML file for the
     *                     MajorMap view.
     */
    @FXML
    void setMajorMap(ActionEvent event) throws IOException {
        String majorName = majorMapLabel.getText().trim();
        if (majorName == null || majorName.isEmpty() || majorName.equals("Undeclared")) {
            try {
                App.setRoot("majorList");
            } catch (IOException e) {
                System.err.println("Error loading MajorList view: " + e.getMessage());
            }
        } else {
            MajorNameStore.getInstance().setMajorName(majorName);
            try {
                App.setRoot("MajorMap");
            } catch (IOException e) {
                System.err.println("Error loading MajorMap view: " + e.getMessage());
            }
        }
    }

    /**
     * Sets the major name displayed on the major map template.
     * 
     * @param majorName The major name to set.
     */
    public void setMajorName(String majorName) {
        majorMapLabel.setText(majorName);
    }
}
