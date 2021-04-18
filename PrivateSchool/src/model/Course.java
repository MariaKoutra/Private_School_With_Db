package model;

import java.sql.Date;

/**
 * This class contains constructors, 'toString', getters and setters for 'Course'.
 * @author Maria
 */
public class Course {

    private int idCourse;
    private String title;
    private String stream;
    private String type;
    private Date startDate;
    private Date endDate;

    public Course(int idCourse, String title, String stream, String type, Date startDate, Date endDate) {
        this.idCourse = idCourse;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(String title, String stream, String type, Date startDate, Date endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return String.format("%s %1s %2s  %-13s %2s  %-10s %2s  %-10s %2s  %-10s %2s  %-10s %2s", 
                                    "|", idCourse, "|", title, "|", stream, "|", type, "|", startDate, "|", endDate, "|");
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
