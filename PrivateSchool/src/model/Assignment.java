package model;

import java.sql.Date;

/**
 * This class contains constructors, 'toString', getters and setters for 'Assignment'.
 * @author Maria
 */
public class Assignment {

    private int idAssignment;
    private String title;
    private String description;
    private Date submissionDate;
    private int maxOralMark;
    private int maxTotalMark;

    public Assignment(int idAssignment, String title, String description, Date submissionDate, int maxOralMark, int maxTotalMark) {
        this.idAssignment = idAssignment;
        this.title = title;
        this.description = description;
        this.submissionDate = submissionDate;
        this.maxOralMark = maxOralMark;
        this.maxTotalMark = maxTotalMark;
    }

    public Assignment(String title, String description, Date submissionDate, int maxOralMark, int maxTotalMark) {
        this.title = title;
        this.description = description;
        this.submissionDate = submissionDate;
        this.maxOralMark = maxOralMark;
        this.maxTotalMark = maxTotalMark;
    }

    public Assignment() {
    }

    @Override
    public String toString() {
        return String.format("%s %1s %2s  %-10s %2s  %-25s %2s  %-20s %2s  %-13s %2s %-13s %2s",
                "|", idAssignment, "|", title, "|", description, "|", submissionDate, "|", maxOralMark, "|", maxTotalMark, "|");
    }

    public int getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(int idAssignment) {
        this.idAssignment = idAssignment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public int getMaxOralMark() {
        return maxOralMark;
    }

    public void setMaxOralMark(int maxOralMark) {
        this.maxOralMark = maxOralMark;
    }

    public int getMaxTotalMark() {
        return maxTotalMark;
    }

    public void setMaxTotalMark(int maxTotalMark) {
        this.maxTotalMark = maxTotalMark;
    }

}
