import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores equivalencies in a HashMap and searches them in both directions.
 *
 * <p>The important data structure is:</p>
 * <pre>
 * HashMap&lt;String, ArrayList&lt;CourseEquivalency&gt;&gt;
 * </pre>
 *
 * <p>The key is "school|courseCode". For example:</p>
 * <pre>
 * BELLEVUE COLLEGE|CS210
 * UNIVERSITY OF WASHINGTON|CSE142
 * </pre>
 *
 * <p>When we add one equivalency row, we add it twice: once for the transfer
 * side and once for the equivalent side. That is why the search is bidirectional.</p>
 */
public class EquivalencySearchEngine {
    private ArrayList<CourseEquivalency> allEquivalencies;
    private HashMap<String, ArrayList<CourseEquivalency>> equivalenciesByCourse;

    /**
     * Creates an empty search engine.
     */
    public EquivalencySearchEngine() {
        allEquivalencies = new ArrayList<>();
        equivalenciesByCourse = new HashMap<>();
    }

    /**
     * Adds many equivalency rows to the search engine.
     *
     * @param equivalencies rows loaded from a CSV file
     */
    public void addEquivalencies(ArrayList<CourseEquivalency> equivalencies) {
        for (CourseEquivalency equivalency : equivalencies) {
            addEquivalency(equivalency);
        }
    }

    /**
     * Adds one equivalency row and indexes both sides.
     *
     * @param equivalency one CSV equivalency row
     */
    public void addEquivalency(CourseEquivalency equivalency) {
        allEquivalencies.add(equivalency);

        // Index the transfer side.
        indexCourse(equivalency.getTransferCourse(), equivalency);

        // Index the equivalent side. This is what makes the search bidirectional.
        indexCourse(equivalency.getEquivalentCourse(), equivalency);
    }

    /**
     * Adds one course side into the HashMap index.
     *
     * @param course the course to index
     * @param equivalency the row that contains this course
     */
    private void indexCourse(Course course, CourseEquivalency equivalency) {
        ArrayList<String> courseCodeKeys = Course.extractCourseCodeKeys(course.getCourseText());

        for (String courseCodeKey : courseCodeKeys) {
            String searchKey = Course.buildSearchKey(course.getInstitution(), courseCodeKey);

            if (!equivalenciesByCourse.containsKey(searchKey)) {
                equivalenciesByCourse.put(searchKey, new ArrayList<CourseEquivalency>());
            }

            equivalenciesByCourse.get(searchKey).add(equivalency);
        }
    }

    /**
     * Searches for equivalencies by school and course.
     *
     * <p>Example searches:</p>
     * <ul>
     *   <li>school = "BC", course = "CS 210"</li>
     *   <li>school = "UW", course = "CSE 142"</li>
     *   <li>school = "Bellevue College", course = "MATH&152"</li>
     * </ul>
     *
     * @param institution school name typed by the user
     * @param courseInput course code typed by the user
     * @return matching equivalency rows
     */
    public ArrayList<CourseEquivalency> search(String institution, String courseInput) {
        String schoolKey = Course.normalizeInstitution(institution);
        ArrayList<String> courseCodeKeys = Course.extractCourseCodeKeys(courseInput);
        ArrayList<CourseEquivalency> results = new ArrayList<>();

        // Exact course-code search through HashMap.
        for (String courseCodeKey : courseCodeKeys) {
            String searchKey = Course.buildSearchKey(schoolKey, courseCodeKey);
            ArrayList<CourseEquivalency> matches = equivalenciesByCourse.get(searchKey);

            if (matches != null) {
                addWithoutDuplicates(results, matches);
            }
        }

        // If exact course-code search fails, try a slower keyword search.
        // This helps when the user types part of a course title instead of a course code.
        if (results.size() == 0) {
            results = searchByKeyword(schoolKey, courseInput);
        }

        return results;
    }

    /**
     * Adds results without duplicates.
     *
     * @param target the list that receives new rows
     * @param source the list of rows to add
     */
    private void addWithoutDuplicates(ArrayList<CourseEquivalency> target,
                                      ArrayList<CourseEquivalency> source) {
        for (CourseEquivalency equivalency : source) {
            if (!target.contains(equivalency)) {
                target.add(equivalency);
            }
        }
    }

    /**
     * Searches by checking whether the raw course text contains the user's text.
     *
     * <p>This is slower than HashMap search because it loops through every row.
     * But it is still useful as a backup search.</p>
     *
     * @param normalizedInstitution normalized school name
     * @param courseInput course text typed by the user
     * @return matching rows
     */
    private ArrayList<CourseEquivalency> searchByKeyword(String normalizedInstitution, String courseInput) {
        ArrayList<CourseEquivalency> results = new ArrayList<>();
        String keyword = Course.normalizeCourseCode(courseInput);

        for (CourseEquivalency equivalency : allEquivalencies) {
            Course transfer = equivalency.getTransferCourse();
            Course equivalent = equivalency.getEquivalentCourse();

            if (courseMatches(transfer, normalizedInstitution, keyword)
                    || courseMatches(equivalent, normalizedInstitution, keyword)) {
                results.add(equivalency);
            }
        }

        return results;
    }

    /**
     * Checks whether one Course object matches the user's search.
     *
     * @param course the course object being checked
     * @param normalizedInstitution normalized school name
     * @param keyword normalized search keyword
     * @return true if this course belongs to the school and contains the keyword
     */
    private boolean courseMatches(Course course, String normalizedInstitution, String keyword) {
        String courseSchool = Course.normalizeInstitution(course.getInstitution());
        String courseText = Course.normalizeCourseCode(course.getCourseText());

        return courseSchool.equals(normalizedInstitution) && courseText.contains(keyword);
    }

    /**
     * @return number of loaded equivalency rows
     */
    public int size() {
        return allEquivalencies.size();
    }

    /**
     * Prints all school names currently found in the data.
     * This is useful when the user is not sure what school name to type.
     */
    public void printSchools() {
        ArrayList<String> schools = new ArrayList<>();

        for (CourseEquivalency equivalency : allEquivalencies) {
            addSchoolIfNew(schools, equivalency.getTransferCourse().getInstitution());
            addSchoolIfNew(schools, equivalency.getEquivalentCourse().getInstitution());
        }

        System.out.println("Schools in loaded data:");
        for (String school : schools) {
            System.out.println("- " + school);
        }
    }

    /**
     * Adds a school to the list if it is not already there.
     *
     * @param schools current school list
     * @param school school name to add
     */
    private void addSchoolIfNew(ArrayList<String> schools, String school) {
        if (!schools.contains(school)) {
            schools.add(school);
        }
    }
}
