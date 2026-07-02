import java.util.ArrayList;

/**
 * Represents one course from one school.
 *
 * <p>This class is intentionally simple for a CS210-level project. It stores
 * the school name and the original course text from the CSV file. It also
 * contains helper methods that normalize school names and course codes so the
 * user can search with shorter input such as "UW", "BC", "CS210", or
 * "MATH& 152".</p>
 */
public class Course {
    private String institution;
    private String courseText;

    /**
     * Creates a Course object.
     *
     * @param institution the school name, for example "UNIVERSITY OF WASHINGTON"
     * @param courseText the course text, for example "CSE 142 COMPUTER PROGRAMMING I (4)"
     */
    public Course(String institution, String courseText) {
        this.institution = clean(institution);
        this.courseText = clean(courseText);
    }

    /**
     * @return the school name exactly as stored after basic cleaning
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @return the full course text exactly as stored after basic cleaning
     */
    public String getCourseText() {
        return courseText;
    }

    /**
     * Removes null values and extra spaces. This keeps the rest of the code safer.
     *
     * @param text the original text
     * @return cleaned text; never returns null
     */
    private static String clean(String text) {
        if (text == null) {
            return "";
        }
        return text.trim();
    }

    /**
     * Normalizes school names so common short names can still work.
     *
     * <p>Example: user input "UW" becomes "UNIVERSITY OF WASHINGTON".
     * This makes the command-line program easier to use.</p>
     *
     * @param school the school name typed by the user or read from the CSV
     * @return normalized uppercase school name
     */
    public static String normalizeInstitution(String school) {
        String result = clean(school).toUpperCase();
        result = result.replaceAll("\\s+", " ");

        // Small alias list. Add more aliases here if you use other schools later.
        // We normalize names because the CSV files sometimes use long official names,
        // but users usually type short names like "UW", "UWB", "BC", or "SeattleU".

        // Bellevue College used to appear as Bellevue Community College in some rows.
        if (result.equals("BC")
                || result.equals("BELLEVUE")
                || result.equals("BELLEVUE COLLEGE")
                || result.equals("BELLEVUE COMMUNITY COLLEGE")) {
            return "BELLEVUE COLLEGE";
        }

        // University of Washington Bothell must be checked BEFORE normal UW,
        // otherwise it would accidentally become just "UNIVERSITY OF WASHINGTON".
        if (result.equals("UWB")
                || result.equals("UW BOTHELL")
                || result.equals("UNIVERSITY OF WASHINGTON BOTHELL")
                || result.startsWith("UNIVERSITY OF WASHINGTON BOTHELL")) {
            return "UNIVERSITY OF WASHINGTON BOTHELL";
        }

        // The Tacoma CSV in this dataset stores the school as "UNIVERSITY OF WASHINGTON".
        // The course codes usually start with T, such as TCSS 142 or T CRIM 101.
        if (result.equals("UWT") || result.equals("UW TACOMA")) {
            return "UNIVERSITY OF WASHINGTON";
        }

        // Main UW Seattle file has names like "UNIVERSITY OF WASHINGTON COURSE DESCRIPTIONS".
        if (result.equals("UW")
                || result.equals("U W")
                || result.equals("UW SEATTLE")
                || result.equals("UNIVERSITY OF WASHINGTON")
                || result.equals("UNIVERSITY OF WASHINGTON COURSE DESCRIPTIONS")
                || result.equals("UNIVERSITY OF WASHINGTON UNDERGRADUATE STUDY")) {
            return "UNIVERSITY OF WASHINGTON";
        }

        // Seattle University appears as "SEATTLE UNIVERSITY UNDERGRADUATE".
        if (result.equals("SEATTLEU")
                || result.equals("SEATTLE U")
                || result.equals("SU")
                || result.equals("SEATTLE UNIVERSITY")
                || result.startsWith("SEATTLE UNIVERSITY")) {
            return "SEATTLE UNIVERSITY";
        }

        return result;
    }

    /**
     * Extracts possible course codes from a course cell.
     *
     * <p>Some CSV cells contain more than one course separated by a line break.
     * Example:
     * MATH& 153 CALCULUS III (5)
     * MATH& 254 CALCULUS IV (5)
     * This method returns both "MATH&153" and "MATH&254" as searchable keys.</p>
     *
     * @param courseText the full course cell text from the CSV
     * @return a list of normalized course-code keys
     */
    public static ArrayList<String> extractCourseCodeKeys(String courseText) {
        ArrayList<String> codes = new ArrayList<>();
        String cleanedText = clean(courseText);

        if (cleanedText.length() == 0) {
            return codes;
        }

        // A cell can contain multiple course lines.
        String[] parts = cleanedText.split("\\r?\\n");

        for (String part : parts) {
            String code = extractOneCourseCode(part);
            String key = normalizeCourseCode(code);

            // Avoid duplicate keys.
            if (key.length() > 0 && !codes.contains(key)) {
                codes.add(key);
            }
        }

        return codes;
    }

    /**
     * Extracts one course code from one line of text.
     *
     * <p>The basic idea: read words from the beginning until we reach the first
     * word that contains a number. This handles examples like:</p>
     * <ul>
     *   <li>"CSE 142 COMPUTER PROGRAMMING I (4)" -> "CSE 142"</li>
     *   <li>"CS 210 FUNDAMENTALS..." -> "CS 210"</li>
     *   <li>"MATH& 152 CALCULUS II (5)" -> "MATH& 152"</li>
     *   <li>"A A 210 ENGINEERING STATICS (4)" -> "A A 210"</li>
     * </ul>
     *
     * @param text one line of course text
     * @return the likely course code
     */
    private static String extractOneCourseCode(String text) {
        String upper = clean(text).toUpperCase();

        if (upper.startsWith("NO CREDIT")) {
            return "NO CREDIT";
        }

        // Remove credit part such as "(5)" before splitting.
        upper = upper.replaceAll("\\([^)]*\\)", " ");
        String[] words = upper.trim().split("\\s+");

        StringBuilder code = new StringBuilder();

        for (String word : words) {
            // Keep only letters, numbers, and ampersand.
            String cleanedWord = word.replaceAll("[^A-Z0-9&]", "");

            if (cleanedWord.length() == 0) {
                continue;
            }

            if (code.length() > 0) {
                code.append(" ");
            }
            code.append(cleanedWord);

            // Once we hit the number part, the course code is complete.
            if (cleanedWord.matches(".*\\d.*")) {
                break;
            }
        }

        return code.toString();
    }

    /**
     * Normalizes a course code for searching.
     *
     * <p>Spaces are removed so "CS 210" and "CS210" match the same key.</p>
     *
     * @param courseCode the course code typed by the user or extracted from the CSV
     * @return normalized key such as "CS210" or "MATH&152"
     */
    public static String normalizeCourseCode(String courseCode) {
        String result = clean(courseCode).toUpperCase();
        result = result.replaceAll("\\([^)]*\\)", " ");
        result = result.replaceAll("[^A-Z0-9&]", "");
        return result;
    }

    /**
     * Builds the combined search key used by the HashMap.
     *
     * @param institution the school name
     * @param courseCodeKey the already-normalized course code key
     * @return a combined key such as "BELLEVUE COLLEGE|CS210"
     */
    public static String buildSearchKey(String institution, String courseCodeKey) {
        return normalizeInstitution(institution) + "|" + normalizeCourseCode(courseCodeKey);
    }

    /**
     * @return a readable string used when printing results
     */
    @Override
    public String toString() {
        return institution + " - " + courseText;
    }
}
