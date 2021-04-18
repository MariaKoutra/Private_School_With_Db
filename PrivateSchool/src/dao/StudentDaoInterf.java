package dao;

import dto.WorkingAssignment;
import java.util.List;
import model.Student;

/**
 *
 * @author Maria
 */
public interface StudentDaoInterf {

    public Student createStudent();

    public void insertStudent(Student s);

    public List<Student> getAllStudents();

    public void printAllStudents();

    public List<Student> getStudentsInManyCourses();
    
    public void printStudentsInManyCourses();
    
    public void addAssignmentInStudent (int idAssign, int idStudent);
    
    public List<WorkingAssignment> getStudentWorkAssignment();
    
    public void printStudentWorkAssignment();
}
