package com.zh.courseeq.repository;

import com.zh.courseeq.model.CourseEquivalency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Loads course equivalency data from a CSV file.
 * No external CSV library is used, so the project stays simple for CS210.
 */
public class EquivalencyRepository {
    private final List<CourseEquivalency> records;

    public EquivalencyRepository(Path csvPath) throws IOException {
        this.records = load(csvPath);
    }

    public List<CourseEquivalency> findAll() {
        return Collections.unmodifiableList(records);
    }

    private List<CourseEquivalency> load(Path csvPath) throws IOException {
        List<CourseEquivalency> loadedRecords = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // skip header
            if (line == null) {
                return loadedRecords;
            }

            while ((line = reader.readLine()) != null) {
                List<String> fields = parseCsvLine(line);
                while (fields.size() < 7) {
                    fields.add("");
                }

                CourseEquivalency record = new CourseEquivalency(
                        fields.get(0), fields.get(1), fields.get(2), fields.get(3),
                        fields.get(4), fields.get(5), fields.get(6)
                );
                loadedRecords.add(record);
            }
        }

        return loadedRecords;
    }

    /** Parses one CSV line and supports quoted commas and escaped quotes. */
    private List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '"') {
                if (insideQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else if (ch == ',' && !insideQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        fields.add(current.toString());
        return fields;
    }
}
