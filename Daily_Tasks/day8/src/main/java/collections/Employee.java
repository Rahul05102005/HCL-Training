package collections;

import java.util.Objects;

/** Employee identity is defined by its positive ID. */
public final class Employee implements Comparable<Employee> {
    private final int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        if (id <= 0) throw new IllegalArgumentException("Employee ID must be positive.");
        this.id = id;
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public void setName(String name) { this.name = requireText(name, "Name"); }
    public void setDepartment(String department) { this.department = requireText(department, "Department"); }
    public void setSalary(double salary) {
        if (!Double.isFinite(salary) || salary < 0) throw new IllegalArgumentException("Salary must be finite and non-negative.");
        this.salary = salary;
    }

    private static String requireText(String value, String label) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(label + " cannot be blank.");
        return value.trim();
    }

    @Override public int compareTo(Employee other) { return Integer.compare(id, other.id); }
    @Override public boolean equals(Object other) {
        return this == other || other instanceof Employee employee && id == employee.id;
    }
    @Override public int hashCode() { return Objects.hash(id); }
    @Override public String toString() {
        return "ID=" + id + " | " + name + " | " + department + " | Salary=" + String.format("%.2f", salary);
    }
}
