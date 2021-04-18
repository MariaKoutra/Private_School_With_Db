package dao;

import java.util.List;
import model.Trainer;

/**
 *
 * @author Maria
 */
public interface TrainerDaoInterf {

    public Trainer createTrainer();

    public void insertTrainer(Trainer t);

    public List<Trainer> getAllTrainers();

    public void printAllTrainers();

    public void addTrainerInCourse(int idCourse, int idTrainer);
}
