package CourseEquivalencyCLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Reads course equivalency records from a CSV file.
 *
 * This reader is more robust than a simple line.split(",") solution. It can
 * handle quoted CSV fields, commas inside quotes, and newline characters inside
 * quoted cells.
 */
public class CsvEquivalencyReader {

    /**
     * Reads a CSV file and returns all valid course equivalencies.
     *
     * @param filePath path to the CSV file
     * @return list of equivalencies found in the file
     * @throws IOException if the file cannot be opened or read
     */
    public ArrayList<CourseEquivalency> readFile(String filePath) throws IOException {
        ArrayList<CourseEquivalency> equivalencies = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            throw new IOException("File does not exist: " + filePath);
        }

        ArrayList<List<String>> records = readCsvRecords(file);
        if (records.isEmpty()) {
            return equivalencies;
        }

        HashMap<String, Integer> headerMap = buildHeaderMap(records.get(0));
        int transferSchoolIndex = getRequiredColumn(headerMap, "TRANSFER INSTITUTION");
        int transferCourseIndex = getRequiredColumn(headerMap, "TRANSFER COURSE");
        int receiveSchoolIndex = getRequiredColumn(headerMap, "RECEIVE INSTITUTION");
        int equivalentCourseIndex = getRequiredColumn(headerMap, "EQUIVALENT COURSE");
        int commentsIndex = getOptionalColumn(headerMap, "COMMENTS");
        int beginDateIndex = getOptionalColumn(headerMap, "BEGIN DATE");
        int endDateIndex = getOptionalColumn(headerMap, "END DATE");

        for (int i = 1; i < records.size(); i++) {
            List<String> record = records.get(i);

            String transferSchool = adjustSchoolBySourceFile(getCell(record, transferSchoolIndex), file.getName());
            String transferCourseCell = getCell(record, transferCourseIndex);
            String receiveSchool = adjustSchoolBySourceFile(getCell(record, receiveSchoolIndex), file.getName());
            String equivalentCourseCell = getCell(record, equivalentCourseIndex);
            String comments = getCell(record, commentsIndex);
            String beginDate = getCell(record, beginDateIndex);
            String endDate = getCell(record, endDateIndex);

            if (transferSchool.isBlank() || transferCourseCell.isBlank()
                    || receiveSchool.isBlank() || equivalentCourseCell.isBlank()) {
                continue;
            }

            ArrayList<String> transferCourses = splitCourseCell(transferCourseCell);
            ArrayList<String> equivalentCourses = splitCourseCell(equivalentCourseCell);

            for (String transferCourseText : transferCourses) {
                for (String equivalentCourseText : equivalentCourses) {
                    Course transferCourse = new Course(transferSchool, transferCourseText);
                    Course equivalentCourse = new Course(receiveSchool, equivalentCourseText);
                    CourseEquivalency equivalency = new CourseEquivalency(
                            transferCourse,
                            equivalentCourse,
                            comments,
                            beginDate,
                            endDate,
                            file.getName()
                    );
                    equivalencies.add(equivalency);
                }
            }
        }

