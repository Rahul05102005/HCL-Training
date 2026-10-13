package collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/** Menu-driven employee directory backed by employee ID. */
public class EmployeeDirectory {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public boolean addEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) return false;
        employees.put(employee.getId(), employee);
        return true;
    }
    public Employee findEmployee(int id) { return employees.get(id); }
    public boolean updateEmployee(int id, String name, String department, double salary) {
        Employee employee = employees.get(id);
        if (employee == null) return false;
        Employee validated;
        try {
            validated = new Employee(id, name, department, salary);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        employee.setName(validated.getName());
        employee.setDepartment(validated.getDepartment());
        employee.setSalary(validated.getSalary());
        return true;
    }
    public boolean removeEmployee(int id) { return employees.remove(id) != null; }
    public List<Employee> employeesById() {
        List<Employee> result = new ArrayList<>(employees.values());
        result.sort(Comparator.naturalOrder());
        return result;
    }
    public List<Employee> topFiveBySalary() {
        List<Employee> result = new ArrayList<>(employees.values());
        result.sort(new EmployeeSalaryComparator());
        return result.subList(0, Math.min(5, result.size()));
    }
    public Map<String, Integer> departmentCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (Employee employee : employees.values()) counts.merge(employee.getDepartment(), 1, Integer::sum);
        return counts;
    }
    public Map<String, DepartmentStats> departmentReport() {
        Map<String, DepartmentStats> report = new TreeMap<>();
        for (Employee employee : employees.values()) {
            DepartmentStats stats = report.computeIfAbsent(employee.getDepartment(), ignored -> new DepartmentStats());
            stats.count++;
            stats.totalSalary += employee.getSalary();
        }
        return report;
    }
    public boolean isEmpty() { return employees.isEmpty(); }

    public static final class DepartmentStats {
        private int count;
        private double totalSalary;
        public int getCount() { return count; }
        public double getTotalSalary() { return totalSalary; }
    }

    private static final int EXIT = 10;
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();
        try (Scanner input = new Scanner(System.in)) {
            runMenu(input, directory);
        }
    }
    static void runMenu(Scanner input, EmployeeDirectory directory) {
        boolean running = true;
        while (running) {
            printMenu();
            Integer choice = readInt(input, "Choice: ");
            if (choice == null) { System.out.println("Invalid menu input. Enter a number from 1 to 10."); continue; }
            switch (choice) {
                case 1 -> addFromInput(input, directory);
                case 2 -> searchFromInput(input, directory);
                case 3 -> updateFromInput(input, directory);
                case 4 -> removeFromInput(input, directory);
                case 5 -> displayAll(directory);
                case 6 -> displayDepartments(directory);
                case 7 -> displayTopFive(directory);
                case 8 -> displayCounts(directory);
                case 9 -> demonstrateEquality();
                case EXIT -> running = false;
                default -> System.out.println("Choose an option from 1 to 10.");
            }
        }
        System.out.println("Goodbye.");
    }
    private static void printMenu() {
        System.out.println("\nEmployee Directory\n1 Add\n2 Search\n3 Update\n4 Remove\n5 Display all (ID order)"
                + "\n6 Department report\n7 Top five salaries\n8 Department frequency\n9 equals/hashCode demo\n10 Exit");
    }
    private static void addFromInput(Scanner in, EmployeeDirectory directory) {
        Employee employee = readEmployee(in);
        if (employee != null) System.out.println(directory.addEmployee(employee) ? "Employee added." : "Employee ID already exists.");
    }
    private static Employee readEmployee(Scanner in) {
        Integer id = readInt(in, "Employee ID: ");
        if (id == null || id <= 0) { System.out.println("Employee ID must be a positive integer."); return null; }
        String name = readText(in, "Name: ");
        String department = readText(in, "Department: ");
        Double salary = readDouble(in, "Salary: ");
        if (name == null || department == null || salary == null || salary < 0 || !Double.isFinite(salary)) {
            System.out.println("Name and department must be non-empty; salary must be finite and non-negative."); return null;
        }
        return new Employee(id, name, department, salary);
    }
    private static void searchFromInput(Scanner in, EmployeeDirectory d) {
        Integer id = readInt(in, "Employee ID: ");
        if (id == null) { System.out.println("Invalid employee ID."); return; }
        Employee employee = d.findEmployee(id);
        System.out.println(employee == null ? "No employee found for ID " + id + "." : employee);
    }
    private static void updateFromInput(Scanner in, EmployeeDirectory d) {
        Integer id = readInt(in, "Employee ID to update: ");
        if (id == null || d.findEmployee(id) == null) { System.out.println("No employee found; update not applied."); return; }
        String name = readText(in, "New name: ");
        String department = readText(in, "New department: ");
        Double salary = readDouble(in, "New salary: ");
        if (name == null || department == null || salary == null || salary < 0 || !Double.isFinite(salary)) {
            System.out.println("Invalid details; update not applied."); return;
        }
        d.updateEmployee(id, name, department, salary);
        System.out.println("Employee updated.");
    }
    private static void removeFromInput(Scanner in, EmployeeDirectory d) {
        Integer id = readInt(in, "Employee ID to remove: ");
        System.out.println(id != null && d.removeEmployee(id) ? "Employee removed." : "No employee found; nothing removed.");
    }
    private static void displayAll(EmployeeDirectory d) {
        if (d.isEmpty()) { System.out.println("Directory is empty."); return; }
        d.employeesById().forEach(System.out::println);
    }
    private static void displayDepartments(EmployeeDirectory d) {
        Map<String, DepartmentStats> report = d.departmentReport();
        if (report.isEmpty()) { System.out.println("Directory is empty."); return; }
        report.forEach((name, stats) -> System.out.printf("%s | Employees: %d | Total salary: %.2f%n", name, stats.getCount(), stats.getTotalSalary()));
    }
    private static void displayTopFive(EmployeeDirectory d) {
        List<Employee> top = d.topFiveBySalary();
        if (top.isEmpty()) { System.out.println("Directory is empty."); return; }
        top.forEach(System.out::println);
    }
    private static void displayCounts(EmployeeDirectory d) {
        Map<String, Integer> counts = d.departmentCounts();
        if (counts.isEmpty()) { System.out.println("Directory is empty."); return; }
        new TreeMap<>(counts).forEach((department, count) -> System.out.println(department + " | Employees: " + count));
    }
    private static void demonstrateEquality() {
        Employee first = new Employee(101, "Asha", "Engineering", 70000);
        Employee second = new Employee(101, "Different details", "Sales", 1000);
        System.out.println("Same ID: equals=" + first.equals(second) + ", hash codes equal=" + (first.hashCode() == second.hashCode()));
        System.out.println("With consistent hashCode, HashMap key replacement leaves one mapping: " + mapSizeAfterDuplicateKey(first, second));
        System.out.println("Isolated broken-hash demonstration: " + HashCodeContractDemo.run());
    }
    private static int mapSizeAfterDuplicateKey(Employee first, Employee second) {
        Map<Employee, String> map = new HashMap<>(); map.put(first, "first"); map.put(second, "second"); return map.size();
    }
    private static Integer readInt(Scanner in, String prompt) {
        System.out.print(prompt); String value = in.nextLine().trim();
        try { return Integer.valueOf(value); } catch (NumberFormatException exception) { return null; }
    }
    private static Double readDouble(Scanner in, String prompt) {
        System.out.print(prompt); String value = in.nextLine().trim();
        try { return Double.valueOf(value); } catch (NumberFormatException exception) { return null; }
    }
    private static String readText(Scanner in, String prompt) {
        System.out.print(prompt); String value = in.nextLine().trim(); return value.isEmpty() ? null : value;
    }
}
