package envisage;
/**
 * Controller for the Frequently Asked Questions (FAQ) Screen
 * @author Row 18
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FAQController implements Initializable {

    // FXML Injected Variables
    @FXML
    private Label AboutLabel;

    @FXML
    private Label FAQlabel;

    @FXML
    private Label UniversityPartnersLabel;

    @FXML
    private Label faqTitleLabel;

    @FXML
    private TreeView<String> faqTree;

    @FXML
    private Label loginLabel;

    /**
     * String Section - Student User Question Edition
     */
    String registerforClasses = 
            "Students can not register for classes on Envisage" +
            " " + "unless stated otherwise by their institution.";
    String contactAdvisor = 
            "Envisage currently does not offer a chat feature" + 
            " " + "for users to communicate with each other.";
    String forgotPassword =
            "Please click the \"Forgot Password\" link on your" +
            " " + "designated login screen and follow the instructions.";
    String forgotOthers = 
            "Please contact your institution's administrator or advisor.";
    String viewingGrades =
            "All grades can be visibile on your \"Student Profile\"" +
            " " + "under \"Completed Courses\"";
    String declareMajor = 
            "To declare your major, go to your \"Student Profile\"" + 
            " " + "and click on the \"Declare a Major\" button." +
            " " + "Follow the instructions provided.";
    String deleteAccount = 
            "Please Contact your institution's administrator.";
    String parentAccess = 
            "Envisage does not offer parent access. However, some" + 
            " " + "institutions may have that featue, so please" + 
            " " + "contact your institution's administrator.";
    String classmatesViewGrades = 
            "All accounts are private; only advisors and administrators" +
            " " + "can view your grades.";
    String studentPay = 
            "Envisage does not require a payment when creating a student account.";
    String transferringAccount = 
            "Accounts can be transferred if the insititution is a university partner" +
            " " + "with Envisage. If so, please contact your current institution's administrator" + 
            " " + "and let them know you would like to do a school transfer. This process is also the" + 
            " " + "same for any K-12 students.";
    String graduatingAccount = 
            "Envisage keeps accounts for an infinite amount of time. However, institutions can" + 
            " " + "change the account timeframe, so please check with your insitution's administrator" + 
            " " + "BEFORE graduating.";
    String shareCoursePlanner = 
            "A feature in Settings, \"Share,\" allows you to share any planner version" +
            " " + "with your peers, but it might be disabled/hidden by some institutions.";
     
    /**
     * String Section - Software Questions Edition
     */
    String freeSoftwareA = 
            "The software is not free for institutions. However, institutions" + 
            " " + "can pay a set price per student or enroll in a monthly subscription.";
    String freeSoftwareB = 
            "If the institution chooses to pay a set price per student, a bill will be " + 
            " " + "sent during the summer of the academic year. This is because students are" + 
            " " + "frequently added and removed, and it is easier to get a final count during the" +
            " " + "summer month of how many students were enrolled at the institution.";
    String freeSoftwareC = 
            "If the institution decides to enroll in a monthly subscription," + 
            " " + "they will be billed an overall price that is not based on the number" + 
            " " + "of students but a fixed price.";
    String userPay = 
            "Payment is not required for any user when creating or logging in to"+
            " " + "an account.";
    String ad= 
            "There are no ads on the default website unless changed by the institution.";
    String studentLimit = 
            "There are no student limits for any institution. Envisage can store and" +
            " " + "manage an endless amount of students.";
    String modifiableCode = 
            "Yes! We aim for a personalizable experience with our software.";
    String internationalInstitution = 
            "Our software can be used and modified to fit foregin languages or foreign institutions.";
    String contactCustomerSupport = 
            "We offer support 24/7. Call us at (803)247 - 3618 or email us at" +
            " " + "customer-support@envisage.edu.";

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up the FAQ tree commonly asked questions about Envisage.
     */
    @Override
    public void initialize(URL url, ResourceBundle arg1) {
        TreeItem<String> envisageQuestions = new TreeItem<>("Envisage Questions");
        TreeItem<String> studentQuestions = new TreeItem<>("Student Questions");
        TreeItem<String> softwareQuestions = new TreeItem<>("Software Questions");
        faqTree.setRoot(envisageQuestions);
        envisageQuestions.getChildren().add(studentQuestions);
        envisageQuestions.getChildren().add(softwareQuestions);

        TreeItem<String> registeringClassesQuestion = new TreeItem<>("Can I register for classes on Envisage?");
        studentQuestions.getChildren().add(registeringClassesQuestion);
        TreeItem<String> registerforClassesResponse = new TreeItem<>(registerforClasses);
        registeringClassesQuestion.getChildren().add(registerforClassesResponse);

        TreeItem<String> contactingAdvisorQuestion = new TreeItem<>("How do I contact my advisor through Envisage?");
        studentQuestions.getChildren().add(contactingAdvisorQuestion);
        TreeItem<String> contactingAdvisorResponse = new TreeItem<>(contactAdvisor);
        contactingAdvisorQuestion.getChildren().add(contactingAdvisorResponse);

        TreeItem<String> forgotPasswordQuestion = new TreeItem<>("I forgot my password.");
        studentQuestions.getChildren().add(forgotPasswordQuestion);
        TreeItem<String> forgotPasswordResponse = new TreeItem<>(forgotPassword);
        forgotPasswordQuestion.getChildren().add(forgotPasswordResponse);

        TreeItem<String> forgotAllThreeQuestion = new TreeItem<>("I forgot my email, password, and school ID.");
        studentQuestions.getChildren().add(forgotAllThreeQuestion);
        TreeItem<String> forgotAllThreeResponse = new TreeItem<>(forgotOthers);
        forgotAllThreeQuestion.getChildren().add(forgotAllThreeResponse);

        TreeItem<String> viewingGradesQuestion = new TreeItem<>("Can I see my grades?");
        studentQuestions.getChildren().add(viewingGradesQuestion);
        TreeItem<String> viewingGradesResponse = new TreeItem<>(viewingGrades);
        viewingGradesQuestion.getChildren().add(viewingGradesResponse);

        TreeItem<String> declareMajorQuestion = new TreeItem<>("Can I declare my major through Envisage?");
        studentQuestions.getChildren().add(declareMajorQuestion);
        TreeItem<String> declareMajorResponse = new TreeItem<>(declareMajor);
        declareMajorQuestion.getChildren().add(declareMajorResponse);

        TreeItem<String> deleteAccountQuestion = new TreeItem<>("How do I delete my account?");
        studentQuestions.getChildren().add(deleteAccountQuestion);
        TreeItem<String> deleteAccountResponse = new TreeItem<>(deleteAccount);
        deleteAccountQuestion.getChildren().add(deleteAccountResponse);

        TreeItem<String> parentAccessQuestion = new TreeItem<>("I want to give my parents access. How do I do that?");
        studentQuestions.getChildren().add(parentAccessQuestion);
        TreeItem<String> parentAccessResponse = new TreeItem<>(parentAccess);
        parentAccessQuestion.getChildren().add(parentAccessResponse);

        TreeItem<String> gradePrivacyQuestion = new TreeItem<>("Can my classmates see my grades?");
        studentQuestions.getChildren().add(gradePrivacyQuestion);
        TreeItem<String> gradePrivacyResponse = new TreeItem<>(classmatesViewGrades);
        gradePrivacyQuestion.getChildren().add(gradePrivacyResponse);

        TreeItem<String> studentPayQuestion = new TreeItem<>("Do Students have to pay?");
        studentQuestions.getChildren().add(studentPayQuestion);
        TreeItem<String> studentPayResponse = new TreeItem<>(studentPay);
        studentPayQuestion.getChildren().add(studentPayResponse);

        TreeItem<String> graduatingAccountQuestion = new TreeItem<>("I am graduating. How long will my account be available?");
        studentQuestions.getChildren().add(graduatingAccountQuestion);
        TreeItem<String> graduatingAccountResponse = new TreeItem<>(graduatingAccount);
        graduatingAccountQuestion.getChildren().add(graduatingAccountResponse);

        TreeItem<String> transferringAccountQuestion = new TreeItem<>("I am transferring. Can I transfer my account to my new instituton?");
        studentQuestions.getChildren().add(transferringAccountQuestion);
        TreeItem<String> transferringAccountResponse = new TreeItem<>(transferringAccount);
        transferringAccountQuestion.getChildren().add(transferringAccountResponse);

        TreeItem<String> shareCoursePlannerQuestion = new TreeItem<>("Can I share my course planner with friends?");
        studentQuestions.getChildren().add(shareCoursePlannerQuestion);
        TreeItem<String> shareCoursePlannerResponse = new TreeItem<>(shareCoursePlanner);
        shareCoursePlannerQuestion.getChildren().add(shareCoursePlannerResponse);

        TreeItem<String> freeSoftwareQuestion = new TreeItem<>("Is this software free?");
        softwareQuestions.getChildren().add(freeSoftwareQuestion);
        TreeItem<String> paymentPlans = new TreeItem<>(freeSoftwareA);
        TreeItem<String> payPerStudent = new TreeItem<>(freeSoftwareB);
        TreeItem<String> monthlySubscription = new TreeItem<>(freeSoftwareC);
        freeSoftwareQuestion.getChildren().add(paymentPlans);
        freeSoftwareQuestion.getChildren().add(payPerStudent);
        freeSoftwareQuestion.getChildren().add(monthlySubscription);

        TreeItem<String> userPayQuestion = new TreeItem<>("Do users have to pay?");
        softwareQuestions.getChildren().add(userPayQuestion);
        TreeItem<String> userPayResponse = new TreeItem<>(userPay);
        userPayQuestion.getChildren().add(userPayResponse);

        TreeItem<String> adQuestion = new TreeItem<>("Are there ads?");
        softwareQuestions.getChildren().add(adQuestion);
        TreeItem<String> adResponse = new TreeItem<>(ad);
        adQuestion.getChildren().add(adResponse);

        TreeItem<String> studentLimitQuestion = new TreeItem<>("Is there a student limit?");
        softwareQuestions.getChildren().add(studentLimitQuestion);
        TreeItem<String> studentLimitResponse = new TreeItem<>(studentLimit);
        studentLimitQuestion.getChildren().add(studentLimitResponse);

        TreeItem<String> modifiableCodeQuestion = new TreeItem<>("Can any of the code be modified?");
        softwareQuestions.getChildren().add(modifiableCodeQuestion);
        TreeItem<String> modifiableCodeResponse = new TreeItem<>(modifiableCode);
        modifiableCodeQuestion.getChildren().add(modifiableCodeResponse);

        TreeItem<String> availableInternationalQuestion = new TreeItem<>("Is this software available for international institutions?");
        softwareQuestions.getChildren().add(availableInternationalQuestion);
        TreeItem<String> availableInternationalResponse = new TreeItem<>(internationalInstitution);
        availableInternationalQuestion.getChildren().add(availableInternationalResponse);

        TreeItem<String> contactCustomerSupportQuestion = new TreeItem<>("How to contact Envisage for Customer Support?");
        softwareQuestions.getChildren().add(contactCustomerSupportQuestion);
        TreeItem<String> contactCustomerSupportResponse = new TreeItem<>(contactCustomerSupport);
        contactCustomerSupportQuestion.getChildren().add(contactCustomerSupportResponse);
    }

    /**
     * Sets the stage for the Login screen
     * @param event representing mouse action
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageLogin(MouseEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    /**
     * Sets the stage for the About Screen
     * @param event representing mouse action
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageAbout(MouseEvent event) throws IOException {
        App.setRoot("About");
    }

    /**
     * Sets the stage for the University Partners screen
     * @param event representing mouse action
     * @throws IOException if an I/O error occurs when setting the root scene
     */
    @FXML
    void setStageUniversityPartners(MouseEvent event) throws IOException {
        App.setRoot("universityPartners");
    }

}
