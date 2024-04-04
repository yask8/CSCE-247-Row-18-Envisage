package AdvisingSoftware;

/**
 * Any User can interact with the Advising Application
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)

 */
public class Driver {

  private Facade facade;

  Driver() {
    facade = Facade.getInstance();
  }

  public void run() {
    //scenario1();
    //scenario2();
    // extra scenario to show off course planner generation
    // scenario3();
  }

  public void scenario1() {
    // Student: Brax West
    // Junior Computer Science major

    facade.login("bwest@email.sc.edu", "bwest060903");
    System.out.println(
      "Login Successful. \nCurrent User: " + facade.getUser().toString()
    );
    // Choosing the Application Area
    System.out.println(
      "\nBrax West looks at the following application areas.\n"
    );
    facade.showAppAreaOptions();
    System.out.println(
      "Brax decides to choose Digital Design as his application area.\n"
    );
    facade.setAppArea("Digital Design");

    // Choosing a GFL class to take
    System.out.println("\nBrax searches for his Major Map by name.");
    System.out.println(facade.getMajorMap("Computer Science"));
    System.out.println("\nBrax notices he did not take a GFL elective yet.");
    System.out.println(
      "\nBrax searches for the elective courses by their code."
    );
    facade.showCoursesByCode("GFL");
    System.out.println("Brax decides to pick SPAN 109 as his GFL elective.");

    facade.getStudentCoursePlanner().addCourse(6, "SPAN109");
    facade.displayProgressStudent();
    facade.writeStudentCoursePlanner(
      facade.getUser().getFirstName() + " " + facade.getUser().getLastName()
    );
    facade.signOut();
  }

  public void scenario2() {
    facade.signUpAdvisor(
      "Osbert",
      "Odden",
      "osberto@mailbox.sc.edu",
      "h3110m0m!2"
    );
    System.out.println(
      "Sign Up Successful. \nCurrent User: " + facade.getUser().toString()
    );

    System.out.println("\nSearching for Student and adding to list");
    facade.addStudentToListOfAdvisees(
      facade.getCurrentUserId(),
      facade.getUserIdByName("Tawnie", "Hill")
    );

    System.out.println("\nStudent found and added to list");
    System.out.println(facade.getListOfAdvisees().toString());
    System.out.println(
      "\nOsbert looks at Tawnie's current degree progress and sees two stat classes"
    );
    System.out.println(
      facade
        .getStudentByAdvisor(
          facade.getUser().getID(),
          facade.getUserIdByName("Tawnie", "Hill")
        )
        .getDegreeProgress()
        .toString()
    );

    System.out.println(
      "\nObsert goes to add the note to her profile\nList of notes before not added\n"
    );
    System.out.println(
      facade
        .getStudentByAdvisor(
          facade.getUser().getID(),
          facade.getUserIdByName("Tawnie", "Hill")
        )
        .getAdvisorNotes()
    );
    System.out.println("\nNote added");
    facade.addNoteToStudentAdvisor(
      facade.getUser().getID(),
      (facade.getUserIdByName("Tawnie", "Hill")),
      "Make Stats Your Application Area"
    );
    System.out.println("\nList of notes after note is made\n");
    System.out.println(
      facade
        .getStudentByAdvisor(
          facade.getUser().getID(),
          facade.getUserIdByName("Tawnie", "Hill")
        )
        .getAdvisorNotes() +
      "\n"
    );

    facade.signOut();
  }

  public void scenario3() {
    System.out.println(
      "\nScenario 3: Not using course planner and generating one"
    );

    // Hardcoded email and password
    String email = "rio.farrah2004@gmail.com";
    String password = "Real?dejaneir0";

    facade.login(email, password);

    if (facade.getUser() != null) {
      System.out.println("Login successful!");
      System.out.println("Current user:");
      System.out.println(facade.getUser());
    } else {
      System.out.println("Login failed. Incorrect email or password.");
    }
    System.out.println("Student checks degree progress\n");
    System.out.println(facade.getStudentDegreeProgress());

    facade
      .getStudentCoursePlanner()
      .generateFromMajorMap(facade.getMajorMap(facade.getStudentMajor()));
    facade.writeStudentCoursePlanner(
      facade.getUser().getFirstName() + " " + facade.getUser().getLastName()
    );

    System.out.println(facade.getUser());

    facade.signOut();
  }

  public static void main(String[] args) {
    Driver advisingInterface = new Driver();
    advisingInterface.run();
  }
}
