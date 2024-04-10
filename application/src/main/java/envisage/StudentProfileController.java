package envisage;

import javafx.event.ActionEvent;
/**
 * @author Yasmine Kennedy (yask8)
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {

    @FXML
    private Button addNoteButton;

    @FXML
    private Label advisingStudentProfileLabel;

    @FXML
    private Label appAreaNotTitleLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button declareAppAreaButton;

    @FXML
    private Button declareMajorButton;

    @FXML
    private Label lastAdvisingAppointmentDateTitleLabel;

    @FXML
    private Label lastAdvisingApptDateLabel;

    @FXML
    private Label majorNotTitleLabel;

    @FXML
    private Label mostRecentNoteTitleLabel;

    @FXML
    private ListView<String> noteListView;

    @FXML
    private TreeView<String> studentCompletionTree;

    @FXML
    private Label studentProfileTitleLabel;

    @FXML
    private Rectangle studentSummaryBox;

    @FXML
    private Label studentsSummaryTitleLabel;

    Facade facade = Facade.getInstance();
    User user = facade.getUser();
    String userFirstName = facade.getUser().getFirstName();
    String userLastName = facade.getUser().getLastName();

    @Override
    public void initialize(URL url, ResourceBundle arg1) {
        ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
        ArrayList<String> studentIncompletedCourses = facade.getStudentDegreeProgress().getIncompleteCourses();
        if (studentCompletedCourses != null && studentIncompletedCourses != null) {
            TreeItem<String> root = new TreeItem<>(userFirstName + "'s Completed and Incompleted Courses");
            studentCompletionTree.setRoot(root);
            TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
            root.getChildren().add(completedItem);
            for (Grades completedCourse : studentCompletedCourses) {
                TreeItem<String> completedCourses = new TreeItem<>(completedCourse.toString());
                completedItem.getChildren().add(completedCourses);
            }
            TreeItem<String> incompletedItem = new TreeItem<>("Incompleted Courses");
            root.getChildren().add(incompletedItem);
            for (String incompletedCourse : studentIncompletedCourses) {
                TreeItem<String> incompletedCourses = new TreeItem<>(incompletedCourse);
                incompletedItem.getChildren().add(incompletedCourses);
            }
        } else {
            if (studentCompletedCourses == null) {
                TreeItem<String> root = new TreeItem<>("Completed Courses Error");
                studentCompletionTree.setRoot(root);
                TreeItem<String> errorItem = new TreeItem<>("Unable to retrieve completed courses.");
                root.getChildren().add(errorItem);
            }
            if (studentIncompletedCourses == null) {
                TreeItem<String> root = new TreeItem<>("Incompleted Courses Error");
                studentCompletionTree.setRoot(root);
                TreeItem<String> errorItem = new TreeItem<>("Unable to retrieve incompleted courses.");
                root.getChildren().add(errorItem);
            }
        }
        advisingStudentProfileLabel.setVisible(false);
        studentProfileTitleLabel.setText(userFirstName + " " + userLastName + "'s Student Profile");
        studentsSummaryTitleLabel.setText("Student Summary:");
        if (facade.getStudentAppArea() != null) {
            appAreaNotTitleLabel.setText(facade.getStudentAppArea());
        } else {
            appAreaNotTitleLabel.setText("Undecided");
        }
        if (facade.getStudentMajor() != null) {
            majorNotTitleLabel.setText(facade.getStudentMajor());
        } else {
            majorNotTitleLabel.setText("Undeclared");
        }

        ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
        if (!advisorNotes.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
            for (Note advisorNote : advisorNotes) {
                String formattedNote = dateFormat.format(advisorNote.getDate()) + " - " + advisorNote.getNote();
                noteListView.getItems().add(formattedNote);
            }
        } else {
            noteListView.getItems().add("No Notes Given Yet");
        }

        boolean isAdvisor = facade.getUser().getUserType().equals("ADVISOR");

        if (!isAdvisor) {
            addNoteButton.setVisible(false);
            noteListView.setPrefHeight(noteListView.getPrefHeight() + addNoteButton.getPrefHeight());
        }
        boolean isStudent = facade.getUser().getUserType().equals("STUDENT");

        if (!isStudent) {
            declareMajorButton.setVisible(false);
            declareAppAreaButton.setVisible(false);

        }
    }

    @FXML
    void setStageDashboard(MouseEvent event) throws IOException {
        if (user == null) {
            return;
        }
        switch (user.getUserType()) {
            case "STUDENT":
                App.setRoot("studentDashboard");
                break;
            case "ADVISOR":
                App.setRoot("advisorDashboard");
                break;
            case "ADMIN":
                App.setRoot("adminDashboard");
                break;
            default:
                break;
        }
    }

    @FXML
    public void declareMajor(ActionEvent event) throws IOException {
        ArrayList<String> allMajors = facade.getMajorList().getAllMajorNames();

        if (allMajors == null || allMajors.isEmpty()) {
            System.err.println("Error: Unable to retrieve majors list.");
            return;
        }

        ChoiceDialog<String> majorDialog = new ChoiceDialog<>();
        majorDialog.setHeaderText("Declare Your Major");
        majorDialog.setContentText("Select your major from the list:");
        majorDialog.getItems().addAll(allMajors);

        Optional<String> selectedMajor = majorDialog.showAndWait();

        if (selectedMajor.isPresent()) {

            String chosenMajor = selectedMajor.get();
            facade.declareMajor(chosenMajor);

            majorNotTitleLabel.setText(chosenMajor);
        }
    }

    @FXML
    public void declareAppArea(ActionEvent event) throws IOException {

        ArrayList<String> allAppAreas = new ArrayList<String>();
        allAppAreas.add("Science");
        allAppAreas.add("Math");
        allAppAreas.add("Digital Design");
        allAppAreas.add("Robotics");
        allAppAreas.add("Speech");

        if (allAppAreas == null || allAppAreas.isEmpty()) {
            System.err.println("Error: Unable to retrieve application area list.");
            return;
        }

        ChoiceDialog<String> appAreaDialog = new ChoiceDialog<>();
        appAreaDialog.setHeaderText("Declare Your Application Area");
        appAreaDialog.setContentText("Select your application area from the list:");
        appAreaDialog.getItems().addAll(allAppAreas);

        Optional<String> selectedAppArea = appAreaDialog.showAndWait();

        if (selectedAppArea.isPresent()) {
            String chosenAppArea = selectedAppArea.get();
            facade.setAppArea(chosenAppArea);

            appAreaNotTitleLabel.setText(chosenAppArea);
        }
    }

    @FXML
    void addNote(ActionEvent event) {

    }
}
