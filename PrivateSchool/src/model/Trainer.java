package model;

/**
 * This class contains constructors, 'toString', getters and setters for 'Trainer'.
 * @author Maria
 */
public class Trainer {

    private int idTrainer;
    private int idCourse;
    private String firstName;
    private String lastName;
    private String subject;

    public Trainer(int idTrainer, int idCourse, String firstName, String lastName, String subject) {
        this.idTrainer = idTrainer;
        this.idCourse = idCourse;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer() {
    }

    @Override
    public String toString() {
        return String.format("%-5s  %-6s %-4s  %-6s %2s  %-15s %2s  %-15s %2s   %-15s %2s",
                                    "|", idTrainer, "|", idCourse, "|", firstName, "|", lastName, "|", subject, "|");
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
