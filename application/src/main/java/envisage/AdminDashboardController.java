package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

//Author @Spillmag and Stephon Johnson

public class AdminDashboardController implements Initializable {

    private Facade facade;
    private User user;

    @FXML
    private Label welcomeLabel;

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    facade = Facade.getInstance();
    user = facade.getUser();
    welcomeLabel.setText("Welcome " + user.getFirstName());
  }

  @FXML
  void setStageStudentLookup(ActionEvent event) throws IOException {
    App.setRoot("studentLookup");
  }

  @FXML
  void setStageAdvisorProfile(ActionEvent event) throws IOException {
    App.setRoot("advisorProfile");
  }

  @FXML
  void setStageMajorList(ActionEvent event) throws IOException {
    App.setRoot("majorList");
  }

  @FXML
  void setStageAddRemoveUsers(ActionEvent event) throws IOException{
    App.setRoot("AdminAddOrRemove");
  }

  @FXML
  void setStageManageCourses(ActionEvent event) throws IOException{
    App.setRoot("manageCourses");
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
}
