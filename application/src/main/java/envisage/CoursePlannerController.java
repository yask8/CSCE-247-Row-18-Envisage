package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Author @Spillmag

public class CoursePlannerController implements Initializable {

  private Facade facade = Facade.getInstance();
  private User user = facade.getUser();

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
    CoursePlanner coursePlanner = facade.getStudentCoursePlanner();

    if (coursePlanner != null) {
      TreeItem<String> root = new TreeItem<>(
        "Course Planner for Major: " + facade.getStudentMajor()
      );
      coursePlannerTree.setRoot(root);

      for (int i = 1; i <= coursePlanner.getNumberOfSemesters(); i++) {
        TreeItem<String> semesterItem = new TreeItem<>("Semester " + i);
        root.getChildren().add(semesterItem);

        String[] courses = coursePlanner.getCoursesForSemester(i);
        if (courses.length == 0) {
          TreeItem<String> noCoursesItem = new TreeItem<>(
            "No courses planned for this semester"
          );
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
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve course planner"
      );
      root.getChildren().add(errorItem);
    }

    ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
    if (!advisorNotes.isEmpty()) {
      Note mostRecentNote = advisorNotes.get(advisorNotes.size() - 1);
      String noteText = mostRecentNote.getNote();
      advisorNoteLabel.setText(noteText);
    } else {
      advisorNoteLabel.setText(" No advisor notes available.");
    }
  }

  @FXML
  void AddCoursePopup(ActionEvent event) throws IOException {
    App.setRoot("courseList");
  }

  @FXML
  void DeleteCourse(ActionEvent event) {
    TreeItem<String> selectedItem = coursePlannerTree
      .getSelectionModel()
      .getSelectedItem();
    if (
      selectedItem != null &&
      selectedItem.getParent() != null &&
      selectedItem.getParent().getParent() != null
    ) {
      String courseToDelete = selectedItem.getValue();
      String semesterStr = selectedItem
        .getParent()
        .getValue()
        .replaceAll("[^0-9]", "");
      int semester = Integer.parseInt(semesterStr);

      Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
      confirmationAlert.setHeaderText("Delete Course");
      confirmationAlert.setContentText(
        "Are you sure you want to delete the course '" + courseToDelete + "'?"
      );

      Stage stage = (Stage) confirmationAlert
        .getDialogPane()
        .getScene()
        .getWindow();
      stage.initStyle(StageStyle.TRANSPARENT);

      Optional<ButtonType> result = confirmationAlert.showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        TreeItem<String> parentItem = selectedItem.getParent();
        parentItem.getChildren().remove(selectedItem);

        CoursePlanner coursePlanner = facade.getStudentCoursePlanner();
        coursePlanner.removeCourse(semester, courseToDelete);

        if (coursePlanner.getCoursesForSemester(semester).length == 0) {
          parentItem.getChildren().clear();
          TreeItem<String> noCoursesItem = new TreeItem<>(
            "No courses planned for this semester"
          );
          parentItem.getChildren().add(noCoursesItem);
        }
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
    if (
      facade.getStudentMajor() == null || facade.getStudentMajor().isEmpty()
    ) {
      Alert majorNotDeclaredAlert = new Alert(Alert.AlertType.WARNING);
      majorNotDeclaredAlert.setHeaderText("Major Not Declared");
      majorNotDeclaredAlert.setContentText("Please declare your major first.");
      majorNotDeclaredAlert.showAndWait();
      return;
    }

    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setHeaderText("Generate Planner");
    Text contentText = new Text(
      "Are you sure you want to generate the course planner from your major? This will overwrite any changes you have made."
    );
    contentText.setWrappingWidth(250);
    Label contentLabel = new Label(null, contentText);
    contentLabel.setGraphicTextGap(20);
    confirmationAlert.getDialogPane().setContent(contentLabel);

    Stage stage = (Stage) confirmationAlert
      .getDialogPane()
      .getScene()
      .getWindow();
    stage.initStyle(StageStyle.TRANSPARENT);

    Optional<ButtonType> response = confirmationAlert.showAndWait();
    if (response.isPresent()) {
      if (response.get() == ButtonType.OK) {
        try {
          facade
            .getStudentCoursePlanner()
            .generateFromMajorMap(facade.getMajorMap(facade.getStudentMajor()));
          facade.saveUsers();
          initialize(null, null);
        } catch (Exception e) {
          Alert errorAlert = new Alert(Alert.AlertType.ERROR);
          errorAlert.setHeaderText("Error Generating Planner");

          Text text = new Text(
            "An error occurred while generating the planner. Please try again later."
          );
          text.setWrappingWidth(250);
          Label label = new Label(null, text);
          label.setGraphicTextGap(20);

          errorAlert.getDialogPane().setContent(label);
          errorAlert.showAndWait();
          e.printStackTrace();
        }
      }
    }
  }

  @FXML
  void SavePlanner(ActionEvent event) {
    facade.saveUsers();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Save Successful");
    alert.setContentText("Changes have been successfully saved.");

    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.initStyle(StageStyle.TRANSPARENT);

    alert.showAndWait();
  }

  @FXML
  void setStageDashboard(ActionEvent event) throws IOException {
    App.setRoot("StudentDashboard");
  }
}
