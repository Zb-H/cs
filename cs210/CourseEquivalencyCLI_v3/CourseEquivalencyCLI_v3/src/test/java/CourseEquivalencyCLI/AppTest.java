package CourseEquivalencyCLI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Basic tests for important helper behavior.
 */
public class AppTest {

    /** Tests that school names with extra words are cleaned for display. */
    @Test
    public void testCleanSchoolName() {
        assertEquals("UNIVERSITY OF WASHINGTON",
                TextUtil.cleanSchoolName("UNIVERSITY OF WASHINGTON COURSE DESCRIPTIONS"));
    }

    /** Tests that credit is extracted from a course string. */
    @Test
    public void testCourseCredit() {
        Course course = new Course("Bellevue College", "CS 210 FUNDAMENTALS OF COMPUTER SCIENCE I (5)");
        assertEquals("CS 210", course.getCourseCode());
        assertEquals("5", course.getCredit());
    }

    /** Tests that common school abbreviations are expanded. */
    @Test
    public void testSchoolAlias() {
        assertEquals("SEATTLE UNIVERSITY", TextUtil.expandSchoolAlias("SeattleU"));
        assertEquals("BELLEVUE COLLEGE", TextUtil.expandSchoolAlias("BC"));
    }

    /** Tests fuzzy search with a small manually created data set. */
    @Test
    public void testFuzzySearch() {
        EquivalencySearchEngine engine = new EquivalencySearchEngine();
        Course bc = new Course("Bellevue College", "CS 210 FUNDAMENTALS OF COMPUTER SCIENCE I (5)");
        Course uw = new Course("University of Washington", "CSE 142 COMPUTER PROGRAMMING I (4)");
        ArrayList<CourseEquivalency> list = new ArrayList<>();
        list.add(new CourseEquivalency(bc, uw, "", "", "", "test.csv"));
        engine.addAll(list);

        assertTrue(engine.searchByKeyword("uw cse142").size() > 0);
    }
}
