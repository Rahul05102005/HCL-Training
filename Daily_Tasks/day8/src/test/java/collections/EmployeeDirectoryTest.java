package collections;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** Dependency-free executable checks for Day 8: java collections.EmployeeDirectoryTest. */
public final class EmployeeDirectoryTest {
    private EmployeeDirectoryTest() { }
    public static void main(String[] args) {
        EmployeeDirectory d = new EmployeeDirectory();
        check(d.isEmpty(), "starts empty");
        check(d.addEmployee(new Employee(4, "Zoe", "Sales", 100)), "add");
        check(!d.addEmployee(new Employee(4, "Duplicate", "Other", 200)), "duplicate rejected");
        check(d.findEmployee(4) != null && d.findEmployee(99) == null, "search found and missing");
        check(d.updateEmployee(4, "Ava", "Engineering", 400), "update");
        check(!d.updateEmployee(99, "Ghost", "None", 1), "missing update rejected");
        check(!d.updateEmployee(4, "Changed", "Engineering", -1)
                && d.findEmployee(4).getName().equals("Ava"), "invalid update leaves employee unchanged");
        d.addEmployee(new Employee(3, "Bea", "Sales", 500));
        d.addEmployee(new Employee(9, "Gia", "Sales", 300));
        d.addEmployee(new Employee(2, "Cy", "Engineering", 500));
        d.addEmployee(new Employee(5, "Dee", "Support", 50));
        d.addEmployee(new Employee(6, "Eli", "Support", 20));
        d.addEmployee(new Employee(7, "Fay", "Support", 10));
        check(d.departmentCounts().get("Sales") == 2, "Map.merge increments existing department count");
        check(d.departmentReport().keySet().toString().equals("[Engineering, Sales, Support]"), "TreeMap sorted department report");
        check(d.employeesById().get(0).getId() == 2, "Comparable ID ordering");
        List<Employee> top = d.topFiveBySalary();
        check(top.size() == 5 && top.get(0).getId() == 3 && top.get(1).getId() == 2, "salary and secondary name ordering");
        Employee one = new Employee(8, "One", "A", 1), two = new Employee(8, "Two", "B", 2);
        check(one.equals(two) && one.hashCode() == two.hashCode(), "equals/hashCode contract");
        java.util.Set<Employee> correctedSet = new java.util.HashSet<>();
        correctedSet.add(one);
        correctedSet.add(two);
        check(correctedSet.size() == 1, "corrected Employee keys collapse in HashSet");
        check(HashCodeContractDemo.run() == false, "broken hash demo reproduces lookup failure on current run");
        check(d.removeEmployee(4) && !d.removeEmployee(4), "remove and missing remove");
        EmployeeDirectory fewer = new EmployeeDirectory(); fewer.addEmployee(new Employee(1, "Small", "A", 1));
        check(fewer.topFiveBySalary().size() == 1, "fewer than five salaries");
        check(fewer.departmentReport().get("A").getCount() == 1, "department stats");
        check(menuOutput("5\n6\n7\n8\nx\n10\n").contains("Directory is empty."), "empty directory menu reports");
        String interaction = menuOutput("1\n40\nAlice\nSales\n100\n1\n40\nDuplicate\nSales\n200\n"
                + "2\n40\n2\n999\n3\n999\n3\n40\n\nSales\n90\n"
                + "3\n40\nAlicia\nEngineering\n150\n4\n40\n4\n40\n5\nx\n10\n");
        check(interaction.contains("Employee added.") && interaction.contains("Employee ID already exists."), "menu add and duplicate");
        check(interaction.contains("ID=40 | Alice") && interaction.contains("No employee found for ID 999."), "menu found and missing search");
        check(interaction.contains("Invalid details; update not applied.") && interaction.contains("Employee updated."), "menu invalid and valid update");
        check(interaction.contains("Employee removed.") && interaction.contains("No employee found; nothing removed."), "menu remove and missing remove");
        check(interaction.contains("Invalid menu input") && interaction.contains("Goodbye."), "invalid menu input recovers and exits");
        System.out.println("All Day 8 checks passed.");
    }
    private static String menuOutput(String input) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream captured = new PrintStream(bytes, true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(input)) {
            System.setOut(captured);
            EmployeeDirectory.runMenu(scanner, new EmployeeDirectory());
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }
    private static void check(boolean condition, String scenario) {
        if (!condition) throw new AssertionError("Failed: " + scenario);
        System.out.println("PASS: " + scenario);
    }
}
