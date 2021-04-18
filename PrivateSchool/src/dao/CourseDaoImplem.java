package dao;

import dto.EnrolledStudentInCourse;
import dto.HasAssignment;
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
import model.Course;
import utils.ClassUtils;
import utils.DbUtils;

/**
 *
 * @author Maria
 */
public class CourseDaoImplem extends ClassUtils implements CourseDaoInterf {

    /**
     * This method create a Course from input data
     * @return a course
     */
    @Override
    public Course createCourse() {
        System.out.println("Please give the title of the Course:");
        String title = MyMain.answer();
        System.out.println("Please give the stream of the Course:");
        String stream = MyMain.answer();
        System.out.println("Please give the type of the Course:");
        String type = MyMain.answer();
        System.out.println("Please give the start date of the Course in format \"yyyy-mm-dd\":");
        Date startDate = checkDate();
        System.out.println("Please give the end date of the Course in format \"yyyy-mm-dd\":");
        Date endDate = checkDate();
        return new Course(title, stream, type, startDate, endDate);
    }

    /**
     * This method insert a course in database
     * @param c is a given course
     */
    @Override
    public void insertCourse(Course c) {
        String sql = "INSERT INTO `school_project`.`course` (`TITLE`, `STREAM`, `TYPE`, `START_DATE`, `END_DATE`) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, c.getTitle());
            ps.setString(2, c.getStream());
            ps.setString(3, c.getType());
            ps.setDate(4, c.getStartDate());
            ps.setDate(5, c.getEndDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method create a list with all the Courses from database
     * @return a list with Course
     */
    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM course";
        List<Course> list = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getInt("ID_COURSE"), rs.getString("TITLE"), rs.getString("STREAM"), rs.getString("TYPE"), rs.getDate("START_DATE"), rs.getDate("END_DATE"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * This method print the list with all the Courses
     */
    @Override
    public void printAllCourses() {
        if (getAllCourses().isEmpty()) {
            System.out.println("There is no courses now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %1s %1s  %-13s %2s  %-10s %2s  %-10s %2s  %-10s %2s  %-10s %2s",
                    "|", "ID", "|", "Title", "|", "Stream", "|", "Type", "|", "Start Date", "|", "End Date", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getAllCourses().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    @Override
    public void addStudentInCourse(int idCourse, int idStudent) {
        String sql = "INSERT INTO `school_project`.`enrolled_stud` (`ID_COURSE`, `ID_STUDENT`) VALUES (?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idCourse);
            ps.setInt(2, idStudent);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<EnrolledStudentInCourse> getEnrolledStudentInCourse() {
        String sql = "SELECT course.ID_COURSE, TITLE, STREAM, TYPE, student.ID_STUDENT, FIRST_NAME, LAST_NAME\n"
                + "FROM enrolled_stud , course , student\n"
                + "WHERE enrolled_stud.ID_COURSE = course.ID_COURSE\n"
                + "	AND enrolled_stud.ID_STUDENT = student.ID_STUDENT\n"
                + "    ORDER BY enrolled_stud.ID_COURSE;";
        List<EnrolledStudentInCourse> result = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnrolledStudentInCourse x = new EnrolledStudentInCourse(rs.getInt("ID_COURSE"), rs.getString("TITLE"), rs.getString("STREAM"),
                        rs.getString("TYPE"), rs.getInt("ID_STUDENT"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"));
                result.add(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void printEnrolledStudentInCourse() {
        if (getEnrolledStudentInCourse().isEmpty()) {
            System.out.println("There is no students in courses now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %10s%2s  %-13s %2s  %-10s %2s  %-10s %2s %10s%1s  %-10s %2s  %-10s %2s",
                    "|", "ID Course", "|", "Title", "|", "Stream", "|", "Type", "|", "ID Student", "|", "First Name", "|", "Last Name", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getEnrolledStudentInCourse().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }

    @Override
    public void addAssignmentInCourse(int idCourse, int idAssignment) {
        String sql = "INSERT INTO `school_project`.`has_assign` (`ID_COURSE`, `ID_ASSIGNMENT`) VALUES (?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idCourse);
            ps.setInt(2, idAssignment);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<HasAssignment> getCourseHasAssignment() {
        String sql = "SELECT course.ID_COURSE, course.TITLE, STREAM, TYPE, assignment.ID_ASSIGNMENT, assignment.TITLE, assignment.DESCRIPTION, assignment.MAX_ORAL_MARK, assignment.MAX_TOTAL_MARK\n"
                + "FROM has_assign , assignment , course\n"
                + "WHERE has_assign.ID_COURSE = course.ID_COURSE\n"
                + "	AND has_assign.ID_ASSIGNMENT = assignment.ID_ASSIGNMENT\n"
                + "    ORDER BY has_assign.ID_COURSE";
        List<HasAssignment> result = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HasAssignment h = new HasAssignment(rs.getInt("ID_COURSE"), rs.getString("TITLE"), rs.getString("STREAM"),
                        rs.getString("TYPE"), rs.getInt("ID_ASSIGNMENT"), rs.getString("TITLE"),
                        rs.getString("DESCRIPTION"), rs.getInt("MAX_ORAL_MARK"), rs.getInt("MAX_TOTAL_MARK"));
                result.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void printCourseHasAssignment() {
        if (getCourseHasAssignment().isEmpty()) {
            System.out.println("There is no assignments in courses now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %s %s  %-14s %-2s  %-10s %-2s  %5s %5s %s %-2s %3s  %-7s  %s %8s %s %s %s %s",
                    "|", "ID Course", "|", "Title Course", "|", "Stream", "|", "Type", "|", "ID Assignment", "|", "Title Assignment", "|", "Description", "|", "Max Oral Mark", "|", "Max Total Mark", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getCourseHasAssignment().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }



}
