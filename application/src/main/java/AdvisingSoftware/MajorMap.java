package AdvisingSoftware;

/**
 * Creates a Major Map
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 * 
 */
import java.util.ArrayList;
import java.util.UUID;

public class MajorMap {
  /**
   * Attributes
   */
  private UUID id;
  private String major;
  private ArrayList<String> semester1;
  private ArrayList<String> semester2;
  private ArrayList<String> semester3;
  private ArrayList<String> semester4;
  private ArrayList<String> semester5;
  private ArrayList<String> semester6;
  private ArrayList<String> semester7;
  private ArrayList<String> semester8;
  private int minTotalHours;
  private int minGradHours;
  private int caroCoreHours;
  private Double minGPA;

  /**
   * Major Map Constructor
   * 
   * @param id            the unique identifier
   * @param major         the major
   * @param semester1     semester one
   * @param semester2     semester two
   * @param semester3     semester three
   * @param semester4     semester four
   * @param semester5     semester five
   * @param semester6     semester six
   * @param semester7     semester seven
   * @param semester8     semester eight (last semester)
   * @param minTotalHours minimum total hours
   * @param minGradHours  minimum grad hours
   * @param caroCoreHours carolina core hours
   * @param minGPA        minimmum GPA
   */
  public MajorMap(
      UUID id,
      String major,
      ArrayList<String> semester1,
      ArrayList<String> semester2,
      ArrayList<String> semester3,
      ArrayList<String> semester4,
      ArrayList<String> semester5,
      ArrayList<String> semester6,
      ArrayList<String> semester7,
      ArrayList<String> semester8,
      int minTotalHours,
      int minGradHours,
      int caroCoreHours,
      Double minGPA) {
        if (major == null || major.trim().isEmpty()) {
          throw new IllegalArgumentException("Major name cannot be null or empty");
      }
    this.id = id;
    this.major = major.trim();
    this.semester1 = semester1;
    this.semester2 = semester2;
    this.semester3 = semester3;
    this.semester4 = semester4;
    this.semester5 = semester5;
    this.semester6 = semester6;
    this.semester7 = semester7;
    this.semester8 = semester8;
    this.minTotalHours = Math.max(0, minTotalHours);
    this.minGradHours = Math.max(0, minGradHours);
    this.caroCoreHours = Math.max(0, caroCoreHours);
    this.minGPA = Math.max(0, minGPA);
  }

  /**
   * Major Map Constructor Overloaded
   * 
   * @param id            the unique identifier
   * @param major         the major
   * @param semester1     semester one
   * @param semester2     semester two
   * @param semester3     semester three
   * @param semester4     semester four
   * @param semester5     semester five
   * @param semester6     semester six
   * @param semester7     semester seven
   * @param semester8     semester eight (last semester)
   * @param minTotalHours minimum total hours
   * @param minGradHours  minimum grad hours
   * @param caroCoreHours carolina core hours
   * @param minGPA        minimum GPA
   */
  public MajorMap(
      String major,
      ArrayList<String> semester1,
      ArrayList<String> semester2,
      ArrayList<String> semester3,
      ArrayList<String> semester4,
      ArrayList<String> semester5,
      ArrayList<String> semester6,
      ArrayList<String> semester7,
      ArrayList<String> semester8,
      int minTotalHours,
      int minGradHours,
      int caroCoreHours,
      Double minGPA) {
        if (major == null || major.trim().isEmpty()) {
          throw new IllegalArgumentException("Major name cannot be null or empty");
      }
    this.id = UUID.randomUUID();
    this.major = major.trim();
    this.semester1 = semester1;
    this.semester2 = semester2;
    this.semester3 = semester3;
    this.semester4 = semester4;
    this.semester5 = semester5;
    this.semester6 = semester6;
    this.semester7 = semester7;
    this.semester8 = semester8;
    this.minTotalHours = Math.max(0, minTotalHours);
    this.minGradHours = Math.max(0, minGradHours);
    this.caroCoreHours = Math.max(0, caroCoreHours);
    this.minGPA = Math.max(0, minGPA);
  }

