package dto;

/**
 * This class contains constructors, 'toString', and getters for 'HasAssignment'.
 * @author Maria
 */
public class HasAssignment {

    private int idCourse;
    private String titleCourse;
    private String stream;
    private String type;
    private int idAssignment;
    private String titleAssignment;
    private String description;
    private int maxOralMark;
    private int maxTotalMark;

    public HasAssignment(int idCourse, String titleCourse, String stream, String type, int idAssignment, String titleAssignment, String description, int maxOralMark, int maxTotalMark) {
        this.idCourse = idCourse;
        this.titleCourse = titleCourse;
        this.stream = stream;
        this.type = type;
        this.idAssignment = idAssignment;
        this.titleAssignment = titleAssignment;
        this.description = description;
        this.maxOralMark = maxOralMark;
        this.maxTotalMark = maxTotalMark;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-4s %2s  %-13s %2s  %-10s %2s  %-10s %-6s  %-6s %2s  %-16s %2s  %-24s %-6s %-8s %-7s %-8s %s",
                "|", idCourse, "|", titleCourse, "|", stream, "|", type, "|", idAssignment, "|", titleAssignment, "|", description, "|", maxOralMark, "|", maxTotalMark, "|");
    }

    public int getIdCourse() {
        return idCourse;
    }

    public String getTitleCourse() {
        return titleCourse;
    }

    public String getStream() {
        return stream;
    }

    public String getType() {
        return type;
    }

    public int getIdAssignment() {
        return idAssignment;
    }

    public String getTitleAssignment() {
        return titleAssignment;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxOralMark() {
        return maxOralMark;
    }

    public int getMaxTotalMark() {
        return maxTotalMark;
    }

}
