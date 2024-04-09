package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import AdvisingSoftware.CoursePlanner;
import AdvisingSoftware.Facade;
import AdvisingSoftware.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

//Author @Spillmag

public class CoursePlannerController implements Initializable {

    @FXML
    private Button addCourseButton;

    @FXML
    private Label advisorNoteLabel;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane coursePlanner;

    @FXML
    private Button deleteCourseButton;

    @FXML
    private Button generateCoursePlannerButton;

    @FXML
    private Button saveCoursePlanner;

    @FXML
    private TreeView<String> coursePlannerTree;

    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Facade facade = Facade.getInstance();
        CoursePlanner coursePlanner = facade.getStudentCoursePlanner();

        if (coursePlanner != null) {
            TreeItem<String> root = new TreeItem<>("Course Planner for Major: " + facade.getStudentMajor());
            coursePlannerTree.setRoot(root);
            
            for (int i = 1; i <= coursePlanner.getNumberOfSemesters(); i++) {
                TreeItem<String> semesterItem = new TreeItem<>("Semester " + i);
                root.getChildren().add(semesterItem);

                String[] courses = coursePlanner.getCoursesForSemester(i);
                if (courses.length == 0) {
                    TreeItem<String> noCoursesItem = new TreeItem<>("No courses planned for this semester");
                    semesterItem.getChildren().add(noCoursesItem);
                } else {
                    for (String course : courses) {
                        TreeItem<String> courseItem = new TreeItem<>(course);
                        semesterItem.getChildren().add(courseItem);
                    }
                }
            }
        } else {
            TreeItem<String> root = new TreeItem<>("Course Planner - Error");
            coursePlannerTree.setRoot(root);
            TreeItem<String> errorItem = new TreeItem<>("Unable to retrieve course planner");
            root.getChildren().add(errorItem);
        }

        ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
        if (!advisorNotes.isEmpty()) {
            Note mostRecentNote = advisorNotes.get(advisorNotes.size() - 1);
            String noteText = mostRecentNote.getNote();
            advisorNoteLabel.setText(noteText);
        } else {
            advisorNoteLabel.setText("No advisor notes available.");
        }
    }

    @FXML
    void AddCoursePopup(ActionEvent event) {

    }

    @FXML
    void DeleteCourse(ActionEvent event) {
        TreeItem<String> selectedItem = coursePlannerTree.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getParent() != null) {
            String courseToDelete = selectedItem.getValue();
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setHeaderText("Delete Course");
            confirmationAlert.setContentText("Are you sure you want to delete the course '" + courseToDelete + "'?");
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedItem.getParent().getChildren().remove(selectedItem);
              
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a course to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    void GeneratePlanner(ActionEvent event) {

    }

    @FXML
    void SavePlanner(ActionEvent event) {

    }

    @FXML
    void setStageDashboard(ActionEvent event) throws IOException {
        App.setRoot("StudentDashboard");
    }

}