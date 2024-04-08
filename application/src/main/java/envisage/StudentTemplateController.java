package envisage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StudentTemplateController implements Initializable {

  @FXML
  private Label studentNameLabel;

  @FXML
  private AnchorPane studentTemplatePane;

  @FXML
  private Button viewStudentInfoButton;

  public void setStudentName(String firstName, String lastName) {
    studentNameLabel.setText(firstName + " " + lastName);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {}
}
