package dao;

import java.util.List;
import model.Assignment;

/**
 *
 * @author Maria
 */
public interface AssignmentDaoInterf {

    public Assignment createAssignment();

    public void insertAssignment(Assignment a);

    public List<Assignment> getAllAssignments();

    public void printAllAssignments();
    
}
