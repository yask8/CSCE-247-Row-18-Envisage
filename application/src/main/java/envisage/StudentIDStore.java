package envisage;

import java.util.UUID;

public class StudentIDStore {
    private static StudentIDStore instance;
    private UUID studentID;

    private StudentIDStore() {}

    public static StudentIDStore getInstance() {
        if (instance == null) {
            instance = new StudentIDStore();
        }
        return instance;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public UUID getStudentID() {
        return studentID;
    }
}