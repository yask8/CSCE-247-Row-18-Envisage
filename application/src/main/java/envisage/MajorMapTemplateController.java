package envisage;

import java.io.IOException;
<<<<<<< HEAD
import java.util.UUID;

import AdvisingSoftware.*;
=======

>>>>>>> 6406cb7d430e1944b5f65db25a7b177d713d7236
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
<<<<<<< HEAD
    void setMajorMap(ActionEvent event) {
=======
    void setStageMajorMap(ActionEvent event) {
>>>>>>> 6406cb7d430e1944b5f65db25a7b177d713d7236
        String majorName = majorMapLabel.getText().trim();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MajorMap.fxml"));
            Parent root = loader.load();
<<<<<<< HEAD

            MajorMapController controller = loader.getController();
            controller.setMajorName(majorName);

=======
    
            MajorMapController controller = loader.getController();
        
            if (controller != null) {
                controller.setMajorName(majorName);
            } else {
                System.out.println("MajorMapController is null");
            }
    
>>>>>>> 6406cb7d430e1944b5f65db25a7b177d713d7236
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
<<<<<<< HEAD
            System.err.println("Error loading MajorMap.fxml: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error setting major name: " + e.getMessage());
=======
            e.printStackTrace();
>>>>>>> 6406cb7d430e1944b5f65db25a7b177d713d7236
        }
    }

    public void setMajorName(String majorName) {
        majorMapLabel.setText(majorName);
    }

}