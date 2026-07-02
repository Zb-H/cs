package com.zh.courseeq.service;

import com.zh.courseeq.model.CourseEquivalency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/** Provides searching and sorting logic for equivalency records. */
public class SearchService {
    private final List<CourseEquivalency> records;

    public SearchService(List<CourseEquivalency> records) {
        this.records = new ArrayList<>(records);
    }

    public int count() {
        return records.size();
    }

    public List<CourseEquivalency> findAll() {
        return new ArrayList<>(records);
    }

    public List<CourseEquivalency> search(String query, SearchMode mode) {
        String normalizedQuery = normalize(query);
        if (normalizedQuery.isBlank()) {
            return findAll();
        }

        List<CourseEquivalency> results = new ArrayList<>();
        for (CourseEquivalency record : records) {
            if (matches(record, normalizedQuery, mode)) {
                results.add(record);
            }
        }

        results.sort(Comparator
                .comparing(CourseEquivalency::getTransferSubject)
                .thenComparing(CourseEquivalency::getTransferCourse)
                .thenComparing(CourseEquivalency::getEquivalentCourse));
        return results;
    }

    private boolean matches(CourseEquivalency record, String query, SearchMode mode) {
        return switch (mode) {
            case UW_COURSE -> containsFlexible(record.getTransferCourse(), query);
            case BC_COURSE -> containsFlexible(record.getEquivalentCourse(), query);
            case KEYWORD -> containsFlexible(record.getTransferCourse(), query)
                    || containsFlexible(record.getEquivalentCourse(), query)
                    || containsFlexible(record.getComments(), query)
                    || containsFlexible(record.getTransferInstitution(), query)
                    || containsFlexible(record.getReceiveInstitution(), query);
        };
    }

    /** Makes search forgiving: "math 124", "MATH124", and "Math& 124" behave similarly. */
    private boolean containsFlexible(String field, String normalizedQuery) {
        String normalizedField = normalize(field);
        return normalizedField.contains(normalizedQuery)
                || compact(normalizedField).contains(compact(normalizedQuery));
    }

    private String normalize(String text) {
        if (text == null) {
            return "";
        }
        return text.toLowerCase(Locale.ROOT)
                .replace("&", "")
                .replaceAll("[^a-z0-9]+", " ")
                .trim();
    }

    private String compact(String text) {
        return text.replace(" ", "");
    }
}
