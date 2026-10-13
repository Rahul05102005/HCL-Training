# Day 8 - Collections Framework Part 2

## Objective and structure

This standalone Java 21 exercise builds a menu-driven employee directory and demonstrates maps, ordering, comparators, and the `equals`/`hashCode` contract. It follows the package and source layout used by Day 7 and adds no dependencies.

```text
day8/
  README.md
  src/main/java/collections/
    Employee.java
    EmployeeDirectory.java
    EmployeeSalaryComparator.java
    HashCodeContractDemo.java
  src/test/java/collections/
    EmployeeDirectoryTest.java
```

Compile the application and its dependency-free checks, then run the checks and application from the repository root in PowerShell:

```powershell
javac -d Daily_Tasks/day8/out Daily_Tasks/day8/src/main/java/collections/*.java Daily_Tasks/day8/src/test/java/collections/*.java
java -cp Daily_Tasks/day8/out collections.EmployeeDirectoryTest
java -cp Daily_Tasks/day8/out collections.EmployeeDirectory
```

## Directory behavior

`EmployeeDirectory` uses a `HashMap<Integer, Employee>` with employee ID as its lookup key. It rejects duplicate IDs and reports missing IDs. The menu adds, searches, updates, removes, lists, reports by department, shows salary leaders, counts departments, demonstrates equality, and exits. Invalid menu or data input prints a message and returns to the menu. An empty directory is reported clearly.

`Employee` encapsulates its fields, requires positive IDs, nonblank names and departments, and finite nonnegative salaries. ID is immutable and defines identity. The `HashMap` is the correct structure for direct ID lookup: basic operations are expected constant time under normal hashing assumptions. Its iteration order is unspecified.

## Map frequency and department report

Department frequency is counted with `counts.merge(department, 1, Integer::sum)`. `merge` inserts the first count or combines an existing count in one operation, avoiding a separate `containsKey` branch. The model rejects blank departments, so invalid names are never counted.

The department report uses `TreeMap<String, DepartmentStats>`, giving alphabetical department names through natural string ordering. Each row displays employee count and total salary. A `TreeMap` sorts keys and gives logarithmic-time basic map operations; the frequency map is not relied on for ordering. Display sorts a copy of counts where alphabetical order is useful.

`LinkedHashMap` preserves encounter order, normally insertion order, and is useful when that predictable order is needed. `HashMap` does not promise encounter order. `TreeMap` orders entries by natural key order or a configured comparator. These collections serve different purposes; the ID lookup stays a `HashMap` while the sorted report uses a `TreeMap`.

## Ordering: Comparable and Comparator

`Employee implements Comparable<Employee>` with natural ordering by ID using `Integer.compare`, so the all-employee display is sorted by ID. A `Comparator` is a separate ordering policy; `EmployeeSalaryComparator` sorts salary descending, then name ascending without regard to case, then ID ascending. The top-five view applies it to a sorted copy and returns up to five employees, including when fewer than five exist.

The comparator chain uses `Comparator.comparing(Employee::getSalary).reversed().thenComparing(...)`. `reversed()` reverses the salary ordering; the chained name comparator remains ascending, and ID resolves remaining ties. `Comparable` provides the class's default/natural order, while `Comparator` defines additional orderings without changing that natural order. They are related but not interchangeable.

## equals and hashCode

The contract requires equal objects to return the same hash code. Unequal objects are allowed to collide. Hash-based collections use a hash to locate candidate buckets and then `equals` to establish a match. With ID-based identity, `Employee.equals` and `Employee.hashCode` both use ID; two separate employees with the same ID are equal and have matching hashes. The menu demonstrates that inserting both as map keys leaves one mapping.

`HashCodeContractDemo` isolates the bug: its private `BrokenEmployee` equality compares ID while its distinct identity-style hash values deliberately disagree. A `HashSet` containing one such object then fails `contains` for another object with the same ID. This class is only a teaching reproduction and is not used by the directory. The production `Employee` implements both methods consistently.

## FR4-FR6 status

Repository and training-documentation search found no official FR4, FR5, or FR6 definitions, nor an existing employee-directory feature to extend. Their wording and acceptance criteria have not been inferred. The independent Day 8 employee exercise is implemented; FR4-FR6 remain unverified pending their official definitions.

## Verification

The dependency-free executable checks cover empty state, add and duplicate rejection, found and missing search, valid and missing update, rejection of invalid updates without partial mutation, removal, incrementing `Map.merge` counts, alphabetically ordered `TreeMap` report, natural ID ordering, descending salary ordering with ascending-name tie breaking, fewer than five employees, department stats, equality/hash consistency in a `HashSet`, and the isolated broken-hash lookup failure. Menu-level checks cover empty displays, add/search/update/remove flows, duplicate IDs, and recovery from malformed menu input. All 23 checks passed with Java 21 using the commands above.
