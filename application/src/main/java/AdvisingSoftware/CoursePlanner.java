package AdvisingSoftware;

import java.util.ArrayList;

/**
 * Helps the student stay organize by allowing to create their own ideal
 * 8-semester plan
 *
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
public class CoursePlanner {

  /**
   * Attributes
   */
  private ArrayList<ArrayList<String>> semesters;

  /**
   * Constructor for the CoursePlanner
   */
  public CoursePlanner() {
    this.semesters = new ArrayList<>();

    // Add 8 semesters
    for (int i = 0; i < 8; i++) {
      this.semesters.add(new ArrayList<>());
    }
  }

  /**
   * Adds a course to the specified semester
   *
   * @param semesterIndex the index of the semester (1-based)
   * @param courseName    the name of the course to add
   */
  public void addCourse(int semesterIndex, String courseName) {
    if (semesterIndex >= 1 && semesterIndex <= 8) {
      this.semesters.get(semesterIndex - 1).add(courseName);
    } else {
      System.out.println("Invalid semester index.");
    }
  }

  /**
   * Removes a course from the specified semester by its name
   *
   * @param semesterIndex the index of the semester (1-based)
   * @param courseName    the name of the course to remove
   */
  public void removeCourse(int semesterIndex, String courseName) {
    if (semesterIndex >= 1 && semesterIndex <= 8) {
      ArrayList<String> semesterCourses = this.semesters.get(semesterIndex - 1);
      if (semesterCourses.contains(courseName)) {
        semesterCourses.remove(courseName);
      } else {
        System.out.println(
          "Course '" + courseName + "' not found in semester " + semesterIndex
        );
      }
    } else {
      System.out.println("Invalid semester index.");
    }
  }

  /**
   * Displays the planner for all semesters
   *
   * @return the displayPlanner
   */
  public String toString() {
    String result = "\n";
    for (int i = 1; i <= 8; i++) {
      result += "********** Semester " + i + " *********" + "\n";
      ArrayList<String> semesterCourses = this.semesters.get(i - 1);
      if (semesterCourses.isEmpty()) {
        result += "No courses added to this semester.\n";
      } else {
        for (String courseName : semesterCourses) {
          result += courseName + "\n";
        }
      }
      result += "\n";
    }
    return result;
  }

  /**
   * Getter for the semesters attribute
   *
   * @return the semesters
   */
  public ArrayList<ArrayList<String>> getSemesters() {
    return semesters;
  }

  /**
   * Gets the number of semesters in the course planner.
   *
   * @return The number of semesters.
   */
  public int getNumberOfSemesters() {
    return semesters.size();
  }

  /**
   * Gets the courses for the specified semester.
   *
   * @param semester The index of the semester (1-based).
   * @return An array of course names for the specified semester.
   */
  public String[] getCoursesForSemester(int semester) {
    if (semester < 1 || semester > semesters.size()) {
      throw new IllegalArgumentException("Invalid semester index.");
    }

    ArrayList<String> semesterCourses = semesters.get(semester - 1);
    return semesterCourses.toArray(new String[0]);
  }

  /**
 * Generates the course planner from the given major map by adding all the
 * courses from the major map into the course planner, provided they are not already
 * present.
 *
 * @param majorMap The major map containing the courses to be added to the
 *                 course planner.
 */
public void generateFromMajorMap(MajorMap majorMap) {
  if (majorMap == null) {
      throw new IllegalArgumentException("Major map cannot be null.");
  }

  ArrayList<ArrayList<String>> majorMapSemesters = new ArrayList<>();
  majorMapSemesters.add(majorMap.getSemester1());
  majorMapSemesters.add(majorMap.getSemester2());
  majorMapSemesters.add(majorMap.getSemester3());
  majorMapSemesters.add(majorMap.getSemester4());
  majorMapSemesters.add(majorMap.getSemester5());
  majorMapSemesters.add(majorMap.getSemester6());
  majorMapSemesters.add(majorMap.getSemester7());
  majorMapSemesters.add(majorMap.getSemester8());

  for (int i = 0; i < 8; i++) {
      ArrayList<String> semesterCourses = majorMapSemesters.get(i);
      for (String course : semesterCourses) {
          if (!this.semesters.get(i).contains(course)) {
              this.semesters.get(i).add(course);
          }
      }
  }
}

  /**
 * Returns whether the given course exists in the course planner or not.
 *
 * @param course String course id
 * @return boolean true if course exists in course planner
 */
public boolean searchPlanner(String course) {
  boolean exists = false;
  String courseLowerCase = course.toLowerCase(); // Convert course to lowercase
  for (ArrayList<String> xSemester : semesters) {
      for (String courseInSemester : xSemester) {
          if (courseInSemester.equalsIgnoreCase(courseLowerCase)) { // Perform case-insensitive comparison
              exists = true;
              break;
          }
      }
      if (exists) {
          break; // Once found, no need to continue searching
      }
  }
  return exists;
}
}
