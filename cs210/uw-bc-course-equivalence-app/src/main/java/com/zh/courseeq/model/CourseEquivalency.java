package com.zh.courseeq.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents one row in the UW/BC course equivalency table.
 */
public class CourseEquivalency {
    private static final Pattern CREDIT_PATTERN = Pattern.compile("\\((\\d+)\\)");

    private final String transferInstitution;
    private final String transferCourse;
    private final String receiveInstitution;
    private final String equivalentCourse;
    private final String comments;
    private final String beginDate;
    private final String endDate;

    public CourseEquivalency(String transferInstitution,
                             String transferCourse,
                             String receiveInstitution,
                             String equivalentCourse,
                             String comments,
                             String beginDate,
                             String endDate) {
        this.transferInstitution = safe(transferInstitution);
        this.transferCourse = safe(transferCourse);
        this.receiveInstitution = safe(receiveInstitution);
        this.equivalentCourse = safe(equivalentCourse);
        this.comments = safe(comments);
        this.beginDate = safe(beginDate);
        this.endDate = safe(endDate);
    }

    public String getTransferInstitution() { return transferInstitution; }
    public String getTransferCourse() { return transferCourse; }
    public String getReceiveInstitution() { return receiveInstitution; }
    public String getEquivalentCourse() { return equivalentCourse; }
    public String getComments() { return comments; }
    public String getBeginDate() { return beginDate; }
    public String getEndDate() { return endDate; }

    /** Extracts the course subject, such as CSE, MATH, or ENGL. */
    public String getTransferSubject() { return extractFirstToken(transferCourse); }

    /** Extracts the equivalent course subject, such as CS, MATH&, or ENGL&. */
    public String getEquivalentSubject() { return extractFirstToken(equivalentCourse); }

    /** Extracts the credit number inside parentheses. Returns -1 if no credit is found. */
    public int getEquivalentCredits() { return extractCredits(equivalentCourse); }

    public boolean containsIgnoreCase(String text) {
        String lowerText = text.toLowerCase();
        return transferCourse.toLowerCase().contains(lowerText)
                || equivalentCourse.toLowerCase().contains(lowerText)
                || comments.toLowerCase().contains(lowerText)
                || transferInstitution.toLowerCase().contains(lowerText)
                || receiveInstitution.toLowerCase().contains(lowerText);
    }

    private static int extractCredits(String course) {
        Matcher matcher = CREDIT_PATTERN.matcher(course);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    private static String extractFirstToken(String course) {
        if (course == null || course.isBlank()) {
            return "";
        }
        return course.trim().split("\\s+")[0];
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    @Override
    public String toString() {
        return transferCourse + " => " + equivalentCourse;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CourseEquivalency other)) return false;
        return Objects.equals(transferCourse, other.transferCourse)
                && Objects.equals(equivalentCourse, other.equivalentCourse)
                && Objects.equals(beginDate, other.beginDate)
                && Objects.equals(endDate, other.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferCourse, equivalentCourse, beginDate, endDate);
    }
}
