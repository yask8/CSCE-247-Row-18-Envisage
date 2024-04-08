package envisage;

import java.io.IOException;

import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInController {

    @FXML
    private AnchorPane LogIn;

    @FXML
    private Label aboutLabel;

    @FXML
    private TextField adminEmailTextField;

    @FXML
    private Label adminInvalidLabel;

    @FXML
    private Button adminLoginButton;

    @FXML
    private PasswordField adminPasswordTextField;

    @FXML
    private TextField advisorEmailTextField;

    @FXML
    private Label advisorInvalidLabel;

    @FXML
    private Button advisorLoginButton;

    @FXML
    private PasswordField advisorPasswordTextField;

    @FXML
    private Text signUpLabel;

    @FXML
    private Text forgotPasswordLabel;

    @FXML
    private TextField studentEmailTextField;

    @FXML
    private Label studentInvalidLabel;

    @FXML
    private Button studentLoginButton;

    @FXML
    private PasswordField studentPasswordTextField;

    @FXML
    void setStageAbout(MouseEvent event) throws IOException {
        App.setRoot("About");
    }

    @FXML
    private void loginStudent(ActionEvent event) throws IOException {
        String email = studentEmailTextField.getText();
        String password = studentPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        User loggedInUser = facade.login(email, password);

        if (loggedInUser != null && loggedInUser.getUserType().equals("STUDENT")) {
            App.setRoot("studentDashboard");
        } else {
            studentInvalidLabel.setText("Invalid login credentials or user is not a student.");
            studentInvalidLabel.setVisible(true);
        }
    }

    @FXML
    private void loginAdmin(ActionEvent event) throws IOException {
        String email = adminEmailTextField.getText();
        String password = adminPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        User loggedInUser = facade.login(email, password);

        if (loggedInUser != null && loggedInUser.getUserType().equals("ADMIN")) {
            App.setRoot("adminDashboard");
        } else {
            adminInvalidLabel.setText("Invalid login credentials or user is not an admin.");
            adminInvalidLabel.setVisible(true);
        }
    }

    @FXML
    private void loginAdvisor(ActionEvent event) throws IOException {
        String email = advisorEmailTextField.getText();
        String password = advisorPasswordTextField.getText();

        Facade facade = Facade.getInstance();
        User loggedInUser = facade.login(email, password);

        if (loggedInUser != null && loggedInUser.getUserType().equals("ADVISOR")) {
            App.setRoot("advisorDashboard");
        } else {
            advisorInvalidLabel.setText("Invalid login credentials or user is not an advisor.");
            advisorInvalidLabel.setVisible(true);
        }
    }

    @FXML
    void setStageSignIn(MouseEvent event) throws IOException {
        App.setRoot("signUp");
    }

    @FXML
    void setStageForgotPassword(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotPassword.fxml"));
        AnchorPane root = loader.load();
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Forgot Password");
        stage.setScene(new Scene(root));
        stage.showAndWait(); 
        

    }

}