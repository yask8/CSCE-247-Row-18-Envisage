package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
/**
 * @author Yasmine Kennedy (yask8) and Garrett Spillman (Spillmag)
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.shape.Rectangle;

public class AdvisorProfileController implements Initializable {

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
  UUID studentID = null;

  @Override
  public void initialize(URL url, ResourceBundle arg1) {
    if (facade.getUser().getUserType().equals("STUDENT")) {
      initStudent();
    } else {
      initOtherUsers(studentID);
    }
  }

  private void initStudent() {
    ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
    ArrayList<String> studentIncompletedCourses = facade
      .getStudentDegreeProgress()
      .getIncompleteCourses();
    if (studentCompletedCourses != null && studentIncompletedCourses != null) {
      TreeItem<String> root = new TreeItem<>(
        userFirstName + "'s Completed and Incompleted Courses"
      );
      studentCompletionTree.setRoot(root);
      TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
      root.getChildren().add(completedItem);
      for (Grades completedCourse : studentCompletedCourses) {
        TreeItem<String> completedCourses = new TreeItem<>(
          completedCourse.toString()
        );
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
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve completed courses."
        );
        root.getChildren().add(errorItem);
      }
      if (studentIncompletedCourses == null) {
        TreeItem<String> root = new TreeItem<>("Incompleted Courses Error");
        studentCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve incompleted courses."
        );
        root.getChildren().add(errorItem);
      }
    }
    advisingStudentProfileLabel.setVisible(false);
    studentProfileTitleLabel.setText(
      userFirstName + " " + userLastName + "'s Student Profile"
    );
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
        String formattedNote =
          dateFormat.format(advisorNote.getDate()) +
          " - " +
          advisorNote.getNote();
        noteListView.getItems().add(formattedNote);
      }
    } else {
      noteListView.getItems().add("No Notes Given Yet");
    }

    boolean isAdvisor = facade.getUser().getUserType().equals("ADVISOR");

    if (!isAdvisor) {
      addNoteButton.setVisible(false);
      noteListView.setPrefHeight(
        noteListView.getPrefHeight() + addNoteButton.getPrefHeight()
      );
    }
    boolean isStudent = facade.getUser().getUserType().equals("STUDENT");

    if (!isStudent) {
      declareMajorButton.setVisible(false);
      declareAppAreaButton.setVisible(false);
    }
  }

  private void initOtherUsers(UUID studentId) {
    Student student = facade.getStudentById(studentId);

    if (student != null) {
      ArrayList<Grades> studentCompletedCourses = student.getCompletedCourses();
      ArrayList<String> studentIncompletedCourses = student
        .getDegreeProgress()
        .getIncompleteCourses();

      if (
        studentCompletedCourses != null && studentIncompletedCourses != null
      ) {
        TreeItem<String> root = new TreeItem<>(
          student.getFirstName() + "'s Completed and Incompleted Courses"
        );
        studentCompletionTree.setRoot(root);

        TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
        root.getChildren().add(completedItem);
        for (Grades completedCourse : studentCompletedCourses) {
          TreeItem<String> completedCourses = new TreeItem<>(
            completedCourse.toString()
          );
          completedItem.getChildren().add(completedCourses);
        }

        TreeItem<String> incompletedItem = new TreeItem<>(
          "Incompleted Courses"
        );
        root.getChildren().add(incompletedItem);
        for (String incompletedCourse : studentIncompletedCourses) {
          TreeItem<String> incompletedCourses = new TreeItem<>(
            incompletedCourse
          );
          incompletedItem.getChildren().add(incompletedCourses);
        }
      } else {
        TreeItem<String> root = new TreeItem<>("Courses Error");
        studentCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve courses."
        );
        root.getChildren().add(errorItem);
      }

      advisingStudentProfileLabel.setVisible(false);
      studentProfileTitleLabel.setText(
        student.getFirstName() +
        " " +
        student.getLastName() +
        "'s Student Profile"
      );
      studentsSummaryTitleLabel.setText("Student Summary:");

      if (student.getApplicationArea() != null) {
        appAreaNotTitleLabel.setText(student.getApplicationArea());
      } else {
        appAreaNotTitleLabel.setText("Undecided");
      }

      if (student.getMajor() != null) {
        majorNotTitleLabel.setText(student.getMajor());
      } else {
        majorNotTitleLabel.setText("Undeclared");
      }

      ArrayList<Note> advisorNotes = student.getAdvisorNotes();
      if (!advisorNotes.isEmpty()) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
        for (Note advisorNote : advisorNotes) {
          String formattedNote =
            dateFormat.format(advisorNote.getDate()) +
            " - " +
            advisorNote.getNote();
          noteListView.getItems().add(formattedNote);
        }
      } else {
        noteListView.getItems().add("No Notes Given Yet");
      }

      boolean isAdvisor = facade.getUser().getUserType().equals("ADVISOR");
      if (!isAdvisor) {
        addNoteButton.setVisible(false);
        noteListView.setPrefHeight(
          noteListView.getPrefHeight() + addNoteButton.getPrefHeight()
        );
      }
      boolean isStudent = facade.getUser().getUserType().equals("STUDENT");
      if (!isStudent) {
        declareMajorButton.setVisible(false);
        declareAppAreaButton.setVisible(false);
      }
    }
  }

  @FXML
  void setStageDashboard(ActionEvent event) throws IOException {
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
  public void declareMajor(@SuppressWarnings("exports") ActionEvent event)
    throws IOException {
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
  public void declareAppArea(@SuppressWarnings("exports") ActionEvent event)
    throws IOException {
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
  public void addNote(ActionEvent event) {
    ArrayList<UUID> advisees = facade.getListOfAdvisees();

    if (!advisees.contains(studentID)) {
      showAlert(
        "Student Not Assigned",
        "You are not assigned to this student."
      );
      return;
    }

    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Note");
    dialog.setHeaderText("Add a new note for the student");
    dialog.setContentText("Enter your note:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(noteText -> {
      facade.addNoteToStudentAdvisor(
        facade.getCurrentUserId(),
        studentID,
        noteText
      );
      noteListView.getItems().clear();
      facade.saveUsers();
      initialize(null, null);
    });
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void setUserId(UUID id) {
    this.studentID = id;
    initialize(null, null);
  }
}
