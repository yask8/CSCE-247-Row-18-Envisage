package AdvisingSoftware;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The DataLoader class provides methods to load data from JSON files into Java
 * objects.
 * 
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8), Owen Shumate (@oshumate)
 */
public class DataLoader extends DataConstants {
    /**
     * Loads users (students, admins, advisors) and puts them into a single
     * list.
     *
     * @return An ArrayList of User objects containing instances of user types.
     */
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        ArrayList<Student> students = loadStudents();
        if (students != null) {
            users.addAll(students);
        }

        ArrayList<Admin> admins = loadAdmin();
        if (admins != null) {
            users.addAll(admins);
        }

        ArrayList<Advisor> advisors = loadAdvisors();
        if (advisors != null) {
            users.addAll(advisors);
        }

        return users;
    }

    /**
     * Loads students from a JSON file.
     * 
     * @return An ArrayList of Student objects loaded from the JSON file.
     */
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < studentArray.size(); i++) {
                JSONObject studentObj = (JSONObject) studentArray.get(i);
                String firstName = (String) studentObj.get(USER_FIRST_NAME);
                String lastName = (String) studentObj.get(USER_LAST_NAME);
                String email = (String) studentObj.get(USER_EMAIL);
                String uscIDString = (String) studentObj.get(USER_USCID);
                UUID uscID = UUID.fromString(uscIDString);
                String password = (String) studentObj.get(USER_PASSWORD);
                String userType = (String) studentObj.get(USER_TYPE);
                String year = (String) studentObj.get(STUDENT_CLASS);
                String major = (String) studentObj.get(STUDENT_MAJOR);
                String applicationArea = (String) studentObj.get(STUDENT_APP_AREA);
                int creditHours = ((Long) studentObj.get(STUDENT_CREDITHOURS)).intValue();
                JSONArray completedCoursesJSON = (JSONArray) studentObj.get(STUDENT_COMPLETED_COURSES);
                ArrayList<Grades> completedCourses = new ArrayList<Grades>();
                if (completedCoursesJSON != null) {
                    for (Object gradeObj : completedCoursesJSON) {
                        JSONObject gradeJSON = (JSONObject) gradeObj;
                        String courseName = (String) gradeJSON.get(GRADES_COURSE_NAME);
                        double grade = ((Number) gradeJSON.get(GRADES_GRADE)).doubleValue();
                        completedCourses.add(new Grades(courseName, grade));
                    }
                }
                double gpa = (double) studentObj.get(STUDENT_GPA);

                JSONObject coursePlannerObj = (JSONObject) studentObj.get(STUDENT_COURSE_PLANNER);
                CoursePlanner coursePlanner = null;
                if (coursePlannerObj != null) {
                    coursePlanner = loadCoursePlannerFromJSON(coursePlannerObj);
                }

                JSONObject degreeProgressObj = (JSONObject) studentObj.get(STUDENT_DEGREE_PROGRESS);
                DegreeProgress degreeProgress = null;
                if (degreeProgressObj != null) {
                    degreeProgress = loadDegreeProgressFromJSON(degreeProgressObj);
                }

                JSONArray advisorNotesJSON = (JSONArray) studentObj.get(STUDENT_ADVISOR_NOTES);
                ArrayList<Note> advisorNotes = new ArrayList<>();
                if (advisorNotesJSON != null) {
                    for (Object noteObj : advisorNotesJSON) {
                        JSONObject noteJSON = (JSONObject) noteObj;
                        String note = (String) noteJSON.get(NOTE_NOTE);
                        String dateString = (String) noteJSON.get(NOTE_DATE);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateFormat.parse(dateString);
                        advisorNotes.add(new Note(note, date));
                    }
                }

                Student student = new Student(firstName, lastName, email, uscID, password, userType, year,
                        major, applicationArea, creditHours, completedCourses, gpa, coursePlanner, degreeProgress,
                        advisorNotes);
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads degree progress from a JSON object.
     * 
     * @param degreeProgressObj The JSON object containing degree progress data.
     * @return The DegreeProgress object loaded from the JSON object, or null if the
     *         input object is null.
     */
    private static DegreeProgress loadDegreeProgressFromJSON(JSONObject degreeProgressObj) {
        if (degreeProgressObj == null) {
            return null;
        }
        String major = (String) degreeProgressObj.get("major");
        ArrayList<String> completeCourses = loadStringsFromJSONArray(
                (JSONArray) degreeProgressObj.get(DEGREE_PROGRESS_COMPLETE_COURSES));
        ArrayList<String> incompleteCourses = loadStringsFromJSONArray(
                (JSONArray) degreeProgressObj.get(DEGREE_PROGRESS_INCOMPLETE_COURSES));

        DegreeProgress degreeProgress = new DegreeProgress(major, completeCourses, incompleteCourses);

        return degreeProgress;
    }

    /**
     * Loads a course planner from a JSON object.
     * 
     * @param coursePlannerObj The JSON object containing course planner data.
     * @return The CoursePlanner object loaded from the JSON object, or null if the
     *         input object is null.
     */
    private static CoursePlanner loadCoursePlannerFromJSON(JSONObject coursePlannerObj) {
        if (coursePlannerObj == null) {
            return null;
        }

        CoursePlanner coursePlanner = new CoursePlanner();
        JSONArray semestersArray = (JSONArray) coursePlannerObj.get(COURSE_PLANNER_SEMESTERS);

        for (int i = 0; i < semestersArray.size(); i++) {
            JSONArray semesterCoursesArray = (JSONArray) semestersArray.get(i);

            for (int j = 0; j < semesterCoursesArray.size(); j++) {
                String courseName = (String) semesterCoursesArray.get(j);
                coursePlanner.addCourse(i + 1, courseName);
            }
        }

        return coursePlanner;
    }

    /**
     * Loads admins from a JSON file.
     * 
     * @return An ArrayList of Admin objects loaded from the JSON file.
     */
    public static ArrayList<Admin> loadAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();

        try {
            FileReader reader = new FileReader(ADMIN_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray adminJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < adminJSON.size(); i++) {
                JSONObject adminOBJ = (JSONObject) adminJSON.get(i);
                String firstName = (String) adminOBJ.get(USER_FIRST_NAME);
                String lastName = (String) adminOBJ.get(USER_LAST_NAME);
                String email = (String) adminOBJ.get(USER_EMAIL);
                String uscIDString = (String) adminOBJ.get(USER_USCID);
                UUID uscID = UUID.fromString(uscIDString);
                String password = (String) adminOBJ.get(USER_PASSWORD);
                String userType = (String) adminOBJ.get(USER_TYPE);

                JSONArray changesMadeJSON = (JSONArray) adminOBJ.get(ADMIN_CHANGES_MADE);
                ArrayList<String> changesMade = new ArrayList<>();
                if (changesMadeJSON != null) {
                    for (Object changeObj : changesMadeJSON) {
                        String change = (String) changeObj;
                        changesMade.add(change);
                    }
                }

                admins.add(new Admin(firstName, lastName, email, uscID, password, userType, changesMade));
            }

            return admins;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Loads advisors from a JSON file.
     * 
     * @return An ArrayList of Advisor objects loaded from the JSON file.
     */
    public static ArrayList<Advisor> loadAdvisors() {
        ArrayList<Advisor> advisors = new ArrayList<>();

        try {
            FileReader reader = new FileReader(ADVISOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorJSON = (JSONArray) parser.parse(reader);

            for (Object obj : advisorJSON) {
                JSONObject advisorOBJ = (JSONObject) obj;
                String firstName = (String) advisorOBJ.get(USER_FIRST_NAME);
                String lastName = (String) advisorOBJ.get(USER_LAST_NAME);
                String email = (String) advisorOBJ.get(USER_EMAIL);
                String uscIDString = (String) advisorOBJ.get(USER_USCID);
                UUID uscID = UUID.fromString(uscIDString);
                String password = (String) advisorOBJ.get(USER_PASSWORD);
                String userType = (String) advisorOBJ.get(USER_TYPE);

                ArrayList<UUID> listOfAdvisees = new ArrayList<>();
                JSONArray adviseesJSON = (JSONArray) advisorOBJ.get(ADVISOR_LIST_OF_ADVISEES);
                for (Object adviseeObj : adviseesJSON) {
                    String adviseeUUIDString = (String) adviseeObj;
                    UUID adviseeUUID = UUID.fromString(adviseeUUIDString);
                    listOfAdvisees.add(adviseeUUID);
                }

                ArrayList<UUID> listOfFailingStudents = new ArrayList<>();
                JSONArray failingStudentsJSON = (JSONArray) advisorOBJ.get(ADVISOR_LIST_OF_FAILING_STUDENTS);
                for (Object failingStudentObj : failingStudentsJSON) {
                    String failingStudentUUIDString = (String) failingStudentObj;
                    UUID failingStudentUUID = UUID.fromString(failingStudentUUIDString);
                    listOfFailingStudents.add(failingStudentUUID);
                }

                advisors.add(new Advisor(firstName, lastName, email, uscID, password, userType, listOfAdvisees,
                        listOfFailingStudents));
            }

            return advisors;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Course> loadCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray courseJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < courseJSON.size(); i++) {
                JSONObject courseObj = (JSONObject) courseJSON.get(i);
                String id = (String) courseObj.get(COURSE_ID);
                String name = (String) courseObj.get(COURSE_NAME);
                String code = (String) courseObj.get(COURSE_CODE);
                String description = (String) courseObj.get(COURSE_DESCRIPTION);
                int creditHours = ((Long) courseObj.get(COURSE_CREDIT_HOURS)).intValue();
                String subject = (String) courseObj.get(COURSE_SUBJECT);
                char passGrade = ((String) courseObj.get(COURSE_PASS_GRADE)).charAt(0);
                boolean elective = (Boolean) courseObj.get(COURSE_ELECTIVE);
                boolean carolinaCore = (Boolean) courseObj.get(COURSE_CAROLINA_CORE);
                JSONArray prerequisitesArray = (JSONArray) courseObj.get(COURSE_PREREQUISITES);
                ArrayList<String> prerequisites = new ArrayList<>();
                if (prerequisitesArray != null) {
                    for (Object prerequisite : prerequisitesArray) {
                        if (prerequisite instanceof String) {
                            prerequisites.add((String) prerequisite);
                        }
                    }
                }
                String semester = (String) courseObj.get(COURSE_SEMESTER);
                String year = (String) courseObj.get(COURSE_YEAR);

                Course course = new Course(id, name, code, description, creditHours, subject, passGrade, elective,
                        carolinaCore, prerequisites, semester, year);
                courses.add(course);
            }

            return courses;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Loads majors from a JSON file.
     *
     * @return An ArrayList of MajorMap objects loaded from the JSON file
     */
    public static ArrayList<MajorMap> loadMajors() {
        ArrayList<MajorMap> majors = new ArrayList<>();

        try {
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray majorJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < majorJSON.size(); i++) {
                JSONObject majorObj = (JSONObject) majorJSON.get(i);
                String name = (String) majorObj.get(MAJOR_NAME);

                UUID id = UUID.fromString((String) majorObj.get(MAJOR_UUID));
                ArrayList<String> semester1 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_1));
                ArrayList<String> semester2 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_2));
                ArrayList<String> semester3 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_3));
                ArrayList<String> semester4 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_4));
                ArrayList<String> semester5 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_5));
                ArrayList<String> semester6 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_6));
                ArrayList<String> semester7 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_7));
                ArrayList<String> semester8 = loadStringsFromJSONArray((JSONArray) majorObj.get(MAJOR_SEMESTER_8));
                int minHours = ((Long) majorObj.get(MAJOR_MIN_HOURS)).intValue();
                int minGradHours = ((Long) majorObj.get(MAJOR_MIN_GRAD_HOURS)).intValue();
                int ccHours = ((Long) majorObj.get(MAJOR_CC_HOURS)).intValue();
                double minGPA = ((Number) majorObj.get(MAJOR_MIN_GPA)).doubleValue();

                MajorMap major = new MajorMap(id, name, semester1, semester2, semester3, semester4, semester5,
                        semester6, semester7, semester8, minHours, minGradHours, ccHours, minGPA);
                majors.add(major);
            }
            return majors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to load strings from a JSONArray.
     * 
     * @param jsonArray The JSONArray containing strings.
     * @return An ArrayList of strings.
     */
    private static ArrayList<String> loadStringsFromJSONArray(JSONArray jsonArray) {
        ArrayList<String> strings = new ArrayList<>();
        if (jsonArray != null) {
            for (Object obj : jsonArray) {
                strings.add((String) obj);
            }
        }
        return strings;
    }
}