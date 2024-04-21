package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
/**
 * Controller class for the Student Profile page in the Envisage application.
 * This class handles the logic for displaying student profile information,
 * such as completed and incompleted courses, advising notes, and allowing
 * users (students or advisors) to declare major and application area, as well
 * as add notes to the student's profile.
 *
 * It initializes the UI components and provides methods for initializing student
 * information, declaring major and application area, adding notes, and navigating back.
 *
 * @author Row 18
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

public class StudentProfileController implements Initializable {

  // FXML injected variables
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
  private Label creditHoursTitleLabel;

  @FXML
  private Label creditHoursNotTitleLabel;

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

  // Instance variables
  Facade facade = Facade.getInstance();
  User user = facade.getUser();
  String userFirstName = facade.getUser().getFirstName();
  String userLastName = facade.getUser().getLastName();
  UUID studentID = null;
  StudentIDStore studentIDStore = StudentIDStore.getInstance();

  /**
   * Initializes the controller after its root element has been completely
   * processed.
   *
   * @param location  The location used to resolve relative paths for the root
   *                  object,
   *                  or null if the location is not known.
   * @param resources The resources used to localize the root object, or null
   *                  if the root object was not localized.
   */
  public void initialize(URL url, ResourceBundle arg1) {
    UUID studentID = studentIDStore.getStudentID();
    if (studentID != null) {
      if (facade.getUser().getUserType().equals("STUDENT")) {
        initStudent();
      } else {
        initOtherUsers(studentID);
        System.out.println(studentID);
      }
    }
  }

  /**
   * Helper method to set the users id
   *
   * @param id the users unique id
   */
  public void setUserId(UUID id) {
    this.studentID = id;
    studentIDStore.setStudentID(id);
    initialize(null, null);
  }

  /**
   * Helper method to initialize a student's completed and incompleted courses
   */
  private void initStudent() {
    ((Student) (facade.getUser())).updateGPA(facade.getCourses());
    ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
    ArrayList<String> studentIncompletedCourses = facade
      .getStudentDegreeProgress()
      .getIncompleteCourses();

    TreeItem<String> root = new TreeItem<>(
      userFirstName + "'s Completed and Incomplete Courses"
    );
    studentCompletionTree.setRoot(root);

    if (studentCompletedCourses != null && !studentCompletedCourses.isEmpty()) {
      TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
      root.getChildren().add(completedItem);
      for (Grades completedCourse : studentCompletedCourses) {
        TreeItem<String> completedCourses = new TreeItem<>(
          completedCourse.toString()
        );
        completedItem.getChildren().add(completedCourses);
      }
    } else {
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve completed courses."
      );
      root.getChildren().add(errorItem);
    }

    if (
      studentIncompletedCourses != null && !studentIncompletedCourses.isEmpty()
    ) {
      TreeItem<String> incompletedItem = new TreeItem<>("Incomplete Courses");
      root.getChildren().add(incompletedItem);
      for (String incompletedCourse : studentIncompletedCourses) {
        TreeItem<String> incompletedCourses = new TreeItem<>(incompletedCourse);
        incompletedItem.getChildren().add(incompletedCourses);
      }
    } else {
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve incomplete courses."
      );
      root.getChildren().add(errorItem);
    }

    advisingStudentProfileLabel.setVisible(false);
    studentProfileTitleLabel.setText(
      userFirstName + " " + userLastName + "'s Student Profile"
    );
    studentsSummaryTitleLabel.setText("Student Summary:");
    appAreaNotTitleLabel.setText(
      facade.getStudentAppArea() != null
        ? facade.getStudentAppArea()
        : "Undecided"
    );
    majorNotTitleLabel.setText(
      facade.getStudentMajor() != null ? facade.getStudentMajor() : "Undeclared"
    );
    String studentCreditHours = Integer.toString(
      facade.getStudentCreditHours()
    );
    creditHoursNotTitleLabel.setText(studentCreditHours);
    ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
    if (!advisorNotes.isEmpty()) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
      for (Note advisorNote : advisorNotes) {
        String formattedNote =
          dateFormat.format(advisorNote.getDate()) +
          " - " +
          advisorNote.getNote();
        noteListView.getItems().add(formattedNote);
        String recentDate = dateFormat.format(advisorNote.getDate());
        lastAdvisingApptDateLabel.setText(recentDate);
      }
    } else {
      noteListView.getItems().add("No Notes Given Yet");
      lastAdvisingApptDateLabel.setText("No date found, yet.");
    }

    boolean isAdvisor = user.getUserType().equals("ADVISOR");
    if (!isAdvisor) {
      addNoteButton.setVisible(false);
      noteListView.setPrefHeight(
        noteListView.getPrefHeight() + addNoteButton.getPrefHeight()
      );
    }
    boolean isStudent = user.getUserType().equals("STUDENT");
    if (!isStudent) {
      declareMajorButton.setVisible(false);
      declareAppAreaButton.setVisible(false);
    }
  }

  /**
   * Helper method to intialize a students completed and incompleted courses for a
   * different user type
   *
   * @param studentId the students id
   */
  private void initOtherUsers(UUID studentId) {
    Student student = facade.getStudentById(studentId);
    student.updateGPA(facade.getCourses());
    user = facade.getUser();

    if (student != null) {
      studentID = student.getID();
      ArrayList<Grades> studentCompletedCourses = student.getCompletedCourses();
      ArrayList<String> studentIncompletedCourses = student
        .getDegreeProgress()
        .getIncompleteCourses();

      if (
        studentCompletedCourses != null && studentIncompletedCourses != null
      ) {
        TreeItem<String> root = new TreeItem<>(
          student.getFirstName() + "'s Completed and Incomplete Courses"
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

        TreeItem<String> incompletedItem = new TreeItem<>("Incomplete Courses");
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

      appAreaNotTitleLabel.setText(
        student.getApplicationArea() != null
          ? student.getApplicationArea()
          : "Undecided"
      );
      majorNotTitleLabel.setText(
        student.getMajor() != null ? student.getMajor() : "Undeclared"
      );
      String studentCreditHours = Integer.toString(student.getCreditHours());
      creditHoursNotTitleLabel.setText(studentCreditHours);

      ArrayList<Note> advisorNotes = student.getAdvisorNotes();
      if (!advisorNotes.isEmpty()) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
        for (Note advisorNote : advisorNotes) {
          String formattedNote =
            dateFormat.format(advisorNote.getDate()) +
            " - " +
            advisorNote.getNote();
          noteListView.getItems().add(formattedNote);
          String recentDate = dateFormat.format(advisorNote.getDate());
          lastAdvisingApptDateLabel.setText(recentDate);
        }
      } else {
        noteListView.getItems().add("No Notes Given Yet");
        lastAdvisingAppointmentDateTitleLabel.setText("No date found, yet.");
      }

      boolean isAdvisor = user.getUserType().equals("ADVISOR");
      if (!isAdvisor) {
        addNoteButton.setVisible(false);
        noteListView.setPrefHeight(
          noteListView.getPrefHeight() + addNoteButton.getPrefHeight()
        );
      }
      boolean isStudent = user.getUserType().equals("STUDENT");
      if (!isStudent) {
        declareMajorButton.setVisible(false);
        declareAppAreaButton.setVisible(false);
      }
    }
  }

  @FXML
  private void goBack(ActionEvent event) {
    try {
      App.goBack();
    } catch (IOException e) {
      e.printStackTrace();
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
  public void addNote(@SuppressWarnings("exports") ActionEvent event) {
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
}
