package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

public class MajorMapController implements Initializable {

  private Facade facade = Facade.getInstance();
  private ArrayList<MajorMap> majorList = facade.getMajors();
  private String majorName;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button backButton;

  @FXML
  private Label caroCoreHoursLabel;

  @FXML
  private TreeView<String> courseListTreeView;

  @FXML
  private Label majorNameLabel;

  @FXML
  private Label minGPAlabel;

  @FXML
  private Label minGradHoursLabel;

  @FXML
  private Label minTotalHoursLabel;

  @FXML
  void setStageMajorList(ActionEvent event) throws IOException {
    System.out.println("Button clicked: setStageMajorList method called.");
    try {
      App.setRoot("majorList");
      System.out.println("Switched to majorList screen successfully.");
    } catch (IOException e) {
      System.err.println(
          "Error switching to majorList screen: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void initialize(URL url, ResourceBundle rb) {
    majorName = MajorNameStore.getInstance().getMajorName();
    if (majorName != null && !majorName.isEmpty()) {
      majorNameLabel.setText(majorName);
      loadMajorMapData();
    }
  }

  private void loadMajorMapData() {
    MajorMap majorMap = findMajorMapByName(majorName);
    if (majorMap != null) {
      caroCoreHoursLabel.setText(Integer.toString(majorMap.getCaroCoreHours()));
      minGPAlabel.setText(Double.toString(majorMap.getMinGPA()));
      minGradHoursLabel.setText(Integer.toString(majorMap.getMinGradHours()));
      minTotalHoursLabel.setText(Integer.toString(majorMap.getMinTotalHours()));

      TreeItem<String> root = new TreeItem<>("Courses");
      courseListTreeView.setRoot(root);

      for (int i = 1; i <= 8; i++) {
        List<String> courses = majorMap.getSemester(i);
        if (courses != null && !courses.isEmpty()) {
          String semesterName = "Semester " + i;
          TreeItem<String> semesterNode = new TreeItem<>(semesterName);
          for (String course : courses) {
            TreeItem<String> courseNode = new TreeItem<>(course);
            semesterNode.getChildren().add(courseNode);
          }
          root.getChildren().add(semesterNode);
        }
      }
    }
  }

  private MajorMap findMajorMapByName(String majorName) {
    for (MajorMap majorMap : majorList) {
      if (majorMap.getMajor().equals(majorName)) {
        return majorMap;
      }
    }
    return null;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
    System.out.println("Major Name set: " + majorName);
    majorNameLabel.setText(majorName);
    MajorNameStore.getInstance().setMajorName(majorName);
    loadMajorMapData();
  }
}
