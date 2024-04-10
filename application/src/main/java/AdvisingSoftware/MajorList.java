/**
 * MajorList class for managing majors
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
package AdvisingSoftware;

import java.util.ArrayList;
import java.util.UUID;

public class MajorList {
    /**
     * Attributes
     */
    private static MajorList majorList;
    private ArrayList<MajorMap> majors;
    private boolean loaded;

    /**
     * MajorList constructor
     */
    private MajorList() {
        majors = new ArrayList<>();
        loaded = false;
    }

    /**
     * Gets the list of majors
     * 
     * @return the majors
     */
    public ArrayList<MajorMap> getMajors() {
        return majors;
    }

    /**
     * Gets an static instance of the MajorList
     * 
     * @return the majorlist
     */
    public static MajorList getInstance() {
        if (majorList == null) {
            majorList = new MajorList();
            if (!majorList.isLoaded()) {
                ArrayList<MajorMap> majorData = DataLoader.loadMajors();
                for (MajorMap majorInData : majorData) {
                    majorList.addMajor(
                            majorInData.getMajor(),
                            majorInData.getSemester1(),
                            majorInData.getSemester2(),
                            majorInData.getSemester3(),
                            majorInData.getSemester4(),
                            majorInData.getSemester5(),
                            majorInData.getSemester6(),
                            majorInData.getSemester7(),
                            majorInData.getSemester8(),
                            majorInData.getMinTotalHours(),
                            majorInData.getMinGradHours(),
                            majorInData.getCaroCoreHours(),
                            majorInData.getMinGPA());
                }
                majorList.setLoaded(true);
            }
        }
        return majorList;
    }

/**
 * Gets the major by name (case insensitive and trim whitespace)
 *
 * @param major the major
 * @return the major
 */
public MajorMap getMajorByName(String major) {
    if (major == null) {
        return null;
    }
    
    for (MajorMap existingMajor : majors) {
        /*
         * Triming whitespace
         */
        if (existingMajor.getMajor().trim().equalsIgnoreCase(major.trim())) {
            return existingMajor;
        }
    }
    return null;
}


    /**
     * Gets the major by it's unique identifier
     * 
     * @param id the id
     * @return the major
     */
    public MajorMap getMajorMapById(UUID id) {
        for (MajorMap existingMajor : majors) {
            if (existingMajor.getId().equals(id)) {
                return existingMajor;
            }
        }
        return null;
    }

/**
 * Adds a major with their semester listings and graduation requirements
 * 
 * @param majorName     name of major
 * @param semester1     first semester
 * @param semester2     second semester
 * @param semester3     third semester
 * @param semester4     fourth semester
 * @param semester5     fifth semester
 * @param semester6     sixth semester
 * @param semester7     seventh semester
 * @param semester8     eighth semester
 * @param minTotalHours minimum total credit hours
 * @param minGradHours  minimum grad hours
 * @param caroCoreHours minimum carolina core hours
 * @param minGPA        minimum GPA
 */
public void addMajor(
        String majorName,
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
        double minGPA) {

    String lowercaseMajorName = majorName.toLowerCase();

    for (MajorMap existingMajor : majors) {
        if (existingMajor.getMajor().toLowerCase().equals(lowercaseMajorName)) {
            System.out.println("A major with the same name already exists.");
            return;
        }
    }
    MajorMap newMajorMap = new MajorMap(
            majorName,
            semester1,
            semester2,
            semester3,
            semester4,
            semester5,
            semester6,
            semester7,
            semester8,
            minTotalHours,
            minGradHours,
            caroCoreHours,
            minGPA);
    majors.add(newMajorMap);
}

    /**
     * Removes a Major
     * 
     * @param major the major
     */
    public void removeMajor(String major) {
        for (MajorMap existingMajor : majors) {
            if (existingMajor.getMajor().equals(major)) {
                majors.remove(existingMajor);
                return;
            }
        }
        System.out.println(major + " does not exist.");
    }

    /**
     * Sets if the major is loaded or not
     * 
     * @param loaded true or false
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * Checks if the major is loaded or not
     * 
     * @return true if major is loaded, false if major is not loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Clears all courses from the course list.
     */
    public void clear() {
        majors.clear();
    }
     /**
     * Gets the names of every major.
     *
     * @return List of major names
     */
    public ArrayList<String> getAllMajorNames() {
        ArrayList<String> majorNames = new ArrayList<>();
        for (MajorMap major : majors) {
            majorNames.add(major.getMajor());
        }
        return majorNames;
    }

}