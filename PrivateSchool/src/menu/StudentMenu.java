package menu;

import dao.AssignmentDaoImplem;
import dao.AssignmentDaoInterf;
import dao.CourseDaoImplem;
import dao.CourseDaoInterf;
import dao.StudentDaoImplem;
import dao.StudentDaoInterf;
import main.MyMain;
import model.Student;
import utils.ClassUtils;

/**
 *
 * @author Maria
 */
public class StudentMenu extends ClassUtils {

    StudentDaoInterf sdao = new StudentDaoImplem();
    CourseDaoInterf cdao = new CourseDaoImplem();
    AssignmentDaoInterf adao = new AssignmentDaoImplem();

    /**
     * Print all students and choices from students' menu.
     * Then calls another method which manages the answer for menu
     * with parameter another method which scan the answer from the user.
     */
    public void studentMenu() {
        sdao.printAllStudents();
        System.out.println(" Press \'create\' to create a new student.");
        System.out.println(" Press \'course\' to add a course in a student.");
        System.out.println(" Press \'assignment\' to add an assignment in a student.");
        System.out.println(" Press \'as\' to see all assignments per student.");
        System.out.println(" Press \'more\' to see all students which belong in more than one course.");
        System.out.println(" Press \'menu\' to go at the main menu.");
        getAnswerForStudent(MyMain.answer());
    }

    /**
     * This method print the options that the user has.
     */
    public void printOptions() {
        System.out.println("Press one from the above options: \'create\',"
                + " \'course\', \'assignment\', \'as\', \'more\', \'menu\'.");
    }

    /**
     * This method manages the answer from the menu
     * @param answerStudent is the answer for menu
     */
    public void getAnswerForStudent(String answerStudent) {
        switch (answerStudent.toLowerCase()) {
            case "create":
                Student s = sdao.createStudent();
                sdao.insertStudent(s);
                studentMenu();
                break;

            case "course":
                caseCourse();
                printOptions();
                getAnswerForStudent(MyMain.answer());
                break;

            case "assignment":
                caseAddAssignment();
                printOptions();
                getAnswerForStudent(MyMain.answer());
                break;

            case "as":
                sdao.printStudentWorkAssignment();
                printOptions();
                getAnswerForStudent(MyMain.answer());
                break;

            case "more":
                sdao.printStudentsInManyCourses();
                printOptions();
                getAnswerForStudent(MyMain.answer());
                break;

            case "menu":
                MyMain.mainMenu();
                break;

            default:
                printOptions();
                getAnswerForStudent(MyMain.answer());
                break;
        }
    }

    /**
     * This method add a course in a student.
     */
    public void caseCourse() {
        System.out.println("Press the ID from the above students you want to add a course.");
        int idStudent = checkInt();
        cdao.printAllCourses();
        System.out.println("Press the ID from the above courses you want to add this student.");
        int idCourse = checkInt();
        cdao.addStudentInCourse(idCourse, idStudent);
        System.out.println("This student was added to this course.");
    }

    /**
     * This method add an assignment in a student.
     */
    public void caseAddAssignment() {
        System.out.println("Press the ID from the above students you want to add an assignment.");
        int idStudent = checkInt();
        adao.printAllAssignments();
        System.out.println("Press the ID from the above assignments you want to add in this student.");
        int idAss = checkInt();
        sdao.addAssignmentInStudent(idAss, idStudent);
        System.out.println("This assignment was added to this student.");
    }

}
