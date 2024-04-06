package envisage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import AdvisingSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = Facade.getInstance();
        user = facade.getUser();
        welcomeLabel.setText("Welcome " + user.getFirstName());
    
        ArrayList<UUID> advisees = facade.getListOfAdvisees();
        ArrayList<UUID> failingStudents = facade.getListOfFailingStudents();
    
        int adviseesCount = advisees != null ? advisees.size() : 0;
        int riskOfFailAdviseesCount = failingStudents != null ? failingStudents.size() : 0;
    
        totalNumberOfAdivseesLabel.setText(adviseesCount > 0 ? String.valueOf(adviseesCount) : "No advisees yet");
        riskOfFailureLabel.setText(riskOfFailAdviseesCount > 0 ? String.valueOf(riskOfFailAdviseesCount) : "No advisees at risk of failing");
    
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