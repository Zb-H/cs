package CourseEquivalencyCLI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the Course Equivalency CLI program.
 *
 * This program reads one or more CSV files, stores course equivalency data,
 * and lets the user search for equivalent courses from the terminal.
 *
 * @author Zhaobi Huang
 * @git URL: https://github.com/Zb-H/cs/tree/main/cs210/Assignment10
 */
public class App {
    private static final String[] DEFAULT_FILES = {
        "data/SEATTLEU_EQUIVALENCY_LIST.csv",
        "data/UW_EQUIVALENCY_LIST.csv",
        "data/UWB_EQUIVALENCY_LIST.csv",
        "data/UWT_EQUIVALENCY_LIST.csv",
        "data/WSU_EQUIVALENCY_LIST.csv"
    };

    /**
     * Program entry point.
     *
     * @param args optional CSV file paths. If none are provided, default files are used.
     */
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            args = DEFAULT_FILES;
        }

        Scanner input = new Scanner(System.in);
        CsvEquivalencyReader reader = new CsvEquivalencyReader();
        EquivalencySearchEngine searchEngine = new EquivalencySearchEngine();
        ArrayList<SearchResult> lastResults = new ArrayList<>();

        loadFiles(args, reader, searchEngine);

        if (searchEngine.size() == 0) {
            System.out.println("No course data was loaded. Please check your CSV files in the data folder.");
            input.close();
            return;
        }

        boolean running = true;
        while (running) {
            try {
                printMenu();
                String choice = readRequiredLine(input, "Choose an option: ");

                if (choice.equals("1")) {
                    lastResults = handleSchoolCourseSearch(input, searchEngine);
                    askExport(input, lastResults);
                } else if (choice.equals("2")) {
                    lastResults = handleKeywordSearch(input, searchEngine);
                    askExport(input, lastResults);
                } else if (choice.equals("3")) {
                    printSchools(searchEngine);
                } else if (choice.equals("4")) {
                    askExport(input, lastResults);
                } else if (choice.equals("5")) {
                    running = false;
                } else {
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong, but the program did not crash.");
                System.out.println("Please try again. Reason: " + e.getMessage());
            }
        }

        input.close();
        System.out.println("Program ended.");
    }

    /**
     * Loads all CSV files into the search engine.
     *
     * @param filePaths CSV file paths
     * @param reader CSV reader
     * @param searchEngine search engine
     */
    private static void loadFiles(String[] filePaths, CsvEquivalencyReader reader,
                                  EquivalencySearchEngine searchEngine) {
        System.out.println("Loading CSV files...");
        for (String filePath : filePaths) {
            try {
                ArrayList<CourseEquivalency> loaded = reader.readFile(filePath);
                searchEngine.addAll(loaded);
                System.out.println("Loaded " + loaded.size() + " records from " + filePath);
            } catch (IOException e) {
                System.out.println("Skipped file: " + filePath);
                System.out.println("Reason: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Skipped file because of unexpected data: " + filePath);
                System.out.println("Reason: " + e.getMessage());
            }
        }
        System.out.println("Total searchable records: " + searchEngine.size());
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("===== Course Equivalency Lookup =====");
        System.out.println("1. Search by school + course");
        System.out.println("2. Fuzzy search with one line");
        System.out.println("3. Show loaded schools");
        System.out.println("4. Export last search results to txt");
        System.out.println("5. Exit");
    }

    /**
     * Handles separate school and course search.
     *
     * @param input scanner
     * @param searchEngine search engine
     * @return latest results
     */
    private static ArrayList<SearchResult> handleSchoolCourseSearch(Scanner input,
                                                                    EquivalencySearchEngine searchEngine) {
        String school;
        String course;

        while (true) {
            school = readLine(input, "School name or abbreviation, e.g. UW, BC, Bellevue, SeattleU: ");
            course = readLine(input, "Course code or words, e.g. CSE 142, CS210, programming: ");

            if (!school.isBlank() || !course.isBlank()) {
                break;
            }
            System.out.println("Please enter at least a school or a course. Both cannot be blank.");
        }

        ArrayList<SearchResult> results = searchEngine.searchBySchoolAndCourse(school, course);
        printResults(results);
        return results;
    }

    /**
     * Handles one-line fuzzy keyword search.
     *
     * @param input scanner
     * @param searchEngine search engine
     * @return latest results
     */
    private static ArrayList<SearchResult> handleKeywordSearch(Scanner input,
                                                               EquivalencySearchEngine searchEngine) {
        String query = readRequiredLine(input, "Type anything, e.g. 'uw cse142', 'bc cs210', 'seattleu programming': ");
        ArrayList<SearchResult> results = searchEngine.searchByKeyword(query);
        printResults(results);
        return results;
    }

    /**
     * Prints search results.
     *
     * @param results search results
     */
    private static void printResults(ArrayList<SearchResult> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("No matching results found. Try a shorter course code or a school abbreviation.");
            return;
        }

        System.out.println();
        System.out.println("Found " + results.size() + " result(s). Showing strongest matches first.");
        System.out.println("=".repeat(125));

        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i).getEquivalency().formatResult(i + 1));
            System.out.println("Score: " + results.get(i).getScore());
            System.out.println("-".repeat(125));
        }
    }

    /**
     * Prints loaded schools.
     *
     * @param searchEngine search engine
     */
    private static void printSchools(EquivalencySearchEngine searchEngine) {
        ArrayList<String> schools = searchEngine.getSchools();
        System.out.println("Loaded schools:");
        for (String school : schools) {
            System.out.println("- " + school);
        }
    }

    /**
     * Asks the user whether to export results.
     *
     * @param input scanner
     * @param results latest search results
     */
    private static void askExport(Scanner input, ArrayList<SearchResult> results) {
        if (results == null || results.isEmpty()) {
            return;
        }

        while (true) {
            String answer = readLine(input, "Export these results to a txt file? (y/n): ").toLowerCase();
            if (answer.equals("n") || answer.equals("no")) {
                return;
            }
            if (answer.equals("y") || answer.equals("yes")) {
                String fileName = readLine(input, "Output file name, or press Enter for search_results.txt: ");
                ResultExporter exporter = new ResultExporter();
                try {
                    exporter.exportToTextFile(fileName, results);
                    System.out.println("Results exported successfully.");
                } catch (IOException e) {
                    System.out.println("Could not export results: " + e.getMessage());
                }
                return;
            }
            System.out.println("Please type y or n.");
        }
    }

    /**
     * Reads a line safely.
     *
     * @param input scanner
     * @param prompt prompt text
     * @return trimmed line
     */
    private static String readLine(Scanner input, String prompt) {
        System.out.print(prompt);
        try {
            if (input.hasNextLine()) {
                return TextUtil.safeTrim(input.nextLine());
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    /**
     * Repeatedly asks for input until the user enters non-blank text.
     *
     * @param input scanner
     * @param prompt prompt text
     * @return non-blank input
     */
    private static String readRequiredLine(Scanner input, String prompt) {
        while (true) {
            String line = readLine(input, prompt);
            if (!line.isBlank()) {
                return line;
            }
            System.out.println("Input cannot be blank. Please try again.");
        }
    }
}
