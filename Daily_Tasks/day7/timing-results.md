# Day 7 timing results and verification

## Performance run

- Dataset: 20,000 integer elements for each collection and operation.
- Java: OpenJDK 21.0.6 (2025-01-21 LTS).
- Warm-up: 3 rounds per operation and collection.
- Measured repetitions: 5; values below are arithmetic means from the program's latest run.
- Timer: `System.nanoTime()`; values converted to milliseconds.
- Append: append 20,000 values to an empty list.
- Interior insertion: prefill 20,000 values, then insert 1,000 values at index 10,000; reported time covers the insertions only.
- Indexed retrieval: prefill 20,000 values, then sum 20,000 calls to `get(i)`; reported time covers the retrieval loop only.
- The timed loops do not print. These are observations from this machine/run, not universal performance guarantees.

| Operation | ArrayList (ms) | LinkedList (ms) |
|---|---:|---:|
| Append elements | 2.359 | 0.882 |
| Insert at interior position | 3.448 | 37.347 |
| Indexed `get(i)` | 0.091 | 566.685 |

The append result varied between executions, which is expected for a small JVM timing experiment. The indexed access results show the cost of repeated positional traversal in `LinkedList`; actual values depend on the JVM, hardware, warm-up, garbage collection, dataset, and measurement design.

## Verification actually performed

- `javac -d Daily_Tasks/day7/out Daily_Tasks/day7/src/main/java/collections/*.java` — passed on Java 21.0.6.
- `java -cp Daily_Tasks/day7/out collections.CollectionPerformanceTest` — completed and produced the measurements above.
- Ran `LibraryWaitlistApp` with scripted console input. Confirmed a book can be added/displayed; duplicate book IDs are rejected; repeated student registration is rejected and IDs retain insertion order; duplicate regular queue entry is rejected; equal-priority faculty requests are served FIFO; faculty work is served before regular work; regular work is served when faculty queue is empty; overdue books are marked and removed through `Iterator.remove()`; malformed menu input recovers; and the program exits normally.
- The scripted run did not separately exercise the higher-priority-versus-lower-priority comparison, empty-queue messages, the no-overdue branch, all statistics values, or faculty-queue display order. Those paths are implemented but are not claimed as manually verified.
- Maven was not used: the Day 7 project has no Maven configuration, and `mvn -version` returned `Access is denied` in this environment.
- Repository search found no official FR1-FR3 definitions, so that project-specific work is not verified and was not invented.
