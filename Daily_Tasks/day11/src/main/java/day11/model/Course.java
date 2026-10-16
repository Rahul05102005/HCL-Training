package day11.model;

import java.util.Objects;

public final class Course {
    private final String courseId;
    private final String title;
    private final int credits;

    public Course(String courseId, String title, int credits) {
        this.courseId = requireText(courseId, "Course ID");
        this.title = requireText(title, "Title");
        if (credits <= 0) throw new IllegalArgumentException("Credits must be positive.");
        this.credits = credits;
    }

    public String getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    private static String requireText(String value, String label) {
        Objects.requireNonNull(value, label + " cannot be null.");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException(label + " cannot be blank.");
        return trimmed;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Course course)) return false;
        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() { return courseId.hashCode(); }
}
