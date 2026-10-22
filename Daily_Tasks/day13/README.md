# Day 13: Functional Programming

## Objective
Practice Java lambdas, functional interfaces, method references, and predicate based product filtering.

## Topics and structure
- `FilterEngine.java` — validated `Product` record, filtering helper, and runnable examples.
- `FunctionalProgrammingChecks.java` — dependency free checks.
- `FunctionalProgrammingNotes.md` — short reference notes.
- `ProjectFunctionalReview.md` — review of existing project opportunities.
- `TestResults.md` — observed results.
- `CompletionChecklist.md` — assignment criteria.

## Features and interfaces
The engine filters products by price, category, stock, and combined predicates. It demonstrates `Predicate` (`test`, `and`, `or`, `negate`), `Function` (`andThen`, `compose`), `Consumer`, `Supplier`, and a custom `@FunctionalInterface` named `PriceLabeler`.

Method references demonstrate static (`String::valueOf`), bound (`System.out::println`), unbound (`Product::name`), and constructor (`String::new`) forms.

## Existing project review
Existing tournament code already uses lambdas and method references for stream filtering, optional fallbacks, and comparator composition. No suitable conversion was identified that justified editing prior work; stateful strategy implementations remain classes.

## Compile and run
From the repository root with JDK 21:

```powershell
$out = 'Daily_Tasks/day13/target/classes'
New-Item -ItemType Directory -Force $out | Out-Null
$sources = Get-ChildItem Daily_Tasks/day13 -Recurse -Filter *.java | Where-Object { $_.FullName -notmatch '\\target\\' } | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d $out $sources
java -cp $out day13.FilterEngine
java -cp $out day13.FunctionalProgrammingChecks
```

## Testing
The Day 13 source compiled and its check harness passed. The existing Core Java production sources also compiled with `javac`. `mvn test` could not start because Maven could not create its default local repository at `C:\.m2\repository` (Access denied); see `TestResults.md`.

## Conclusion
Day 13 adds an isolated, runnable product filtering exercise and examples while leaving the earlier daily tasks and tournament behavior unchanged.
