import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads course equivalency data from a CSV file.
 *
 * <p>This class does not use any outside library. That keeps the project closer
 * to CS210 level. The parser supports commas inside quotes and line breaks
 * inside quoted cells, which are both possible in the provided UW CSV file.</p>
 */
public class CsvEquivalencyReader {
    /** The expected number of columns in this CSV format. */
    private static final int EXPECTED_COLUMNS = 7;

    /** Column index for Transfer Institution. */
    private static final int TRANSFER_INSTITUTION = 0;

    /** Column index for Transfer Course. */
    private static final int TRANSFER_COURSE = 1;

    /** Column index for Receive Institution. */
    private static final int RECEIVE_INSTITUTION = 2;

    /** Column index for Equivalent Course. */
    private static final int EQUIVALENT_COURSE = 3;

    /** Column index for Comments. */
    private static final int COMMENTS = 4;

    /** Column index for Begin Date. */
    private static final int BEGIN_DATE = 5;

    /** Column index for End Date. */
    private static final int END_DATE = 6;

    /**
     * Reads a CSV file and turns each data row into a CourseEquivalency object.
     *
     * @param fileName path to the CSV file
     * @return list of course equivalency rows
     * @throws IOException if the file cannot be read
     */
    public ArrayList<CourseEquivalency> readFile(String fileName) throws IOException {
        ArrayList<CourseEquivalency> equivalencies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // First logical row is the header, so we read and ignore it.
            readLogicalCsvRow(reader);

            String row;
            while ((row = readLogicalCsvRow(reader)) != null) {
                ArrayList<String> values = parseCsvRow(row);

                // Skip broken or incomplete rows instead of crashing the program.
                if (values.size() < EXPECTED_COLUMNS) {
                    continue;
                }

                CourseEquivalency equivalency = new CourseEquivalency(
                        values.get(TRANSFER_INSTITUTION),
                        values.get(TRANSFER_COURSE),
                        values.get(RECEIVE_INSTITUTION),
                        values.get(EQUIVALENT_COURSE),
                        values.get(COMMENTS),
                        values.get(BEGIN_DATE),
                        values.get(END_DATE)
                );

                equivalencies.add(equivalency);
            }
        }

        return equivalencies;
    }

    /**
     * Reads one complete CSV row.
     *
     * <p>A normal row is one line. However, CSV allows a quoted cell to contain
     * line breaks. This method keeps reading until the quote marks are balanced.</p>
     *
     * @param reader the file reader
     * @return one complete CSV row, or null if the file ended
     * @throws IOException if the file cannot be read
     */
    private String readLogicalCsvRow(BufferedReader reader) throws IOException {
        String line = reader.readLine();

        if (line == null) {
            return null;
        }

        StringBuilder row = new StringBuilder(line);

        // If quote marks are not balanced, this row continues onto the next line.
        while (hasUnclosedQuote(row.toString())) {
            String nextLine = reader.readLine();

            if (nextLine == null) {
                break;
            }

            row.append("\n").append(nextLine);
        }

        return row.toString();
    }

    /**
     * Checks whether a CSV row currently has an unclosed quote.
     *
     * @param text current row text
     * @return true if the row is still inside a quoted cell
     */
    private boolean hasUnclosedQuote(String text) {
        boolean inQuotes = false;

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == '"') {
                // In CSV, two quotes inside a quoted cell mean one real quote.
                if (i + 1 < text.length() && text.charAt(i + 1) == '"') {
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            }
        }

        return inQuotes;
    }

    /**
     * Parses one CSV row into cells.
     *
     * <p>This method handles:</p>
     * <ul>
     *   <li>normal commas between cells</li>
     *   <li>commas inside quoted cells</li>
     *   <li>escaped quotes written as two quotes</li>
     * </ul>
     *
     * @param row one complete CSV row
     * @return list of cell values
     */
    private ArrayList<String> parseCsvRow(String row) {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < row.length(); i++) {
            char current = row.charAt(i);

            if (current == '"') {
                if (inQuotes && i + 1 < row.length() && row.charAt(i + 1) == '"') {
                    // Escaped quote inside a quoted cell.
                    currentValue.append('"');
                    i++;
                } else {
                    // Start or end of quoted cell.
                    inQuotes = !inQuotes;
                }
            } else if (current == ',' && !inQuotes) {
                // Comma outside quotes means the current cell is complete.
                values.add(currentValue.toString().trim());
                currentValue.setLength(0);
            } else {
                currentValue.append(current);
            }
        }

        // Add the final cell.
        values.add(currentValue.toString().trim());
        return values;
    }
}
