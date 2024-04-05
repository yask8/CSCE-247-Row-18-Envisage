
package envisage;

/**
 * @author Yasmine Kennedy (yask8)
 */
import java.io.IOException;

import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;


public class SignUpController {

    @FXML
    private Button aboutLabel;

    @FXML
    private TextField adminConfirmPasswordTextField;

    @FXML
    private TextField adminEmailTextField;

    @FXML
    private TextField adminFirstNameTextField;

    @FXML
    private TextField adminLastNameTextField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private Button adminSignUpButton;

    @FXML
    private Tab adminSignUpTab;

    @FXML
    private Label adminInvalidLabel;

    @FXML
    private PasswordField adminConfirmPasswordHidden;

    @FXML 
    private PasswordField adminPasswordHidden;

    @FXML
    private CheckBox adminShowConfirmedPasswordCheckBox;

    @FXML
    private CheckBox adminShowPasswordCheckBox;

    @FXML
    private TextField advisorConfirmPasswordTextField;

    @FXML
    private TextField advisorEmailTextField;

    @FXML
    private TextField advisorFirstNameTextField;

    @FXML
    private TextField advisorLastNameTextField;

    @FXML
    private TextField advisorPasswordTextField;

    @FXML
    private Button advisorSignUpButton;

    @FXML
    private Tab advisorSignUpTab;

    @FXML
    private Label advisorInvalidLabel;

    @FXML
    private PasswordField advisorConfirmPasswordHidden;

    @FXML 
    private PasswordField advisorPasswordHidden;

    @FXML
    private CheckBox advisorShowConfirmedPasswordCheckBox;

    @FXML
    private CheckBox advisorShowPasswordCheckBox;

    @FXML
    private Button faqLabel;

    @FXML
    private Button signUpLabel;

    @FXML
    private TextField studentConfirmPasswordTextField;

    @FXML
    private TextField studentEmailTextField;

    @FXML
    private TextField studentFirstNameTextField;

    @FXML
    private TextField studentLastNameTextField;

    @FXML
    private TextField studentPasswordTextField;

    @FXML
    private Button studentSignUpButton;

    @FXML
    private Tab studentSignUpTab;

    @FXML
    private Label studentInvalidLabel;

    @FXML
    private PasswordField studentConfirmPasswordHidden;

    @FXML 
    private PasswordField studentPasswordHidden;

    @FXML
    private CheckBox studentShowConfirmedPasswordCheckBox;

    @FXML
    private CheckBox studentShowPasswordCheckBox;

    @FXML
    private Button universityPartnersLabel;

    @FXML
    private void signingUpStudent(){
        String firstName = studentFirstNameTextField.getText();
        String lastName = studentLastNameTextField.getText();
        String email = studentEmailTextField.getText();
        String password = studentPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        if(checkIfConfirmPasswordMatchesPassword() != false) {
            facade.signUpStudent(firstName, lastName, email, password);
        }

    }

    @FXML
    private void signingUpAdvisor(){
        String firstName = advisorFirstNameTextField.getText();
        String lastName = advisorLastNameTextField.getText();
        String email = advisorEmailTextField.getText();
        String password = advisorPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        if(checkIfConfirmPasswordMatchesPassword() != false) {
            facade.signUpAdvisor(firstName, lastName, email, password);
        }
    }

    @FXML
    private void signingUpAdmin(){
        String firstName = adminFirstNameTextField.getText();
        String lastName = adminLastNameTextField.getText();
        String email = adminEmailTextField.getText();
        String password = adminPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        if(checkIfConfirmPasswordMatchesPassword() != false) {
            facade.signUpAdmin(firstName, lastName, email, password);
        }
    }

    @FXML
    private void showStudentHiddenPassword(ActionEvent event){
        if(studentShowPasswordCheckBox.isSelected()){
            studentPasswordTextField.setText(studentPasswordHidden.getText());
        }
    }

    @FXML
    private void showAdvisorHiddenPassword(ActionEvent event){

    }

    @FXML
    private void showAdminHiddenPassword(ActionEvent event){

    }

    @FXML
    private boolean checkIfConfirmPasswordMatchesPassword(){
        boolean matches = true;
        if(!studentConfirmPasswordTextField.getText().equals(studentPasswordTextField.getText())){
            matches = false;
            studentInvalidLabel.setText("Passwords do not match.");
            studentInvalidLabel.setVisible(true);
            
        }
        if(!advisorConfirmPasswordTextField.getText().equals(advisorPasswordTextField.getText())){
            matches = false;
            advisorInvalidLabel.setText("Passwords do not match.");
            advisorInvalidLabel.setVisible(true);
        }
        if(!adminConfirmPasswordTextField.getText().equals(adminPasswordTextField.getText())){
            matches = false;
            adminInvalidLabel.setText("Passwords do not match.");
            adminInvalidLabel.setVisible(true);
        }
        return matches;
    }

}