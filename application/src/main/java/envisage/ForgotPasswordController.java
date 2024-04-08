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
import javafx.scene.layout.AnchorPane;
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
    private Label errorForgotPasswordLabel;

    @FXML
    private Text forgotPasswordMessage;

    @FXML
    private Label incorrectIDLabel;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Button resetPasswordButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        checkNewPassword.setVisible(false);
        enterNewPasswordMessage.setVisible(false);
        errorForgotPasswordLabel.setVisible(false);
        newPassword.setVisible(false);
        incorrectIDLabel.setVisible(false);
    }

    @FXML
    private void checkingSchoolID(){
        Facade facade = Facade.getInstance();
        UUID id = UUID.fromString(enterSchoolIDTextField.getText());
        if(facade.getUserList().getUserbyUSCID(id) == null){
            incorrectIDLabel.setVisible(true);
            incorrectIDLabel.setText("School ID does not exist.");
        } else {
            incorrectIDLabel.setVisible(false);

        }
    }
}
