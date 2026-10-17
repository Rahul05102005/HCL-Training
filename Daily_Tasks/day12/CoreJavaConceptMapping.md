# Core Java Concept Mapping

| Core Java concept | Class or file | How it is demonstrated |
|---|---|---|
| JVM, JRE, and JDK | `Daily_Tasks/day1/PlatformInfo.java` | Earlier exercise reports Java platform/runtime details. |
| Variables, types, constants, conversion, overflow | `Daily_Tasks/day2/Constants.java`, `MonthlyUsageAnalyser.java`, `OverflowDemo.java` | Earlier exercises show primitives, constants, numeric operations, and overflow. |
| Conditionals, loops, Maven | `Daily_Tasks/day3/src/main/java/ATMSimulator.java`, `Daily_Tasks/day3/pom.xml` | Earlier console exercise uses menu control flow and Maven build conventions. |
| Inheritance, polymorphism, overriding | `Daily_Tasks/day5/src/main/java/model/UserRole.java`, `Daily_Tasks/day5/src/main/java/payment/` | Earlier domain and payment exercises model specialization and interchangeable implementations. The assessment reuses the same patterns through `Player extends Person`. |
| Exception handling and debugging | `Daily_Tasks/day5/src/main/java/payment/`, `Daily_Tasks/day6/src/main/java/exception/`, `Daily_Tasks/day6/AI-Stack-Trace-Notes.md` | Earlier tasks practice checked/unchecked handling, domain exceptions, and stack-trace diagnosis; Day 12 handles invalid console operations. |
| Collections and waitlist operations | `Daily_Tasks/day7/src/main/java/collections/LibraryWaitlistApp.java`, `FacultyRequest.java` | Earlier collection exercise models an ordered waitlist and request processing. |
| HashMap, TreeMap, Comparable, Comparator, equals/hashCode | `Daily_Tasks/day8/src/main/java/collections/EmployeeDirectory.java`, `EmployeeSalaryComparator.java`, `HashCodeContractDemo.java` | Earlier directory and comparator work demonstrate keyed/sorted collection behavior and equality contracts; the assessment's generic repository uses ID-keyed `LinkedHashMap`. |
| Generics | `Daily_Tasks/day9/src/main/java/collections/CrudRepository.java`, `InMemoryRepository.java`, `GenericUtils.java` | Earlier tasks introduce reusable typed repositories and generic utilities; Day 12 applies the repository pattern to domain entities. |
| Stack/heap, garbage collection, StringBuilder, integer caching | `Daily_Tasks/day10/StackHeapDemo.java`, `GarbageCollectionDemo.java`, `StringPerformanceDemo.java`, `IntegerCacheDemo.java` | Earlier memory exercises provide the runtime and allocation context; Day 12 avoids extra caches and does not force a StringBuilder into short output. |
| AI-assisted review, encapsulation, collection safety, null validation | `Daily_Tasks/day11/src/main/java/day11/service/StudentService.java`, `CourseRepository.java`, `Daily_Tasks/day11/StudentServiceReview.md` | Earlier review focused on validation and encapsulated collection access; Day 12 uses defensive snapshots and explicit validation. |
| Variables, constants, conditionals, loops, methods | `AssessmentApp.java` | Menu loop, named title constant, switch selection, prompt and workflow methods. |
| Input and validation | `AssessmentApp.java`, `TournamentAssessmentService.java` | Numeric parsing errors are reported; business fields and positive numeric values are validated. |
| Encapsulation and inheritance | Reused `BaseEntity`, `Team`, `Player`, `Fixture` | Domain state is private; `Player` extends `Person`, and domain entities extend `BaseEntity`. |
| Polymorphism and overriding | Reused `Person`, `Player`, `BaseEntity` | `Player` overrides `getRoleTitle`/`toString`; entity equality/hashCode are inherited consistently. |
| Exceptions | `AssessmentApp.java`, `TournamentAssessmentService.java`, reused `Team`/`Fixture` | Invalid operations produce meaningful exceptions, caught at the UI boundary; jersey and self-match rules come from domain methods. |
| Collections | `EntityRepository.java`, reused `Team.java` | Generic `LinkedHashMap` keyed by ID; team uses an ordered list and set for roster/jersey uniqueness. |
| Generics | `EntityRepository.java` | `T extends BaseEntity` repository provides typed storage and retrieval. |
| Equality and hashCode | Reused `BaseEntity.java` | Same class and non-null ID determine equality and hash code, so ID duplicates match stable entity identity. |
| Collection safety | `EntityRepository.java`, reused `Team.java` | Repository listings are immutable snapshots; team roster access is unmodifiable. |
| Memory-aware code / StringBuilder | `TournamentAssessmentService.java`, `AssessmentApp.java` | Bounded application-lifetime in-memory records; no static cache. StringBuilder is not useful for the short outputs here, so it is not forced into the code. |
| AI code review | `AssessmentDesign.md`, `TestResults.md` | Review fixed a possible partial player-registration state by applying team roster validation before storing the player. |
| Java platform basics, stack/heap, GC, StringBuilder | `AssessmentPractice.md`; Days 1, 10 | Runtime and memory topics are included in the practice review; no artificial memory demo is added to a business workflow. |
