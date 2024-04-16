package envisage;

import java.io.IOException;
import java.util.UUID;

import AdvisingSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MajorMapTemplateController {

    Facade facade = Facade.getInstance();

    @FXML
    private Label majorMapLabel;

    @FXML
    private AnchorPane majrMapTemplatePane;

    @FXML
    private Button viewMajorMapButton;

    @FXML
    void setMajorMap(ActionEvent event) {
        String majorName = majorMapLabel.getText().trim();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MajorMap.fxml"));
            Parent root = loader.load();

            MajorMapController controller = loader.getController();
            controller.setMajorName(majorName);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading MajorMap.fxml: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error setting major name: " + e.getMessage());
        }
    }

    public void setMajorName(String majorName) {
        majorMapLabel.setText(majorName);
    }

}