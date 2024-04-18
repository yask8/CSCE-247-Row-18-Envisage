package envisage;

import AdvisingSoftware.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class controls the advisor dashboard in the Envisage Advising Software.
 * It provides functionality for displaying advisor-specific information and navigating to other functionalities.
 */
public class AdvisorDashboardController implements Initializable {

    private Facade facade;
    private User user;

    @FXML
    private Button adviseStudentButton;

    @FXML
    private Button advisorProfileButton;

    @FXML
    private Button editListOfAdviseesButton;

    @FXML
    private Button listOfAdviseesButton;

    @FXML
    private PieChart progressPieChart;

    @FXML
    private Label riskOfFailureLabel;

    @FXML
    private Button studentLookupButton;

    @FXML
    private Label totalNumberOfAdivseesLabel;

    @FXML
    private Button viewMajorMapsButton;

    @FXML
    private Label welcomeLabel;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up the advisor dashboard with user-specific information.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        user = facade.getUser();
        welcomeLabel.setText("Welcome " + user.getFirstName());

        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        ArrayList<UUID> failingStudents = facade.getListOfFailingStudents();

        int adviseesCount = advisees != null ? advisees.size() : 0;
        int riskOfFailAdviseesCount = failingStudents != null
                ? failingStudents.size()
                : 0;

        totalNumberOfAdivseesLabel.setText(
                adviseesCount > 0 ? String.valueOf(adviseesCount) : "No advisees yet"
        );
        riskOfFailureLabel.setText(
                riskOfFailAdviseesCount > 0
                        ? String.valueOf(riskOfFailAdviseesCount)
                        : "No advisees at risk of failing"
        );

        if (adviseesCount > 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Low Risk", adviseesCount - riskOfFailAdviseesCount),
                    new PieChart.Data("Risk of Fail", riskOfFailAdviseesCount)
            );
            progressPieChart.setData(pieChartData);
        } else {
            progressPieChart.setVisible(false);
        }
    }

    /**
     * Redirects the user to the student lookup functionality.
     *
     * @param event The ActionEvent triggered by clicking the student lookup button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void setStageStudentLookup(ActionEvent event) throws IOException {
        App.setRoot("studentLookup");
    }

    /**
     * Redirects the user to the advisee management functionality.
     *
     * @param event The ActionEvent triggered by clicking the advisee management button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void setStageAdviseeManage(ActionEvent event) throws IOException {
        App.setRoot("adviseeManage");
    }

    /**
     * Redirects the user to the advisor profile management functionality.
     *
     * @param event The ActionEvent triggered by clicking the advisor profile button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void setStageAdvisorProfile(ActionEvent event) throws IOException {
        App.setRoot("advisorProfile");
    }

    /**
     * Redirects the user to the major list view functionality.
     *
     * @param event The ActionEvent triggered by clicking the major list button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void setStageMajorList(ActionEvent event) throws IOException {
        App.setRoot("majorList");
    }

    /**
     * Signs out the user and redirects to the login screen.
     *
     * @param event The ActionEvent triggered by clicking the sign out button.
     */
    @FXML
    void signOut(ActionEvent event) {
        try {
            Facade.getInstance().signOut();
            App.setRoot("LogIn");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
