package collections;

import java.util.Comparator;

/** Salary descending, then name ascending, then ID ascending for deterministic ties. */
public final class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee first, Employee second) {
        return Comparator.comparing(Employee::getSalary).reversed()
                .thenComparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)
                .thenComparingInt(Employee::getId)
                .compare(first, second);
    }
}
