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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateMajorMapList();
    }

    private void populateMajorMapList() {

        facade = facade.getInstance();
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

    @FXML
    void nextPage(ActionEvent event) {
        if (currentPage < getTotalPages() - 1) {
            currentPage++;
            populateMajorMapList();
        }
    }

    @FXML
    void previousPage(ActionEvent event) {
        if (currentPage > 0) {
            currentPage--;
            populateMajorMapList();
        }
    }
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

    @FXML
    void clear(ActionEvent event) {
        searchBarTextField.clear();
        populateMajorMapList();
    }

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

    private int getTotalPages() {
        return (int) Math.ceil((double) majors.size() / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));
    }
}