package CourseEquivalencyCLI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Writes search results to a text file.
 */
public class ResultExporter {

    /**
     * Exports search results to a txt file.
     *
     * @param fileName output file name
     * @param results search results to export
     * @throws IOException if writing fails
     */
    public void exportToTextFile(String fileName, ArrayList<SearchResult> results) throws IOException {
        String safeFileName = cleanFileName(fileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(safeFileName))) {
            writer.println("Course Equivalency Search Results");
            writer.println("Generated: " + LocalDateTime.now());
            writer.println("Total results: " + results.size());
            writer.println("=".repeat(125));

            for (int i = 0; i < results.size(); i++) {
                writer.println(results.get(i).getEquivalency().formatResult(i + 1));
                writer.println("Score: " + results.get(i).getScore());
                writer.println("-".repeat(125));
            }
        }
    }

    /**
     * Makes sure the file name is safe and ends with .txt.
     *
     * @param fileName raw user input
     * @return safe txt file name
     */
    private String cleanFileName(String fileName) {
        String clean = TextUtil.safeTrim(fileName);
        if (clean.isEmpty()) {
            clean = "search_results.txt";
        }
        clean = clean.replaceAll("[\\\\/:*?\"<>|]", "_");
        if (!clean.toLowerCase().endsWith(".txt")) {
            clean += ".txt";
        }
        return clean;
    }
}
