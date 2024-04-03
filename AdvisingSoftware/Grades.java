package AdvisingSoftware;

/**
 * Creates the Grades
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8)
 */
public class Grades {
    /**
     * Attributes
     */
    private double grade;
    private String courseName;

    /**
     * Constructor for Grades
     * 
     * @param course The course
     * @param grade  The grade for that course
     */
    public Grades(String course, double grade) {
        this.grade = grade;
        courseName = course;
    }

    /**
     * Get the grade
     * 
     * @return The grade received
     */
    public Double getGrade() {
        if (grade > 0) {
            return this.grade;
        } else {
            return null;
        }
    }

    /**
     * Gets the name of the course
     * 
     * @return The name of the course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the grade
     * 
     * @param grade The grade recieved for a class
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Sets the course name
     * 
     * @param course The name of the course
     */
    public void setCourseName(String course) {
        this.courseName = course;
    }

    /**
 * Checks if grade is passing or failing
 * @param courseGrade the grade received
 * @return "PASS" if grade is passing, "FAIL" if grade is failing, "INVALID" if grade is null
 */
public String checkPass(Double courseGrade) {
    if (courseGrade == null) {
        return "INVALID"; // Handle null input as invalid
    }

    if (courseGrade < 0) {
        return "INVALID"; // Handle negative input as invalid
    }

    boolean gradeDPlus = (courseGrade <= 65 && courseGrade >= 69.99);
    boolean gradeD = (courseGrade <= 60 && courseGrade >= 64.99);
    boolean gradeF = courseGrade < 59.99;

    if (gradeDPlus || gradeD || gradeF) {
        return "FAIL";
    } else {
        return "PASS";
    }
}

    /**
     * Displays the course and the grade and if the course is passing or failing
     * 
     * @return The course and the grade
     */
    public String toString() {
        return courseName + ": " + grade + "(" + checkPass(grade) + ")";
    }

    /**
     * Testing purposes
     * @param args
     */
    public static void main(String[] args) {
        String courseName = "MATH 142";
        double grade = 45.2;
        Grades g = new Grades(courseName, grade);

        System.out.println(g.toString());

    }
}
