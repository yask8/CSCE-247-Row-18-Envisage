
package envisage;

/**
 * @author Yasmine Kennedy (yask8)
 */
import java.io.IOException;

import AdvisingSoftware.*;
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
    private ChoiceBox<String> roleTypeChoiceBox;

    @FXML
    private Button signUpButton;

    @FXML
    private AnchorPane singUp;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Student", "Advisor", "Admin");
        roleTypeChoiceBox.setItems(roleOptions);
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

    private boolean checkIfConfirmPasswordMatchesPassword() {
        boolean matches = true;
        if (!passwordCheckTextField.getText().equals(passwordTextField.getText())) {
            matches = false;
            // Set error message
        }
        return matches;
    }
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }
    @FXML
    void setStageAbout(MouseEvent event) throws IOException {
        App.setRoot("About");
    }
}