  /**
   * Deletes a major map
   * 
   * @param major the major being deleted
   */
  public void deleteMajor(String major) {
    if (this.major.equals(major)) {
      this.major = null;
      this.semester1 = null;
      this.semester2 = null;
      this.semester3 = null;
      this.semester4 = null;
      this.semester5 = null;
      this.semester6 = null;
      this.semester7 = null;
      this.semester8 = null;
      this.id = null;
      this.minTotalHours = 0;
      this.minGradHours = 0;
      this.caroCoreHours = 0;
      this.minGPA = 0.0;
    }
  }

  /**
   * Gets the courses for the major
   * 
   * @param major the major
   * @return a new arraylist of courses for the majormap
   */
  public ArrayList<String> getCoursesForMajor(String major) {
    if (this.major.equalsIgnoreCase(major)) {
      ArrayList<String> allCourses = new ArrayList<>();
      allCourses.addAll(semester1);
      allCourses.addAll(semester2);
      allCourses.addAll(semester3);
      allCourses.addAll(semester4);
      allCourses.addAll(semester5);
      allCourses.addAll(semester6);
      allCourses.addAll(semester7);
      allCourses.addAll(semester8);
      return allCourses;
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * Displays the major map
   * 
   * @param major the major
   */
  public void displayMajorMap(String major) {
    if (this.major.equals(major)) {
      System.out.println("\n************ Major Map *************");
      System.out.println("Major: " + this.major);
      System.out.println("MajorMap ID: " + this.id);
      System.out.println("Semester 1: " + this.semester1);
      System.out.println("Semester 2: " + this.semester2);
      System.out.println("Semester 3: " + this.semester3);
      System.out.println("Semester 4: " + this.semester4);
      System.out.println("Semester 5: " + this.semester5);
      System.out.println("Semester 6: " + this.semester6);
      System.out.println("Semester 7: " + this.semester7);
      System.out.println("Semester 8: " + this.semester8);
      System.out.println("Minimum Total Hours: " + this.minTotalHours);
      System.out.println("Minimum Grad Hours: " + this.minGradHours);
      System.out.println("Carolina Core Hours: " + this.caroCoreHours);
      System.out.println("Minimum GPA: " + this.minGPA);
    } else {
      System.out.println("No matching major found.");
    }
  }

  /**
   * The Major Map Displayed more indepth
   * 
   * @return the string format of the Major Map
   */
  public String toString() {
    return "\n********* MAJOR MAP *********\n" +
        "Major: " + this.major + "\n" +
        "MajorMap ID: " + this.id + "\n" +
        "\n********* Minimum Requirements *********\n" +
        "Minimum Total Hours: " + this.minTotalHours + "\n" +
        "Minimum Grad Hours: " + this.minGradHours + "\n" +
        "Carolina Core Hours: " + this.caroCoreHours + "\n" +
        "Minimum GPA: " + this.minGPA + "\n" +
        "\n********* By Semester Details *********\n" +
        "\n********* Year 1 *********\n" +
        "Semester 1: " + this.semester1 + "\n" +
        "Semester 2: " + this.semester2 + "\n" +
        "\n********* Year 2 *********\n" +
        "Semester 3: " + this.semester3 + "\n" +
        "Semester 4: " + this.semester4 + "\n" +
        "\n********* Year 3 *********\n" +
        "Semester 5: " + this.semester5 + "\n" +
        "Semester 6: " + this.semester6 + "\n" +
        "\n********* Year 4 *********\n" +
        "Semester 7: " + this.semester7 + "\n" +
        "Semester 8: " + this.semester8 + "\n";
  }

  /**
   * Gets the id
   * 
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * Gets the major
   * 
   * @return the major
   */
  public String getMajor() {
    return major;
  }

  /**
   * Gets the list of an ideal semester 1
   * 
   * @return semester 1
   */
  public ArrayList<String> getSemester1() {
    return semester1;
  }

  /**
   * Gets the list of an ideal semester 2
   * 
   * @return semester 2
   */
  public ArrayList<String> getSemester2() {
    return semester2;
  }

  /**
   * Gets the list of an ideal semester 3
   * 
   * @return semester 3
   */
  public ArrayList<String> getSemester3() {
    return semester3;
  }

  /**
   * Gets the list of an ideal semester 4
   * 
   * @return semester 4
   */
  public ArrayList<String> getSemester4() {
    return semester4;
  }

  /**
   * Gets the list of an ideal semester 5
   * 
   * @return semester 5
   */
  public ArrayList<String> getSemester5() {
    return semester5;
  }

  /**
   * Gets the list of an ideal semester 6
   * 
   * @return semester 6
   */
  public ArrayList<String> getSemester6() {
    return semester6;
  }

  /**
   * Gets the list of an ideal semester 7
   * 
   * @return semester 7
   */
  public ArrayList<String> getSemester7() {
    return semester7;
  }

  /**
   * Gets the list of an ideal semester 8
   * 
   * @return semester 8
   */
  public ArrayList<String> getSemester8() {
    return semester8;
  }

  /**
   * Get the courses for the specified semester.
   *
   * @param semesterNumber The number of the semester (1-8).
   * @return The courses for the specified semester.
   * @throws IllegalArgumentException if semester number is out of range.
   */
  public ArrayList<String> getSemester(int semesterNumber) {
    if (semesterNumber < 1 || semesterNumber > 8) {
      throw new IllegalArgumentException("Semester number must be between 1 and 8");
    }

    switch (semesterNumber) {
      case 1:
        return semester1;
      case 2:
        return semester2;
      case 3:
        return semester3;
      case 4:
        return semester4;
      case 5:
        return semester5;
      case 6:
        return semester6;
      case 7:
        return semester7;
      case 8:
        return semester8;
      default:
        throw new IllegalArgumentException("Invalid semester number: " + semesterNumber);
    }
  }

  /**
   * Gets the minimum total hours
   * 
   * @return total hours
   */
  public int getMinTotalHours() {
    return minTotalHours;
  }

  /**
   * Gets the minimum grad hours
   * 
   * @return grad hours
   */
  public int getMinGradHours() {
    return minGradHours;
  }

  /**
   * Gets the carolina core hours
   * 
   * @return carolina core hours
   */
  public int getCaroCoreHours() {
    return caroCoreHours;
  }

  /**
   * Gets the minimum gpa
   * 
   * @return the minimum gpa
   */
  public Double getMinGPA() {
    return minGPA;
  }

  /**
   * Sets the minimum total hours
   */
  public void setMinTotalHours(int minTotalHr) {
    if (minTotalHr > 0) {
      this.minTotalHours = minTotalHr;
    }
  }

  /**
   * Sets the minimum grad hours
   */
  public void setMinGradHours(int minGradHr) {
    if (minGradHr > 0) {
      this.minGradHours = minGradHr;
    }
  }

  /**
   * Sets the carolina core hours
   */
  public void setCaroCoreHours(int minCaroCoreHr) {
    if (minCaroCoreHr > 0) {
      this.caroCoreHours = minCaroCoreHr;
    }
  }

  /*
   * Sets the minimum gpa
   */
  public void setMinGPA(Double minGPA) {
    if (minGPA > 0) {
      this.minGPA = minGPA;
    }
  }

  /**
   * Checks if major map contains the course
   * 
   * @param courseName name of course
   * @return true if course is present, false if course is not
   */
  public boolean containsCourse(String courseName) {
    return semester1.contains(courseName) || semester2.contains(courseName) ||
        semester3.contains(courseName) || semester4.contains(courseName) ||
        semester5.contains(courseName) || semester6.contains(courseName) ||
        semester7.contains(courseName) || semester8.contains(courseName);
  }

  public void addCourseToSemester(String courseName, int semesterNumber) {
    if (courseName == null) {
      System.out.println("Course name cannot be null.");
      return;
    }

    if (semesterNumber < 1 || semesterNumber > 8) {
      System.out.println("Semester number must be between 1 and 8.");
      return;
    }

    courseName = courseName.trim();
    ArrayList<String> semester = getSemester(semesterNumber);

    if (!containsCourse(courseName)) {
      semester.add(courseName);
    } else {
      System.out.println("Course " + courseName + " already exists in semester " + semesterNumber);
    }
  }

  public boolean removeCourseFromSemester(String courseName, int semesterNumber) {
    if (courseName == null) {
      System.out.println("Course name cannot be null.");
      return false;
    }

    if (semesterNumber < 1 || semesterNumber > 8) {
      System.out.println("Semester number must be between 1 and 8.");
      return false;
    }

    ArrayList<String> semester = getSemester(semesterNumber);
    courseName = courseName.trim();

    if (semester.contains(courseName)) {
      semester.remove(courseName);
      return true;
    } else {
      System.out.println("Course " + courseName + " does not exist in semester " + semesterNumber);
      return false;
    }
  }
}
