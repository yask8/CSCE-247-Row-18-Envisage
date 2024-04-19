package AdvisingSoftware;

/**
 * Creates an Advisor User
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Advisor extends User {

  /**
   * Attributes
   */
  private ArrayList<UUID> listOfAdvisees;
  private ArrayList<UUID> listOfFailingStudents;

  /**
   * Advisor Constructor
   *
   * @param firstName             String Advisor's first name
   * @param lastName              String Advisor's last name
   * @param email                 String Advisor's email
   * @param uscID                 UUID Advisor's uscID
   * @param password              String Advisor's password
   * @param userType              String userType
   * @param listOfAdvisees        ArrayList<UUID> List of advisor's advisees
   * @param listOfFailingStudents ArrayList<UUID> List of advisor's advisees at
   *                              risk of failing
   */
  public Advisor(
    String firstName,
    String lastName,
    String email,
    UUID uscID,
    String password,
    String userType,
    ArrayList<UUID> listOfAdvisees,
    ArrayList<UUID> listOfFailingStudents
  ) {
    super(firstName, lastName, email, uscID, password, userType);
    this.listOfAdvisees = listOfAdvisees;
    this.listOfFailingStudents = listOfFailingStudents;
  }

  /**
   * Allows the advisor to view a student's profile
   *
   * @param studentId UUID of the student to view
   * @param userList  UserList containing all users
   * @return The student's profile if found, otherwise an empty string
   */
  public String viewStudentProfile(UUID studentId, UserList userList) {
    Student student = getStudentByAdvisor(studentId, userList);
    if (student != null) {
      return student.toString();
    } else {
      return "Student not found or not advisee of the advisor.";
    }
  }

  /**
   * Allows advisor to add a student to their list of advisees
   */
  public void addStudent(UUID uuid) {
    listOfAdvisees.add(uuid);
  }

  /**
   * Allows advisor to remove a student from their list of advisees
   *
   * @param studentId UUID of the student to remove
   */
  public void removeStudent(UUID studentId) {
    listOfAdvisees.remove(studentId);
  }

  /**
   * used to search a Student by uscID
   *
   * @param studentId UUID Student's uscID
   * @param userList  UserList list of all users
   * @return Student
   */
  public Student getStudentByAdvisor(UUID studentId, UserList userList) {
    if (this.getUserType().equals("ADVISOR")) {
      ArrayList<UUID> adviseeIds = this.getListOfAdvisees();
      if (adviseeIds.contains(studentId)) {
        User studentUser = userList.getUserbyUSCID(studentId);
        if (
          studentUser != null && studentUser.getUserType().equals("STUDENT")
        ) {
          return (Student) studentUser;
        }
      }
    }
    return null;
  }

  /**
   * adds a Note to Student being advised
   *
   * @param studentId   UUID Student's uscID
   * @param noteContent String Advisor's note(s) to advisee
   * @param userList    UserList list of all users
   */
  public void addNoteToStudentAdvisor(
    UUID studentId,
    String noteContent,
    UserList userList
  ) {
    Student student = getStudentByAdvisor(studentId, userList);
    if (student != null) {
      Note newNote = new Note(noteContent, new Date());
      student.getAdvisorNotes().add(newNote);
    } else {
      System.out.println("Student not found or not advisee of the advisor.");
    }
  }

  /**
   * adds a Student to ListOfAdvisees
   *
   * @param studentId UUID Student's uscID
   * @return boolean to indicate of action was successful
   */
  public boolean addStudentToListOfAdvisees(UUID studentId) {
    if (!getListOfAdvisees().contains(studentId)) {
      getListOfAdvisees().add(studentId);
      return true;
    }
    return false;
  }

  /**
   * Allows advisor to search for a student
   *
   * @param uscID    uscID for the specific student
   * @param userList UserList containing all users
   * @return Student instance if found, otherwise null
   */
  public Student studentLookUp(String uscID, UserList userList) {
    UUID studentUUID = UUID.fromString(uscID);
    return userList.getStudentById(studentUUID);
  }

  /**
   * Allows advisor to see if a student is failing
   *
   * @param gpa    GPA of the student
   * @param minGPA minumum GPA requirement for passing
   * @return boolean of to recognize pass/fail
   */
  public boolean checkStudentFailStatus(double gpa, double minGPA) {
    if (gpa < minGPA) {
      System.out.println("Failing");
    } else {
      System.out.println("Passing");
    }
    return gpa < minGPA;
  }

  /**
   * Adds students at risk of failure to the list and returns them.
   *
   * @param userList   The UserList instance containing all users.
   * @param minimumGPA The minimum GPA considered failing.
   * @return The list of students at risk of failure.
   */
  public ArrayList<Student> addStudentRiskOfFailure(
    UserList userList,
    double minimumGPA
  ) {
    ArrayList<Student> adviseesAtRisk = new ArrayList<>();

    if (listOfAdvisees != null) {
      for (UUID studentId : listOfAdvisees) {
        Student student = userList.getStudentById(studentId);
        if (student != null && student.getGpa() < minimumGPA) {
          adviseesAtRisk.add(student);
        }
      }
    }

    return adviseesAtRisk;
  }

  /**
   * Getter for listOfAdvisees
   *
   * @return ArrayList of advisees under the advisor
   */
  public ArrayList<UUID> getListOfAdvisees() {
    return listOfAdvisees;
  }

  /**
   * Getter for listOfFailingStudents
   *
   * @return ArrayList of failing students under the advisor
   */
  public ArrayList<UUID> getListOfFailingStudents() {
    return listOfFailingStudents;
  }

  /**
   * To string to view user details
   *
   * @return the String format of the user details
   *
   * @author @Spillmag
   */
  public String toString() {
    return (
      "\n********* ADVISOR PROFILE *********\n" +
      super.toString() +
      "\nList of Advisees: " +
      listOfAdvisees +
      "\nList of Failing Students: " +
      listOfFailingStudents
    );
  }
}
