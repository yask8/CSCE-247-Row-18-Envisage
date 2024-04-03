package AdvisingSoftware;

import java.util.ArrayList;
import java.util.UUID;

/**
 * UserList class for managing users.
 * This class implements the Singleton design pattern.
 *
 * @author @Spillmag
 */
public class UserList {

  private static UserList userList;
  private ArrayList<User> users;
  private boolean loaded;

  private UserList() {
    users = new ArrayList<>();
    loaded = false;
  }

  public static UserList getInstance() {
    if (userList == null) {
      userList = new UserList();
      if (!userList.isLoaded()) {
        ArrayList<User> userData = DataLoader.loadUsers();
        if (userData != null) {
          userList.users.addAll(userData);
        } else {
          System.out.println("Failed to load users from DataLoader.");
        }
      }
    }
    return userList;
  }

  /**
   * Get a user by their USC ID.
   *
   * @param uscID The USC ID of the user.
   * @return The user with the specified USC ID
   */
  public User getUserbyUSCID(UUID uscID) {
    for (User user : users) {
      if (user.getID().equals(uscID)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Get a user by their email and password.
   *
   * @param email    The email of the user.
   * @param password The password of the user.
   * @return The user with the specified email and password.
   */
  public User getUserByLoginInfo(String email, String password) {
    for (User user : users) {
      if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Add a new student to the list.
   *
   * @param firstName The first name of the student.
   * @param lastName  The last name of the student.
   * @param email     The email of the student.
   * @param uscID     The USC ID of the student.
   * @param password  The password of the student.
   * @param userType  The type of the user "STUDENT"
   * @param year      The student's academic year.
   * @param major     The student's major.
   */
  public void addStudent(
      String firstName,
      String lastName,
      String email,
      UUID uscID,
      String password,
      String userType,
      String year,
      String major,
      String applicationArea) {
    Student student = new Student(
        firstName,
        lastName,
        email,
        uscID,
        password,
        userType,
        year,
        major,
        applicationArea,
        0,
        null,
        0,
        null,
        null,
        null);
    users.add(student);
  }

  /**
   * Add a new admin to the list.
   *
   * @param firstName The first name of the admin.
   * @param lastName  The last name of the admin.
   * @param email     The email of the admin.
   * @param uscID     The USC ID of the admin.
   * @param password  The password of the admin.
   * @param userType  The type of the user "ADMIN"
   */
  public void addAdmin(
      String firstName,
      String lastName,
      String email,
      UUID uscID,
      String password,
      String userType) {
    Admin admin = new Admin(
        firstName,
        lastName,
        email,
        uscID,
        password,
        userType,
        null);
    users.add(admin);
  }

  /**
   * Add a new advisor to the list.
   *
   * @param firstName The first name of the advisor.
   * @param lastName  The last name of the advisor.
   * @param email     The email of the advisor.
   * @param uscID     The USC ID of the advisor.
   * @param password  The password of the advisor.
   * @param userType  The type of the user "ADVISOR"
   */
  public void addAdvisor(
      String firstName,
      String lastName,
      String email,
      UUID uscID,
      String password,
      String userType) {
    Advisor advisor = new Advisor(
        firstName,
        lastName,
        email,
        uscID,
        password,
        userType,
        null,
        null);
    users.add(advisor);
  }

  /**
   * Add a new user to the list.
   *
   * @param user The user object to add.
   */
  public void addUser(User user) {
    users.add(user);
  }

  /**
   * Remove a user from the list by their USC ID.
   *
   * @param uscID The USC ID of the user to be removed.
   */
  public void removeUser(UUID uscID) {
    for (int i = 0; i < users.size(); i++) {
      User user = users.get(i);
      if (user.getID().equals(uscID)) {
        users.remove(i);
        break;
      }
    }
  }

  /**
   * Get all users in the list.
   *
   * @return The list of users.
   */
  public ArrayList<User> getUsers() {
    return users;
  }

  /**
   * Sets the flag indicating whether userList is loaded with data.
   *
   * @param loaded Boolean indicating if userList is loaded
   */
  public void setLoaded(boolean loaded) {
    this.loaded = loaded;
  }

  /**
   * Checks if userList is loaded.
   *
   * @return True if userList is loaded, false otherwise
   */
  public boolean isLoaded() {
    return loaded;
  }

  /**
   * Checks if the email already exists in the user databse.
   *
   * @param email The email address to check for.
   * @return true if the email exists in the database, false otherwise.
   */
  public boolean emailExists(String email) {
    for (User user : users) {
      if (user.getEmail().equalsIgnoreCase(email)) {
        return true;
      }
    }
    return false;
  }

 
  /**
   * Signs up a new user.
   *
   * @param firstName The first name of the user.
   * @param lastName  The last name of the user.
   * @param email     The email of the user.
   * @param password  The password of the user.
   * @param userType  The type of user ('STUDENT', 'ADMIN', or 'ADVISOR').
   * @return true if the sign-up process is successful, false otherwise.
   */
  public boolean signUp(
      String firstName,
      String lastName,
      String email,
      String password,
      String userType) {

    if (emailExists(email)) {
      System.out.println("Sign up failed. Email already exists. Please choose a different email.");
      return false;
    }

    UUID uscID = UUID.randomUUID();

    User newUser = null;
    if (userType.equals("STUDENT")) {
      newUser = new Student(firstName, lastName, email, uscID, password, userType, "Freshman", "Undeclared",
          "Undeclared", 0, new ArrayList<Grades>(), 0.0, new CoursePlanner(),
          new DegreeProgress("Undeclared", new ArrayList<String>(), new ArrayList<String>()),
          new ArrayList<Note>());
    } else if (userType.equals("ADMIN")) {
      newUser = new Admin(firstName, lastName, email, uscID, password, userType, new ArrayList<String>());
    } else if (userType.equals("ADVISOR")) {
      newUser = new Advisor(firstName, lastName, email, uscID, password, userType, new ArrayList<UUID>(),
          new ArrayList<UUID>());
    } else {
      System.out.println("Sign up failed. Invalid user type. Please specify either 'STUDENT', 'ADMIN', or 'ADVISOR'.");
      return false;
    }

    addUser(newUser);
    System.out.println("Sign up successful! New profile:");
    System.out.println(newUser.toString());
    return true;
  }

  /**
   * Gets the user's uscID by their first and last name
   * 
   * @author Yasmine Kennedy (yask8)
   * @param first user's first name
   * @param last  user's last name
   * @return the user's uscID
   */
  public UUID getIDByName(String first, String last) {
    for (User user : users) {
      if (user.getFirstName().equals(first) && user.getLastName().equals(last)) {
        return user.getID();
      }
    }
    return null;
  }

  public Advisor getAdvisorById(UUID advisorId) {
    User user = getUserbyUSCID(advisorId);
    if (user != null && user.getUserType().equals("ADVISOR")) {
      return (Advisor) user;
    }
    return null;
  }

  public Student getStudentById(UUID studentId) {
    User user = getUserbyUSCID(studentId);
    if (user != null && user.getUserType().equals("STUDENT")) {
      return (Student) user;
    }
    return null;
  }

  public Admin getAdminById(UUID adminId) {
    User user = getUserbyUSCID(adminId);
    if (user != null && user.getUserType().equals("ADMIN")) {
      return (Admin) user;
    }
    return null;
  }

  /**
   * Clears all courses from the course list.
   */
  public void clear() {
    users.clear();
  }

}
