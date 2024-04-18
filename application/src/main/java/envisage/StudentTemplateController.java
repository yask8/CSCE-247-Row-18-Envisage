package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import AdvisingSoftware.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

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
      try {
          Facade facade = Facade.getInstance();
          String fullName = studentNameLabel.getText().trim();
          String[] nameParts = fullName.split(" ");
          String firstName = nameParts[0].trim();
          String lastName = nameParts[1].trim();
  
          UUID Id = facade.getUserIdByName(firstName, lastName);
  
          if (Id != null) {
              StudentIDStore.getInstance().setStudentID(Id);
              App.setRoot("studentProfile");
          } else {
              setStageStudentLookup(event);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  @FXML
  void setStageStudentLookup(ActionEvent event) throws IOException {
    App.setRoot("studentLookup");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }
}