package day11.service;

import day11.model.Student;
import java.util.ArrayList;
import java.util.List;

public final class StudentServiceInitial {
    private final List<Student> students = new ArrayList<>();

    public void add(Student student) { students.add(student); }
    public List<Student> getAll() { return students; }
    public Student find(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) return student;
        }
        return null;
    }
}
