package day11.repository;

import day11.model.Course;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public final class CourseRepository {
    private final Map<String, Course> courses = new TreeMap<>();

    public Course save(Course course) {
        Objects.requireNonNull(course, "Course cannot be null.");
        if (courses.putIfAbsent(course.getCourseId(), course) != null)
            throw new IllegalArgumentException("Course ID already exists: " + course.getCourseId());
        return course;
    }

    public Optional<Course> findById(String courseId) {
        return Optional.ofNullable(courses.get(requireId(courseId)));
    }

    public List<Course> findAll() { return List.copyOf(courses.values()); }

    public boolean deleteById(String courseId) { return courses.remove(requireId(courseId)) != null; }

    private static String requireId(String courseId) {
        Objects.requireNonNull(courseId, "Course ID cannot be null.");
        if (courseId.isBlank()) throw new IllegalArgumentException("Course ID cannot be blank.");
        return courseId.trim();
    }
}
