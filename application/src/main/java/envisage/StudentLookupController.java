package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class StudentLookupController implements Initializable {

  private Facade facade;
  private User user;
  private ArrayList<Student> filteredStudents;
  private final int ROWS_PER_PAGE = 3;
  private final int COLUMNS_PER_PAGE = 3;
  private int currentPage = 0;

  @FXML
  private Button backButton;

  @FXML
  private Button clearSearchButton;

  @FXML
  private ChoiceBox<?> filterByChoiceBox;

  @FXML
  private Button nextPageButton;

  @FXML
  private Label pageNumberLabel;

  @FXML
  private Button previousPageButton;

  @FXML
  private TextField searchBarTextField;

  @FXML
  private Button searchButton;

  @FXML
  private Label searchErrorLabel;

  @FXML
  private AnchorPane studentLookup;

  @FXML
  private GridPane studentLookupGridPane;

  @FXML
  private Label studentLookupLabel;

  @FXML
  void nextPage(ActionEvent event) {}

  @FXML
  void previousPage(ActionEvent event) {}

  @FXML
  void search(ActionEvent event) {}

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'initialize'"
    );
  }

  private void displayFilteredStudents(ArrayList<Student> filteredStudents) {
    studentLookupGridPane.getChildren().clear();

    for (int i = 0; i < filteredStudents.size(); i++) {
      Student student = filteredStudents.get(i);
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("StudentTemplate.fxml")
      );
      try {
        AnchorPane studentTemplate = loader.load();

        StudentTemplateController controller = loader.getController();
        controller.setStudentName(
          student.getFirstName(),
          student.getLastName()
        );

        int row = i / COLUMNS_PER_PAGE;
        int column = i % COLUMNS_PER_PAGE;
        studentLookupGridPane.add(studentTemplate, column, row);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  void setStageDashboard(ActionEvent event) throws IOException {
    if (user == null) {
      return;
    }
    switch (user.getUserType()) {
      case "STUDENT":
        App.setRoot("studentDashboard");
        break;
      case "ADVISOR":
        App.setRoot("advisorDashboard");
        break;
      case "ADMIN":
        App.setRoot("adminDashboard");
        break;
      default:
        break;
    }
  }

  @FXML
  void clear(ActionEvent event) {
    searchBarTextField.clear();
    filterByChoiceBox.getSelectionModel().clearSelection();
    filteredStudents = null;
    currentPage = 0;
    populateStudentList();
  }
}
