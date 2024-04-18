package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
/**
 * Controller class for AdvisorProfile page
 * @author Lia Zhao (zhaolia9), Yasmine Kennedy (yask8), and Garrett Spillman (Spillmag)
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

  UUID advisorID = null;

  @Override
  public void initialize(URL url, ResourceBundle arg1) {
    if (facade.getUser().getUserType().equals("ADVISOR")) {
      IDNotTitleLabel.setText(user.getID().toString());
      mainEmailTitleLabel.setText(user.getEmail());

      try {
        initAdvised();
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * getter for Advisor to get advisees
   * converts UUID to Student array list
   * @return ArrayList<Student> list of Advisees
   */
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

  /**
   * returns and updates the advising period based on current time
   * @return Date advising period
   * @throws ParseException
   */
  private Date updateAdvisingPeriod() throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);

    String fallAdvisingDate = "09-01-" + (year - 1);
    String springAdvisingDate = "01-01-" + year;

    if (month >= 9) {
      return dateFormat.parse(fallAdvisingDate);
    } else {
      return dateFormat.parse(springAdvisingDate);
    }
  }

  /**
   * populates the AdvisorProfile screen with a tree that shows advised/unadvised students
   * @throws ParseException
   */
  private void initAdvised() throws ParseException {
    ArrayList<Student> listOfAdvisees = getAdvisees();
    Date advisingComparison = updateAdvisingPeriod();

    ArrayList<Student> advisedList = new ArrayList<>();
    ArrayList<Student> unadvisedList = new ArrayList<>();

    for (Student adviseeCheck : getAdvisees()) {
      if (adviseeCheck.getAdvisorNotes().isEmpty()) {
        // add to list of unadvised
        unadvisedList.add(adviseeCheck);
      } else {
        //if most recent note is after the advising period then
        //the student is added to advised
        if (
          adviseeCheck
            .getAdvisorNotes()
            .get(adviseeCheck.getAdvisorNotes().size() - 1)
            .getDate()
            .after(advisingComparison)
        ) {
          advisedList.add(adviseeCheck);
          //otherwise the student is added to unadvised
        } else {
          unadvisedList.add(adviseeCheck);
        }
      }
    }

    if (!listOfAdvisees.isEmpty()) {
      TreeItem<String> root = new TreeItem<>(userFirstName + "'s Advisees");

      adviseeCompletionTree.setRoot(root);
      TreeItem<String> advisedTreeItem = new TreeItem<>("Advised Advisees");
      root.getChildren().add(advisedTreeItem);

      for (Student xadvisee : advisedList) {
        TreeItem<String> displayedAdvisedAdviseesList = new TreeItem<>(
          xadvisee.getFirstName() + " " + xadvisee.getLastName()
        );
        advisedTreeItem.getChildren().add(displayedAdvisedAdviseesList);
      }

      TreeItem<String> unadvisedTreeItem = new TreeItem<>("Unadvised Advisees");
      root.getChildren().add(unadvisedTreeItem);

      for (Student xadvisee : unadvisedList) {
        TreeItem<String> displayedUnadvisedAdviseesList = new TreeItem<>(
          xadvisee.getFirstName() + " " + xadvisee.getLastName()
        );
        unadvisedTreeItem.getChildren().add(displayedUnadvisedAdviseesList);
      }
    } else {
      TreeItem<String> root = new TreeItem<>("Advised Advisees List Error");
      adviseeCompletionTree.setRoot(root);
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve any advised advisees."
      );
      root.getChildren().add(errorItem);

      root = new TreeItem<>("Unadvised Advisees List Error");
      adviseeCompletionTree.setRoot(root);
      errorItem = new TreeItem<>("Unable to retrieve any unadvised advisees.");
      root.getChildren().add(errorItem);
    }
  }

  /**
   * shows whether a gpa is failing or passing
   * @param event when button is clicked, a pop up appears to take an input of gpa and major
   * @throws IOException
   */
  @FXML
  void checkFailButton(ActionEvent event) throws IOException {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Check Fail Status With GPA");
    dialog.setHeaderText("Enter the current GPA to check fail status");
    dialog.setContentText("Enter the current GPA");
    Optional<String> result = dialog.showAndWait();

    result.ifPresent(noteText -> {
      double d = Double.parseDouble(noteText);
      dialog.setTitle("Check Fail Status With GPA");
      dialog.setHeaderText("Enter the current major to check fail status");
      dialog.setContentText("Enter the major");
      Optional<String> result2 = dialog.showAndWait();
      result2.ifPresent(xmajor -> {
        if (facade.getMajorMap(xmajor) != null) {
          boolean fail =
            ((Advisor) user).checkStudentFailStatus(
                d,
                facade.getMajorMap(xmajor).getMinGPA()
              );

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Check Fail Status Calculated");
          alert.setHeaderText("Complete!");
          String failing = "passing!";
          if (fail) {
            failing = "failing!";
          }
          alert.setContentText(
            "With this GPA, the student would be " + failing
          );
          alert.showAndWait();
        } else {
          boolean fail =
            ((Advisor) user).checkStudentFailStatus(
                d,
                facade.getMajorMap("Computer Science").getMinGPA()
              );

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Check Fail Status Calculated");
          alert.setHeaderText("Complete!");
          String failing = "passing!";
          if (fail) {
            failing = "failing!";
          }
          alert.setContentText(
            "With this GPA, the student would be " + failing
          );
          alert.showAndWait();
        }
      });
    });
  }

  /**
   *
   * @param event when button is clicked it returns to AdviseeManage screen
   * @throws IOException
   */
  @FXML
  void setStageAdviseeManage(ActionEvent event) throws IOException {
    App.setRoot("adviseeManage");
  }

  /**
   * return to user dashboard
   * @param event when button is clicked it checks user type to return to corresponding dashboard
   * @throws IOException
   */
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
