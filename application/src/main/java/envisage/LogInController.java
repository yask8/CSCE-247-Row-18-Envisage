package envisage;

import java.io.IOException;

import AdvisingSoftware.Facade;
import AdvisingSoftware.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogInController {

    @FXML
    private TextField studentEmailTextField;

    @FXML
    private TextField studentPasswordTextField;

    @FXML
    private Button studentLoginButton;

    @FXML
    private Label studentInvalidLabel;

    @FXML
    private TextField adminEmailTextField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private Button adminLoginButton;
    
    @FXML
    private Label adminInvalidLabel;

    @FXML
    private TextField advisorEmailTextField;

    @FXML
    private TextField advisorPasswordTextField;

    @FXML
    private Button advisorLoginButton;

    @FXML
    private Label advisorInvalidLabel;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void loginStudent() throws IOException {
        String email = studentEmailTextField.getText();
        String password = studentPasswordTextField.getText();
    
        Facade facade = new Facade();
        User loggedInUser = facade.login(email, password);
    
        if (loggedInUser != null && loggedInUser.getUserType().equals("STUDENT")) {
            App.setRoot("studentDashboard");
        } else {
            studentInvalidLabel.setText("Invalid login credentials or user is not a student.");
            studentInvalidLabel.setVisible(true);
        }
    }

    @FXML
    private void loginAdmin() throws IOException {
        String email = adminEmailTextField.getText();
        String password = adminPasswordTextField.getText();

        Facade facade = new Facade();
        User loggedInUser = facade.login(email, password);

        if (loggedInUser != null && loggedInUser.getUserType().equals("ADMIN")) {
 
            App.setRoot("adminDashboard");
        } else {
            adminInvalidLabel.setText("Invalid login credentials or user is not a Admin.");
            adminInvalidLabel.setVisible(true);
        }
    }

    @FXML
    private void loginAdvisor() throws IOException {
        String email = advisorEmailTextField.getText();
        String password = advisorPasswordTextField.getText();

        Facade facade = new Facade();
        User loggedInUser = facade.login(email, password);

        if (loggedInUser != null && loggedInUser.getUserType().equals("ADVISOR")) {
            App.setRoot("advisorDashboard");
        } else {
            advisorInvalidLabel.setText("Invalid login credentials or user is not a Admin.");
            advisorInvalidLabel.setVisible(true);
        }
    }
}