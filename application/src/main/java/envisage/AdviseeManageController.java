package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
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

public class AdviseeManageController implements Initializable {

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
  private ChoiceBox<String> filterByChoiceBox;

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
  private AnchorPane adviseeManage;

  @FXML
  private GridPane adviseeManageGridPane;

  @FXML
  private Label adviseeManageLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    facade = Facade.getInstance();
    user = facade.getUser();
    ObservableList<String> roleOptions = FXCollections.observableArrayList(
      "First Name",
      "Last Name",
      "Risk Of Failure"
    );
    filterByChoiceBox.setItems(roleOptions);
    populateAdviseeList();
  }

  private ArrayList<Student> getAdvisees() {
    ArrayList<UUID> advisees = ((Advisor) user).getListOfAdvisees();
    ArrayList<Student> students = new ArrayList<Student>();

    for (UUID advisee : advisees) {
      students.add(
        ((Advisor) user).getStudentByAdvisor(advisee, facade.getUserList())
      );
    }
    return students;
  }

  private void populateAdviseeList() {
    ArrayList<Student> students = getAdvisees();

    int totalStudents = students.size();
    int totalPages = (int) Math.ceil(
      (double) totalStudents / (ROWS_PER_PAGE * COLUMNS_PER_PAGE)
    );

    currentPage = Math.min(currentPage, totalPages - 1);
    currentPage = Math.max(currentPage, 0);

    int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
    int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalStudents);

    adviseeManageGridPane.getChildren().clear();

    for (int i = start; i < end; i++) {
      Student student = students.get(i);
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

        int row = (i - start) / COLUMNS_PER_PAGE;
        int column = (i - start) % COLUMNS_PER_PAGE;
        adviseeManageGridPane.add(studentTemplate, column, row);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    pageNumberLabel.setText("Page " + (currentPage + 1) + " / " + totalPages);
  }

  @FXML
  void nextPage(ActionEvent event) {
    ArrayList<Student> xstudents;
    if (filteredStudents != null) {
      xstudents = filteredStudents;
    } else {
      xstudents = new ArrayList<Student>();
    }

    int totalStudents = xstudents.size();
    int totalPages = (int) Math.ceil(
      (double) totalStudents / (ROWS_PER_PAGE * COLUMNS_PER_PAGE)
    );

    if (currentPage < totalPages - 1) {
      currentPage++;
      adviseeManageGridPane.getChildren().clear();
      populateAdviseeList();
    }
  }

  @FXML
  void previousPage(ActionEvent event) {
    if (currentPage > 0) {
      currentPage--;
      adviseeManageGridPane.getChildren().clear();
      populateAdviseeList();
    }
  }

  @FXML
  void search(ActionEvent event) {
    String searchText = searchBarTextField.getText().trim().toLowerCase();
    String filterCriteria = filterByChoiceBox.getValue();

    if (searchText.isEmpty()/* || filterCriteria == null*/) {
      if (
        !filterCriteria.equalsIgnoreCase("Risk of Failure") ||
        filterCriteria == null
      ) {
        searchErrorLabel.setText(
          "Please enter search text and select a filter."
        );
        return;
      }
    }

    ArrayList<Student> students = getAdvisees();

    filteredStudents = new ArrayList<>();

    for (Student student : students) {
      switch (filterCriteria) {
        case "First Name":
          if (student.getFirstName().toLowerCase().contains(searchText)) {
            filteredStudents.add(student);
          }
          break;
        case "Last Name":
          if (student.getLastName().toLowerCase().contains(searchText)) {
            filteredStudents.add(student);
          }
          break;
        case "Risk Of Failure":
          for (UUID fail : ((Advisor) user).getListOfFailingStudents()) {
            Student displayedStudent =
              ((Advisor) user).getStudentByAdvisor(fail, facade.getUserList());
            String fullName =
              displayedStudent.getFirstName() +
              " " +
              displayedStudent.getLastName();

            boolean searchAdvisee =
              (fullName.toLowerCase().contains(searchText));

            if (searchText.isEmpty() || searchText.isBlank() || searchAdvisee) {
              if (!filteredStudents.contains(displayedStudent)) {
                filteredStudents.add(displayedStudent);
              }
            }
          }
          break;
        default:
          break;
      }
    }

    if (filteredStudents.isEmpty()) {
      searchErrorLabel.setText("No matching students found.");
    } else {
      searchErrorLabel.setText("");
      displayFilteredStudents(filteredStudents);

      currentPage = 0;

      int totalStudents = filteredStudents.size();
      int totalPages = (int) Math.ceil(
        ((double) totalStudents) / (ROWS_PER_PAGE * COLUMNS_PER_PAGE)
      );
      pageNumberLabel.setText("Page " + (currentPage + 1) + " / " + totalPages);
    }
  }

  private void displayFilteredStudents(ArrayList<Student> filteredStudents) {
    adviseeManageGridPane.getChildren().clear();

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
        adviseeManageGridPane.add(studentTemplate, column, row);
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
    populateAdviseeList();
  }
}
