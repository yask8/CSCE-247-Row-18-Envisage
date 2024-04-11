package envisage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdvisingSoftware.*;

//Author @Spillmag

public class StudentDashboardController implements Initializable {

    private Facade facade;
    private User user;

    @FXML
    private Label advisingNoteLabel;

    @FXML
    private Label appAreaLabel;

    @FXML
    private Button coursePlannerButton;

    @FXML
    private Label gpaLabel;

    @FXML
    private Label majorLabel;

    @FXML
    private Button printCoursePlannerButton;

    @FXML
    private PieChart progressPieChart;

    @FXML
    private Button scheduleAptButton;

    @FXML
    private Label semesterLabel;

    @FXML
    private Button singOutButton;

    @FXML
    private AnchorPane studentDashboard;

    @FXML
    private Button studentProfileButton;

    @FXML
    private Button courseListButton;

    @FXML
    private Button viewMajorMapButton;

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        user = facade.getUser();
        welcomeLabel.setText("Welcome " + user.getFirstName());
        majorLabel.setText(facade.getStudentMajor());
        gpaLabel.setText(String.valueOf(facade.getStudentGPA()));
        appAreaLabel.setText(facade.getStudentAppArea());
        ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
        if (!advisorNotes.isEmpty()) {
            Note mostRecentNote = advisorNotes.get(advisorNotes.size() - 1);
            String noteText = mostRecentNote.getNote();
            advisingNoteLabel.setText(noteText);
        } else {
            advisingNoteLabel.setText("No advisor notes available.");

        }

        DegreeProgress degreeProgress = facade.getStudentDegreeProgress();

        if (degreeProgress != null) {
            int completedCoursesCount = degreeProgress.getCompleteCoursesCount();
            int totalCoursesCount = degreeProgress.getTotalCoursesCount();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Completed", completedCoursesCount),
                    new PieChart.Data("Incomplete", totalCoursesCount - completedCoursesCount));

            for (PieChart.Data data : pieChartData) {
                double percentage = (data.getPieValue() / totalCoursesCount) * 100;
                String tooltip = String.format("%.2f%%", percentage);
                Tooltip.install(data.getNode(), new Tooltip(tooltip));
            }

            progressPieChart.setData(pieChartData);
        } else {
            progressPieChart.setVisible(false);
        }
    }

    @FXML
    void setStageCourseList(ActionEvent event) throws IOException {
        App.setRoot("courseList");
        ;
    }

    @FXML
    void setStageCoursePlanner(ActionEvent event) throws IOException {
        App.setRoot("coursePlanner");
        ;
    }

    @FXML
    void signOut(ActionEvent event) {
        try {
            Facade.getInstance().signOut();
            App.setRoot("LogIn");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setStageStudentProfile(MouseEvent event) throws IOException {
        App.setRoot("studentProfile");
    }

    @FXML
    public void printCoursePlanner(@SuppressWarnings("exports") ActionEvent event) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = firstName + " " + lastName;

        facade.writeStudentCoursePlanner(fullName);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Course Planner");
        alert.setHeaderText("Complete!");
        alert.setContentText("Your course planner has been printed successfully.");
        alert.showAndWait();
    }
}