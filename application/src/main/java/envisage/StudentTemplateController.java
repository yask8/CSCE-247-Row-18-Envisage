package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StudentTemplateController implements Initializable {

  @FXML
  private Label studentNameLabel;

  @FXML
  private AnchorPane studentTemplatePane;

  @FXML
  private Button viewStudentProfileButton;

  public void setStudentName(String firstName, String lastName) {
    studentNameLabel.setText(firstName + " " + lastName);
  }

  @FXML
  void setStudentProfile(ActionEvent event) {
    // werid because i almost need to redo the initilze method of studentProfile
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }
}
