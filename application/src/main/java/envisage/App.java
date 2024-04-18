package envisage;

import java.io.IOException;
import java.util.Stack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  private static Scene scene;
  private static Stack<String> screenStack = new Stack<>();

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("LogIn"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  /**
   *
   * @param fxml
   * @throws IOException
   */
  static void setRoot(String fxml) throws IOException {
    screenStack.push(fxml);
    scene.setRoot(loadFXML(fxml));
    System.out.println("Navigated to: " + fxml);
  }

  /**
   *
   * @throws IOException
   */
  static void goBack() throws IOException {
    if (!screenStack.isEmpty()) {
      String currentScreen = screenStack.pop();
      if (!screenStack.isEmpty()) {
        String previousScreen = screenStack.peek();
        scene.setRoot(loadFXML(previousScreen));
        System.out.println(
          "Navigated back from: " + currentScreen + " to: " + previousScreen
        );
      } else {
        System.out.println("Cannot go back. Already at the initial screen.");
      }
    } else {
      System.out.println("No screens to navigate back from.");
    }
  }

  static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
      App.class.getResource(fxml + ".fxml")
    );
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}
