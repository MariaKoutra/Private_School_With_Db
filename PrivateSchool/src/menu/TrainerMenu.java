package menu;

import dao.CourseDaoImplem;
import dao.CourseDaoInterf;
import dao.TrainerDaoImplem;
import dao.TrainerDaoInterf;
import main.MyMain;
import model.Trainer;
import utils.ClassUtils;

/**
 *
 * @author Maria
 */
public class TrainerMenu extends ClassUtils {

    TrainerDaoInterf tdao = new TrainerDaoImplem();
    CourseDaoInterf cdao = new CourseDaoImplem();

    /**
     * Print all trainers and choices from trainers' menu.
     * Then calls another method which manages the answer for menu
     * with parameter another method which scan the answer from the user.
     */
    public void trainerMenu() {
        tdao.printAllTrainers();
        System.out.println(" Press \'create\' to create a new trainer.");
        System.out.println(" Press \'course\' to add a trainer in a course.");
        System.out.println(" Press \'menu\' to go at the main menu.");
        getAnswerForTrainer(MyMain.answer());
    }

    /**
     * This method print the options that the user has.
     */
    public void printOptions() {
        System.out.println("Press one from the above options: \'create\', \'course\', \'menu\'.");
    }

    /**
     * This method manages the answer from the menu
     * @param answerTrainer is the answer for menu
     */
    public void getAnswerForTrainer(String answerTrainer) {
        switch (answerTrainer.toLowerCase()) {
            case "create":
                Trainer t = tdao.createTrainer();
                tdao.insertTrainer(t);
                trainerMenu();
                break;

            case "course":
                caseCourse();
                trainerMenu();
                break;

            case "menu":
                MyMain.mainMenu();
                break;

            default:
                printOptions();
                getAnswerForTrainer(MyMain.answer());
                break;
        }
    }

    /**
     * This method add a trainer in a course.
     */
    public void caseCourse() {
        System.out.println("Press the ID from the above trainers you want to add in a course.");
        int idTrainer = checkInt();
        cdao.printAllCourses();
        System.out.println("Press the ID from the above courses you want to add this trainer.");
        int idCourse = checkInt();
        tdao.addTrainerInCourse(idCourse, idTrainer);
    }
}
