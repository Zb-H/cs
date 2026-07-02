package CourseEquivalencyCLI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Stores course equivalencies and performs exact/fuzzy searches.
 *
 * This class is the main data-processing class. It keeps an ArrayList of all
 * equivalencies and searches through them using normalized text. The program
 * could use a HashMap for strict exact matching, but fuzzy matching is easier
 * to explain at CS210 level with a score-based ArrayList search.
 */
public class EquivalencySearchEngine {
    private final ArrayList<CourseEquivalency> equivalencies;

    /**
     * Creates an empty search engine.
     */
    public EquivalencySearchEngine() {
        equivalencies = new ArrayList<>();
    }

    /**
     * Adds many equivalencies to the search engine.
     *
     * @param newEquivalencies equivalencies from one or more CSV files
     */
    public void addAll(ArrayList<CourseEquivalency> newEquivalencies) {
        if (newEquivalencies == null) {
            return;
        }
        equivalencies.addAll(newEquivalencies);
    }

    /**
     * @return number of equivalencies stored
     */
    public int size() {
        return equivalencies.size();
    }

    /**
     * Searches by separate school and course inputs.
     *
     * Either input may be blank, but both cannot be blank. The method never
     * throws an exception for strange input; it simply returns an empty list.
     *
     * @param schoolInput user-entered school name, abbreviation, or phrase
     * @param courseInput user-entered course code or course phrase
     * @return sorted fuzzy search results
     */
    public ArrayList<SearchResult> searchBySchoolAndCourse(String schoolInput, String courseInput) {
        ArrayList<SearchResult> results = new ArrayList<>();
        String schoolQuery = TextUtil.expandSchoolAlias(TextUtil.safeTrim(schoolInput));
        String courseQuery = TextUtil.safeTrim(courseInput);

        if (schoolQuery.isBlank() && courseQuery.isBlank()) {
            return results;
        }

        boolean hasSchool = !TextUtil.safeTrim(schoolQuery).isBlank();
        boolean hasCourse = !TextUtil.safeTrim(courseQuery).isBlank();

        for (CourseEquivalency equivalency : equivalencies) {
            int leftScore = scoreSide(equivalency.getLeftCourse(), schoolQuery, courseQuery);
            int rightScore = scoreSide(equivalency.getRightCourse(), schoolQuery, courseQuery);
            int bestScore = Math.max(leftScore, rightScore);

            int neededScore;
            if (hasSchool && hasCourse) {
                neededScore = 85;
            } else if (hasCourse) {
                neededScore = 45;
            } else {
                neededScore = 50;
            }

            if (bestScore >= neededScore) {
                results.add(new SearchResult(equivalency, bestScore));
            }
        }

        Collections.sort(results);
        return limitResults(results, 50);
    }

    /**
     * Searches using one flexible line of text.
     *
     * Example inputs include "uw cse142", "bellevue cs 210", "seattleu csse",
     * or even partial phrases like "programming 142".
     *
     * @param query user-entered search phrase
     * @return sorted fuzzy search results
     */
    public ArrayList<SearchResult> searchByKeyword(String query) {
        ArrayList<SearchResult> results = new ArrayList<>();
        String safeQuery = TextUtil.safeTrim(query);

        if (safeQuery.isBlank()) {
            return results;
        }

        String expandedQuery = TextUtil.expandSchoolAlias(safeQuery);
        for (CourseEquivalency equivalency : equivalencies) {
            int score = scoreKeyword(equivalency, safeQuery, expandedQuery);
            if (score >= 25) {
                results.add(new SearchResult(equivalency, score));
            }
        }

        Collections.sort(results);
        return limitResults(results, 50);
    }

    /**
     * Scores how well one side of an equivalency matches school and course input.
     *
     * @param course course side being scored
     * @param schoolQuery school input
     * @param courseQuery course input
     * @return score value
     */
    private int scoreSide(Course course, String schoolQuery, String courseQuery) {
        boolean hasSchool = !TextUtil.safeTrim(schoolQuery).isBlank();
        boolean hasCourse = !TextUtil.safeTrim(courseQuery).isBlank();

        int schoolScore = hasSchool ? scoreSchool(course.getSchool(), schoolQuery) : 0;
        int courseScore = hasCourse ? scoreCourse(course, courseQuery) : 0;

        if (hasSchool && hasCourse) {
            if (schoolScore > 0 && courseScore > 0) {
                return schoolScore + courseScore;
            }
            return 0;
        }
        if (hasSchool) {
            return schoolScore;
        }
        return courseScore;
    }

    /**
     * Scores school-name similarity.
     *
     * @param school stored school name
     * @param query school query
     * @return score value
     */
    private int scoreSchool(String school, String query) {
        String expandedQuery = TextUtil.expandSchoolAlias(query);
        String schoolWords = TextUtil.normalizeWords(school);
        String queryWords = TextUtil.normalizeWords(expandedQuery);
        String schoolCompact = TextUtil.normalizeCompact(school);
        String queryCompact = TextUtil.normalizeCompact(expandedQuery);

        if (queryWords.isEmpty()) {
            return 0;
        }
        if (schoolWords.equals(queryWords)) {
            return 60;
        }
        if (schoolCompact.equals(queryCompact)) {
            return 60;
        }
        if (schoolWords.contains(queryWords) || queryWords.contains(schoolWords)) {
            return 50;
        }
        if (TextUtil.containsAllWords(schoolWords, queryWords)) {
            return 40;
        }
        return 0;
    }

