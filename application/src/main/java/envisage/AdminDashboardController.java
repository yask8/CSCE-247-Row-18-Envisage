package envisage;

/**
 * Controller class for the Admin Dashboard page.
 * Handles actions and navigation for the admin dashboard UI.
 * @author Row 18
 */

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdminDashboardController implements Initializable {

  // Instance variables
  private Facade facade;
  private User user;

  // FXML injected elements
  @FXML
  private Label welcomeLabel;

  @FXML
  private Label IDNotTitleLabel;

  @FXML
  private Label mainEmailTitleLabel;

  @FXML
  private AnchorPane adminDashboard;

  @FXML
  private Button adminProfileButton;

  @FXML
  private Button manageAppAreasButton;

  @FXML
  private Button manageCoursesButton;

  @FXML
  private Button manageMajorMapsButton;

  @FXML
  private Button AddOrRemoveUsersButton;

  @FXML
  private Button signOutButton;

  @FXML
  private Button studentLookupButton;

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
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    facade = Facade.getInstance();
    user = facade.getUser();
    welcomeLabel.setText("Welcome " + user.getFirstName());
    IDNotTitleLabel.setText(user.getID().toString());
    mainEmailTitleLabel.setText(user.getEmail());
  }

  /**
   * Navigates to the Student Lookup page.
   *
   * @param event The event representing the action.
   * @throws IOException if an I/O error occurs when setting the root scene.
   */
  @FXML
  void setStageStudentLookup(ActionEvent event) throws IOException {
    App.setRoot("studentLookup");
  }

  /**
   * Navigates to the Advisor Profile page.
   *
   * @param event The event representing the action.
   * @throws IOException if an I/O error occurs when setting the root scene.
   */
  @FXML
  void setStageAdvisorProfile(ActionEvent event) throws IOException {
    App.setRoot("advisorProfile");
  }

  /**
   * Navigates to the Admin Profile page.
   *
   * @param event The event representing the action.
   */
  @FXML
  void setStageAdminProfile(ActionEvent event) {
    // Implement navigation to Admin Profile page
  }

  /**
   * Navigates to the Major List page.
   *
   * @param event The event representing the action.
   * @throws IOException if an I/O error occurs when setting the root scene.
   */
  @FXML
  void setStageMajorList(ActionEvent event) throws IOException {
    App.setRoot("majorList");
  }

  /**
   * ActionEvent that sets the stage to the Add/Remove Users screen.
   * 
   * @param event The event representing the action.
   * @throws IOExeception if an I/O error occurs when setting the root scene.
   */
  @FXML
  void setStageAddRemoveUsers(ActionEvent event) throws IOException {
    App.setRoot("AdminAddOrRemove");
  }

  /**
   * ActionEvent that sets the stage to the Manage Courses screen.
   * 
   * @param event The event representing the action.
   * @throws IOException
   */
  @FXML
  void setStageManageCourses(ActionEvent event) throws IOException {
    App.setRoot("manageCourses");
  }

  /**
   * Signs out the user and navigates to the Login page.
   *
   * @param event The event representing the action.
   */
  @FXML
  void signOut(ActionEvent event) {
    try {
      Facade.getInstance().signOut();
      App.setRoot("LogIn");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
