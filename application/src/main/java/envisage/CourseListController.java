package envisage;

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

public class CourseListController implements Initializable {

    private Facade facade;
    private User user;
    private final int ROWS_PER_PAGE = 3;
    private final int COLUMNS_PER_PAGE = 3;
    private int currentPage = 0;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane courseList;

    @FXML
    private GridPane courseListGridPane;

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
    private ChoiceBox<String> filterByChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        user = facade.getUser();
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Code", "Name", "ID");
        filterByChoiceBox.setItems(roleOptions);
        populateCourseList();
    }

    private void populateCourseList() {
        ArrayList<Course> courses = facade.getCourses();
    
        int start = currentPage * ROWS_PER_PAGE * COLUMNS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE * COLUMNS_PER_PAGE, courses.size());
    
        for (int i = start; i < end; i++) {
            Course course = courses.get(i);
    
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseTemplate.fxml"));
    
            try {
                AnchorPane courseTemplate = loader.load();
    
                CourseTemplateController controller = loader.getController();
                controller.setCourseName(course.getName());
    
             
                boolean isStudent = user.getUserType().equals("STUDENT");

                
                controller.showAddCourseButton(isStudent);
    
                int row = (i - start) / COLUMNS_PER_PAGE;
                int column = (i - start) % COLUMNS_PER_PAGE;
                courseListGridPane.add(courseTemplate, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        pageNumberLabel.setText("Page " + (currentPage + 1));
    }
    @FXML
    void nextPage(ActionEvent event) {
        currentPage++;
        courseListGridPane.getChildren().clear();
        populateCourseList();
    }
    
    @FXML
    void previousPage(ActionEvent event) {
        if (currentPage > 0) {
            currentPage--;
            courseListGridPane.getChildren().clear();
            populateCourseList();
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
}