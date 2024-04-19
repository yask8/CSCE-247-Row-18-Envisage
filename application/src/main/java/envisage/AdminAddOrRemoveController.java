package envisage;

/**
 * Controller class for the Admin Add or Remove User page.
 * Handles actions and logic related to adding or removing users by an admin.
 * @author Row 18
 */

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

public class AdminAddOrRemoveController implements Initializable {

  // Instance variables
  private Facade facade;
  private ArrayList<User> filteredUsers;
  private final int ROWS_PER_PAGE = 3;
  private final int COLUMNS_PER_PAGE = 3;
  private int currentPage = 0;

  // FXML injected elements
  @FXML
  private AnchorPane AddOrRemoveUser;

  @FXML
  private ChoiceBox<String> IAmChoiceBox;

  @FXML
  private Button SaveButton;

  @FXML
  private GridPane addOrRemoveGridPane;

  @FXML
  private Label addOrremoveLabel;

  @FXML
  private Button backButton;

  @FXML
  private Button clearSearchButton;

  @FXML
  private ChoiceBox<String> filterByChoiceBox;

  @FXML
  private Button nextPageButton;

  @FXML
  private Button previousPageButton;

  @FXML
  private TextField searchBarTextField;

  @FXML
  private Button searchButton;

  @FXML
  private Label searchErrorLabel;

  @FXML
  private Label ErrormessageLabel;

  /**
   * Initializes the controller after its root element has been completely
   * processed.
   *
   * @param location  The location used to resolve relative paths for the root
   *                  object,
   *                  or null if the location is not known.
   * @param resources The resources used to localize the root object, or null
   *                  if the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    facade = Facade.getInstance();
    ObservableList<String> roleOptions = FXCollections.observableArrayList(
      "Advisor",
      "Admin",
      "Student"
    );
    IAmChoiceBox.setItems(roleOptions);
    populateUserList();
  }

  /**
   * Populates the user list grid with users based on the current page and
   * filters.
   */
  private void populateUserList() {
    ArrayList<User> users = new ArrayList<>();
    users.addAll(facade.getUserList().getUsers());

    int totalUsers = users.size();
    int totalPages = (int) Math.ceil(
      (double) totalUsers / (ROWS_PER_PAGE * COLUMNS_PER_PAGE)
    );

    currentPage = Math.min(currentPage, totalPages - 1);
    currentPage = Math.max(currentPage, 0);

    int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
    int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalUsers);

    addOrRemoveGridPane.getChildren().clear();

    for (int i = start; i < end; i++) {
      User user = users.get(i);
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("StudentTemplate.fxml")
      );
      try {
        AnchorPane studentTemplate = loader.load();

        StudentTemplateController controller = loader.getController();
        controller.setStudentName(user.getFirstName(), user.getLastName());

        int row = (i - start) / COLUMNS_PER_PAGE;
        int column = (i - start) % COLUMNS_PER_PAGE;
        addOrRemoveGridPane.add(studentTemplate, column, row);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    /*for (int i = start; i < end; i++) {
            User user = users.get(i);
            Label userLabel = new Label(user.getFirstName() + " " + user.getLastName());
            addOrRemoveGridPane.add(userLabel, (i - start) % COLUMNS_PER_PAGE, (i - start) / COLUMNS_PER_PAGE);
        }*/

    searchErrorLabel.setText("");
    searchErrorLabel.setVisible(false);
  }

  /**
   * Handles the save action, either adding or removing users based on the
   * selected action.
   *
   * @param event The event representing the action.
   */
  @FXML
  void save(ActionEvent event) {
    String selectedAction = IAmChoiceBox.getValue().toString();

    if (selectedAction == null || selectedAction.isEmpty()) {
      ErrormessageLabel.setText("Please select an action (Add/Remove).");
      return;
    }

    switch (selectedAction) {
      case "Add":
        ErrormessageLabel.setText("Users added successfully.");
        // Implement add logic
        break;
      case "Remove":
        ErrormessageLabel.setText("Users removed successfully.");
        // Implement remove logic
        break;
      default:
        ErrormessageLabel.setText("Invalid action selected.");
        break;
    }
  }

