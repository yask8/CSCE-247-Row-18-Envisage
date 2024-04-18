package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for the CourseTemplate.fxml file.
 * This class handles the initialization of the course template view and
 * provides functionality for adding courses to a course planner.
 */
public class CourseTemplateController implements Initializable {

    @FXML
    private Button AddCourseButton;

    @FXML
    private Button editCourseInfoButton;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button viewCourseInfoButton;

    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded.
     * It sets the visibility of the edit course info button to false initially.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editCourseInfoButton.setVisible(false);
    }

    /**
     * Handles the action event for adding a course to the course planner.
     * It prompts the user to select a semester and adds the selected course to the course planner.
     * @param event The action event that occurred.
     */
    @FXML
    void addCoursetoCoursePlanner(ActionEvent event) {
        // Get the current user from the facade
        Facade facade = Facade.getInstance();
        User currentUser = facade.getUser();

        if (currentUser != null) {
            String courseName = courseNameLabel.getText();

            // Options for semester selection
            String[] semesterOptions = { "1", "2", "3", "4", "5", "6", "7", "8" };
            JComboBox<String> semesterDropdown = new JComboBox<>(semesterOptions);

            // Show a dialog for semester selection
            int option = JOptionPane.showOptionDialog(
                    null, semesterDropdown, "Select Semester for Course " + courseName, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                int semester = Integer.parseInt((String) semesterDropdown.getSelectedItem());

                if (semester >= 1 && semester <= 8) {
                    // Add the course to the course planner for the selected semester
                    facade.getStudentCoursePlanner().addCourse(semester, courseName);
                    JOptionPane.showMessageDialog(null, "Course successfully added to the course planner.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a semester between 1 and 8.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Add course canceled.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Unable to add course to course planner.");
        }
    }

    /**
     * Sets the name of the course to be displayed.
     * @param courseName The name of the course.
     */
    public void setCourseName(String courseName) {
        courseNameLabel.setText(courseName);
    }

    /**
     * Sets the visibility of the AddCourseButton.
     * @param show If true, the AddCourseButton is visible; otherwise, it is hidden.
     */
    public void showAddCourseButton(boolean show) {
        AddCourseButton.setVisible(show);
    }

    /**
     * Shows the edit course button and hides other buttons.
     */
    public void showEditCourseButton() {
        editCourseInfoButton.setVisible(true);
        showAddCourseButton(false);
        viewCourseInfoButton.setVisible(false);
    }
    
    /**
     * Handles the action event for setting the stage to edit courses.
     * @param event The action event that occurred.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @FXML
    void setStageEditCourses(ActionEvent event) throws IOException {
        App.setRoot("editCourse");
    }
}