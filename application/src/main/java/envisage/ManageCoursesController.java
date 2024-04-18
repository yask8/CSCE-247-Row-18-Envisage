package envisage;

import javafx.collections.FXCollections;
/**
 * @author Yasmine Kennedy (yask8) and Garrett Spillman (Spillmag)
 */
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdvisingSoftware.*;

public class ManageCoursesController implements Initializable {

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
    private AnchorPane manageCourses;

    @FXML
    private GridPane manageCoursesGridPane;

    private Facade facade;
    private User user;
    private ArrayList<Course> filteredCourses;
    private final int ROWS_PER_PAGE = 3;
    private final int COLUMNS_PER_PAGE = 3;
    private int currentPage = 0; 

    @Override
    public void initialize(URL url, ResourceBundle arg1 ){
        facade = Facade.getInstance();
        user = facade.getUser();
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Code", "Name", "ID");
        filterByChoiceBox.setItems(roleOptions);
        populateCourseList();
    }

    private void populateCourseList(){
        ArrayList<Course> courses = facade.getCourses();
        int totalCourses = courses.size();
        int totalPages = (int) Math.ceil((double)totalCourses / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        currentPage = Math.min(currentPage,totalPages-1);
        currentPage = Math.max(currentPage,0);

        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalCourses);

        manageCoursesGridPane.getChildren().clear();

        for(int i = start; i < end; i++){
            Course course = courses.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseTemplate.fxml"));
            try{
                AnchorPane courseTemplate = loader.load();

                CourseTemplateController controller = loader.getController();
                controller.setCourseName(course.getName());

                boolean isAdmin = user.getUserType().equals("ADMIN");
                if(isAdmin) {
                    controller.showEditCourseButton();
                }

                int row = (i-start) / COLUMNS_PER_PAGE;
                int column = (i-start) % COLUMNS_PER_PAGE;
                manageCoursesGridPane.add(courseTemplate, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pageNumberLabel.setText("Page " + (currentPage + 1) + " / " + totalPages);
    }
    
    @FXML
    void clear(ActionEvent event) {
        searchBarTextField.clear();
        filterByChoiceBox.getSelectionModel().clearSelection();
        filteredCourses = null;
        currentPage = 0;
        populateCourseList();
    }

    @FXML
    void nextPage(ActionEvent event) {
        ArrayList<Course> courses;
        if(filteredCourses != null) {
            courses = filteredCourses;
        } else {
            courses = facade.getCourses();
        }
        int totalCourses = courses.size();
        int totalPages = (int)Math.ceil((double) totalCourses / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));

        if(currentPage < totalPages - 1){
            currentPage++;
            manageCoursesGridPane.getChildren().clear();
            populateCourseList();
        }
    }

    @FXML
    void previousPage(ActionEvent event) {
        if(currentPage > 0){
            currentPage--;
            manageCoursesGridPane.getChildren().clear();
            populateCourseList();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String searchText = searchBarTextField.getText().toLowerCase();
        String filterCriteria = filterByChoiceBox.getValue();
        if(searchText.isEmpty() || filterCriteria == null){
            searchErrorLabel.setText("Please enter search text and select a filter.");
            return;
        }

        ArrayList<Course> courses = facade.getCourses();
        filteredCourses = new ArrayList<>();

        for(Course course : courses){
            switch (filterCriteria) {
                case "Code":
                    if(course.getCode().toLowerCase().contains(searchText)){
                        filteredCourses.add(course);
                    }
                    break;
                case "Name":
                    if(course.getName().toLowerCase().contains(searchText)){
                        filteredCourses.add(course);
                    }
                    break;
                case "ID":
                    if(course.getID().toLowerCase().contains(searchText)){
                        filteredCourses.add(course);
                    }
                    break;
                default:
                    break;
            }
        }

        if(filteredCourses.isEmpty()){
            searchErrorLabel.setText("No matching courses found.");
        } else {
            searchErrorLabel.setText("");
            displayFilteredCourses(filteredCourses);

            currentPage = 0;

            int totalCourses = filteredCourses.size();
            int totalPages = (int) Math.ceil((double) totalCourses / (ROWS_PER_PAGE * COLUMNS_PER_PAGE));
            pageNumberLabel.setText("Page "+ (currentPage + 1) + " / " + totalPages);
        }
    }

    private void displayFilteredCourses(ArrayList<Course> filteredCourses){
        manageCoursesGridPane.getChildren().clear();

        int totalCourses = filteredCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / (ROWS_PER_PAGE *COLUMNS_PER_PAGE));
        currentPage = Math.min(currentPage, totalPages -1);
        currentPage = Math.max(currentPage, 0);

        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, totalCourses);

        for(int i = start; i < end; i++){
            Course course = filteredCourses.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseTemplate.fxml"));
            try {
                AnchorPane courseTemplate = loader.load();

                CourseTemplateController controller = loader.getController();
                controller.setCourseName(course.getName());

                boolean isAdmin = user.getUserType().equals("ADMIN");
                if(isAdmin){
                    controller.showEditCourseButton();
                }
                int row = (i - start) / COLUMNS_PER_PAGE;
                int column = (i-start) % COLUMNS_PER_PAGE;
                manageCoursesGridPane.add(courseTemplate, column, row);
            } catch(IOException e){
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

}