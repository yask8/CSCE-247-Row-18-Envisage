package envisage;

/**
 * Controller class for the Student Template page in the Envisage application.
 * This class handles the logic for displaying student information and navigating to
 * the student profile page.
 * 
 * It initializes the UI components and provides methods for setting student name,
 * viewing student profile, and navigating to the student lookup page.
 * 
 * @author Garrett Spillman
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import AdvisingSoftware.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StudentTemplateController implements Initializable {

    @FXML
    private Label studentNameLabel;

    @FXML
    private AnchorPane studentTemplatePane;

    @FXML
    private Button viewStudentProfileButton;

    /**
     * Sets the name of the student displayed on the template.
     * 
     * @param firstName The first name of the student
     * @param lastName  The last name of the student
     */
    public void setStudentName(String firstName, String lastName) {
        studentNameLabel.setText(firstName + " " + lastName);
    }

    /**
     * Handles the event when the user clicks on the "View Student Profile" button.
     * It retrieves the student's full name, gets the corresponding user ID from the
     * facade, and sets the student ID in the StudentIDStore. Then, it switches the
     * scene to the student profile page.
     * 
     * @param event The action event triggered by clicking the button
     */
    @FXML
    void setStudentProfile(ActionEvent event) {
        try {
            Facade facade = Facade.getInstance();
            String fullName = studentNameLabel.getText().trim();
            String[] nameParts = fullName.split(" ");
            String firstName = nameParts[0].trim();
            String lastName = nameParts[1].trim();

            UUID Id = facade.getUserIdByName(firstName, lastName);

            if (Id != null) {
                StudentIDStore.getInstance().setStudentID(Id);
                App.setRoot("studentProfile");
            } else {
                setStageStudentLookup(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the event when the user clicks on the "Student Lookup" button.
     * It switches the scene to the student lookup page.
     * 
     * @param event The action event triggered by clicking the button
     * @throws IOException If an error occurs while loading the FXML file
     */
    @FXML
    void setStageStudentLookup(ActionEvent event) throws IOException {
        App.setRoot("studentLookup");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}