  /**
   * Handles the search action, filtering users based on search text and criteria.
   *
   * @param event The event representing the action.
   */
  @FXML
  void search(ActionEvent event) {
    String searchText = searchBarTextField.getText().trim().toLowerCase();
    String filterCriteria = filterByChoiceBox.getValue();

    if (searchText.isEmpty() || filterCriteria == null) {
      searchErrorLabel.setText("Please enter search text and select a filter.");
      searchErrorLabel.setVisible(true);
      return;
    }

    ArrayList<User> users = new ArrayList<>();
    users.addAll(facade.getUserList().getUsers());

    filteredUsers = new ArrayList<>();

    for (User user : users) {
      switch (filterCriteria) {
        case "Advisor":
          if (
            user.getUserType().equals("ADVISOR") &&
            matchesSearch(user, searchText)
          ) {
            filteredUsers.add(user);
          }
          break;
        case "Admin":
          if (
            user.getUserType().equals("ADMIN") &&
            matchesSearch(user, searchText)
          ) {
            filteredUsers.add(user);
          }
          break;
        case "Student":
          if (
            user.getUserType().equals("STUDENT") &&
            matchesSearch(user, searchText)
          ) {
            filteredUsers.add(user);
          }
          break;
      }
    }

    if (filteredUsers.isEmpty()) {
      searchErrorLabel.setText("No matching users found.");
      searchErrorLabel.setVisible(true);
    } else {
      searchErrorLabel.setText("");
      searchErrorLabel.setVisible(false);
      displayFilteredUsers();
    }
  }

  /**
   * Checks if a user's full name matches the search text.
   *
   * @param user       The user to check.
   * @param searchText The search text to match against.
   * @return True if the user's full name matches the search text, false
   *         otherwise.
   */
  private boolean matchesSearch(User user, String searchText) {
    String fullName = user.getFirstName() + " " + user.getLastName();
    return fullName.toLowerCase().contains(searchText);
  }

  /**
   * Displays the filtered users in the grid.
   */
  private void displayFilteredUsers() {
    addOrRemoveGridPane.getChildren().clear();

    for (int i = 0; i < filteredUsers.size(); i++) {
      User user = filteredUsers.get(i);
      Label userLabel = new Label(
        user.getFirstName() + " " + user.getLastName()
      );
      addOrRemoveGridPane.add(
        userLabel,
        i % COLUMNS_PER_PAGE,
        i / COLUMNS_PER_PAGE
      );
    }
  }

  /**
   * Clears the search filters and resets the user list to the initial state.
   *
   * @param event The event representing the action.
   */
  @FXML
  void clearSearch(ActionEvent event) {
    searchBarTextField.clear();
    filterByChoiceBox.getSelectionModel().clearSelection();
    searchErrorLabel.setText("");
    searchErrorLabel.setVisible(false);
    filteredUsers = null;
    currentPage = 0;
    populateUserList();
  }

  /**
   * Navigates to the next page of users in the grid.
   *
   * @param event The event representing the action.
   */
  @FXML
  void nextPage(ActionEvent event) {
    int totalUsers = (filteredUsers != null)
      ? filteredUsers.size()
      : facade.getUserList().getUsers().size();
    int totalPages = (int) Math.ceil(
      (double) totalUsers / (ROWS_PER_PAGE * COLUMNS_PER_PAGE)
    );

    if (currentPage < totalPages - 1) {
      currentPage++;
      populateUserList();
    }
  }

  /**
   * Navigates to the previous page of users in the grid.
   *
   * @param event The event representing the action.
   */
  @FXML
  void previousPage(ActionEvent event) {
    if (currentPage > 0) {
      currentPage--;
      populateUserList();
    }
  }

  /**
   * Sets the stage to the appropriate dashboard based on the user's role.
   *
   * @param event The event representing the action.
   * @throws IOException if an I/O error occurs when setting the root scene.
   */
  @FXML
  void setStageDashboard(ActionEvent event) throws IOException {
    if (facade.getUser() == null) {
      return;
    }
    switch (facade.getUser().getUserType()) {
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
}
