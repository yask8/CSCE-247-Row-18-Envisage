package envisage;

import AdvisingSoftware.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.UUID;

public class AdvisingDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddOrRemoveAdviseeButton;

    @FXML
    private Text TextField3;

    @FXML
    private Text TextField4;

    @FXML
    private Button adviseStudentButton;

    @FXML
    private Button advisingProfileButton;

    @FXML
    private AnchorPane advisorDashboard;

    @FXML
    private Button listOfAdviseesButton;

    @FXML
    private Button studentLookupButton;

    @FXML
    private Text textField1;

    @FXML
    private Text textField2;

    @FXML
    private Text textField5;

    @FXML
    private Text textField6;

    @FXML
    private Button viewMajorMapsButton;

    private Facade facade = Facade.getInstance();

    @FXML
    private void AddOrRemoveAdvisee(MouseEvent event) {
        // Perform action to add or remove advisees
        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        // Use the advisees list for further actions if needed
        System.out.println("List of Advisees: " + advisees);
    }

    @FXML
    private void adviseStudent(MouseEvent event) {
        // Perform action to advise a student
        UUID advisorId = facade.getCurrentUserId();
        UUID studentId = null; // You need to set the student ID
        String noteContent = "Advice for the student."; // Add your advice content
        facade.addNoteToStudentAdvisor(advisorId, studentId, noteContent);
        System.out.println("Advised student with ID: " + studentId);
    }

    @FXML
    private void lookupStudent(MouseEvent event) {
        // Perform action to lookup a student
        UUID studentId = null; // You need to set the student ID to lookup
        Student student = facade.getStudentByAdvisor(facade.getCurrentUserId(), studentId);
        if (student != null) {
            System.out.println("Student Found: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Major: " + student.getMajor());
        } else {
            System.out.println("Student not found.");
        }
    }

    @FXML
    private void viewAdvisingProfile(MouseEvent event) {
        // Perform action to view advising profile
        if (facade.getUser().getUserType().equals("ADVISOR")) {
            Advisor advisor = (Advisor) facade.getUser();
            System.out.println("Advising Profile for: " + advisor.getFirstName() + " " + advisor.getLastName());
            System.out.println("Advisees: " + advisor.getListOfAdvisees());
            System.out.println("Changes Made: " + facade.getAdminChangesMade());
        } else {
            System.out.println("Not an advisor.");
        }
    }

    @FXML
    private void viewListOfAdvisees(MouseEvent event) {
        // Perform action to view list of advisees
        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        System.out.println("List of Advisees: " + advisees);
    }

    @FXML
    private void viewMajorMaps(MouseEvent event) {
        // Perform action to view major maps
        String majorName = "Computer Science"; // You need to set the major name
        MajorMap majorMap = facade.getMajorMap(majorName);
        if (majorMap != null) {
            System.out.println("Major Map for " + majorName + ":");
            System.out.println(majorMap.toString());
        } else {
            System.out.println("Major Map not found for " + majorName);
        }
    }

    @FXML
    void initialize() {
        assert AddOrRemoveAdviseeButton != null : "fx:id=\"AddOrRemoveAdviseeButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert TextField3 != null : "fx:id=\"TextField3\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert TextField4 != null : "fx:id=\"TextField4\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert adviseStudentButton != null : "fx:id=\"adviseStudentButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert advisingProfileButton != null : "fx:id=\"advisingProfileButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert advisorDashboard != null : "fx:id=\"advisorDashboard\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert listOfAdviseesButton != null : "fx:id=\"listOfAdviseesButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert studentLookupButton != null : "fx:id=\"studentLookupButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert textField1 != null : "fx:id=\"textField1\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert textField2 != null : "fx:id=\"textField2\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert textField5 != null : "fx:id=\"textField5\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert textField6 != null : "fx:id=\"textField6\" was not injected: check your FXML file 'advisingDashboard.fxml'.";
        assert viewMajorMapsButton != null : "fx:id=\"viewMajorMapsButton\" was not injected: check your FXML file 'advisingDashboard.fxml'.";

    }

}