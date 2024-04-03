package AdvisingSoftware;

import java.util.ArrayList;

/**
 * Represents a list of courses in the system.
 * 
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson
 *         (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
public class CourseList {

  private static CourseList courseList;
  private ArrayList<Course> courses;
  private boolean loaded;

  /**
   * Private constructor Initializes the list of courses
   */
  private CourseList() {
    courses = new ArrayList<Course>();
    loaded = false;
  }

  /**
   * Get the singleton instance of CourseList.
   *
   * @return The singleton instance of CourseList.
   */
  public static CourseList getInstance() {
    if (courseList == null) {
      courseList = new CourseList();
      if (!courseList.isLoaded()) {
        ArrayList<Course> courseData = DataLoader.loadCourses();
        for (Course course : courseData) {
          courseList.addCourse(
              course.getName(),
              course.getCode(),
              course.getDescription(),
              course.getCreditHours(),
              course.getSubject(),
              course.getPassGrade(),
              course.isElective(),
              course.isCarolinaCore(),
              course.getPreReqs(),
              course.getYear(),
              course.getSemester());
        }
        courseList.setLoaded(true);
      }
    }
    return courseList;
  }

  /**
   * Gets a course based on the given course code.
   *
   * @param code The course code to search for.
   * @return The course with the matching code, or null if not found.
   */
  public Course getCourse(String code) {
    for (Course course : courses) {
      if (course.getCode().equals(code)) {
        return course;
      }
    }
    return null;
  }

  /**
   * Removes a course from the course list based on the course code.
   *
   * @param code The course code to remove.
   * @return true if the course was found and removed, false otherwise.
   */
  public boolean removeCourse(String code) {
    Course courseToRemove = null;
    for (Course course : courses) {
      if (course.getCode().equals(code)) {
        courseToRemove = course;
        break;
      }
    }
    if (courseToRemove != null) {
      courses.remove(courseToRemove);
      return true;
    }
    return false;
  }

  /**
   * Adds a new course to the course list without year or semester
   *
   * @param name         The name of the course.
   * @param code         The code of the course.
   * @param description  The description of the course.
   * @param creditHours  The credit hours of the course.
   * @param subject      The subject of the course.
   * @param passGrade    The passing grade of the course.
   * @param elective     Indicates if the course is elective.
   * @param carolinaCore Indicates if the course is part of Carolina Core.
   * @param prereqs      The prerequisites of the course.
   */
  public void addCourseNoYearorSem(
      String name,
      String code,
      String description,
      int creditHours,
      String subject,
      char passGrade,
      boolean elective,
      boolean carolinaCore,
      ArrayList<String> prereqs) {
    if (!courseWithNameExists(name)) {
      Course newCourse = new Course(
          name,
          code,
          description,
          creditHours,
          subject,
          passGrade,
          elective,
          carolinaCore,
          prereqs,
          null,
          null);
      courses.add(newCourse);
    } else {
      System.out.println("A course with the same name already exists.");
    }
  }

  /**
   * adds a course to list of courses
   * 
   * @param name         The name of the course.
   * @param code         The code of the course.
   * @param description  The description of the course.
   * @param creditHours  The credit hours of the course.
   * @param subject      The subject of the course.
   * @param passGrade    The passing grade of the course.
   * @param elective     Indicates if the course is elective.
   * @param carolinaCore Indicates if the course is part of Carolina Core.
   * @param prereqs      The prerequisites of the course.
   * @param year         The default year of the course.
   * @param semester     the default semester of the course.
   */
  public void addCourse(
      String name,
      String code,
      String description,
      int creditHours,
      String subject,
      char passGrade,
      boolean elective,
      boolean carolinaCore,
      ArrayList<String> prereqs,
      String year,
      String semester) {
    if (prereqs == null) {
      prereqs = new ArrayList<>();
    }

    if (!courseWithNameExists(name)) {
      Course newCourse = new Course(
          name,
          code,
          description,
          creditHours,
          subject,
          passGrade,
          elective,
          carolinaCore,
          prereqs,
          year,
          semester);
      courses.add(newCourse);
    }
  }

  public void addCourseObject(Course newCourse) {
    if (!courseWithNameExists(newCourse.getName())) {
      courses.add(newCourse);
    } else {
      System.out.println("A course with the same name already exists.");
    }
  }

  /**
   * checks if course exists in the list of all courses
   * 
   * @param name String name of Course
   * @return boolean
   */
  private boolean courseWithNameExists(String name) {
    for (Course course : courses) {
      if (course.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Sets the loaded status of the course list.
   *
   * @param loaded The loaded status to set.
   */
  public void setLoaded(boolean loaded) {
    this.loaded = loaded;
  }

  /**
   * Checks if the course list is loaded.
   *
   * @return true if the course list is loaded, false otherwise.
   */
  public boolean isLoaded() {
    return loaded;
  }

  public void displayAllCourses() {
    System.out.println("Printing Course Details:");
    ArrayList<Course> allCourses = getCourses();
    for (Course course : allCourses) {
      System.out.println(course.toString());
    }
  }

  /**
   * Gets the list of courses.
   *
   * @return The list of courses.
   */
  public ArrayList<Course> getCourses() {
    return courses;
  }

  /**
   * Checks if a course with the given ID exists in the course list.
   *
   * @param id The ID of the course to check.
   * @return true if the course exists, false otherwise.
   */
  public boolean courseExistsById(String id) {
    for (Course course : courses) {
      if (course.getID().equals(id)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets a course based on the given ID.
   *
   * @param id The ID of the course to retrieve.
   * @return The course with the matching ID, or null if not found.
   */
  public Course getCourseById(String id) {
    for (Course course : courses) {
      if (course.getID().equals(id)) {
        return course;
      }
    }
    return null;
  }

  /**
 * Displays courses that have a certain code.
 * 
 * @param courseCode The course code to search for.
 */
public void showCoursesByCode(String courseCode) {
  boolean found = false; // Flag to check if any courses were found
  System.out.println("***********" + courseCode + " Courses***********");
  for (Course course : getCourses()) {
      if (course.getCode().equalsIgnoreCase(courseCode)) {
          System.out.println(course.toString());
          found = true;
      }
  }
  if (!found) {
      System.out.println("No courses found with code: " + courseCode);
  }
}


  /**
   * Clears all courses from the course list.
   */
  public void clear() {
    courses.clear();
  }

  /**
   * Returns a copy of the list of all courses.
   *
   * @return The list of all courses.
   */
  public ArrayList<Course> getAllCourses() {
    return new ArrayList<>(courses);
  }
}
