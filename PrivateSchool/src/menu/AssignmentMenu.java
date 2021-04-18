package menu;

import dao.AssignmentDaoImplem;
import dao.AssignmentDaoInterf;
import dao.CourseDaoImplem;
import dao.CourseDaoInterf;
import dao.StudentDaoImplem;
import dao.StudentDaoInterf;
import main.MyMain;
import utils.ClassUtils;

/**
 *
 * @author Maria
 */
public class AssignmentMenu extends ClassUtils {

    AssignmentDaoInterf adao = new AssignmentDaoImplem();
    CourseDaoInterf cdao = new CourseDaoImplem();
    StudentDaoInterf sdao = new StudentDaoImplem();

    /**
     * Print all assignments and choices from assignments' menu.
     * Then calls another method which manages the answer for menu with parameter another
     * method which scan the answer from the user.
     */
    public void assignmentMenu() {
        adao.printAllAssignments();
        System.out.println(" Press \'create\' to create a new assignment.");
        System.out.println(" Press \'student\' to add an assignment in a student.");
        System.out.println(" Press \'course\' to add an assignment in a course.");
        System.out.println(" Press \'menu\' to go at the main menu.");
        getAnswerForAssignment(MyMain.answer());
    }

    /**
     * This method print the options that the user has.
     */
    public void printOptions() {
        System.out.println("Press one from the above options: \'create\',"
                + " \'student\', \'course\', \'menu\'.");
    }

    /**
     * This method manages the answer from the menu
     * @param answerAssignment is the answer for menu
     */
    public void getAnswerForAssignment(String answerAssignment) {
        switch (answerAssignment.toLowerCase()) {
            case "create":
                adao.insertAssignment(adao.createAssignment());
                assignmentMenu();
                break;

            case "student":
                caseStudent();
                printOptions();
                getAnswerForAssignment(MyMain.answer());
                break;

            case "course":
                caseCourse();
                printOptions();
                getAnswerForAssignment(MyMain.answer());
                break;

            case "menu":
                MyMain.mainMenu();
                break;

            default:
                printOptions();
                getAnswerForAssignment(MyMain.answer());
                break;
        }
    }

    /**
     * This method add an assignment in a course.
     */
    public void caseCourse() {
        System.out.println("Press the ID from the above assignments you want to add a course.");
        int idAs = checkInt();
        cdao.printAllCourses();
        System.out.println("Press the ID from the above courses you want to add this assignment.");
        int idC = checkInt();
        cdao.addAssignmentInCourse(idC, idAs);
        System.out.println("This assignment was added to this course.");
    }

    /**
     * This method add an assignment in a student.
     */
    public void caseStudent() {
        System.out.println("Press the ID from the above assignments you want to add in a student.");
        int idAss = checkInt();
        sdao.printAllStudents();
        System.out.println("Press the ID from the above students you want to add this assignment.");
        int idSt = checkInt();
        sdao.addAssignmentInStudent(idAss, idSt);
        System.out.println("This assignment was added to this student.");
    }
}
