package envisage;

import java.io.IOException;
import java.util.Stack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the Envisage application.
 * This class extends JavaFX Application and serves as the entry point for the application.
 * It manages navigation between different screens using a stack.
 */
public class App extends Application {

  /**
   * The main scene of the application.
   */
  private static Scene scene;

  /**
   * A stack to keep track of the screens navigated.
   */
  private static Stack<String> screenStack = new Stack<>();

  /**
   * Starts the JavaFX application by loading the initial screen.
   * @param stage The primary stage for this application.
   * @throws IOException If an error occurs while loading the FXML file.
   */
  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("LogIn"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Sets the root node of the scene to the FXML file specified.
   * Pushes the FXML file path onto the screen stack.
   * @param fxml The name of the FXML file to load.
   * @throws IOException If an error occurs while loading the FXML file.
   */
  static void setRoot(String fxml) throws IOException {
    screenStack.push(fxml);
    scene.setRoot(loadFXML(fxml));
    System.out.println("Navigated to: " + fxml);
  }

  /**
   * Navigates back to the previous screen if possible.
   * Pops the current screen from the screen stack and sets the root node to the previous screen.
   * Prints a message indicating the navigation.
   * If there are no screens to navigate back from, prints an appropriate message.
   * @throws IOException If an error occurs while loading the FXML file.
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

  /**
   * Loads the FXML file specified and returns its root node.
   * @param fxml The name of the FXML file to load.
   * @return The root node of the loaded FXML file.
   * @throws IOException If an error occurs while loading the FXML file.
   */
  static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
      App.class.getResource(fxml + ".fxml")
    );
    return fxmlLoader.load();
  }

  /**
   * The main method that launches the JavaFX application.
   * @param args Command-line arguments (unused).
   */
  public static void main(String[] args) {
    launch();
  }
}
