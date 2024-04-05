package envisage;

import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AdvisingDashboardController {

    @FXML
    private Button AddOrRemoveAdviseeButton;

    @FXML
    private Text ViewRiskOfFailureButton;

    @FXML
    private Text TextField3;

    @FXML
    private Text TextField4;

    @FXML
    private Button adviseStudentButton;

    @FXML
    private Button advisingProfileButton;

    @FXML
    private Button listOfAdviseesButton;

    @FXML
    private Button studentLookupButton;

    @FXML
    private Button viewMajorMapsButton;

    @FXML
    private Text textField1;

    @FXML
    private Text textField2;

    @FXML
    private Text textField5;

    @FXML
    private Text textField6;

    @FXML
    private Text signOut;

    @FXML
    private Facade facade = Facade.getInstance();

    @FXML
    private void AddOrRemoveAdvisee(ActionEvent event) {
        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        UUID studentIdToAddOrRemove = UUID.randomUUID();

        if (advisees.contains(studentIdToAddOrRemove)) {
            facade.removeStudent(studentIdToAddOrRemove);
            TextField3.setText("Removed student with ID: " + studentIdToAddOrRemove);
        } else {
            facade.addStudent(studentIdToAddOrRemove);
            TextField3.setText("Added student with ID: " + studentIdToAddOrRemove);
        }
        advisees = facade.getListOfAdvisees();
        TextField4.setText("Updated List of Advisees: " + advisees);
    }

    @FXML
    private void ViewRiskOfFailure(ActionEvent event) {
        UUID advisorId = facade.getCurrentUserId();
        ArrayList<UUID> adviseeIds = facade.getListOfAdvisees();
        ArrayList<Student> adviseesAtRisk = new ArrayList<>();
        double minimumGPA = 2.0;

        for (UUID adviseeId : adviseeIds) {
            Student student = facade.getStudentByAdvisor(advisorId, adviseeId);
            if (student != null && student.getGpa() < minimumGPA) {
                adviseesAtRisk.add(student);
            }
        }

        if (!adviseesAtRisk.isEmpty()) {
            String riskText = "Advisees at Risk of Failure:\n";
            for (Student advisee : adviseesAtRisk) {
                riskText += "Name: " + advisee.getFirstName() + " " + advisee.getLastName() + "\n";
                riskText += "GPA: " + advisee.getGpa() + "\n";
                riskText += "---\n";
            }
            ViewRiskOfFailureButton.setText(riskText);
        } else {
            ViewRiskOfFailureButton.setText("No advisees at risk of failure.");
        }
    }

    @FXML
    private void adviseStudent(ActionEvent event) {
        UUID advisorId = facade.getCurrentUserId();
        UUID studentId = null; // null for now
        String noteContent = "Advice for the student.";
        facade.addNoteToStudentAdvisor(advisorId, studentId, noteContent);
        adviseStudentButton.setText("Advised student with ID: " + studentId);
    }

    @FXML
    private void lookupStudent(ActionEvent event) {
        UUID studentId = null; // null for now
        Student student = facade.getStudentByAdvisor(facade.getCurrentUserId(), studentId);
        if (student != null) {
            studentLookupButton.setText("Student Found: " + student.getFirstName() + " " + student.getLastName());
            textField1.setText("Major: " + student.getMajor());
        } else {
            studentLookupButton.setText("Student not found.");
        }
    }

    @FXML
    private void viewAdvisingProfile(ActionEvent event) {
        if (facade.getUser().getUserType().equals("ADVISOR")) {
            Advisor advisor = (Advisor) facade.getUser();
            advisingProfileButton.setText("Advising Profile for: " + advisor.getFirstName() + " " + advisor.getLastName());
            textField2.setText("Advisees: " + advisor.getListOfAdvisees());
        } else {
            advisingProfileButton.setText("Not an advisor.");
        }
    }

    @FXML
    private void viewListOfAdvisees(ActionEvent event) {
        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        listOfAdviseesButton.setText("List of Advisees: " + advisees);
    }

    @FXML
    private void viewMajorMaps(ActionEvent event) {
        String majorName = null; //null for now
        MajorMap majorMap = facade.getMajorMap(majorName);
        if (majorMap != null) {
            viewMajorMapsButton.setText("Major Map for " + majorName + ":\n" + majorMap.toString());
        } else {
            viewMajorMapsButton.setText("Major Map not found for " + majorName);
        }
    }

   @FXML
    void signOut(ActionEvent event) {
        try {
            Facade.getInstance().signOut();
            App.setRoot("LogIn");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
