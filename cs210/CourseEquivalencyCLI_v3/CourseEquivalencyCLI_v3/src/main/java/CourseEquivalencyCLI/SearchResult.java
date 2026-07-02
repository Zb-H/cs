package CourseEquivalencyCLI;

/**
 * Stores one search result and its score.
 *
 * The score is used to sort fuzzy search results so stronger matches appear first.
 */
public class SearchResult implements Comparable<SearchResult> {
    private final CourseEquivalency equivalency;
    private final int score;

    /**
     * Creates a search result.
     *
     * @param equivalency matching equivalency
     * @param score fuzzy match score
     */
    public SearchResult(CourseEquivalency equivalency, int score) {
        this.equivalency = equivalency;
        this.score = score;
    }

    /**
     * @return matching equivalency
     */
    public CourseEquivalency getEquivalency() {
        return equivalency;
    }

    /**
     * @return fuzzy match score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sorts higher scores first.
     *
     * @param other another search result
     * @return comparison value
     */
    @Override
    public int compareTo(SearchResult other) {
        return Integer.compare(other.score, this.score);
    }
}
