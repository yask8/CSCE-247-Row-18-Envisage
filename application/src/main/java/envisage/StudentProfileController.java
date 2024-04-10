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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import AdvisingSoftware.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {

    @FXML
    private Label advisingStudentProfileLabel;

    @FXML
    private Label lastAdvisingAppointmentDateTitleLabel;

    @FXML
    private Label lastAdvisingApptDateLabel;

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
    private Label mostRecentNoteTitleLabel;

    @FXML
    private Label dateTitleLabel;

    @FXML
    private Label dateLabel;

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
    
    Facade facade = Facade.getInstance();
    User user = facade.getUser();
    String userFirstName = facade.getUser().getFirstName();
    String userLastName = facade.getUser().getLastName();

    @Override
    public void initialize(URL url, ResourceBundle arg1){
        ArrayList<Grades> studentCompletedCourses = facade.getStudentCompletedCourses();
        ArrayList<String> studentIncompletedCourses = facade.getStudentDegreeProgress().getIncompleteCourses();
        if(studentCompletedCourses != null && studentIncompletedCourses != null){
            TreeItem<String> root = new TreeItem<>(userFirstName + "'s Completed and Incompleted Courses");
            studentCompletionTree.setRoot(root);
            TreeItem<String> completedItem = new TreeItem<>("Completed Courses");
            root.getChildren().add(completedItem);
            for(Grades completedCourse : studentCompletedCourses){
                TreeItem<String> completedCourses = new TreeItem<>(completedCourse.toString());
                completedItem.getChildren().add(completedCourses);  
            }
            TreeItem<String> incompletedItem = new TreeItem<>("Incompleted Courses");
            root.getChildren().add(incompletedItem);
            for(String incompletedCourse : studentIncompletedCourses){
                TreeItem<String> incompletedCourses = new TreeItem<>(incompletedCourse);
                incompletedItem.getChildren().add(incompletedCourses);
            }
        } else {
            if(studentCompletedCourses == null){
                TreeItem<String> root = new TreeItem<>("Completed Courses Error");
                studentCompletionTree.setRoot(root);
                TreeItem<String> errorItem = new TreeItem<>("Unable to retrieve completed courses.");
                root.getChildren().add(errorItem);
            }
            if(studentIncompletedCourses == null){
                TreeItem<String> root = new TreeItem<>("Incompleted Courses Error");
                studentCompletionTree.setRoot(root);
                TreeItem<String> errorItem = new TreeItem<>("Unable to retrieve incompleted courses.");
                root.getChildren().add(errorItem);
            }
        }
        advisingStudentProfileLabel.setVisible(false);
        studentProfileTitleLabel.setText(userFirstName + " " + userLastName+"'s Student Profile");
        studentsSummaryTitleLabel.setText("Student Summary:");
        if(facade.getStudentAppArea() != null){
            appAreaNotTitleLabel.setText(facade.getStudentAppArea());
        } else {
            appAreaNotTitleLabel.setText("Undecided");
        }
        if(facade.getStudentMajor() != null){
            majorNotTitleLabel.setText(facade.getStudentMajor());
        } else {
            majorNotTitleLabel.setText("Undeclared");
        }

        ArrayList<Note> advisorNotes = facade.getStudentAdvisorNotes();
        if(!advisorNotes.isEmpty()){
            Note mostRecentNote = advisorNotes.get(advisorNotes.size() - 1);
            String noteText = mostRecentNote.getNote();
            noteLabel.setText(noteText);
            lastAdvisingApptDateLabel.setText(mostRecentNote.getDate().toString());
        } else {
            noteLabel.setText("No advisor notes available.");
            lastAdvisingApptDateLabel.setText("None Found.");
        }
    }

    @FXML
    void setStageStudentDashboard(MouseEvent event) throws IOException{
        App.setRoot("studentDashboard");
    }


}
