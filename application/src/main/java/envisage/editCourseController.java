package envisage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class editCourseController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> AssignToChoiceBox;

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
    private AnchorPane EditCoursePane;

    @FXML
    private Label EditLabel;

    @FXML
    private Text PreReqLabel;

    @FXML
    private Text PreReqTextField;

    @FXML
    private Button SaveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initializeCourseInformation(String courseID, String courseCode, String courseName,
        String courseDescription, int creditHours, String carolinaCore, String preRequisites) {
        CourseIDText.setText(courseID);
        CourseCodeText.setText(courseCode);
        CourseText.setText(courseName);
        CourseDescriptionText.setText(courseDescription);
        CourseCreditHourText.setText(Integer.toString(creditHours));
        CarolinaCoreText.setText(carolinaCore);
        PreReqTextField.setText(preRequisites);
    }
}
