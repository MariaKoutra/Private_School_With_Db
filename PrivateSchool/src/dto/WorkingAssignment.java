package dto;

/**
 * This class contains constructors, 'toString', and getters for 'WorkingAssignment'.
 * @author Maria
 */
public class WorkingAssignment {

    private int idStud;
    private String firstName;
    private String lastName;
    private int idCourse;
    private String titleC;
    private String streamC;
    private String typeC;
    private int idAss;
    private String titleAss;
    private int maxOralMarkAss;
    private int maxTotalMarkAss;

    public WorkingAssignment(int idStud, String firstName, String lastName, int idCourse, String titleC, String streamC, String typeC, int idAss, String titleAss, int maxOralMarkAss, int maxTotalMarkAss) {
        this.idStud = idStud;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCourse = idCourse;
        this.titleC = titleC;
        this.streamC = streamC;
        this.typeC = typeC;
        this.idAss = idAss;
        this.titleAss = titleAss;
        this.maxOralMarkAss = maxOralMarkAss;
        this.maxTotalMarkAss = maxTotalMarkAss;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-6s %-1s %-10s %2s %-10s %3s %5s %5s %-14s %s %-13s %1s %-11s %2s %6s %8s %-16s %-5s %3s %7s %7s %8s",
                "|", idStud, "|", firstName, "|", lastName, "|", idCourse, "|", titleC, "|", streamC, "|", typeC, "|", idAss, "|", titleAss, "|", maxOralMarkAss, "|", maxTotalMarkAss, "|");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitleC() {
        return titleC;
    }

    public String getStreamC() {
        return streamC;
    }

    public String getTypeC() {
        return typeC;
    }

    public String getTitleAss() {
        return titleAss;
    }

    public int getMaxOralMarkAss() {
        return maxOralMarkAss;
    }

    public int getMaxTotalMarkAss() {
        return maxTotalMarkAss;
    }

    
}
