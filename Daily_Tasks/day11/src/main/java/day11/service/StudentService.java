package day11.service;

import day11.model.Student;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public final class StudentService {
    private final Map<String, Student> students = new TreeMap<>();

    public Student save(Student student) {
        Objects.requireNonNull(student, "Student cannot be null.");
        String id = student.getStudentId();
        if (students.containsKey(id)) throw new IllegalArgumentException("Student ID already exists: " + id);
        students.put(id, student);
        return student;
    }

    public Optional<Student> findById(String studentId) {
        return Optional.ofNullable(students.get(requireId(studentId)));
    }

    public List<Student> findAll() { return List.copyOf(students.values()); }

    public boolean deleteById(String studentId) { return students.remove(requireId(studentId)) != null; }

    private static String requireId(String studentId) {
        Objects.requireNonNull(studentId, "Student ID cannot be null.");
        if (studentId.isBlank()) throw new IllegalArgumentException("Student ID cannot be blank.");
        return studentId.trim();
    }
}
