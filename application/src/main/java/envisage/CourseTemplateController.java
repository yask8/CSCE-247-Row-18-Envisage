package envisage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//Author @Spillmag

public class CourseTemplateController implements Initializable {

    @FXML
    private Button AddCourseButton;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button viewCourseInfoButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCourseName(String courseName) {
        courseNameLabel.setText(courseName);
    }

    
     public void showAddCourseButton(boolean show) {
        AddCourseButton.setVisible(show);
    }
}

