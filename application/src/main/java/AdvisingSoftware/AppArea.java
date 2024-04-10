package AdvisingSoftware;

/**
 * The class that creates adds a Application areas
 * to a list of application areas.
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
import java.util.ArrayList;

public class AppArea {

  /**
   * Attributes
   */
  private String appAreaName;
  private ArrayList<String> majorElectives = new ArrayList<String>();
  private ArrayList<String> appAreaCourses = new ArrayList<String>();
  private ArrayList<String> appAreaOptions = new ArrayList<String>();

  /**
   * Application Area Constructor
   * 
   * @param appAreaName the name of the application area wanting to be found
   */
  public AppArea(String appAreaName) {
    appAreaOptions.add("Science");
    appAreaOptions.add("Math");
    appAreaOptions.add("Digital Design");
    appAreaOptions.add("Robotics");
    appAreaOptions.add("Speech");
    if (appAreaOptions.contains(appAreaName)) {
      this.appAreaName = appAreaName;
    }
    setMajorElectives(appAreaName);
    setAppAreaCourses(appAreaName);
  }

  /**
   * Displays the application options
   * 
   * @return the application area options
   */
  public String showAppAreaOptions() {
    String appAreaOptions = "*******Application Areas*******\n";
    for (String appArea : this.appAreaOptions) {
      appAreaOptions += appArea + ", ";
    }
    return appAreaOptions + "and more to come...";
  }

  /**
   * Gets the application area options
   * 
   * @return the list of application area options
   */
  public ArrayList<String> getAppAreaOptions() {
    return appAreaOptions;
  }

  /**
   * Gets the major electives
   * 
   * @return the list of major eletives
   */
  public ArrayList<String> getmajorElectives() {
    return majorElectives;
  }

  /**
   * Get the application area courses
   * 
   * @return the list of application area courses
   */
  public ArrayList<String> getAppAreaCourses() {
    return appAreaCourses;
  }

  /**
   * Sets the major electives
   * 
   * @param appAreaName the name of the application area
   */
  public void setMajorElectives(String appAreaName) {
    if (appAreaName.equalsIgnoreCase("Science")) {
      majorElectives.add("CSCE520");
      majorElectives.add("CSCE580");
      majorElectives.add("CSCE582");
      majorElectives.add("CSCE567");
      majorElectives.add("CSCE578");
    }

    if (appAreaName.equalsIgnoreCase("Math")) {
      majorElectives.add("CSCE564");
      majorElectives.add("CSCE565");
      majorElectives.add("CSCE567");
      majorElectives.add("CSCE569");
    }
    if (appAreaName.equalsIgnoreCase("Digital Design")) {
      majorElectives.add("CSCE520");
      majorElectives.add("CSCE552");
      majorElectives.add("CSCE564");
      majorElectives.add("CSCE565");
      majorElectives.add("CSCE567");
    }
    if (appAreaName.equalsIgnoreCase("Robotics")) {
      majorElectives.add("CSCE574");
      majorElectives.add("CSCE580");
    }
    if (appAreaName.equalsIgnoreCase("Speech")) {
      majorElectives.add("CSCE520");
      majorElectives.add("CSCE531");
      majorElectives.add("CSCE587");
      majorElectives.add("CSCE580");
    }
  }

  /**
   * depending on the application area name, the method would
   * populate the given lists with the corresponding courses
   * 
   * @param appAreaName String application area name
   */
  public void setAppAreaCourses(String appAreaName) {
    if (appAreaName.equalsIgnoreCase("Science")) {
      appAreaCourses.add("STAT530");
      appAreaCourses.add("STAT511");
      appAreaCourses.add("STAT535");
      appAreaCourses.add("STAT511");
      appAreaCourses.add("STAT512");
      appAreaCourses.add("STAT513");
    }
    if (appAreaName.equalsIgnoreCase("Math")) {
      appAreaCourses.add("MATH242");
      appAreaCourses.add("MATH300");
      appAreaCourses.add("MATH520");
      appAreaCourses.add("MATH546");
      appAreaCourses.add("MATH554");
      appAreaCourses.add("MATH574");
    }
    if (appAreaName.equalsIgnoreCase("Digital Design")) {
      appAreaCourses.add("MART201");
      appAreaCourses.add("MART210");
      appAreaCourses.add("MART371");
      appAreaCourses.add("MART380");
    }
    if (appAreaName.equalsIgnoreCase("Robotics")) {
      appAreaCourses.add("EMCH535");
      appAreaCourses.add("ELCT331");
      appAreaCourses.add("ELCT531");
    }
    if (appAreaName.equalsIgnoreCase("Speech")) {
      appAreaCourses.add("LING340");
      appAreaCourses.add("LING421");
      appAreaCourses.add("LING440");
      appAreaCourses.add("LING565");
      appAreaCourses.add("LING567");
    }
  }

  /**
   * The display of the application area: major electives and courses
   * 
   * @return the string format of the string application area
   */
  public String toString() {
    String appAreaText = "";
    appAreaText += "\n*******Application Area*******\n";
    appAreaText += appAreaName;

    appAreaText += "\n*******Major Electives*******\n";
    for (String electiveM : majorElectives) {
      appAreaText += electiveM;
      appAreaText += "\n";
    }

    appAreaText += "\n*******Application Area Courses*******\n";
    for (String appAreaC : appAreaCourses) {
      appAreaText += appAreaC;
      appAreaText += "\n";
    }
    return appAreaText;
  }
}
