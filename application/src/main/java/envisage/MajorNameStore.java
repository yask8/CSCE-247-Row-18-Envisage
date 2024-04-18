package envisage;

public class MajorNameStore {
    private static MajorNameStore instance = new MajorNameStore();
    private String majorName;

    private MajorNameStore() {}

    public static MajorNameStore getInstance() {
        return instance;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}