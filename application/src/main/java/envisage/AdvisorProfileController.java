package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
      initAdvisor();
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

  private void initAdvised() throws ParseException {
    ArrayList<Student> listOfAdvisees = getAdvisees();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
    String stringDateSPRING24 = "01-01-2024";
    String stringDateFALL23 = "09-01-2023";
    Date dateSPRING24 = dateFormat.parse(stringDateSPRING24);
    Date dateFALL23 = dateFormat.parse(stringDateFALL23);

    for (Student adviseeCheck : getAdvisees()) {
      if (adviseeCheck.getAdvisorNotes().isEmpty()) {} else {}
    }

    if (!listOfAdvisees.isEmpty()) {
      TreeItem<String> root = new TreeItem<>(userFirstName + "'s Advisees");

      adviseeCompletionTree.setRoot(root);
      TreeItem<String> cumulativeItem = new TreeItem<>("All Advisees");
      root.getChildren().add(cumulativeItem);

      for (Student xadvisee : listOfAdvisees) {
        TreeItem<String> displayedAdviseeList = new TreeItem<>(
          xadvisee.toString()
        );
        cumulativeItem.getChildren().add(displayedAdviseeList);
      }

      TreeItem<String> subCategoryItem = new TreeItem<>("At-Risk Advisees");
      root.getChildren().add(subCategoryItem);

      for (Student xadvisee : listOfFailing) {
        TreeItem<String> displayedFailingList = new TreeItem<>(
          xadvisee.toString()
        );
        subCategoryItem.getChildren().add(displayedFailingList);
      }
    } else {
      TreeItem<String> root = new TreeItem<>("Advisee List Error");
      adviseeCompletionTree.setRoot(root);
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve any advisees."
      );
      root.getChildren().add(errorItem);

      /*TreeItem<String> */root = new TreeItem<>("At-Risk List Error");
      adviseeCompletionTree.setRoot(root);
      /*TreeItem<String> */errorItem =
        new TreeItem<>("Unable to retrieve At-Risk advisees.");
      root.getChildren().add(errorItem);
      /* 
      if (listOfAdvisees == null) {
        TreeItem<String> root = new TreeItem<>("Advisee List Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve any advisees."
        );
        root.getChildren().add(errorItem);
      }
      if (listOfFailing == null) {
        TreeItem<String> root = new TreeItem<>("At-Risk List Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve At-Risk advisees."
        );
        root.getChildren().add(errorItem);
      }
    */
    }
  }

  private void initAdvisor() {
    ArrayList<Student> listOfAdvisees = getAdvisees();
    ArrayList<Student> listOfFailing = new ArrayList<Student>();

    for (UUID fail : ((Advisor) user).getListOfFailingStudents()) {
      Student displayedStudent =
        ((Advisor) user).getStudentByAdvisor(fail, facade.getUserList());

      if (!listOfFailing.contains(displayedStudent)) {
        listOfFailing.add(displayedStudent);
      }
    }

    if (!listOfAdvisees.isEmpty()) {
      TreeItem<String> root = new TreeItem<>(userFirstName + "'s Advisees");

      adviseeCompletionTree.setRoot(root);
      TreeItem<String> cumulativeItem = new TreeItem<>("All Advisees");
      root.getChildren().add(cumulativeItem);

      for (Student xadvisee : listOfAdvisees) {
        TreeItem<String> displayedAdviseeList = new TreeItem<>(
          xadvisee.toString()
        );
        cumulativeItem.getChildren().add(displayedAdviseeList);
      }

      TreeItem<String> subCategoryItem = new TreeItem<>("At-Risk Advisees");
      root.getChildren().add(subCategoryItem);

      for (Student xadvisee : listOfFailing) {
        TreeItem<String> displayedFailingList = new TreeItem<>(
          xadvisee.toString()
        );
        subCategoryItem.getChildren().add(displayedFailingList);
      }
    } else {
      TreeItem<String> root = new TreeItem<>("Advisee List Error");
      adviseeCompletionTree.setRoot(root);
      TreeItem<String> errorItem = new TreeItem<>(
        "Unable to retrieve any advisees."
      );
      root.getChildren().add(errorItem);

      /*TreeItem<String> */root = new TreeItem<>("At-Risk List Error");
      adviseeCompletionTree.setRoot(root);
      /*TreeItem<String> */errorItem =
        new TreeItem<>("Unable to retrieve At-Risk advisees.");
      root.getChildren().add(errorItem);
      /* 
      if (listOfAdvisees == null) {
        TreeItem<String> root = new TreeItem<>("Advisee List Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve any advisees."
        );
        root.getChildren().add(errorItem);
      }
      if (listOfFailing == null) {
        TreeItem<String> root = new TreeItem<>("At-Risk List Error");
        adviseeCompletionTree.setRoot(root);
        TreeItem<String> errorItem = new TreeItem<>(
          "Unable to retrieve At-Risk advisees."
        );
        root.getChildren().add(errorItem);
      }
    */
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
