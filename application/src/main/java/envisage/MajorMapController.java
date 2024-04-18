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

/**
 * Controller class for the MajorMap.fxml file.
 * Handles actions and displays related to the major map functionality.
 */
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

  /**
   * Handles the action event for navigating back to the major list or student
   * dashboard.
   * 
   * @param event The action event that occurred.
   * @throws IOException If an error occurs while loading the FXML file for the
   *                     destination view.
   */
  @FXML
  void setStageMajorList(ActionEvent event) throws IOException {
    User user = facade.getUser();
    if (user.getUserType().equals("STUDENT") && (facade.getStudentMajor() != null && !facade.getStudentMajor().isEmpty()
        && !facade.getStudentMajor().equals("Undeclared"))) {

      try {
        App.setRoot("studentDashboard");
      } catch (IOException e) {
        System.err.println("Error switching to studentDashboard screen: " + e.getMessage());
        e.printStackTrace();
      }
    } else {

      try {
        App.setRoot("majorList");
      } catch (IOException e) {
        System.err.println("Error switching to majorList screen: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  /**
   * Initializes the major map view.
   * Loads major map data based on the selected major.
   * 
   * @param url The location used to resolve relative paths for the root object,
   *            or null if the location is not known.
   * @param rb  The resources used to localize the root object, or null if the
   *            root object was not localized.
   */
  public void initialize(URL url, ResourceBundle rb) {
    majorName = MajorNameStore.getInstance().getMajorName();
    if (majorName != null && !majorName.isEmpty()) {
      majorNameLabel.setText(majorName);
      loadMajorMapData();
    }
  }

  /**
   * Loads data for the selected major map.
   * Populates the major map details and course tree view.
   */
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

  /**
   * Finds a major map by its name.
   * 
   * @param majorName The name of the major map to find.
   * @return The MajorMap object if found, or null if not found.
   */
  private MajorMap findMajorMapByName(String majorName) {
    for (MajorMap majorMap : majorList) {
      if (majorMap.getMajor().equals(majorName)) {
        return majorMap;
      }
    }
    return null;
  }

  /**
   * Setter method for the major name.
   * Updates the major name and loads data for the corresponding major map.
   * 
   * @param majorName The name of the major.
   */
  public void setMajorName(String majorName) {
    this.majorName = majorName;
    System.out.println("Major Name set: " + majorName);
    majorNameLabel.setText(majorName);
    MajorNameStore.getInstance().setMajorName(majorName);
    loadMajorMapData();
  }
}
