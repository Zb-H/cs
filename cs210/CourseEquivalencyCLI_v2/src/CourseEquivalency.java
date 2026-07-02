/**
 * Represents one equivalency row from the CSV file.
 *
 * <p>One row usually means: a course from the transfer institution is equivalent
 * to a course from the receiving institution. This program indexes both sides,
 * so the user can search from UW to BC or from BC to UW.</p>
 */
public class CourseEquivalency {
    private Course transferCourse;
    private Course equivalentCourse;
    private String comments;
    private String beginDate;
    private String endDate;

    /**
     * Creates one equivalency relationship.
     *
     * @param transferInstitution the school in the "Transfer Institution" column
     * @param transferCourseText the course in the "Transfer Course" column
     * @param receiveInstitution the school in the "Receive Institution" column
     * @param equivalentCourseText the course in the "Equivalent Course" column
     * @param comments notes from the CSV, can be empty
     * @param beginDate begin date from the CSV, can be empty
     * @param endDate end date from the CSV, can be empty
     */
    public CourseEquivalency(String transferInstitution,
                             String transferCourseText,
                             String receiveInstitution,
                             String equivalentCourseText,
                             String comments,
                             String beginDate,
                             String endDate) {
        this.transferCourse = new Course(transferInstitution, transferCourseText);
        this.equivalentCourse = new Course(receiveInstitution, equivalentCourseText);
        this.comments = clean(comments);
        this.beginDate = clean(beginDate);
        this.endDate = clean(endDate);
    }

    /**
     * @return the course from the transfer side of the CSV row
     */
    public Course getTransferCourse() {
        return transferCourse;
    }

    /**
     * @return the course from the receive/equivalent side of the CSV row
     */
    public Course getEquivalentCourse() {
        return equivalentCourse;
    }

    /**
     * @return comments from the CSV row
     */
    public String getComments() {
        return comments;
    }

    /**
     * @return begin date from the CSV row
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * @return end date from the CSV row
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Cleans null values and extra spaces.
     *
     * @param text original text
     * @return cleaned text; never null
     */
    private String clean(String text) {
        if (text == null) {
            return "";
        }
        return text.trim();
    }

    /**
     * Formats this row for console output.
     *
     * @return readable multi-line result
     */
    public String toDisplayString() {
        StringBuilder result = new StringBuilder();

        result.append("Transfer side:   ").append(transferCourse).append("\n");
        result.append("Equivalent side: ").append(equivalentCourse).append("\n");

        if (comments.length() > 0) {
            result.append("Comments:        ").append(comments.replace("\n", "; ")).append("\n");
        }
        if (beginDate.length() > 0) {
            result.append("Begin Date:      ").append(beginDate).append("\n");
        }
        if (endDate.length() > 0) {
            result.append("End Date:        ").append(endDate).append("\n");
        }

        return result.toString();
    }
}
