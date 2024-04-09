package envisage;

/**
 * @author Yasmine Kennedy (yask8)
 */

import AdvisingSoftware.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;



public class ForgotPasswordController implements Initializable {

    @FXML
    private PasswordField checkNewPassword;

    @FXML
    private Button enterIDButton;

    @FXML
    private Text enterNewPasswordMessage;

    @FXML
    private TextField enterSchoolIDTextField;

    @FXML
    private Label passwordLengthErrorLabel;

    @FXML
    private Text forgotPasswordMessage;

    @FXML
    private Label incorrectIDLabel;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Button resetPasswordButton;

    @FXML
    private Label checkPasswordsErrorLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        checkNewPassword.setVisible(false);
        newPassword.setVisible(false);
        enterNewPasswordMessage.setVisible(false);
        passwordLengthErrorLabel.setVisible(false);
        checkPasswordsErrorLabel.setVisible(false);
        incorrectIDLabel.setVisible(false);
    }

    @FXML
    private void checkingSchoolID(MouseEvent event){
        Facade facade = Facade.getInstance();
        UUID id = UUID.fromString(enterSchoolIDTextField.getText());
        if(facade.getUserList().getUserbyUSCID(id) != null){
            incorrectIDLabel.setVisible(false);
            enterSchoolIDTextField.setVisible(false);
            forgotPasswordMessage.setVisible(false);
            enterIDButton.setVisible(false);
            enterNewPasswordMessage.setVisible(true);
            newPassword.setVisible(true);
            checkNewPassword.setVisible(true);
            resetPasswordButton.setVisible(true);
        } else {
            incorrectIDLabel.setVisible(true);
            incorrectIDLabel.setText("That School ID does not exist.");
        }
    }

    @FXML
    private void resetForgottenPassword(MouseEvent event){
        Facade facade = Facade.getInstance();
        UUID id = UUID.fromString(enterSchoolIDTextField.getText());
        String password = newPassword.getText();
        if(password.length() >= 8 && password.length() <= 25 ){
            facade.getUserList().getUserbyUSCID(id).editPassword(password);
            String modified_password = facade.getUserList().getUserbyUSCID(id).getPassword();
            if(checkIfConfirmPasswordMatchesPassword() && modified_password.equals(password)){
                passwordLengthErrorLabel.setVisible(true);
                passwordLengthErrorLabel.setText("Password reset succesful! Login with new password.");
            } else {
                passwordLengthErrorLabel.setText("Unsuccessful password reset.");
            }
        } else {
            if (password.length() <= 7) {
                passwordLengthErrorLabel.setVisible(true);
                passwordLengthErrorLabel.setText("Password is too short.");
            }
            if(password.length() >= 26) {
                passwordLengthErrorLabel.setVisible(true);
                passwordLengthErrorLabel.setText("Password is too long.");
            }
        }

    }

    private boolean checkIfConfirmPasswordMatchesPassword() {
        boolean matches = true;
        if (!checkNewPassword.getText().equals(newPassword.getText())) {
            checkPasswordsErrorLabel.setVisible(true);
            checkPasswordsErrorLabel.setText("Passwords do not match.");
            matches = false;
        }
        return matches;
    }

}
