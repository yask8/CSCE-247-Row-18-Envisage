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
 * @author Lia Zhao (zhaolia9), Yasmine Kennedy (yask8), and Garrett Spillman (Spillmag)
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
  private Label AdvisorProfileTitleLabel;

  @FXML
  private Label IDNotTitleLabel;

  @FXML
  private Label PersonalEmailTitleLabel;

  @FXML
  private Button addAdviseeButton;

  @FXML
  private Button addNoteButton;

  @FXML
  private TreeView<String> adviseeCompletionTree;

  @FXML
  private Label advisingStudentProfileLabel;

  @FXML
  private Rectangle advisorSummaryBox;

  @FXML
  private Button backButton;

  @FXML
  private Label mainEmailTitleLabel;

  @FXML
  private Label mostRecentNoteTitleLabel;

  @FXML
  private ListView<String> noteListView;

  @FXML
  private Label personalEmailNotTitleLabel;

  @FXML
  private Label phoneNumberNotTitleLabel;

  @FXML
  private Label phoneNumberTitleLabel;

  @FXML
  private Button removeAdviseeButton;

  Facade facade = Facade.getInstance();
  User user = facade.getUser();
  String userFirstName = facade.getUser().getFirstName();
  String userLastName = facade.getUser().getLastName();

  //UUID advisorID = null;

  @Override
  public void initialize(URL url, ResourceBundle arg1) {
    if (facade.getUser().getUserType().equals("ADVISOR")) {
      IDNotTitleLabel.setText(user.getID().toString());
      mainEmailTitleLabel.setText(user.getEmail());
      //initStudent();
    } else {
      //initOtherUsers(studentID);
    }
  }

  private ArrayList<Student> getAdvisees() {
    ArrayList<UUID> advisees = ((Advisor) user).getListOfAdvisees();
    ArrayList<Student> students = new ArrayList<Student>();

    for (UUID advisee : advisees) {
      students.add(
        ((Advisor) user).getStudentByAdvisor(advisee, facade.getUserList())
      );
    }
    return students;
  }

  private void initAdvisor() {
    ArrayList<Student> listOfAdvisees = getAdvisees();

    ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
    ArrayList<String> studentIncompletedCourses = facade
      .getStudentDegreeProgress()
      .getIncompleteCourses();

    if (!listOfAdvisees.isEmpty()) {
      TreeItem<String> root = new TreeItem<>(
        userFirstName + "'s Advised and Unadvised Advisees"
      );

      adviseeCompletionTree.setRoot(root);
      TreeItem<String> completedItem = new TreeItem<>("Advised Advisees");
      root.getChildren().add(completedItem);
      // Trying to figure out how to track advised/unadvised students

      for (Grades completedCourse : studentCompletedCourses) {
        TreeItem<String> completedCourses = new TreeItem<>(
          completedCourse.toString()
        );
        completedItem.getChildren().add(completedCourses);
      }
      TreeItem<String> incompletedItem = new TreeItem<>("Unadvised Advisees");
      root.getChildren().add(incompletedItem);
      for (String incompletedCourse : studentIncompletedCourses) {
        TreeItem<String> incompletedCourses = new TreeItem<>(incompletedCourse);
        incompletedItem.getChildren().add(incompletedCourses);
      }
    } else {
      if (studentCompletedCourses == null) {
        TreeItem<String> root = new TreeItem<>("Completed Courses Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve completed courses."
        );
        root.getChildren().add(errorItem);
      }
      if (studentIncompletedCourses == null) {
        TreeItem<String> root = new TreeItem<>("Incompleted Courses Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve incompleted courses."
        );
        root.getChildren().add(errorItem);
      }
    }
    advisingStudentProfileLabel.setVisible(false);
    //studentProfileTitleLabel.setText(userFirstName + " " + userLastName + "'s Student Profile");
    //studentsSummaryTitleLabel.setText("Student Summary:");
    if (facade.getStudentAppArea() != null) {
      //appAreaNotTitleLabel.setText(facade.getStudentAppArea());
    } else {
      //appAreaNotTitleLabel.setText("Undecided");
    }
    if (facade.getStudentMajor() != null) {
      //majorNotTitleLabel.setText(facade.getStudentMajor());
    } else {
      //majorNotTitleLabel.setText("Undeclared");
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
      //addNoteButton.setVisible(false);
      //noteListView.setPrefHeight(noteListView.getPrefHeight() + addNoteButton.getPrefHeight());
    }
    boolean isStudent = facade.getUser().getUserType().equals("STUDENT");

    if (!isStudent) {
      //declareMajorButton.setVisible(false);
      //declareAppAreaButton.setVisible(false);

    }
  }

  @FXML
  void setStageAdviseeManage(ActionEvent event) throws IOException {
    App.setRoot("adviseeManage");
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
}
