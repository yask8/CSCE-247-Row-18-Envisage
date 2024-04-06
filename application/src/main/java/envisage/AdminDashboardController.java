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
import javafx.scene.layout.AnchorPane;

public class AdminDashboardController implements Initializable {

    private Facade facade;
    private User user;


    @FXML
    private Label welcomeLabel;

    @FXML
    private AnchorPane adminDashboard;

    @FXML
    private Button adminProfileButton;

    @FXML
    private Button manageAppAreasButton;

    @FXML
    private Button manageCoursesButton;

    @FXML
    private Button manageMajorMapsButton;

    @FXML
    private Button manageUserButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button studentLookupButton;

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