package envisage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    @FXML
    void AddOrRemoveAdvisee(MouseEvent event) {

    }

    @FXML
    void adviseStudent(MouseEvent event) {

    }

    @FXML
    void lookupStudent(MouseEvent event) {

    }

    @FXML
    void viewAdvisingProfile(MouseEvent event) {

    }

    @FXML
    void viewListOfAdvisees(MouseEvent event) {

    }

    @FXML
    void viewMajorMaps(MouseEvent event) {

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