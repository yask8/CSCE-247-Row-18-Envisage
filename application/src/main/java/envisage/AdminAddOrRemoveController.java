package envisage;
/*
 * @author Stephon Johnson
 */
import AdvisingSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminAddOrRemoveController implements Initializable {

    private Facade facade;
    private ArrayList<User> filteredUsers;
    private final int ROWS_PER_PAGE = 3;
    private final int COLUMNS_PER_PAGE = 3;
    private int currentPage = 0;

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

    private void populateUserList() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(facade.getUserList().getUsers());

        int totalUsers = users.size();
        int totalPages = (int) Math.ceil((double) totalUsers / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        currentPage = Math.min(currentPage, totalPages - 1);
        currentPage = Math.max(currentPage, 0);

        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalUsers);

        addOrRemoveGridPane.getChildren().clear();

        for (int i = start; i < end; i++) {
            User user = users.get(i);
            Label userLabel = new Label(user.getFirstName() + " " + user.getLastName());
            addOrRemoveGridPane.add(userLabel, (i - start) % COLUMNS_PER_PAGE, (i - start) / COLUMNS_PER_PAGE);
        }

        searchErrorLabel.setText("");
        searchErrorLabel.setVisible(false);
    }

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
                    if (user.getUserType().equals("ADVISOR") && matchesSearch(user, searchText)) {
                        filteredUsers.add(user);
                    }
                    break;
                case "Admin":
                    if (user.getUserType().equals("ADMIN") && matchesSearch(user, searchText)) {
                        filteredUsers.add(user);
                    }
                    break;
                case "Student":
                    if (user.getUserType().equals("STUDENT") && matchesSearch(user, searchText)) {
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

    private boolean matchesSearch(User user, String searchText) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        return fullName.toLowerCase().contains(searchText);
    }

    private void displayFilteredUsers() {
        addOrRemoveGridPane.getChildren().clear();

        for (int i = 0; i < filteredUsers.size(); i++) {
            User user = filteredUsers.get(i);
            Label userLabel = new Label(user.getFirstName() + " " + user.getLastName());
            addOrRemoveGridPane.add(userLabel, i % COLUMNS_PER_PAGE, i / COLUMNS_PER_PAGE);
        }
    }

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

    @FXML
    void nextPage(ActionEvent event) {
        int totalUsers = (filteredUsers != null) ? filteredUsers.size() : facade.getUserList().getUsers().size();
        int totalPages = (int) Math.ceil((double) totalUsers / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        if (currentPage < totalPages - 1) {
            currentPage++;
            populateUserList();
        }
    }

    @FXML
    void previousPage(ActionEvent event) {
        if (currentPage > 0) {
            currentPage--;
            populateUserList();
        }
    }

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

