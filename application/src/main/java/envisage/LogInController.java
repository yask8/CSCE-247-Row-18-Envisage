package envisage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController {

    @FXML
    private TextField studentEmailTextField;

    @FXML
    private TextField studentPasswordTextField;

    @FXML
    private Button studentLoginButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void loginStudent() throws IOException {
        String email = studentEmailTextField.getText();
        String password = studentPasswordTextField.getText();

        // Validate the Login for student here for testing assume all credentials are
        // valid
        // Valid and set the stage
        App.setRoot("studentDashboard");
    }

    @FXML
    private TextField adminEmailTextField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private Button adminLoginButton;

    @FXML
    private void loginAdmin() throws IOException {
        String email = adminEmailTextField.getText();
        String password = adminPasswordTextField.getText();

        // Validate the Login for admin here for testing assume all credentials are
        // valid
        // Valid and set the stage
        App.setRoot("adminDashboard");
    }

    @FXML
    private TextField advisorEmailTextField;

    @FXML
    private TextField advisorPasswordTextField;

    @FXML
    private Button advisorLoginButton;

    @FXML
    private void loginAdvisor() throws IOException {
        String email = advisorEmailTextField.getText();
        String password = advisorPasswordTextField.getText();

        // Validate the Login for advisor here for testing assume all credentials are
        // valid
        // Valid and set the stage
        App.setRoot("advisorDashboard");
    }
}