        return equivalencies;
    }

    /**
     * Uses the source file name to make UW branch names clearer when the CSV
     * itself only says "UNIVERSITY OF WASHINGTON".
     *
     * @param rawSchool school name from CSV
     * @param sourceFile CSV file name
     * @return adjusted school name
     */
    private String adjustSchoolBySourceFile(String rawSchool, String sourceFile) {
        String cleanSchool = TextUtil.cleanSchoolName(rawSchool);
        String fileName = TextUtil.normalizeCompact(sourceFile);

        if (cleanSchool.equals("UNIVERSITY OF WASHINGTON") && fileName.contains("UWB")) {
            return "UNIVERSITY OF WASHINGTON BOTHELL";
        }
        if (cleanSchool.equals("UNIVERSITY OF WASHINGTON") && fileName.contains("UWT")) {
            return "UNIVERSITY OF WASHINGTON TACOMA";
        }
        return cleanSchool;
    }

    /**
     * Reads CSV records from a file character by character.
     *
     * @param file CSV file
     * @return list of CSV records, where each record is a list of fields
     * @throws IOException if reading fails
     */
    private ArrayList<List<String>> readCsvRecords(File file) throws IOException {
        ArrayList<List<String>> records = new ArrayList<>();
        ArrayList<String> currentRecord = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value;
            while ((value = reader.read()) != -1) {
                char c = (char) value;

                if (c == '"') {
                    reader.mark(1);
                    int next = reader.read();

                    if (inQuotes && next == '"') {
                        currentField.append('"');
                    } else {
                        inQuotes = !inQuotes;
                        if (next != -1) {
                            reader.reset();
                        }
                    }
                } else if (c == ',' && !inQuotes) {
                    currentRecord.add(currentField.toString());
                    currentField.setLength(0);
                } else if ((c == '\n' || c == '\r') && !inQuotes) {
                    if (c == '\r') {
                        reader.mark(1);
                        int next = reader.read();
                        if (next != '\n' && next != -1) {
                            reader.reset();
                        }
                    }
                    currentRecord.add(currentField.toString());
                    currentField.setLength(0);

                    if (!isEmptyRecord(currentRecord)) {
                        records.add(currentRecord);
                    }
                    currentRecord = new ArrayList<>();
                } else {
                    currentField.append(c);
                }
            }
        }

        currentRecord.add(currentField.toString());
        if (!isEmptyRecord(currentRecord)) {
            records.add(currentRecord);
        }

        return records;
    }

    /**
     * Checks whether a CSV record is empty.
     *
     * @param record CSV record
     * @return true if every field is blank
     */
    private boolean isEmptyRecord(List<String> record) {
        for (String field : record) {
            if (!TextUtil.safeTrim(field).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds a map from header name to column index.
     *
     * @param headers first CSV row
     * @return header lookup map
     */
    private HashMap<String, Integer> buildHeaderMap(List<String> headers) {
        HashMap<String, Integer> headerMap = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String cleanHeader = TextUtil.normalizeWords(headers.get(i));
            headerMap.put(cleanHeader, i);
        }
        return headerMap;
    }

    /**
     * Finds a required column.
     *
     * @param headerMap header lookup map
     * @param columnName expected column name
     * @return column index
     * @throws IOException if the column does not exist
     */
    private int getRequiredColumn(HashMap<String, Integer> headerMap, String columnName) throws IOException {
        Integer index = headerMap.get(TextUtil.normalizeWords(columnName));
        if (index == null) {
            throw new IOException("Missing required column: " + columnName);
        }
        return index;
    }

    /**
     * Finds an optional column.
     *
     * @param headerMap header lookup map
     * @param columnName optional column name
     * @return column index, or -1 if missing
     */
    private int getOptionalColumn(HashMap<String, Integer> headerMap, String columnName) {
        Integer index = headerMap.get(TextUtil.normalizeWords(columnName));
        if (index == null) {
            return -1;
        }
        return index;
    }

    /**
     * Safely gets a cell from a CSV record.
     *
     * @param record CSV record
     * @param index column index
     * @return cell value or empty string
     */
    private String getCell(List<String> record, int index) {
        if (index < 0 || index >= record.size()) {
            return "";
        }
        return TextUtil.safeTrim(record.get(index));
    }

    /**
     * Splits a course cell into separate course strings.
     *
     * Some CSV cells contain multiple courses separated by newlines.
     *
     * @param cell raw CSV course cell
     * @return list of course strings
     */
    private ArrayList<String> splitCourseCell(String cell) {
        ArrayList<String> courses = new ArrayList<>();
        String[] lines = TextUtil.safeTrim(cell).split("\\R+");

        for (String line : lines) {
            String clean = TextUtil.safeTrim(line);
            if (!clean.isEmpty()) {
                courses.add(clean);
            }
        }

        if (courses.isEmpty() && !TextUtil.safeTrim(cell).isEmpty()) {
            courses.add(TextUtil.safeTrim(cell));
        }

        return courses;
    }
}
