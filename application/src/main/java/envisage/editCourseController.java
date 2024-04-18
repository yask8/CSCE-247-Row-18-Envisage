package envisage;

import AdvisingSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for the editCourse.fxml file.
 * This class handles the editing of course information and provides
 * functionality for saving changes.
 */
public class editCourseController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Text CarolinaCoreLabel;

    @FXML
    private Text CarolinaCoreText;

    @FXML
    private Text CourseCodeLabel;

    @FXML
    private Text CourseCodeText;

    @FXML
    private Text CourseCreditHourText;

    @FXML
    private Text CourseDescriptionLabel;

    @FXML
    private Text CourseDescriptionText;

    @FXML
    private Text CourseIDLabel;

    @FXML
    private Text CourseIDText;

    @FXML
    private Text CourseLabel;

    @FXML
    private Text CourseText;

    @FXML
    private Text CreditHoursLabel;

    @FXML
    private Label EditCourseLabel;

    @FXML
    private Label EditLabel;

    @FXML
    private Text PreReqLabel;

    @FXML
    private Text PreReqTextField;

    @FXML
    private Button SaveButton;

    @FXML
    private Label carolinaCoreLabel;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseCreditHours;

    @FXML
    private Label courseDescriptionLabel;

    @FXML
    private Label courseIDLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label directionsLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private AnchorPane editCourse;

    @FXML
    private TextField newInfoTextField;

    @FXML
    private ChoiceBox<String> pickToEditChoiceBox;

    Facade facade;

    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the choice box with edit options.
     * 
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        ObservableList<String> editOptions = FXCollections.observableArrayList("Course Name", "Course Credit Hours",
                "Course Description",
                "Carolina Core", "Prerequisites");
        pickToEditChoiceBox.setItems(editOptions);
    }

    /**
     * Handles mouse event for assigning to.
     * 
     * @param event The mouse event that occurred.
     */
    @FXML
    void AssignTo(MouseEvent event) {
        // Implementation for AssignTo method goes here
    }

    /**
     * Handles action event for setting the stage to dashboard.
     * 
     * @param event The action event that occurred.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @FXML
    void setStageDashboard(ActionEvent event) throws IOException {
        if (facade.getUser() == null) {
            return;
        }
        save();
        App.setRoot("adminDashboard");
    }

    /**
     * Saves the changes made to the course information.
     */
    public void save() {
        String editCriteria = pickToEditChoiceBox.getValue();
        if (editCriteria == null) {
            errorLabel.setText("Please pick an info option to edit.");
            return;
        }

        ArrayList<Course> courses = facade.getCourses();
        for (Course course : courses) {
            switch (editCriteria) {
                case "Course Name":
                    course.setName(newInfoTextField.getText());
                    break;
                case "Course Credit Hours":
                    Integer creditHour = Integer.parseInt(newInfoTextField.getText());
                    course.setCreditHours(creditHour);
                    break;
                case "Course Description":
                    course.setDescription(newInfoTextField.getText());
                    break;
                case "Carolina Core":
                    // Handle editing Carolina Core
                    break;
                case "Prerequisites":
                    // Handle editing prerequisites
                    break;
                default:
                    break;
            }
        }
    }
}
