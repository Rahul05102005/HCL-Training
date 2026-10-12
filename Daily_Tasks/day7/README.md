# Day 7 - Collections Framework Part 1

## Objective
Practice choosing Java collection types through a small library waitlist console application and a repeatable ArrayList/LinkedList timing experiment.

## Application and structure

```text
day7/
  README.md
  timing-results.md
  src/main/java/collections/
    Book.java
    FacultyRequest.java
    LibraryWaitlistApp.java
    CollectionPerformanceTest.java
```

Compile and run from the repository root with Java 21:

```powershell
javac -d Daily_Tasks/day7/out Daily_Tasks/day7/src/main/java/collections/*.java
java -cp Daily_Tasks/day7/out collections.LibraryWaitlistApp
java -cp Daily_Tasks/day7/out collections.CollectionPerformanceTest
```

The menu lets you view/add books, register students, enqueue regular or faculty requests, serve a request, view queues, remove overdue books, inspect counts, run the performance experiment, and exit. A book is overdue when its `overdue` property is true; the model exposes this property for the exercise. As this simple console exercise has no checkout/date workflow, overdue status is a model flag rather than a calculated due date.

## Why these collections

- `ArrayList<Book>` holds the catalogue. It keeps insertion order, appends efficiently in typical use, and provides fast indexed access.
- `LinkedHashSet<String>` makes student IDs unique and preserves registration order.
- `ArrayDeque<String>` models the regular FIFO waitlist with `addLast` and `removeFirst`.
- `PriorityQueue<FacultyRequest>` serves the highest numeric priority first, with earlier sequence first for ties. Queue iteration is not sorted, so display polls a copy and leaves the live queue untouched.

## Collection comparisons

### List, Set, and Queue

A `List` is ordered and permits duplicates; use it when position and indexed access matter. A `Set` enforces uniqueness, with ordering determined by its implementation. A `Queue` represents items awaiting processing and exposes operations suited to its ordering policy (FIFO or priority, for example).

### ArrayList and LinkedList

`ArrayList` provides constant-time indexed access and low-overhead appends, while insertion/removal in the middle shifts later elements. `LinkedList` has linear indexed access and additional node memory; once a node position is reached, local insertion/removal is cheap. Which performs better depends on the operation pattern and workload.

### HashSet, LinkedHashSet, and TreeSet

`HashSet` offers expected constant-time basic operations and no iteration-order promise. `LinkedHashSet` also preserves insertion order, with some additional bookkeeping. `TreeSet` keeps elements sorted according to natural order or a comparator and generally costs logarithmic time for basic operations.

### ArrayDeque and PriorityQueue

`ArrayDeque` is a resizable double-ended queue, suitable for FIFO work and operations at either end. `PriorityQueue` exposes its least element under its comparator at the head and is suitable when service order depends on priority; iteration itself is not sorted.

## Iteration and safe removal

An enhanced for-each loop is concise for reading elements. An `Iterator` gives explicit traversal control. `Iterator.remove()` safely removes the most recently returned element during traversal and is used to remove overdue books. A `ListIterator` is list-specific and can move both forward and backward, replace an element, and insert around its current position.

`ConcurrentModificationException` commonly occurs when a fail-fast collection is structurally changed directly while its iterator is traversing it (for example, removing from an `ArrayList` inside an enhanced for-each loop). Use the iterator's own `remove` method for this pattern. Fail-fast behavior is a bug detector, not a thread-safety guarantee.

## Performance experiment

`CollectionPerformanceTest` compares both lists at 20,000 elements. It performs three warm-up rounds, then five measured rounds and reports the arithmetic mean using `System.nanoTime()`. Append adds the same sequence. Interior insertion starts from equivalent populated lists and inserts 1,000 values at index 10,000; this fixes the test position and includes movement/traversal for each structure. Indexed access reads every `get(i)` over the same range and consumes values in a checksum, with no output inside the timed loop. The results are a small local experiment, not a universal benchmark; JVM, hardware, garbage collection, and methodology affect them.

See [timing-results.md](timing-results.md) for the run recorded on this machine.

## FR1-FR3 and verification

Repository search found no official FR1, FR2, or FR3 definitions or existing console project to extend. Those requirements are not inferred or implemented. The standalone waitlist is the independent Day 7 task.

Java 21 is available. The source compilation and manual scenarios are recorded in timing-results.md. Maven is installed or present on PATH but its version command returned `Access is denied`; this project has no Maven build file and compiles directly with `javac`.