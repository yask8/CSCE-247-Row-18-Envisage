package AdvisingSoftware;

import java.util.ArrayList;

/**
 * Class that creates a course
 *
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson
 *         (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
public class Course {

  /**
   * Attributes
   */
  private String id;
  private String name;
  private String code;
  private String description;
  private int creditHours;
  private String subject;
  private char passGrade;
  private boolean elective;
  private boolean carolinaCore;
  private ArrayList<String> preReqs;
  private String semester;
  private String year;

  /**
   * Course constructor
   *
   * @param id           UUID course id
   * @param name         String course name
   * @param code         String course code
   * @param description  String course description
   * @param creditHours  int course credit hours
   * @param subject      String course subject
   * @param passGrade    char minimum grade needed to pass course
   * @param elective     boolean shows whether course is an elective or not
   * @param carolinaCore boolean shows whether course is a Carolina Core or not
   * @param preReqs      ArrayList<Course> list of prerequisites for course
   */
  public Course(
    String id,
    String name,
    String code,
    String description,
    int creditHours,
    String subject,
    char passGrade,
    boolean elective,
    boolean carolinaCore,
    ArrayList<String> preReqs,
    String year,
    String semester
  ) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.creditHours = creditHours;
    this.subject = subject;
    this.passGrade = passGrade;
    this.elective = elective;
    this.carolinaCore = carolinaCore;
    this.preReqs = preReqs;
    this.semester = semester;
    this.year = year;

    name = name.trim();

    // Generate or update the course ID based on the trimmed course name
    String[] nameParts = name.split("\\s+");
    if (nameParts.length >= 2) {
      this.id = nameParts[0] + nameParts[1].replaceAll("\\s+", "");
    } else {
      this.id = nameParts[0];
    }
  }

  /**
   * Course constructor that generates UUID
   *
   * @param name         String course name
   * @param code         String course code
   * @param description  String course description
   * @param creditHours  int course credit hours
   * @param subject      String course subject
   * @param passGrade    char minimum grade needed to pass course
   * @param elective     boolean shows whether course is an elective or not
   * @param carolinaCore boolean shows whether course is a Carolina Core or not
   * @param preReqs      ArrayList<Course> list of prerequisites for course
   */
  public Course(
    String name,
    String code,
    String description,
    int creditHours,
    String subject,
    char passGrade,
    boolean elective,
    boolean carolinaCore,
    ArrayList<String> preReqs,
    String year,
    String semester
  ) {
    this.name = name;
    this.code = code;
    this.description = description;
    this.creditHours = creditHours;
    this.subject = subject;
    this.passGrade = passGrade;
    this.elective = elective;
    this.carolinaCore = carolinaCore;
    this.preReqs = preReqs;
    this.semester = semester;
    this.year = year;

    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Course name cannot be null or empty");
    }

    String[] nameParts = name.split("\\s+");
    if (nameParts.length >= 2) {
      this.id = nameParts[0] + nameParts[1].replaceAll("\\s+", "");
    } else {
      this.id = nameParts[0];
    }
  }

  /**
   * Edits the attributes of the course and returns the modified course.
   *
   * @param name         The new name of the course.
   * @param code         The new code of the course.
   * @param description  The new description of the course.
   * @param creditHours  The new credit hours of the course.
   * @param subject      The new subject of the course.
   * @param passGrade    The new pass grade of the course.
   * @param elective     The new status of the course being elective or not.
   * @param carolinaCore The new status of the course being a Carolina Core or not.
   * @param preReqs      The new list of prerequisites for the course.
   * @param year         The new year of the course.
   * @param semester     The new semester of the course.
   * @return The modified course.
   */
  public Course editCourse(
    String name,
    String code,
    String description,
    int creditHours,
    String subject,
    char passGrade,
    boolean elective,
    boolean carolinaCore,
    ArrayList<String> preReqs,
    String year,
    String semester
  ) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Course name cannot be null or empty");
    }

    this.name = name;
    this.code = code;
    this.description = description;
    this.creditHours = creditHours;
    this.subject = subject;
    this.passGrade = passGrade;
    this.elective = elective;
    this.carolinaCore = carolinaCore;
    this.preReqs = preReqs;
    this.year = year;
    this.semester = semester;

    // Generate or update the course ID based on the course name
    String[] nameParts = name.split("\\s+");
    if (nameParts.length >= 2) {
      this.id = nameParts[0] + nameParts[1].replaceAll("\\s+", "");
    } else {
      this.id = nameParts[0];
    }

    return this; // Return the modified course
  }

  /**
   * To string to view user details
   *
   * @return the string format of course info
   */
  public String toString() {
    return (
      "\n********* COURSE INFO *********\n" +
      "id: " +
      id +
      '\n' +
      "name: " +
      name +
      '\n' +
      "code: " +
      code +
      '\n' +
      "description: " +
      description +
      '\n' +
      "creditHours: " +
      creditHours +
      '\n' +
      "subject: " +
      subject +
      '\n' +
      "passGrade: " +
      passGrade +
      '\n' +
      "elective: " +
      elective +
      '\n' +
      "carolinaCore: " +
      carolinaCore +
      '\n' +
      "preReqs: " +
      preReqs +
      '\n' +
      "semester: " +
      semester +
      '\n' +
      "year: " +
      year +
      '\n'
    );
  }

  /**
   * Gets the id of the course
   *
   * @return the id
   */
  public String getID() {
    return id;
  }

  /**
   * Gets the name of the course
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the code of the course
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Gets the description of the course
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the course credit hours
   *
   * @return the credit hours
   */
  public int getCreditHours() {
    return creditHours;
  }

  /**
   * Gets the course subject
   *
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Gets the passing grade for course
   *
   * @return the passing grade
   */
  public char getPassGrade() {
    return passGrade;
  }

  /**
   * Checks if course is elective
   *
   * @return return true or false
   */
  public boolean isElective() {
    return elective;
  }

  /**
   * Checks if course is carolina core
   *
   * @return true or false
   */
  public boolean isCarolinaCore() {
    return carolinaCore;
  }

  /**
   * Gets the list of prerequired course
   *
   * @return the prerequired courses
   */
  public ArrayList<String> getPreReqs() {
    return preReqs;
  }

  /**
   * Gets the semester
   *
   * @return the semester
   */
  public String getSemester() {
    return semester;
  }

  /**
   * Gets the year
   *
   * @return the year
   */
  public String getYear() {
    return year;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreditHours(int creditHours) {
    this.creditHours = creditHours;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setPassGrade(char passGrade) {
    this.passGrade = passGrade;
  }

  public void setElective(boolean elective) {
    this.elective = elective;
  }

  public void setCarolinaCore(boolean carolinaCore) {
    this.carolinaCore = carolinaCore;
  }

  public void setPrereqs(ArrayList<String> preReqs) {
    this.preReqs = preReqs;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }
}
