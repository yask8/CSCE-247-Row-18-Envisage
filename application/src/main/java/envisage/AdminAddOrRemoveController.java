package envisage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import AdvisingSoftware.*;
import java.io.IOException;
import java.util.ArrayList;


public class AdminAddOrRemoveController implements Initializable{
    private Facade facade;
    private User user;
    private ArrayList<Student> filteredUsList;
    private final int ROWS_PER_PAGE = 3;
    private final int COLUMNS_PER_PAGE = 3;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AddOrRemoveUser;

    @FXML
    private ChoiceBox<?> IAmFilterChoiceBox;

    @FXML
    private GridPane addOrRemoveGridPane;

    @FXML
    private Label addOrremoveLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button clearSearchButton;

    @FXML
    private ChoiceBox<?> filterByChoiceBox;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private TextField searchBarTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Label searchErrorLabel;

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void nextPage(ActionEvent event) {

    }

    @FXML
    void previousPage(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void setStageDashboard(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}
