package menu;

import dao.AssignmentDaoImplem;
import dao.AssignmentDaoInterf;
import dao.CourseDaoImplem;
import dao.CourseDaoInterf;
import dao.StudentDaoImplem;
import dao.StudentDaoInterf;
import dao.TrainerDaoImplem;
import dao.TrainerDaoInterf;
import main.MyMain;
import utils.ClassUtils;

/**
 *
 * @author Maria
 */
public class CourseMenu extends ClassUtils {

    CourseDaoInterf cdao = new CourseDaoImplem();
    StudentDaoInterf sdao = new StudentDaoImplem();
    AssignmentDaoInterf adao = new AssignmentDaoImplem();
    TrainerDaoInterf tdao = new TrainerDaoImplem();

    /**
     * Print all courses and choices from courses' menu.
     * Then calls another method which manages the answer for menu
     * with parameter another method which scan the answer from the user.
     */
    public void courseMenu() {
        cdao.printAllCourses();
        System.out.println(" Press \'create\' to create a new course.");
        System.out.println(" Press \'student\' to add a student in a course.");
        System.out.println(" Press \'trainer\' to add a trainer in a course.");
        System.out.println(" Press \'assignment\' to add an assignment in a course.");
        System.out.println(" Press \'st\' to see all the students per course.");
        System.out.println(" Press \'tr\' to see all the trainers per course.");
        System.out.println(" Press \'as\' to see all the assignments per course.");
        System.out.println(" Press \'menu\' to go at the main menu.");
        getAnswerForCourse(MyMain.answer());
    }
    
    /**
     * This method print the options that the user has.
     */
    public void printOptions() {
        System.out.println("Press one from the above options: \'create\',"
                + " \'student\', \'trainer\', \'assignment\', \'st\', \'tr\', \'as\', \'menu\'.");
    }
    
    /**
     * This method manages the answer from the menu
     * @param answerCourse is the answer for menu
     */
    public void getAnswerForCourse(String answerCourse) {
        switch (answerCourse.toLowerCase()) {
            case "create":
                cdao.insertCourse(cdao.createCourse());
                courseMenu();
                break;

            case "student":
                caseStudent();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "trainer":
                caseTrainer();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "assignment":
                caseAssignment();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "st":
                cdao.printEnrolledStudentInCourse();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "tr":
                tdao.printAllTrainers();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "as":
                cdao.printCourseHasAssignment();
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;

            case "menu":
                MyMain.mainMenu();
                break;

            default:
                printOptions();
                getAnswerForCourse(MyMain.answer());
                break;
        }
    }

    /**
     * This method add a student in a course.
     */
    public void caseStudent() {
        System.out.println("Press the ID from the above courses you want to add a student.");
        int idCourse = checkInt();
        sdao.printAllStudents();
        System.out.println("Press the ID from the above students you want to add in this course.");
        int idStudent = checkInt();
        cdao.addStudentInCourse(idCourse, idStudent);
        System.out.println("This student was added to this course.");
    }

    /**
     * This method add an assignment in a course.
     */
    public void caseAssignment() {
        System.out.println("Press the ID from the above courses you want to add an assignment.");
        int idCour = checkInt();
        adao.printAllAssignments();
        System.out.println("Press the ID from the above assignments you want to add in this course.");
        int idAssign = checkInt();
        cdao.addAssignmentInCourse(idCour, idAssign);
        System.out.println("This assignment was added to this course.");
    }

    /**
     * This method add a trainer in a course.
     */
    public void caseTrainer() {
        System.out.println("Press the ID from the above courses you want to add a trainer.");
        int idC = checkInt();
        tdao.printAllTrainers();
        System.out.println("Press the ID from the above trainers you want to add in this course.");
        int idTr = checkInt();
        tdao.addTrainerInCourse(idC, idTr);
        tdao.printAllTrainers();
    }
}
