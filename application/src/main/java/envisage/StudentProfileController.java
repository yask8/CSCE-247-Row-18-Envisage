package envisage;
/**
 * @author Yasmine Kennedy (yask8)
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import AdvisingSoftware.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {

    @FXML
    private Label advisingStudentProfileLabel;

    @FXML
    private Label appAreaNotTitleLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button declareAppAreaButton;

    @FXML
    private Button declareMajorButton;

    @FXML
    private Label majorNotTitleLabel;

    @FXML
    private Label noteLabel;

    @FXML
    private TreeView<String> studentCompletionTree;

    @FXML
    private Label studentProfileTitleLabel;

    @FXML
    private Rectangle studentSummaryBox;

    @FXML
    private Label studentsSummaryTitleLabel;

    @FXML
    private TextFlow textFlowBox;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Facade facade = Facade.getInstance();
        ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
        ArrayList<String> studentIncompletedCourses = facade.getStudentDegreeProgress().getIncompleteCourses();
        if(studentCompletedCourses != null){
            TreeItem<String> root = new TreeItem<>(facade.getUser().getFirstName() + " Completed and Completed Courses");
            studentCompletionTree.setRoot(root);

            TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
            root.getChildren().add(completedItem);
            for(int i = 1; i <= studentCompletedCourses.size(); i++){
                for(Grades completedCourse : studentCompletedCourses){
                        TreeItem<String> completedCourses = new TreeItem<>(completedCourse.toString());
                        completedItem.getChildren().add(completedCourses);  
                }
            }
        }
    }


}
