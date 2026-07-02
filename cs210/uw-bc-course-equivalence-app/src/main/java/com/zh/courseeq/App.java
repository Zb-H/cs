package com.zh.courseeq;

import com.zh.courseeq.gui.EquivalencyFrame;
import com.zh.courseeq.model.CourseEquivalency;
import com.zh.courseeq.repository.EquivalencyRepository;
import com.zh.courseeq.service.SearchMode;
import com.zh.courseeq.service.SearchService;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the UW/BC course equivalency app.
 *
 * <p>Run with no arguments for CLI mode. Run with "gui" for Swing GUI mode:</p>
 * <pre>mvn exec:java -Dexec.args="gui"</pre>
 */
public class App {
    private static final String DEFAULT_CSV = "data/course_equivalencies.csv";

    public static void main(String[] args) {
        try {
            EquivalencyRepository repository = new EquivalencyRepository(Path.of(DEFAULT_CSV));
            SearchService service = new SearchService(repository.findAll());

            if (args.length > 0 && args[0].equalsIgnoreCase("gui")) {
                SwingUtilities.invokeLater(() -> new EquivalencyFrame(service).setVisible(true));
            } else {
                runCli(service);
            }
        } catch (IOException e) {
            System.out.println("Could not load course data: " + e.getMessage());
            System.out.println("Make sure you run this command from the project root folder.");
        }
    }

    private static void runCli(SearchService service) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("UW <-> Bellevue College Course Equivalence App");
        System.out.println("Loaded " + service.count() + " equivalency records.\n");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> search(scanner, service, SearchMode.UW_COURSE);
                case "2" -> search(scanner, service, SearchMode.BC_COURSE);
                case "3" -> search(scanner, service, SearchMode.KEYWORD);
                case "4" -> printFirst(service.findAll(), 15);
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Search by UW course, e.g. CSE 142 or MATH 124");
        System.out.println("2. Search by BC course, e.g. CS 210 or MATH& 151");
        System.out.println("3. Search by keyword, e.g. calculus, anthropology, programming");
        System.out.println("4. Show sample records");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private static void search(Scanner scanner, SearchService service, SearchMode mode) {
        System.out.print("Enter search text: ");
        String query = scanner.nextLine().trim();

        List<CourseEquivalency> results = service.search(query, mode);
        if (results.isEmpty()) {
            System.out.println("No results found for: " + query);
            return;
        }

        System.out.println("Found " + results.size() + " result(s). Showing up to 20:");
        printFirst(results, 20);
    }

    private static void printFirst(List<CourseEquivalency> records, int limit) {
        int count = Math.min(records.size(), limit);
        for (int i = 0; i < count; i++) {
            CourseEquivalency record = records.get(i);
            System.out.printf("%n%d) %s%n", i + 1, record.getTransferCourse());
            System.out.printf("   => %s%n", record.getEquivalentCourse());
            if (!record.getComments().isBlank()) {
                System.out.printf("   Notes: %s%n", record.getComments());
            }
            if (!record.getBeginDate().isBlank() || !record.getEndDate().isBlank()) {
                System.out.printf("   Date range: %s to %s%n", emptyAsDash(record.getBeginDate()), emptyAsDash(record.getEndDate()));
            }
        }
    }

    private static String emptyAsDash(String value) {
        return value == null || value.isBlank() ? "N/A" : value;
    }
}
