package AdvisingSoftware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Creates a Student User
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
public class Student extends User {

  /**
   * Attributes
   */
  private String year;
  private String major;
  private int creditHours;
  private ArrayList<Grades> completedCourses;
  private double gpa;
  private String applicationArea;
  private CoursePlanner coursePlanner;
  private DegreeProgress degreeProgress;
  private ArrayList<Note> advisorNotes;

  /**
   * Student Constructor
   *
   * @param year              Student's year/class
   * @param major             Student's major
   * @param creditHours       Student's number of credit hours taken
   * @param completedCourses2 Courses the student has completed
   * @param gpa               Student's GPA
   * @param coursePlanner     Student's courses planned for the future
   * @param degreeProgress    Student's degree progress
   * @param advisorNotes      Student's notes left by advisor
   */
  public Student(
    String firstName,
    String lastName,
    String email,
    UUID uscID,
    String password,
    String userType,
    String year,
    String major,
    String applicationArea,
    int creditHours,
    ArrayList<Grades> completedCourses,
    double gpa,
    CoursePlanner coursePlanner,
    DegreeProgress degreeProgress,
    ArrayList<Note> advisorNotes
  ) {
    super(firstName, lastName, email, uscID, password, userType);
    this.year = year;
    this.major = major;
    this.applicationArea = applicationArea;
    this.creditHours = creditHours;
    this.completedCourses = completedCourses;
    this.gpa = gpa;
    this.coursePlanner = coursePlanner;
    this.degreeProgress = degreeProgress;
    this.advisorNotes = advisorNotes;
  }

  /**
   * Allows student to view the details of their profile
   */
  public void viewProfile() {
    System.out.println(
      "************** Student Profile **************\n" +
      super.toString() +
      "year: '" +
      year +
      "'\n" +
      "major: '" +
      major +
      "'\n" +
      "creditHours: " +
      creditHours +
      "\n" +
      "completedCourses: " +
      completedCourses +
      "\n" +
      "gpa: " +
      gpa +
      "\n" +
      "applicationArea: " +
      applicationArea +
      "\n" +
      "coursePlanner: " +
      coursePlanner +
      "\n" +
      "degreeProgress: " +
      degreeProgress +
      "\n" +
      "advisorNotes: " +
      advisorNotes +
      "\n"
    );
  }

  /**
   * Allows student to edit their profile
   */
  public void editProfile() {}

  /**
   * Allows student to update their year/class
   *
   * @param creditHours updated number of credit hours the student has taken
   * @return String of newly updated year/class
   */
  public String updateYear(int creditHours) {
    if (creditHours <= 29 && creditHours >= 0) {
      setYear("Freshman");
    } else if (creditHours < 60 && creditHours >= 30) {
      setYear("Sophomore");
    } else if (creditHours < 90 && creditHours >= 60) {
      setYear("Junior");
    } else if (creditHours <= 90) {
      setYear("Senior");
    } else if (creditHours > 91) {
      setYear("Super Senior");
    }
    return year;
  }

  /**
   * Allows student to view their major map
   *
   * @param major to identify which major map to view
   * @return the correct Major Map
   */
  public MajorMap viewMajorMap(String major) {
    return viewMajorMap(major);
  }

  /**
   * Allows student to view their major map
   *
   * @param major to identify which major map to view
   * @return the correct Major Map
   */
  public MajorMap viewMajorMap(String major, ArrayList<MajorMap> majorList) {
    MajorMap found = null;
    for (MajorMap find : majorList) {
      if (major.equalsIgnoreCase(find.getMajor())) {
        found = find;
      }
    }
    return found;
  }

  /**
   * Allows student to view their completed courses
   *
   * @param completedCourses ArrayList of students completed courses with their
   *                         respective grade
   */
  public void viewCompletedCourses(ArrayList<Grades> completedCourses) {
    System.out.println("**************Completed Courses**************");
    for (Grades course : completedCourses) {
      System.out.println(course.toString());
    }
  }

  /**
   * Allows student to view their course planner
   *
   * @return course planner
   */
  public String viewCoursePlanner() {
    return coursePlanner.toString();
  }

  /**
   * Allows student to view their degree progress
   *
   * @return degree progress
   */
  public String viewDegreeProgress() {
    return degreeProgress.toString();
  }

  /**
   * Shows student degree progress percentage
   * @param major MajorMap Student's major
   * @param xcompleteCourses ArrayList<Grades> Student's completed courses list
   * @return String degree progress percentage of student
   */
  public String displayProgressStudent(
    ArrayList<Grades> xcompleteCourses,
    ArrayList<Course> courseList
  ) {
    return degreeProgress.displayProgress(
      getStudentsMajorMap(),
      xcompleteCourses,
      courseList
    );
  }

  /**
   * Allows student to add a completed course
   *
   * @param code  Course-specific code
   * @param grade Course grade
   */
  public void addCompleteCourse(String code, char grade) {
    Grades complete = new Grades(code, grade);
    completedCourses.add(complete);
  }

