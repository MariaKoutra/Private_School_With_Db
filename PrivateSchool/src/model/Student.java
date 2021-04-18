package model;

import java.sql.Date;

/**
 * This class contains constructors, 'toString', getters and setters for 'Student'.
 * @author Maria
 */
public class Student {

    private int idStudent;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int tuitionFees;

    public Student(int idStudent, String firstName, String lastName, Date birthday, int tuitionFees) {
        this.idStudent = idStudent;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
    }

    public Student(String firstName, String lastName, Date birthday, int tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.tuitionFees = tuitionFees;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return String.format("%s %1s %2s  %-10s %2s  %-10s %2s   %-11s %2s      %-8s %2s",
                "|", idStudent, "|", firstName, "|", lastName, "|", birthday, "|", tuitionFees, "|");
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

}
