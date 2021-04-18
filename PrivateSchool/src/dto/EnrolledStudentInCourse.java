package dto;

/**
 * This class contains constructors, 'toString', and getters for 'EnrolledStudentInCourse'.
 * @author Maria
 */
public class EnrolledStudentInCourse{
    
    private int idCourse;
    private String title;
    private String stream;
    private String type;
    private int idStudent;
    private String firstName;
    private String lastName;

    public EnrolledStudentInCourse(int idCourse, String title, String stream, String type, int idStudent, String firstName, String lastName) {
        this.idCourse = idCourse;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-5s %2s  %-13s %2s  %-10s %2s  %-10s %2s %5s %5s  %-10s %2s  %-10s %2s",
                "|", idCourse, "|", title, "|", stream, "|", type, "|", idStudent, "|", firstName, "|", lastName, "|");
    }

    public int getIdCourse() {
        return idCourse;
    }

    public String getTitle() {
        return title;
    }

    public String getStream() {
        return stream;
    }

    public String getType() {
        return type;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    
    
    
}
