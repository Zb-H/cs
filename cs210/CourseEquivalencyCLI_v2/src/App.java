import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the Course Equivalency Lookup program.
 *
 * <p>This program reads one or more CSV files and lets the user search course
 * equivalencies in both directions. For example, the same data row can answer:</p>
 * <ul>
 *   <li>"What is UW CSE 142 at Bellevue College?"</li>
 *   <li>"What is Bellevue College CS 210 at UW?"</li>
 * </ul>
 *
 * <p>Run example:</p>
 * <pre>
 * javac *.java
 * java App UW_EQUIVALENCY_LIST.csv
 * </pre>
 */
public class App {
    /**
     * Program entry point.
     *
     * @param args optional CSV file paths from the command line
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        CsvEquivalencyReader reader = new CsvEquivalencyReader();
        EquivalencySearchEngine searchEngine = new EquivalencySearchEngine();

        loadFiles(args, keyboard, reader, searchEngine);

        if (searchEngine.size() == 0) {
            System.out.println("No equivalency data was loaded. Program ending.");
            return;
        }

        runMenu(keyboard, searchEngine);
    }

    /**
     * Loads CSV files into the search engine.
     *
     * <p>If file paths are passed through command-line args, this method uses
     * those paths. Otherwise, it asks the user to type CSV file paths.</p>
     *
     * @param args command-line arguments
     * @param keyboard Scanner for user input
     * @param reader CSV reader object
     * @param searchEngine search engine that stores the data
     */
    private static void loadFiles(String[] args,
                                  Scanner keyboard,
                                  CsvEquivalencyReader reader,
                                  EquivalencySearchEngine searchEngine) {
        if (args.length > 0) {
            for (String fileName : args) {
                loadOneFile(fileName, reader, searchEngine);
            }
        } else {
            System.out.println("Enter CSV file path. If you have multiple files, separate them with commas.");
            System.out.print("File path(s): ");
            String input = keyboard.nextLine();
            String[] fileNames = input.split(",");

            for (String fileName : fileNames) {
                loadOneFile(fileName.trim(), reader, searchEngine);
            }
        }
    }

    /**
     * Loads one CSV file and adds its data to the search engine.
     *
     * @param fileName CSV file path
     * @param reader CSV reader object
     * @param searchEngine search engine that stores the data
     */
    private static void loadOneFile(String fileName,
                                    CsvEquivalencyReader reader,
                                    EquivalencySearchEngine searchEngine) {
        if (fileName.length() == 0) {
            return;
        }

        try {
            ArrayList<CourseEquivalency> data = reader.readFile(fileName);
            searchEngine.addEquivalencies(data);
            System.out.println("Loaded " + data.size() + " rows from " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read file: " + fileName);
            System.out.println("Reason: " + e.getMessage());
        }
    }

    /**
     * Runs the command-line menu.
     *
     * @param keyboard Scanner for user input
     * @param searchEngine search engine used to find equivalencies
     */
    private static void runMenu(Scanner keyboard, EquivalencySearchEngine searchEngine) {
        boolean keepGoing = true;

        while (keepGoing) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = keyboard.nextLine().trim();

            if (choice.equals("1")) {
                searchForCourse(keyboard, searchEngine);
            } else if (choice.equals("2")) {
                searchEngine.printSchools();
            } else if (choice.equals("3")) {
                keepGoing = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("===== Course Equivalency Lookup =====");
        System.out.println("1. Search by school and course");
        System.out.println("2. Show schools in loaded data");
        System.out.println("3. Exit");
    }

    /**
     * Gets one search request from the user and prints results.
     *
     * @param keyboard Scanner for user input
     * @param searchEngine search engine used to find equivalencies
     */
    private static void searchForCourse(Scanner keyboard, EquivalencySearchEngine searchEngine) {
        System.out.println();
        System.out.println("Examples:");
        System.out.println("School: UW        Course: CSE 142");
        System.out.println("School: BC        Course: CS 210");
        System.out.println("School: UWB       Course: B BIO 180");
        System.out.println("School: SeattleU  Course: CSSE 151");

        System.out.print("Enter school: ");
        String school = keyboard.nextLine();

        System.out.print("Enter course code: ");
        String course = keyboard.nextLine();

        ArrayList<CourseEquivalency> results = searchEngine.search(school, course);

        System.out.println();
        if (results.size() == 0) {
            System.out.println("No result found for " + school + " " + course + ".");
            System.out.println("Tip: choose option 2 to check the school names in the data.");
        } else {
            System.out.println("Found " + results.size() + " result(s):");
            for (int i = 0; i < results.size(); i++) {
                System.out.println("----------------------------------------");
                System.out.println("Result " + (i + 1));
                System.out.print(results.get(i).toDisplayString());
            }
            System.out.println("----------------------------------------");
        }
    }
}
