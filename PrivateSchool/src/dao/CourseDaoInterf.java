package dao;

import dto.EnrolledStudentInCourse;
import dto.HasAssignment;
import java.util.List;
import model.Course;


/**
 *
 * @author Maria
 */
public interface CourseDaoInterf {
    
    public Course createCourse();

    public void insertCourse (Course c);

    public List <Course> getAllCourses ();

    public void printAllCourses ();
    
    public void addStudentInCourse (int idCourse, int idStudent);
    
    public List<EnrolledStudentInCourse> getEnrolledStudentInCourse();
    
    public void printEnrolledStudentInCourse();
    
    public void addAssignmentInCourse (int idCourse, int idAssignment);
    
    public List<HasAssignment> getCourseHasAssignment();
    
    public void printCourseHasAssignment();
}
