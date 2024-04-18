package envisage;

/**
 * The MajorNameStore class is a singleton class used to store and retrieve a single major name.
 * Only one instance of this class can exist throughout the runtime of the application.
 * @author Row 18
 */
public class MajorNameStore {

    private static MajorNameStore instance = new MajorNameStore();
    private String majorName;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private MajorNameStore() {}

    /**
     * Returns the singleton instance of the MajorNameStore class.
     * @return The singleton instance of MajorNameStore.
     */
    public static MajorNameStore getInstance() {
        return instance;
    }

    /**
     * Retrieves the currently stored major name.
     * @return The major name.
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * Sets the major name to be stored.
     * @param majorName The major name to be stored.
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}