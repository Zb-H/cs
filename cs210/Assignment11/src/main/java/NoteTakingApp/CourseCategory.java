package NoteTakingApp;

/**
 * CourseCategory represents one course subject/category.
 * Each category has a display name, a folder name, and a file name.
 */
public class CourseCategory {
    private String displayName;
    private String folderName;
    private String fileName;

    /**
     * Creates a course category object.
     *
     * @param displayName the name shown to the user
     * @param folderName  the folder used to store this course's notes
     * @param fileName    the file used to store this course's notes
     */
    public CourseCategory(String displayName, String folderName, String fileName) {
        this.displayName = displayName;
        this.folderName = folderName;
        this.fileName = fileName;
    }

    /**
     * Gets the display name of the course.
     *
     * @return course display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the folder name for this course.
     *
     * @return folder name
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Gets the note file name for this course.
     *
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the course display name.
     *
     * @return display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}