  /**
   * Allows student to view advisor notes
   *
   * @return ArrayList of advisor notes
   */
  public ArrayList<String> viewNotes() {
    ArrayList<String> notes = new ArrayList<String>();
    for (Note current : advisorNotes) {
      String temp = current.toString();
      notes.add(temp);
    }
    return notes;
  }

  /**
   * Get the MajorMap object corresponding to the student's major.
   *
   * @return The MajorMap object of the student's major if found, or null if not
   *         found.
   */
  public MajorMap getStudentsMajorMap() {
    String studentMajor = getMajor();

    if (studentMajor != null || studentMajor == "Undeclared") {
      MajorList majorList = MajorList.getInstance();
      return majorList.getMajorByName(studentMajor);
    } else {
      System.out.println(
        "Please declare major before trying to view your major map"
      );
    }

    return null;
  }

  /**
   * Writes the course planner of this student to a text file.
   *
   * @param studentName The name of the student.
   */
  public void writeCoursePlannerToFile(String studentName) {
    String directoryName = "StudentCoursePlanners";
    String fileName = studentName + "_CoursePlanner.txt";
    Path directoryPath = Paths.get(directoryName);
    Path filePath = directoryPath.resolve(fileName);

    try {
      if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
      }

      try (
        BufferedWriter writer = new BufferedWriter(
          new FileWriter(filePath.toFile())
        )
      ) {
        writer.write("Course Planner for Student: " + studentName);
        writer.newLine();
        writer.write("===============================================");
        writer.newLine();

        for (
          int semester = 1;
          semester <= coursePlanner.getNumberOfSemesters();
          semester++
        ) {
          writer.newLine();
          writer.write("Semester " + semester + ":");
          writer.newLine();
          for (String course : coursePlanner.getCoursesForSemester(semester)) {
            writer.write(course);
            writer.newLine();
          }
        }
        System.out.println("Student's course planner written to " + filePath);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Allows student to update their GPA
   *
   * @return returns updated gpa
   */

  public double updateGPA(ArrayList<Course> courseList) {
    this.gpa = degreeProgress.calculateGPA(courseList, completedCourses);
    return gpa;
  }

  public UUID getStudentsID() {
    return getID();
  }

  /**
   * Getter for year
   *
   * @return the student's year/class
   */
  public String getYear() {
    this.year = updateYear(creditHours);
    return year;
  }

  /**
   * Setter for student's year/class
   * @return
   */
  public void setYear(String year) {
    this.year = year;
  }

  /**
   * Getter for major
   *
   * @return the student's major
   */
  public String getMajor() {
    return major;
  }

  /**
   * Getter for creditHours
   *
   * @return the number of credit hours the student has taken
   */
  public int getCreditHours() {
    this.creditHours = degreeProgress.getTotalCreditHours();
    return creditHours;
  }

  /**
   * Getter for completedCourses
   *
   * @return the courses the student has completed
   */
  public ArrayList<Grades> getCompletedCourses() {
    return completedCourses;
  }

  /**
   * Getter for gpa
   *
   * @return the student's GPA
   */
  public double getGpa() {
    return gpa;
  }

  /**
   * Getter for application area
   *
   * @return the student's application area
   */
  public String getApplicationArea() {
    return applicationArea;
  }

  public void showAppAreaOptions() {
    degreeProgress.displayAllAppAreas();
  }

  public void setAppArea(String xappArea) {
    this.applicationArea = xappArea;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  /**
   * Getter for coursePlanner
   *
   * @return the student's course planner
   */
  public CoursePlanner getCoursePlanner() {
    return coursePlanner;
  }

  /**
   * Getter for degreeProgress
   *
   * @return the student's degree progress
   */
  public DegreeProgress getDegreeProgress() {
    MajorMap majorMap = getStudentsMajorMap();
    if (majorMap != null) {
      degreeProgress.saveCompleteCourses(completedCourses);
      degreeProgress.populateIncompleteCoursesFromMajorMap(majorMap);
      degreeProgress.populateIncompleteCoursesFromAppArea(this.applicationArea);
      degreeProgress.updateCourseCompletion(completedCourses);
    }

    return degreeProgress;
  }

  /**
   * Getter for advisorNotes
   *
   * @return the student's advisor notes
   */
  public ArrayList<Note> getAdvisorNotes() {
    return advisorNotes;
  }

  /**
   * Displays student profile
   * @return student's profile in string format
   */
  public String toString() {
    return (
      "************** Student Profile **************\n" +
      super.toString() +
      "year: '" +
      year +
      "'\n" +
      "major: '" +
      major +
      "'\n" +
      "creditHours: " +
      creditHours +
      "\n" +
      "completedCourses: " +
      completedCourses +
      "\n" +
      "gpa: " +
      gpa +
      "\n" +
      "applicationArea: " +
      applicationArea +
      "\n" +
      "coursePlanner: " +
      coursePlanner.toString() +
      "\n" +
      "degreeProgress: " +
      degreeProgress.toString() +
      "\n" +
      "advisorNotes: " +
      advisorNotes.toString() +
      "\n"
    );
  }
}
