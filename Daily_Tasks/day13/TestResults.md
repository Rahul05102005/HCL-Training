# Day 13 Test Results

Day 13 was compiled with `javac` and run using `FunctionalProgrammingChecks` and `FilterEngine`. The existing Core Java main sources compiled. `mvn test` was attempted but Maven could not create `C:\.m2\repository` (Access denied), so Maven/JUnit tests were not run.

| Test case | Expected result | Actual result | Status |
| --- | --- | --- | --- |
| Product validation | Invalid blank product name rejected | `IllegalArgumentException` observed | PASS |
| Price filtering | Only product(s) at or below test price returned | One product returned | PASS |
| Category filtering | Stationery items returned | Two products returned | PASS |
| Stock filtering | Positive stock items returned | Two products returned | PASS |
| Combined predicates | Price, category, and stock conditions all applied | One product returned | PASS |
| Predicate methods | `test`, `and`, `or`, `negate` produce expected booleans | All assertions true | PASS |
| Function transformations | Product name transformed and string length computed | `Notebook`, length 8 | PASS |
| Function composition | `andThen` and `compose` apply in order | Expected values observed | PASS |
| Consumer output | Consumer accepts product and emits output | Product record printed by runnable example; check accepted value | PASS |
| Supplier default values | Supplier returns `General` | `General` observed | PASS |
| Custom functional interface | Price label maps 45 to `Budget` | `Budget` observed | PASS |
| Method-reference examples | Static, bound, unbound, and constructor references work | All four forms asserted; runnable examples printed | PASS |
| Lambda and capture examples | Basic lambdas run; captured threshold remains effectively final | Output included `true`, `true`, `5`, and captured check `true` | PASS |
| Existing project changes | Review and compile without modifying previous task files | No previous files changed; review found no necessary improvement | PASS |
| Core Java Maven tests | Existing test suite runs | Maven blocked creating default local repository `C:\.m2\repository` | BLOCKED |
| Core Java production compilation | Existing module sources compile | `javac` compiled all `src/main/java` sources | PASS |
