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
  private TreeView<?> adviseeCompletionTree;

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
  private ListView<?> noteListView;

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
  UUID studentID = null;

  @Override
  public void initialize(URL url, ResourceBundle arg1) {
    if (facade.getUser().getUserType().equals("STUDENT")) {
      //initStudent();
    } else {
      //initOtherUsers(studentID);
    }
  }
}
