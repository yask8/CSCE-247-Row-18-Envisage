package envisage;

import java.util.UUID;

/**
 * Singleton class for storing the UUID of the currently selected student.
 * This class ensures that only one instance of the StudentIDStore exists throughout
 * the application runtime.
 * 
 * It provides methods for setting and getting the UUID of the student.
 * 
 * @author Garrrett Spillman
 */
public class StudentIDStore {
    private static StudentIDStore instance;
    private UUID studentID;

    private StudentIDStore() {}

    /**
     * Returns the instance of StudentIDStore.
     * If the instance doesn't exist, it creates a new one.
     * 
     * @return The instance of StudentIDStore
     */
    public static StudentIDStore getInstance() {
        if (instance == null) {
            instance = new StudentIDStore();
        }
        return instance;
    }

    /**
     * Sets the UUID of the student.
     * 
     * @param studentID The UUID of the student
     */
    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    /**
     * Retrieves the UUID of the student.
     * 
     * @return The UUID of the student
     */
    public UUID getStudentID() {
        return studentID;
    }
}