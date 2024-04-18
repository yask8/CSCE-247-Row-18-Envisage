package envisage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdvisingSoftware.*;

/**
 * Controller class for the MajorList.fxml file.
 * This class handles the display of major maps and provides functionality for
 * navigation between pages,
 * searching for majors, and clearing the search.
 */
public class MajorListController implements Initializable {

    private Facade facade;
    private User user;
    private ArrayList<MajorMap> majors;
    private final int ROWS_PER_PAGE = 3;
    private final int COLUMNS_PER_PAGE = 3;
    private int currentPage = 0;

    @FXML
    private Button backButton;

    @FXML
    private Button clearSearchButton;

    @FXML
    private AnchorPane courseMajorMapList;

    @FXML
    private GridPane majorMapListGridPane;

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

    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded.
     * It populates the major map list on the initial display.
     * 
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateMajorMapList();
    }

    private void populateMajorMapList() {

        facade = Facade.getInstance();
        user = facade.getUser();

        majors = facade.getMajors();
        int totalMajorMaps = majors.size();
        int totalPages = (int) Math.ceil((double) totalMajorMaps / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        currentPage = Math.min(currentPage, totalPages - 1);
        currentPage = Math.max(currentPage, 0);

        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalMajorMaps);

        majorMapListGridPane.getChildren().clear();

        for (int i = start; i < end; i++) {
            MajorMap majorMap = majors.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MajorMapTemplate.fxml"));
            try {
                AnchorPane majorMapTemplate = loader.load();

                MajorMapTemplateController controller = loader.getController();
                controller.setMajorName(majorMap.getMajor());

                int row = (i - start) / COLUMNS_PER_PAGE;
                int column = (i - start) % COLUMNS_PER_PAGE;
                majorMapListGridPane.add(majorMapTemplate, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pageNumberLabel.setText("Page " + (currentPage + 1) + " / " + totalPages);
    }

    /**
     * Handles the action event for navigating to the next page of major maps.
     * Increments the current page index and repopulates the major map list.
     * 
     * @param event The action event that occurred.
     */
    @FXML
    void nextPage(ActionEvent event) {
        if (currentPage < getTotalPages() - 1) {
            currentPage++;
            populateMajorMapList();
        }
    }

    /**
     * Handles the action event for navigating to the previous page of major maps.
     * Decrements the current page index and repopulates the major map list.
     * 
     * @param event The action event that occurred.
     */
    @FXML
    void previousPage(ActionEvent event) {
        if (currentPage > 0) {
            currentPage--;
            populateMajorMapList();
        }
    }

    /**
     * Handles the action event for searching for majors.
     * Retrieves the search text from the search bar, filters the majors based on
     * the search text,
     * and displays the filtered majors.
     * 
     * @param event The action event that occurred.
     */
    @FXML
    void search(ActionEvent event) {
        String searchText = searchBarTextField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            populateMajorMapList();
            return;
        }

        ArrayList<MajorMap> filteredMajors = new ArrayList<>();

        for (MajorMap majorMap : majors) {
            if (majorMap.getMajor().toLowerCase().contains(searchText)) {
                filteredMajors.add(majorMap);
            }
        }

        displayFilteredMajors(filteredMajors);
    }

    /**
     * Handles the action event for clearing the search bar.
     * Clears the search bar text and repopulates the major map list.
     * 
     * @param event The action event that occurred.
     */
    @FXML
    void clear(ActionEvent event) {
        searchBarTextField.clear();
        populateMajorMapList();
    }

    /**
     * Displays the filtered list of majors.
     * Updates the major map list with the filtered majors and adjusts the page
     * numbering accordingly.
     * 
     * @param filteredMajors The list of filtered majors to display.
     */
    private void displayFilteredMajors(ArrayList<MajorMap> filteredMajors) {
        int totalFilteredMajors = filteredMajors.size();
        int totalPages = (int) Math.ceil((double) totalFilteredMajors / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        currentPage = Math.min(currentPage, totalPages - 1);
        currentPage = Math.max(currentPage, 0);

        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalFilteredMajors);

        majorMapListGridPane.getChildren().clear();

        for (int i = start; i < end; i++) {
            MajorMap majorMap = filteredMajors.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MajorMapTemplate.fxml"));
            try {
                AnchorPane majorMapTemplate = loader.load();

                MajorMapTemplateController controller = loader.getController();
                controller.setMajorName(majorMap.getMajor());

                int row = (i - start) / COLUMNS_PER_PAGE;
                int column = (i - start) % COLUMNS_PER_PAGE;
                majorMapListGridPane.add(majorMapTemplate, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pageNumberLabel.setText("Page " + (currentPage + 1) + " / " + totalPages);
    }

    /**
     * Handles the action event for navigating to the dashboard.
     * Depending on the user type, navigates to the appropriate dashboard.
     * 
     * @param event The action event that occurred.
     * @throws IOException If an error occurs while loading the FXML file for the
     *                     dashboard.
     */
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

    /**
     * Calculates the total number of pages based on the number of majors and the
     * rows and columns per page.
     * 
     * @return The total number of pages.
     */
    private int getTotalPages() {
        return (int) Math.ceil((double) majors.size() / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));
    }
}