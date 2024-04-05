
package envisage;

/**
 * @author Yasmine Kennedy (yask8)
 */
import java.io.IOException;

import AdvisingSoftware.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Label aboutLabel;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField passwordCheckTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private PasswordField hiddenCheckPassword;

    @FXML
    private PasswordField hiddenPassword;

    @FXML
    private CheckBox passwordCheckBox;

    @FXML
    private CheckBox checkPasswordCheckBox;

    @FXML
    private ChoiceBox<String> roleTypeChoiceBox;

    @FXML
    private Button signUpButton;

    @FXML
    private AnchorPane signUp;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Student", "Advisor", "Admin");
        roleTypeChoiceBox.setItems(roleOptions);
        passwordTextField.setVisible(false);
    }

    @FXML
    private void signUpUser() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String roleType = roleTypeChoiceBox.getValue().toString();

        Facade facade = Facade.getInstance();
        if (checkIfConfirmPasswordMatchesPassword()) {
            switch (roleType) {
                case "Student":
                    facade.signUpStudent(firstName, lastName, email, password);
                    break;
                case "Advisor":
                    facade.signUpAdvisor(firstName, lastName, email, password);
                    break;
                case "Admin":
                    facade.signUpAdmin(firstName, lastName, email, password);
                    break;
                default:
                    break;
            }
        }
    }

    // Extra Screen Features Section
    @FXML
    private void showHiddenPassword(ActionEvent event){
        // Password TextField
        if(passwordCheckBox.isSelected()){
            passwordTextField.setText(hiddenPassword.getText());
            passwordTextField.setVisible(true);
            hiddenPassword.setVisible(false);
            return;
        } else {
            hiddenPassword.setText(passwordTextField.getText());
            hiddenPassword.setVisible(true);
            passwordTextField.setVisible(false);
        }

        // Confirm Password TextField
        if(checkPasswordCheckBox.isSelected()){
            passwordCheckTextField.setText(hiddenCheckPassword.getText());
            passwordCheckTextField.setVisible(true);
            hiddenCheckPassword.setVisible(false);
            return;
        } else {
            hiddenCheckPassword.setText(passwordCheckTextField.getText());
            hiddenCheckPassword.setVisible(true);
            passwordCheckTextField.setVisible(false);
        }
    }

    // Helper Methods Section
    private boolean checkIfConfirmPasswordMatchesPassword() {
        boolean matches = true;
        if (!passwordCheckTextField.getText().equals(passwordTextField.getText())) {
            errorMessageLabel.setText("Passwords do not match.");
            matches = false;
        }
        return matches;
    }

    // Transition Section --> Switching Screens
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }
    @FXML
    void setStageAbout(MouseEvent event) throws IOException {
        App.setRoot("About");
    }
    
}