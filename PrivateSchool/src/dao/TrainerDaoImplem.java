package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MyMain;
import model.Trainer;
import utils.ClassUtils;
import utils.DbUtils;

/**
 *
 * @author Maria
 */
public class TrainerDaoImplem extends ClassUtils implements TrainerDaoInterf {

/**
 * This method create a Trainer from input data
 * @return a Trainer
 */
    public Trainer createTrainer() {
        System.out.println("Please give the first name of the trainer.");
        String firstName = MyMain.answer();
        System.out.println("Please give the last name of the trainer.");
        String lastName = MyMain.answer();
        System.out.println("Please give the subject of the trainer.");
        String subject = MyMain.answer();
        return new Trainer(firstName, lastName, subject);
    }

/**
 * This method insert a trainer in database
 * @param t is a given trainer
 */
    @Override
    public void insertTrainer(Trainer t) {
        String sql = "INSERT INTO `school_project`.`trainer` (`FIRST_NAME`, `LAST_NAME`, `SUBJECT`) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DbUtils.getConnection().prepareStatement(sql)) {
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getSubject());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/**
 * This method create a list with all the Trainers from database
 * with id of the course that each trainer is
 * @return a list with Trainer
 */
    @Override
    public List<Trainer> getAllTrainers() {
        String sql = "SELECT * FROM trainer";
        List<Trainer> list = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trainer t = new Trainer(rs.getInt("ID_TRAINER"), rs.getInt("ID_COURSE"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("SUBJECT"));
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

/**
 * This method print the list with all the Trainers
 */
    @Override
    public void printAllTrainers() {
        if (getAllTrainers().isEmpty()) {
            System.out.println("There is no trainers now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %10s %2s %10s %2s  %-15s %2s  %-15s %2s   %-15s %2s",
                    "|", "ID Trainer", "|", "ID Course", "|", "First Name", "|", "Last Name", "|", "Subject", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getAllTrainers().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    
/**
 * This method set the default id of course which is '0' (means no course).
 * Specifically this method add a trainer in a course
 * @param idCourse is the given id of the course will add in the Trainer
 * @param idTrainer is the given id of the trainer that the course will be added
 */
    @Override
    public void addTrainerInCourse(int idCourse, int idTrainer) {
        String sql = "UPDATE `school_project`.`trainer` SET `ID_COURSE` = ? WHERE (`ID_TRAINER` = ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idCourse);
            ps.setInt(2, idTrainer);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}