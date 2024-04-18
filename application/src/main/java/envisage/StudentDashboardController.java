package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StudentDashboardController implements Initializable {

  private Facade facade;
  private User user;
  private StudentIDStore studentIDStore = StudentIDStore.getInstance();

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
    studentIDStore.setStudentID(user.getID());
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

    int studentCreditHours = facade.getStudentCreditHours();
    calculatingCurrentSemester(studentCreditHours);

    DegreeProgress degreeProgress = facade.getStudentDegreeProgress();

    if (degreeProgress != null) {
      int completedCoursesCount = degreeProgress.getCompleteCoursesCount();
      int totalCoursesCount = degreeProgress.getTotalCoursesCount();

      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
          new PieChart.Data("Completed", completedCoursesCount),
          new PieChart.Data(
              "Incomplete",
              totalCoursesCount - completedCoursesCount));

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
  
  /**
   * Helper method to calculate the student's current semester
   * @param credithours the student's credit hours
   */
  public void calculatingCurrentSemester(int credithours){
    int studentCreditHours = credithours;
    if(studentCreditHours >= 0) {
      if(studentCreditHours >= 0 && studentCreditHours <= 29){
        semesterLabel.setText("1");
      }
      if(studentCreditHours >= 30 && studentCreditHours <= 44){
        semesterLabel.setText("2");
      }
      if(studentCreditHours >= 44 && studentCreditHours <= 59){
        semesterLabel.setText("3");
      }
      if(studentCreditHours >= 60 && studentCreditHours <= 74){
        semesterLabel.setText("4");
      }
      if(studentCreditHours >= 75 && studentCreditHours <= 89){
        semesterLabel.setText("5");
      }
      if(studentCreditHours >= 90 && studentCreditHours <= 104){
        semesterLabel.setText("6");
      }
      if(studentCreditHours >= 105 && studentCreditHours <= 119){
        semesterLabel.setText("7");
      }
      if(studentCreditHours >= 120){
        semesterLabel.setText("8");
      }
    } else {
      semesterLabel.setText("Could not calculate current semester. Invalid credit hours.");
    }
  }

  @FXML
  void setStageCourseList(ActionEvent event) throws IOException {
    App.setRoot("courseList");
  }

  @FXML
  void setStageCoursePlanner(ActionEvent event) throws IOException {
    App.setRoot("coursePlanner");
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
    UUID studentID = studentIDStore.getStudentID();
    if (studentID != null) {
      App.setRoot("studentProfile");
    } else {
      System.err.println("Error: Student ID is not set.");
    }
  }

  @FXML
  public void printCoursePlanner(
      @SuppressWarnings("exports") ActionEvent event) {
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

  @FXML
  void setStageMajorMap(ActionEvent event) throws IOException {
    String majorName = facade.getStudentMajor();
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
}