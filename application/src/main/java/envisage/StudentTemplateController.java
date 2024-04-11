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
    Facade facade = Facade.getInstance();
    String fullName = studentNameLabel.getText().trim();
    String[] nameParts = fullName.split(" ");
    String firstName = nameParts[0].trim();
    String lastName = nameParts[1].trim();
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("studentProfile.fxml"));
      Parent root = loader.load();

      UUID Id = facade.getUserIdByName(firstName, lastName);

      if (Id != null) {
        StudentProfileController controller = loader.getController();
        controller.setUserId(Id);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
      } else {

      }
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }
}