package dao;

import dto.WorkingAssignment;
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
import model.Student;
import utils.ClassUtils;
import utils.DbUtils;

/**
 *
 * @author Maria
 */
public class StudentDaoImplem extends ClassUtils implements StudentDaoInterf {

    /**
     * This method create a Student from input data
     *
     * @return a Student
     */
    @Override
    public Student createStudent() {
        System.out.println("Please give the first name of the student:");
        String firstName = MyMain.answer();
        System.out.println("Please give the last name of the student:");
        String lastName = MyMain.answer();
        System.out.println("Please give the date of birth of the student in format \"yyyy-mm-dd\":");
        Date birthday = checkDate();
        System.out.println("Please give the tuition fees of the student:");
        int tuitionFees = checkInt();
        return new Student(firstName, lastName, birthday, tuitionFees);
    }

    /**
     * This method insert a student in database
     *
     * @param s is a given student
     */
    @Override
    public void insertStudent(Student s) {
        String sql = "INSERT INTO `school_project`.`student` (`FIRST_NAME`, `LAST_NAME`, `BIRTHDATE`, `TUITION_FEES`) VALUES (?, ?, ?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setDate(3, s.getBirthday());
            ps.setInt(4, s.getTuitionFees());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method create a list with all the Students from database
     *
     * @return a list with Student
     */
    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> list = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getInt("ID_STUDENT"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getDate("BIRTHDATE"), rs.getInt("TUITION_FEES"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * This method print the list with all the Students
     */
    @Override
    public void printAllStudents() {
        if (getAllStudents().isEmpty()) {
            System.out.println("There is no students now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %2s %1s  %-10s %2s  %-10s %2s  %-10s %2s  %8s %2s",
                    "|", "ID", "|", "First Name", "|", "Last Name", "|", "Day of Birth", "|", "Tuition Fees", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getAllStudents().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    /**
     * This method create a list with all the Students who are enrolled in more than one course
     * @return a list with Student
     */
    @Override
    public List<Student> getStudentsInManyCourses() {
        String sql = "SELECT student.*\n"
                + "FROM enrolled_stud , student\n"
                + "WHERE enrolled_stud.ID_STUDENT = student.ID_STUDENT\n"
                + "GROUP BY enrolled_stud.ID_STUDENT\n"
                + "HAVING count(enrolled_stud.ID_STUDENT) > 1";
        List<Student> result = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getInt("ID_STUDENT"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getDate("BIRTHDATE"), rs.getInt("TUITION_FEES"));
                result.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * This method print the list with all the Students who are enrolled in more
     * than one course
     */
    @Override
    public void printStudentsInManyCourses() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(String.format("%s %2s %1s  %-10s %2s  %-10s %2s  %-10s %2s  %8s %2s",
                "|", "ID", "|", "First Name", "|", "Last Name", "|", "Day of Birth", "|", "Tuition Fees", "|"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        getStudentsInManyCourses().forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    /**
     * This method insert an assignment in a student 
     * @param idAssign   is the given id of the assignment will add in the Student
     * @param idStudent is the given id of the student will add the assignment
     */
    @Override
    public void addAssignmentInStudent(int idAssign, int idStudent) {
        String sql = "INSERT INTO `school_project`.`working_assign` (`ID_ASSIGNMENT`, `ID_STUDENT`) VALUES (?, ?)";
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idAssign);
            ps.setInt(2, idStudent);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method create a list with all the Students with theirs courses and theirs assignments
     * @return the list
     */
    @Override
    public List<WorkingAssignment> getStudentWorkAssignment() {
        String sql = "SELECT student.ID_STUDENT, student.FIRST_NAME, student.LAST_NAME, \n"
                + "	   course.ID_COURSE, course.TITLE AS COURSE_TITLE, course.STREAM, course.TYPE, \n"
                + "       assignment.ID_ASSIGNMENT, assignment.TITLE AS ASS_TITLE, assignment.MAX_ORAL_MARK, assignment.MAX_TOTAL_MARK       \n"
                + "FROM course, student, working_assign, enrolled_stud, assignment\n"
                + "WHERE course.ID_COURSE = enrolled_stud.ID_COURSE\n"
                + "AND student.ID_STUDENT = enrolled_stud.ID_STUDENT\n"
                + "AND assignment.ID_ASSIGNMENT = working_assign.ID_ASSIGNMENT\n"
                + "AND student.ID_STUDENT = working_assign.ID_STUDENT\n"
                + "ORDER BY working_assign.ID_STUDENT";
        List<WorkingAssignment> result = new ArrayList<>();
        try (Connection con = DbUtils.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WorkingAssignment w = new WorkingAssignment(rs.getInt("ID_STUDENT"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
                        rs.getInt("ID_COURSE"), rs.getString("COURSE_TITLE"), rs.getString("STREAM"), rs.getString("TYPE"),
                        rs.getInt("ID_ASSIGNMENT"), rs.getString("ASS_TITLE"), rs.getInt("MAX_ORAL_MARK"), rs.getInt("MAX_TOTAL_MARK"));
                result.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * This method print the list with all the Students with theirs courses and theirs assignments
     */
    @Override
    public void printStudentWorkAssignment() {
        if (getStudentWorkAssignment().isEmpty()) {
            System.out.println("There is no assignments in students now.");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(String.format("%s %s %-1s %4s %2s  %8s  %2s %s %s %s %3s %s %1s %s %2s %s %s %s %s %s %s %s %s",
                    "|", "ID Student", "|", "First Name", "|", "Last Name", "|", "ID Course", "|", "Title Course", "|", "Stream Course", "|", "Type Course", "|", "ID Assignment", "|", "Title Assignment", "|",
                    "Max Oral Mark", "|", "Max Total Mark", "|"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            getStudentWorkAssignment().forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

}
