package main;

import java.util.Scanner;
import menu.AssignmentMenu;
import menu.CourseMenu;
import menu.StudentMenu;
import menu.TrainerMenu;

public class MyMain {

    static StudentMenu sm = new StudentMenu();
    static TrainerMenu tm = new TrainerMenu();
    static CourseMenu cm = new CourseMenu();
    static AssignmentMenu am = new AssignmentMenu();
    static Scanner scan = new Scanner(System.in);

    public static String answer() {
        String s = scan.nextLine();
        return s;
    }

    public static void mainMenu() {
        System.out.println("~~~~~~~~~~~~ Main menu ~~~~~~~~~~~~~~");
        System.out.println(" Press \'c\' to go at Course's menu.");
        System.out.println(" Press \'s\' to go at Student's menu.");
        System.out.println(" Press \'t\' to go at Trainer's menu.");
        System.out.println(" Press \'a\' to go at Assignment's menu.");
        System.out.println(" Press \'e\' to go exit from program.");
        getAnswerMenu(answer());
    }

    public static void getAnswerMenu(String answerMenu) {
        switch (answerMenu.toLowerCase()) {
            case "s":               // Case Student
                sm.studentMenu();

                break;
            case "t":           // Case Trainer
                tm.trainerMenu();

                break;
            case "c":  // Case Course
                cm.courseMenu();

                break;
            case "a":  // Case Assignments
                am.assignmentMenu();

                break;
            case "e":   // Case exit : terminate

                break;
            default:
                System.out.println("Please press one of these options.");
                getAnswerMenu(answer());
                break;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to this private school!!! \n");

        mainMenu();
    }

}
