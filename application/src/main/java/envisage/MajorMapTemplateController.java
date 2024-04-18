package envisage;

import AdvisingSoftware.Facade;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MajorMapTemplateController {

  Facade facade = Facade.getInstance();

  @FXML
  private Label majorMapLabel;

  @FXML
  private AnchorPane majrMapTemplatePane;

  @FXML
  private Button viewMajorMapButton;

  @FXML
  void setMajorMap(ActionEvent event) throws IOException {
      String majorName = majorMapLabel.getText().trim();
      if (majorName == null || majorName.isEmpty() || majorName.equals("Undeclared")) {
          try {
              App.setRoot("majorList");
          } catch (IOException e) {
              System.err.println("Error loading MajorList view: " + e.getMessage());
          }
      } else {
          MajorNameStore.getInstance().setMajorName(majorName);
          try {
              App.setRoot("MajorMap");
          } catch (IOException e) {
              System.err.println("Error loading MajorMap view: " + e.getMessage());
          }
      }
    }

  public void setMajorName(String majorName) {
    majorMapLabel.setText(majorName);
  }
}
