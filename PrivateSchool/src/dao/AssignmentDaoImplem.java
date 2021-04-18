package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MyMain;
import model.Assignment;
import utils.ClassUtils;
import utils.DbUtils;

/**
 *
 * @author Maria
 */
public class AssignmentDaoImplem extends ClassUtils implements AssignmentDaoInterf {

    /**
     * This method create an Assignment from input data
     * @return an assignment
     */
    @Override
    public Assignment createAssignment() {
        System.out.println("Please give the title of the assignment:");
        String title = MyMain.answer();
        System.out.println("Please give the description of the assignment:");
        String description = MyMain.answer();
        System.out.println("Please give the date of submission of the assignment in format \"yyyy-mm-dd\":");
        Date submissionDate = checkDate();
        System.out.println("Please give the max oral mark of the assignment:");
        int maxOralMark = checkInt();
        System.out.println("Please give the max total mark of the assignment:");
        int maxTotalMark = checkInt();
        return new Assignment(title, description, submissionDate, maxOralMark, maxTotalMark);
    }

    /**
     * This method insert an assignment in database
     * @param a is a given assignment
     */
    @Override
    public void insertAssignment(Assignment a) {
        String sql = "INSERT INTO `school_project`.`assignment` (`TITLE`, `DESCRIPTION`, `SUBMISSION_DATE`, `MAX_ORAL_MARK`, `MAX_TOTAL_MARK`) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getDescription());
            ps.setDate(3, a.getSubmissionDate());
            ps.setInt(4, a.getMaxOralMark());
            ps.setInt(5, a.getMaxTotalMark());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method create a list with all the Assignments from database
     * @return a list with Assignment
     */
    @Override
    public List<Assignment> getAllAssignments() {
        String sql = "SELECT * FROM assignment";
        List<Assignment> list = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment(rs.getInt("ID_ASSIGNMENT"), rs.getString("TITLE"), rs.getString("DESCRIPTION"),
                        rs.getDate("SUBMISSION_DATE"), rs.getInt("MAX_ORAL_MARK"), rs.getInt("MAX_TOTAL_MARK"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * This method print the list with all the Assignments
     */
    @Override
    public void printAllAssignments() {
        if (getAllAssignments().isEmpty()) {
            System.out.println("There is no courses now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %1s %2s %-10s %2s  %-25s %2s  %-20s %2s  %-13s %2s %-13s %1s",
                    "|", "ID", "|", "Title", "|", "Description", "|", "Submission Date", "|", "Max Oral Mark", "|", "Max Total Mark", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getAllAssignments().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
