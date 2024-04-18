package envisage;
/**
 * @author Stephon Johnson
 */

import AdvisingSoftware.*;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCourseController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Text CarolinaCoreLabel;

    @FXML
    private Text CarolinaCoreText;

    @FXML
    private Text CourseCodeLabel;

    @FXML
    private Text CourseCodeText;

    @FXML
    private Text CourseCreditHourText;

    @FXML
    private Text CourseDescriptionLabel;

    @FXML
    private Text CourseDescriptionText;

    @FXML
    private Text CourseIDLabel;

    @FXML
    private Text CourseIDText;

    @FXML
    private Text CourseLabel;

    @FXML
    private Text CourseText;

    @FXML
    private Text CreditHoursLabel;

    @FXML
    private Label EditCourseLabel;

    @FXML
    private Label EditLabel;

    @FXML
    private Text PreReqLabel;

    @FXML
    private Text PreReqTextField;

    @FXML
    private Button SaveButton;

    @FXML
    private Label carolinaCoreLabel;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseCreditHours;

    @FXML
    private Label courseDescriptionLabel;

    @FXML
    private Label courseIDLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label directionsLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private AnchorPane editCourse;

    @FXML
    private TextField newInfoTextField;

    @FXML
    private ChoiceBox<String> pickToEditChoiceBox;

    @FXML
    private Label preRequisiteLabel;

    Facade facade;
    public void initialize (URL location, ResourceBundle arg1) {
        facade = Facade.getInstance();
        ObservableList<String> editOptions = FXCollections.observableArrayList("Course Name", "Course Credit Hours", "Course Description",
                                                                                "Carolina Core","Prerequisites");
        pickToEditChoiceBox.setItems(editOptions);

    }

    @FXML
    void AssignTo(MouseEvent event) {

    }

    @FXML
    void setStageDashboard(ActionEvent event) throws IOException {
        if(facade.getUser() == null){
            return;
        }
        save();
        App.setRoot("adminDashboard");
    }

    public void save() {
        String editCriteria = pickToEditChoiceBox.getValue();
        if(pickToEditChoiceBox == null){
            errorLabel.setText("Please pick an info option to edit.");
            return;
        }
        
        ArrayList<Course> courses = facade.getCourses();
        for(Course course : courses){
                switch(editCriteria){
                    case "Course Name":
                        course.setName(newInfoTextField.getText());
                        break;
                    //ask if they should be able to change everything
                    case "Course Credit Hours":
                        Integer creditHour = Integer.parseInt(newInfoTextField.getText());
                        course.setCreditHours(creditHour);
                        break;
                    case "Course Description":
                        course.setDescription(newInfoTextField.getText());
                        break;
                    case "Carolina Core":
                        //ask about this one
                        break;
                    case "Prerequisites":
                        //ask about this one
                        break;
                    default:
                        break;
            
            }
            
        }
    }
}