    /**
     * Scores course-code and title similarity.
     *
     * @param course stored course
     * @param query course query
     * @return score value
     */
    private int scoreCourse(Course course, String query) {
        String courseCodeCompact = TextUtil.normalizeCompact(course.getCourseCode());
        String queryCompact = TextUtil.normalizeCompact(query);
        String courseText = course.getSearchText();

        if (queryCompact.isEmpty()) {
            return 0;
        }
        if (courseCodeCompact.equals(queryCompact)) {
            return 80;
        }
        if (courseCodeCompact.contains(queryCompact) || queryCompact.contains(courseCodeCompact)) {
            return 65;
        }
        if (TextUtil.containsAllWords(courseText, query)) {
            return 45;
        }
        if (TextUtil.normalizeWords(courseText).contains(TextUtil.normalizeWords(query))) {
            return 35;
        }
        return 0;
    }

    /**
     * Scores one-line keyword searches.
     *
     * @param equivalency equivalency being checked
     * @param rawQuery original user query
     * @param expandedQuery query after possible school alias expansion
     * @return score value
     */
    private int scoreKeyword(CourseEquivalency equivalency, String rawQuery, String expandedQuery) {
        String searchText = equivalency.getSearchText();
        String compactSearchText = TextUtil.normalizeCompact(searchText);
        String compactRawQuery = TextUtil.normalizeCompact(rawQuery);
        String compactExpandedQuery = TextUtil.normalizeCompact(expandedQuery);
        int score = 0;

        if (compactSearchText.contains(compactRawQuery)) {
            score += 45;
        }
        if (!compactExpandedQuery.equals(compactRawQuery) && compactSearchText.contains(compactExpandedQuery)) {
            score += 45;
        }
        if (TextUtil.containsAllWords(searchText, rawQuery)) {
            score += 35;
        }
        if (!expandedQuery.equals(rawQuery) && TextUtil.containsAllWords(searchText, expandedQuery)) {
            score += 35;
        }

        score = Math.max(score, scoreCourse(equivalency.getLeftCourse(), rawQuery));
        score = Math.max(score, scoreCourse(equivalency.getRightCourse(), rawQuery));
        score = Math.max(score, scoreSchool(equivalency.getLeftCourse().getSchool(), rawQuery));
        score = Math.max(score, scoreSchool(equivalency.getRightCourse().getSchool(), rawQuery));
        score = Math.max(score, scoreSmartCombinedQuery(equivalency.getLeftCourse(), rawQuery));
        score = Math.max(score, scoreSmartCombinedQuery(equivalency.getRightCourse(), rawQuery));

        return score;
    }

    /**
     * Handles compact searches such as "bccs210" or "uw cse142".
     *
     * @param course stored course side
     * @param rawQuery full user query
     * @return score value
     */
    private int scoreSmartCombinedQuery(Course course, String rawQuery) {
        String[] aliases = {
            "BELLEVUE COLLEGE", "BELLEVUE", "BC",
            "UNIVERSITY OF WASHINGTON BOTHELL", "BOTHELL", "UWB",
            "UNIVERSITY OF WASHINGTON TACOMA", "TACOMA", "UWT",
            "UNIVERSITY OF WASHINGTON", "WASHINGTON", "UW", "UDUB",
            "SEATTLE UNIVERSITY", "SEATTLEU", "SU",
            "WASHINGTON STATE UNIVERSITY", "WASHINGTON STATE", "WSU"
        };

        String compactQuery = TextUtil.normalizeCompact(rawQuery);
        String wordQuery = TextUtil.normalizeWords(rawQuery);

        for (String alias : aliases) {
            String aliasCompact = TextUtil.normalizeCompact(alias);
            String aliasWords = TextUtil.normalizeWords(alias);
            boolean hasAlias = compactQuery.startsWith(aliasCompact)
                    || compactQuery.contains(aliasCompact)
                    || wordQuery.contains(aliasWords);

            if (hasAlias) {
                int schoolScore = scoreSchool(course.getSchool(), alias);
                if (schoolScore == 0) {
                    continue;
                }

                String remainingCompact = compactQuery.replace(aliasCompact, "");
                String remainingWords = wordQuery.replace(aliasWords, "").trim();
                int courseScore = Math.max(scoreCourse(course, remainingCompact), scoreCourse(course, remainingWords));

                if (courseScore > 0) {
                    return schoolScore + courseScore + 20;
                }
            }
        }
        return 0;
    }

    /**
     * Limits the result list to avoid printing hundreds of matches.
     *
     * @param results sorted results
     * @param max maximum number of results
     * @return limited list
     */
    private ArrayList<SearchResult> limitResults(ArrayList<SearchResult> results, int max) {
        ArrayList<SearchResult> limited = new ArrayList<>();
        int count = Math.min(max, results.size());
        for (int i = 0; i < count; i++) {
            limited.add(results.get(i));
        }
        return limited;
    }

    /**
     * Returns all school names found in the loaded data.
     *
     * @return sorted list of school names
     */
    public ArrayList<String> getSchools() {
        HashSet<String> schools = new HashSet<>();
        for (CourseEquivalency equivalency : equivalencies) {
            schools.add(equivalency.getLeftCourse().getSchool());
            schools.add(equivalency.getRightCourse().getSchool());
        }

        ArrayList<String> sortedSchools = new ArrayList<>(schools);
        Collections.sort(sortedSchools);
        return sortedSchools;
    }
}
