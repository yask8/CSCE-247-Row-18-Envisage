package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdvisorDashboardController implements Initializable {

    private Facade facade;
    private User user;

    @FXML
    private Button adviseStudentButton;

    @FXML
    private Button advisorProfileButton;

    @FXML
    private Button editListOfAdviseesButton;

    @FXML
    private Button listOfAdviseesButton;

    @FXML
    private Button studentLookupButton;

    @FXML
    private Button viewMajorMapsButton;

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        user = facade.getUser();
        welcomeLabel.setText("Welcome " + user.getFirstName());
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