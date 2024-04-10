package envisage;

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

//Author @Spillmag

public class CourseTemplateController implements Initializable {

    @FXML
    private Button AddCourseButton;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button viewCourseInfoButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void addCoursetoCoursePlanner(ActionEvent event) {
        Facade facade = Facade.getInstance();
        User currentUser = facade.getUser();

        if (currentUser != null) {
            String courseName = courseNameLabel.getText();

            String[] semesterOptions = { "1", "2", "3", "4", "5", "6", "7", "8" };
            JComboBox<String> semesterDropdown = new JComboBox<>(semesterOptions);

            int option = JOptionPane.showOptionDialog(
                    null, semesterDropdown, "Select Semester for Course " + courseName, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                int semester = Integer.parseInt((String) semesterDropdown.getSelectedItem());

                if (semester >= 1 && semester <= 8) {
                    facade.getStudentCoursePlanner().addCourse(semester, courseName);
                    JOptionPane.showMessageDialog(null, "Course successfully added to the course planner.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a semester between 1 and 8.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "add course canceled.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Unable to add course to course planner.");
        }
    }

    public void setCourseName(String courseName) {
        courseNameLabel.setText(courseName);
    }

    public void showAddCourseButton(boolean show) {
        AddCourseButton.setVisible(show);
    }
}
