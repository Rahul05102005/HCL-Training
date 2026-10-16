package day11.model;

import java.util.Objects;

public final class Student {
    private final String studentId;
    private String name;
    private String email;
    private String department;

    public Student(String studentId, String name, String email, String department) {
        this.studentId = requireText(studentId, "Student ID");
        setName(name);
        setEmail(email);
        setDepartment(department);
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public void setName(String name) { this.name = requireText(name, "Name"); }
    public void setEmail(String email) { this.email = requireText(email, "Email"); }
    public void setDepartment(String department) { this.department = requireText(department, "Department"); }

    private static String requireText(String value, String label) {
        Objects.requireNonNull(value, label + " cannot be null.");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException(label + " cannot be blank.");
        return trimmed;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Student student)) return false;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() { return studentId.hashCode(); }

    @Override
    public String toString() {
        return "Student{studentId='" + studentId + "', name='" + name + "', department='" + department + "'}";
    }
}
