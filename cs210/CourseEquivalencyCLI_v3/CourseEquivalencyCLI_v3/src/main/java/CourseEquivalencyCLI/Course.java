package CourseEquivalencyCLI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents one course from one institution.
 *
 * A Course object stores both the original raw course text from the CSV and
 * cleaned information used by the program, such as course code, title, credit,
 * and display school name.
 */
public class Course {
    private static final Pattern CREDIT_PATTERN = Pattern.compile("\\((\\d+(?:\\.\\d+)?(?:-\\d+(?:\\.\\d+)?)?)\\)\\s*$");

    private final String school;
    private final String rawCourseText;
    private final String courseCode;
    private final String title;
    private final String credit;

    /**
     * Creates a Course object from raw school and course text.
     *
     * @param school raw institution name from the CSV file
     * @param rawCourseText raw course text from the CSV file
     */
    public Course(String school, String rawCourseText) {
        this.school = TextUtil.cleanSchoolName(school);
        this.rawCourseText = TextUtil.safeTrim(rawCourseText).replaceAll("\\s+", " ");
        this.credit = extractCredit(this.rawCourseText);
        String withoutCredit = removeCredit(this.rawCourseText);
        this.courseCode = extractCourseCode(withoutCredit);
        this.title = extractTitle(withoutCredit, this.courseCode);
    }

    /**
     * @return cleaned school name used for display
     */
    public String getSchool() {
        return school;
    }

    /**
     * @return original course text from CSV, normalized to one line
     */
    public String getRawCourseText() {
        return rawCourseText;
    }

    /**
     * @return extracted course code, such as "CS 210" or "CSE 142"
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @return extracted course title without the course code and credit
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return credit value from parentheses, or "N/A" if not found
     */
    public String getCredit() {
        return credit;
    }

    /**
     * Builds searchable text for fuzzy matching.
     *
     * @return school, course code, title, and raw course text combined
     */
    public String getSearchText() {
        return school + " " + courseCode + " " + title + " " + rawCourseText;
    }

    /**
     * Extracts credit from course text when the text ends with something like "(5)".
     *
     * @param text course text
     * @return credit number or "N/A"
     */
    private static String extractCredit(String text) {
        Matcher matcher = CREDIT_PATTERN.matcher(TextUtil.safeTrim(text));
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "N/A";
    }

    /**
     * Removes a final credit marker such as "(5)" from a course string.
     *
     * @param text raw course text
     * @return course text without final credit marker
     */
    private static String removeCredit(String text) {
        return CREDIT_PATTERN.matcher(TextUtil.safeTrim(text)).replaceFirst("").trim();
    }

    /**
     * Extracts the course code from the beginning of a course string.
     *
     * The method keeps reading tokens until it finds the token that contains
     * a number. This handles codes such as "CS 210", "CSE 142", "B BIO 180",
     * and "MATH& 151".
     *
     * @param text course text without credit
     * @return extracted course code
     */
    private static String extractCourseCode(String text) {
        String clean = TextUtil.safeTrim(text).replaceAll("\\s+", " ");
        if (clean.isEmpty()) {
            return "UNKNOWN";
        }

        String[] tokens = clean.split(" ");
        StringBuilder code = new StringBuilder();

        for (String token : tokens) {
            if (code.length() > 0) {
                code.append(" ");
            }
            code.append(token);

            if (token.matches(".*\\d.*")) {
                return code.toString().trim();
            }
        }

        return tokens[0];
    }

    /**
     * Extracts the title after the course code.
     *
     * @param text course text without credit
     * @param code extracted course code
     * @return course title or an empty string
     */
    private static String extractTitle(String text, String code) {
        String clean = TextUtil.safeTrim(text).replaceAll("\\s+", " ");
        if (clean.toUpperCase().startsWith(code.toUpperCase())) {
            return clean.substring(code.length()).trim();
        }
        return "";
    }

    /**
     * @return readable one-line course display
     */
    @Override
    public String toString() {
        String result = school + " - " + courseCode;
        if (!title.isEmpty()) {
            result += " " + title;
        }
        result += " | Credit: " + credit;
        return result;
    }
}
