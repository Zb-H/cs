package NoteTakingApp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * App is the main class for the Note Taking Application.
 * It displays the menu and handles user input.
 */
public class App {
    private static Scanner sc = new Scanner(System.in);
    private static NoteManager noteManager = new NoteManager();

    /**
     * Entrance of the program.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNoteMenu();
                    break;
                case 2:
                    viewNotesMenu();
                    break;
                case 3:
                    searchNotesMenu();
                    break;
                case 4:
                    listCategories();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    /**
     * Prints the main menu.
     */
    private static void printMainMenu() {
        System.out.println("\n********* Main Menu *********");
        System.out.println("1. Add a note");
        System.out.println("2. View notes");
        System.out.println("3. Search notes");
        System.out.println("4. List course categories");
        System.out.println("0. Exit");
        System.out.println("*******************************");
    }

    /**
     * Handles the add note menu.
     */
    private static void addNoteMenu() {
        CourseCategory category = selectCategory();

        if (category == null) {
            return;
        }

        System.out.println("Enter your note for " + category.getDisplayName() + ":");
        String note = sc.nextLine();

        noteManager.addNote(category, note);
    }

    /**
     * Handles the view notes menu.
     */
    private static void viewNotesMenu() {
        CourseCategory category = selectCategory();

        if (category == null) {
            return;
        }

        noteManager.viewNotes(category);
    }

    /**
     * Handles the search notes menu.
     */
    private static void searchNotesMenu() {
        CourseCategory category = selectCategory();

        if (category == null) {
            return;
        }

        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        noteManager.searchNotes(category, keyword);
    }

    /**
     * Allows the user to select a course category.
     *
     * @return selected CourseCategory, or null if selection is invalid
     */
    private static CourseCategory selectCategory() {
        ArrayList<CourseCategory> categories = noteManager.getCategories();

        System.out.println("\nChoose a course category:");

        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getDisplayName());
        }

        System.out.println("0. Cancel");

        int choice = getIntInput("Enter category number: ");

        if (choice == 0) {
            System.out.println("Cancelled.");
            return null;
        }

        if (choice < 1 || choice > categories.size()) {
            System.out.println("Invalid category.");
            return null;
        }

        return noteManager.getCategory(choice - 1);
    }

    /**
     * Prints all available categories.
     */
    private static void listCategories() {
        ArrayList<CourseCategory> categories = noteManager.getCategories();

        System.out.println("\nAvailable course categories:");

        for (CourseCategory category : categories) {
            System.out.println("- " + category.getDisplayName());
        }
    }

    /**
     * Gets an integer input from the user.
     * This method prevents the program from crashing if the user enters text.
     *
     * @param prompt message shown to the user
     * @return valid integer input
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = sc.nextInt();
                sc.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}
