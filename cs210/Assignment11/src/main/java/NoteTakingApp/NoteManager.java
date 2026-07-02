package NoteTakingApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * NoteManager handles all file operations for the note taking application.
 * It can create folders, append notes, view notes, and search notes.
 */
public class NoteManager {
    private ArrayList<CourseCategory> categories;
    private Path baseFolder;

    /**
     * Creates a NoteManager object.
     * It initializes the course categories and creates folders/files if needed.
     */
    public NoteManager() {
        categories = new ArrayList<>();
        baseFolder = Path.of("notes_data");

        loadDefaultCategories();
        setupFoldersAndFiles();
    }

    /**
     * Adds default course categories to the ArrayList.
     */
    private void loadDefaultCategories() {
        categories.add(new CourseCategory("Math", "Math", "math_notes.txt"));
        categories.add(new CourseCategory("Computer Science", "Computer_Science", "computer_science_notes.txt"));
        categories.add(new CourseCategory("Philosophy", "Philosophy", "philosophy_notes.txt"));
        categories.add(new CourseCategory("Sociology", "Sociology", "sociology_notes.txt"));
    }

    /**
     * Creates the main data folder, course folders, and note files.
     * This method uses exception handling to prevent the program from crashing.
     */
    private void setupFoldersAndFiles() {
        try {
            Files.createDirectories(baseFolder);

            for (CourseCategory category : categories) {
                Path courseFolder = getCourseFolder(category);
                Path noteFile = getNoteFile(category);

                Files.createDirectories(courseFolder);

                if (!Files.exists(noteFile)) {
                    Files.createFile(noteFile);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not create folders or files.");
            System.out.println("Details: " + e.getMessage());
        }
    }

    /**
     * Gets all course categories.
     *
     * @return ArrayList of CourseCategory objects
     */
    public ArrayList<CourseCategory> getCategories() {
        return categories;
    }

    /**
     * Gets one course category by index.
     *
     * @param index the selected index
     * @return the selected CourseCategory
     */
    public CourseCategory getCategory(int index) {
        return categories.get(index);
    }

    /**
     * Builds the folder path for a course category.
     *
     * @param category the selected course category
     * @return path to the course folder
     */
    private Path getCourseFolder(CourseCategory category) {
        return baseFolder.resolve(category.getFolderName());
    }

    /**
     * Builds the note file path for a course category.
     *
     * @param category the selected course category
     * @return path to the course note file
     */
    private Path getNoteFile(CourseCategory category) {
        return getCourseFolder(category).resolve(category.getFileName());
    }

    /**
     * Appends a note to the selected course's note file.
     * The note includes the current date and time.
     *
     * @param category the selected course category
     * @param note     the note text entered by the user
     */
    public void addNote(CourseCategory category, String note) {
        if (note == null || note.trim().isEmpty()) {
            System.out.println("Note cannot be empty.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStamp = LocalDateTime.now().format(formatter);

        String finalNote = "[" + timeStamp + "] " + note + System.lineSeparator();

        try {
            Files.writeString(
                    getNoteFile(category),
                    finalNote,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Could not write note to file.");
            System.out.println("Details: " + e.getMessage());
        }
    }

    /**
     * Displays all notes from the selected course's note file.
     *
     * @param category the selected course category
     */
    public void viewNotes(CourseCategory category) {
        Path noteFile = getNoteFile(category);

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(noteFile));

            if (lines.isEmpty()) {
                System.out.println("No notes found for " + category.getDisplayName() + ".");
                return;
            }

            System.out.println("\n========== " + category.getDisplayName() + " Notes ==========");

            for (String line : lines) {
                System.out.println(line);
            }

            System.out.println("====================================");

        } catch (IOException e) {
            System.out.println("Error: Could not read notes.");
            System.out.println("Details: " + e.getMessage());
        }
    }

    /**
     * Searches the selected course's note file for a keyword.
     * Search is case-insensitive.
     *
     * @param category the selected course category
     * @param keyword  keyword entered by the user
     */
    public void searchNotes(CourseCategory category, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Keyword cannot be empty.");
            return;
        }

        Path noteFile = getNoteFile(category);
        String lowerKeyword = keyword.toLowerCase();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(noteFile));
            boolean found = false;

            System.out.println("\nSearch results for \"" + keyword + "\" in "
                    + category.getDisplayName() + ":");

            for (String line : lines) {
                if (line.toLowerCase().contains(lowerKeyword)) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No matching notes found.");
            }

        } catch (IOException e) {
            System.out.println("Error: Could not search notes.");
            System.out.println("Details: " + e.getMessage());
        }
    }
}
