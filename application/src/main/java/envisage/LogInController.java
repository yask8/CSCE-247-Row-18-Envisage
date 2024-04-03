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
        
        // Validate the Login in here for testing assume all credentials are valid
        // Valid and set the stage
        App.setRoot("studentDashboard");
    }
}