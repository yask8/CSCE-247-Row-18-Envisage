package envisage;

/**
 * Controller for the User Sign Up Screen
 * Handles action and logic for signing up a user
 * @author Row 18
 */
import java.io.IOException;

import AdvisingSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    // FXML injected variables
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

    @FXML
    private Label universityPartnersLabel;

    @FXML
    private Label faqLabel;

    /**
     * Initializes the controller after its root element has been completely
     * processed and sets the role options
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object,
     *                  or null if the location is not known.
     * @param resources The resources used to localize the root object, or null
     *                  if the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Student", "Advisor", "Admin");
        roleTypeChoiceBox.setItems(roleOptions);
        passwordTextField.setVisible(false);
    }

    /***
     * Signs up the user and then switches to that specific dashboard
     */
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
            switchToDashboard(roleType);
        }
    }

    /**
     * Helper method to switch to dashboard
     * 
     * @param roleType what the user type is
     */
    private void switchToDashboard(String roleType) {
        try {
            switch (roleType) {
                case "Student":
                    App.setRoot("studentDashboard");
                    break;
                case "Advisor":
                    App.setRoot("advisorDashboard");
                    break;
                case "Admin":
                    App.setRoot("adminDashboard");
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the hidden password
     * 
     * @param event representing action event
     */
    @FXML
    private void showHiddenPassword(ActionEvent event) {
        if (passwordCheckBox.isSelected()) {
            passwordTextField.setText(hiddenPassword.getText());
            passwordTextField.setVisible(true);
            hiddenPassword.setVisible(false);
            return;
        } else {
            hiddenPassword.setText(passwordTextField.getText());
            hiddenPassword.setVisible(true);
            passwordTextField.setVisible(false);
        }
    }

    /**
     * Shows the hidden confirm password field
     * 
     * @param event representing an action
     */
    @FXML
    private void showHiddenCheckPassword(ActionEvent event) {
        if (checkPasswordCheckBox.isSelected()) {
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

    /**
     * Helper method to check if the password and confirm password matches
     * 
     * @return if the passwords do or do not match
     */
    private boolean checkIfConfirmPasswordMatchesPassword() {
        boolean matches = true;
        if (!hiddenCheckPassword.getText().equals(hiddenPassword.getText())
                || passwordCheckTextField.getText().equals(passwordTextField.getText())) {
            errorMessageLabel.setText("Passwords do not match.");
            matches = false;
        }
        return matches;
    }

    /**
     * Sets the stage to the Login Screen
     * 
     * @param event representing a mouse event
     * @throws IOException if an I/O error occurs when setting the scene
     */
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    /**
     * Sets the stage to the About Screen
     * 
     * @param event representing a mouse event
     * @throws IOException if an I/O error occurs when setting the scene
     */
    @FXML
    void setStageAbout(MouseEvent event) throws IOException {
        App.setRoot("About");
    }

    /**
     * Sets the stage to the University Partners Screen
     * 
     * @param event representing a mouse event
     * @throws IOException if an I/O error occurs when setting the scene
     */
    @FXML
    void setStageUniversityPartners(MouseEvent event) throws IOException {
        App.setRoot("universityPartners");
    }

    /**
     * Sets the stage to the FAQ Screen
     * 
     * @param event representing a mouse event
     * @throws IOException if an I/O error occurs when setting the scene
     */
    @FXML
    void setStageFAQ(MouseEvent event) throws IOException {
        App.setRoot("faqPage");
    }
}