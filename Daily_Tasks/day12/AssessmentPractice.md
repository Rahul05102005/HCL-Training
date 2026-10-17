# Assessment Practice

## A. Warm-up quiz (30 minutes)

Choose one answer where options are shown. Suggested timing: 18 minutes for questions 1–8, 12 minutes for questions 9–12.

1. **(MC)** Which component executes Java bytecode? A) `javac` B) JVM C) Maven D) JAR
2. **(Short)** State one difference between JDK and JRE.
3. **(MC)** What does `int x = Integer.MAX_VALUE; x++;` illustrate? A) widening B) overflow C) garbage collection D) autoboxing
4. **(Short)** Give an example where inheritance is meaningful and explain what polymorphism lets a caller do.
5. **(MC)** Which exception generally must be caught or declared? A) `IllegalArgumentException` B) `NullPointerException` C) `IOException` D) `ArithmeticException`
6. **(MC)** Which collection preserves insertion order and supports key-based lookup? A) `HashMap` B) `LinkedHashMap` C) `TreeSet` D) `PriorityQueue`
7. **(Short)** How does a `TreeMap` determine key order?
8. **(MC)** If two objects are equal according to `equals`, what must be true? A) Their hash codes are equal. B) Their references are identical. C) Their fields are all mutable. D) They are in the same collection.
9. **(Short)** What problem do generics solve in a repository such as `Repository<T>`?
10. **(Short)** Which memory area holds a method's local variables, and which typically holds objects?
11. **(MC)** Calling `System.gc()` guarantees immediate collection. A) True B) False
12. **(Short)** Name two checks you would perform during review of AI-generated collection code.

### Answer key
1. B. 2. The JDK includes developer tools such as the compiler; the runtime environment supplies what is needed to run Java programs. 3. B; integer arithmetic wraps at its fixed-width boundary. 4. Example: `Player extends Person`; a `Person` reference can invoke an overridden role method for different subclasses. 5. C. 6. B. 7. Natural key ordering or a supplied comparator. 8. A. 9. Compile-time type safety and fewer casts while allowing reusable typed operations. 10. Stack; heap. 11. B; it is a request/hint, not a guarantee. 12. Examples: null handling, mutation exposure, equals/hashCode consistency, duplicate behavior, exception handling, and type safety.

## B. Coding exercises

1. Parse an age from console text and reject nonnumeric or nonpositive values with a useful message.
2. Implement duplicate-safe insertion into `Map<Long, Player>` and a missing-ID lookup that reports a domain error.
3. Add players to a team's roster while preventing duplicate jersey numbers; demonstrate that callers cannot mutate the returned roster.
4. Implement equality for an immutable value object and verify equal objects have equal hash codes and behave as one `HashSet` element.
5. Write a generic `findById` method for a repository whose entity type exposes an identifier.

## C. Practical preparation

The application demonstrates input parsing, validation, methods, a menu loop, encapsulated services and repositories, generic storage, ID-based collection lookup, duplicate handling, exceptions, and safe collection snapshots. It reuses SportX domain inheritance and equality, team jersey checks, and fixture validation. Days 1–11 provide the supporting ideas: platform/runtime, fundamentals, control flow and Maven, inheritance and polymorphism, exceptions/debugging, collections, generics, memory/string practices, and code review. This file is practice material; it does not record a quiz attempt.
