package CourseEquivalencyCLI;

import java.util.Locale;

/**
 * Utility methods for cleaning and comparing user input, school names, and course codes.
 *
 * This class keeps text-normalization code in one place so the rest of the program
 * can stay easier to read. Most methods are static because TextUtil does not need
 * to store object state.
 */
public final class TextUtil {

    /** Prevents other classes from creating TextUtil objects. */
    private TextUtil() {
    }

    /**
     * Converts null into an empty String and trims whitespace.
     *
     * @param text any String, including null
     * @return a safe, trimmed String
     */
    public static String safeTrim(String text) {
        if (text == null) {
            return "";
        }
        return text.trim();
    }

    /**
     * Normalizes text for general fuzzy matching.
     *
     * @param text raw input text
     * @return uppercase text with punctuation replaced by spaces
     */
    public static String normalizeWords(String text) {
        String value = safeTrim(text).toUpperCase(Locale.ROOT);
        value = value.replace('&', ' ');
        value = value.replaceAll("[^A-Z0-9]+", " ");
        return value.trim().replaceAll("\\s+", " ");
    }

    /**
     * Normalizes a course code by removing spaces and punctuation.
     *
     * Example: "CSE 142" and "cse142" both become "CSE142".
     *
     * @param text raw course code or course text
     * @return compact uppercase course code text
     */
    public static String normalizeCompact(String text) {
        return safeTrim(text).toUpperCase(Locale.ROOT).replaceAll("[^A-Z0-9]", "");
    }

    /**
     * Cleans institution names from CSV files.
     *
     * Some files may contain extra phrases such as "COURSE DESCRIPTIONS" after
     * the school name. For display, the program only wants the school name.
     *
     * @param rawSchool raw school name from CSV
     * @return clean display school name
     */
    public static String cleanSchoolName(String rawSchool) {
        String school = safeTrim(rawSchool).toUpperCase(Locale.ROOT);
        school = school.replaceAll("\\s+", " ");

        String[] removablePhrases = {
            " COURSE DESCRIPTIONS",
            " COURSE DESCRIPTION",
            " EQUIVALENCY LIST",
            " EQUIVALENCY",
            " TRANSFER GUIDE",
            " TRANSFER COURSE GUIDE",
            " COURSES"
        };

        for (String phrase : removablePhrases) {
            int index = school.indexOf(phrase);
            if (index >= 0) {
                school = school.substring(0, index).trim();
            }
        }

        if (school.contains("BELLEVUE COLLEGE") || school.contains("BELLEVUE COMMUNITY COLLEGE")) {
            return "BELLEVUE COLLEGE";
        }
        if (school.contains("UNIVERSITY OF WASHINGTON BOTHELL")) {
            return "UNIVERSITY OF WASHINGTON BOTHELL";
        }
        if (school.contains("UNIVERSITY OF WASHINGTON TACOMA")) {
            return "UNIVERSITY OF WASHINGTON TACOMA";
        }
        if (school.contains("SEATTLE UNIVERSITY")) {
            return "SEATTLE UNIVERSITY";
        }
        if (school.contains("WASHINGTON STATE UNIVERSITY")) {
            return "WASHINGTON STATE UNIVERSITY";
        }
        if (school.contains("UNIVERSITY OF WASHINGTON")) {
            return "UNIVERSITY OF WASHINGTON";
        }

        return school;
    }

    /**
     * Expands common short school names into a more searchable form.
     *
     * @param input user input such as "UW", "BC", or "SeattleU"
     * @return expanded school phrase when a known shortcut is found
     */
    public static String expandSchoolAlias(String input) {
        String compact = normalizeCompact(input);
        String words = normalizeWords(input);

        if (compact.equals("BC") || words.contains("BELLEVUE")) {
            return "BELLEVUE COLLEGE";
        }
        if (compact.equals("UW") || compact.equals("UDUB") || words.equals("WASHINGTON")) {
            return "UNIVERSITY OF WASHINGTON";
        }
        if (compact.equals("UWB") || words.contains("BOTHELL")) {
            return "UNIVERSITY OF WASHINGTON BOTHELL";
        }
        if (compact.equals("UWT") || words.contains("TACOMA")) {
            return "UNIVERSITY OF WASHINGTON TACOMA";
        }
        if (compact.equals("SU") || compact.equals("SEATTLEU") || words.contains("SEATTLE UNIVERSITY")) {
            return "SEATTLE UNIVERSITY";
        }
        if (compact.equals("WSU") || words.contains("WASHINGTON STATE")) {
            return "WASHINGTON STATE UNIVERSITY";
        }

        return safeTrim(input);
    }

    /**
     * Checks whether all words in a query appear somewhere in a target string.
     *
     * @param target text being searched
     * @param query user query
     * @return true if every query word is contained in target
     */
    public static boolean containsAllWords(String target, String query) {
        String cleanTarget = normalizeWords(target);
        String cleanQuery = normalizeWords(query);

        if (cleanQuery.isEmpty()) {
            return true;
        }

        String[] words = cleanQuery.split(" ");
        for (String word : words) {
            if (!cleanTarget.contains(word)) {
                return false;
            }
        }
        return true;
    }
}
