package envisage;

import java.net.URL;
import java.util.ResourceBundle;

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

        String semesterInput = JOptionPane.showInputDialog(null, "Enter the semester for course " + courseName + ":");

        if (semesterInput != null) {
            try {
                int semester = Integer.parseInt(semesterInput);
                if (semester >= 1 && semester <= 8) {
                    facade.getStudentCoursePlanner().addCourse(semester,courseName);
                    
                    System.out.println("Course: " + courseName + ", Semester: " + semester + " added to course planner for user " + currentUser.getFirstName() + " "+ currentUser.getLastName());
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a semester between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            }
        } else {
            System.out.println("Operation canceled.");
        }
    } else {
        System.out.println("User not found. Unable to add course to course planner.");
    }
}

    public void setCourseName(String courseName) {
        courseNameLabel.setText(courseName);
    }

    public void showAddCourseButton(boolean show) {
        AddCourseButton.setVisible(show);
    }
}
