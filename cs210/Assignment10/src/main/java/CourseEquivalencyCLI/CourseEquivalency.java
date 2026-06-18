package CourseEquivalencyCLI;

/**
 * Represents a relationship between two equivalent courses.
 *
 * The object stores one course from the transfer side and one course from the
 * receiving/equivalent side. The program treats the relationship as two-way,
 * so either course can be used as the search input.
 */
public class CourseEquivalency {
    private final Course leftCourse;
    private final Course rightCourse;
    private final String comments;
    private final String beginDate;
    private final String endDate;
    private final String sourceFile;

    /**
     * Creates a course equivalency relationship.
     *
     * @param leftCourse transfer-side course
     * @param rightCourse receive-side equivalent course
     * @param comments optional CSV comments
     * @param beginDate begin date from CSV
     * @param endDate end date from CSV
     * @param sourceFile file name where this equivalency came from
     */
    public CourseEquivalency(Course leftCourse, Course rightCourse, String comments,
                             String beginDate, String endDate, String sourceFile) {
        this.leftCourse = leftCourse;
        this.rightCourse = rightCourse;
        this.comments = TextUtil.safeTrim(comments).replaceAll("\\s+", " ");
        this.beginDate = TextUtil.safeTrim(beginDate);
        this.endDate = TextUtil.safeTrim(endDate);
        this.sourceFile = TextUtil.safeTrim(sourceFile);
    }

    /**
     * @return left-side course
     */
    public Course getLeftCourse() {
        return leftCourse;
    }

    /**
     * @return right-side course
     */
    public Course getRightCourse() {
        return rightCourse;
    }

    /**
     * @return optional comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @return begin date
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * @return end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @return source CSV file name
     */
    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * Combines all searchable text from both courses.
     *
     * @return combined searchable text
     */
    public String getSearchText() {
        return leftCourse.getSearchText() + " " + rightCourse.getSearchText() + " " + comments;
    }

    /**
     * Builds a formatted result with a centered vertical bar.
     *
     * @param resultNumber result number shown before the result
     * @return formatted multi-line result
     */
    public String formatResult(int resultNumber) {
        int leftWidth = 58;
        int rightWidth = 58;
        StringBuilder builder = new StringBuilder();

        builder.append("Result #").append(resultNumber).append(System.lineSeparator());
        builder.append(center(leftCourse.getSchool(), leftWidth))
               .append(" | ")
               .append(center(rightCourse.getSchool(), rightWidth))
               .append(System.lineSeparator());
        builder.append(center(leftCourse.getCourseCode(), leftWidth))
               .append(" | ")
               .append(center(rightCourse.getCourseCode(), rightWidth))
               .append(System.lineSeparator());
        builder.append(center(trimToWidth(leftCourse.getTitle(), leftWidth), leftWidth))
               .append(" | ")
               .append(center(trimToWidth(rightCourse.getTitle(), rightWidth), rightWidth))
               .append(System.lineSeparator());
        builder.append(center("Credit: " + leftCourse.getCredit(), leftWidth))
               .append(" | ")
               .append(center("Credit: " + rightCourse.getCredit(), rightWidth))
               .append(System.lineSeparator());

        if (!comments.isEmpty()) {
            builder.append("Comment: ").append(comments).append(System.lineSeparator());
        }
        if (!beginDate.isEmpty() || !endDate.isEmpty()) {
            builder.append("Dates: ").append(beginDate.isEmpty() ? "N/A" : beginDate)
                   .append(" - ").append(endDate.isEmpty() ? "Present" : endDate)
                   .append(System.lineSeparator());
        }
        if (!sourceFile.isEmpty()) {
            builder.append("Source: ").append(sourceFile).append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Centers text in a fixed-width field.
     *
     * @param text text to center
     * @param width target width
     * @return centered text
     */
    private static String center(String text, int width) {
        String safe = trimToWidth(TextUtil.safeTrim(text), width);
        int padding = width - safe.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return " ".repeat(leftPadding) + safe + " ".repeat(rightPadding);
    }

    /**
     * Prevents very long titles from breaking the table layout.
     *
     * @param text original text
     * @param width max width
     * @return shortened text when necessary
     */
    private static String trimToWidth(String text, int width) {
        String safe = TextUtil.safeTrim(text);
        if (safe.length() <= width) {
            return safe;
        }
        if (width <= 3) {
            return safe.substring(0, width);
        }
        return safe.substring(0, width - 3) + "...";
    }

    /**
     * @return default display used when printing the object directly
     */
    @Override
    public String toString() {
        return formatResult(1);
    }
}
