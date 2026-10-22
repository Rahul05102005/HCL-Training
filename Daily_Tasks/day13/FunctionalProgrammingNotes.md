# Functional Programming Notes

## Lambda expressions
A lambda supplies the implementation of one abstract method: `(a, b) -> a + b`. Types can often be inferred (`value -> value > 0`); explicit types are allowed (`(Integer value) -> value > 0`). A block lambda can use `return` when it has multiple statements.

```java
Predicate<Integer> positive = n -> n > 0;
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
```

Lambdas use lexical `this`. Anonymous classes have their own `this` and can declare state and extra methods, so use a lambda only when one abstract operation captures the needed behavior.

## Effectively final capture
A lambda may read a local variable that is final or effectively final (assigned once and never reassigned).

```java
int minimum = 10;
Predicate<Integer> above = value -> value > minimum;
// minimum++; // Would make it non-effectively-final and fail compilation.
```

## Predicate<T>
`test(value)` returns a boolean. `and`, `or`, and `negate` compose conditions and short-circuit like `&&`, `||`, and `!`.

```java
Predicate<Product> affordable = p -> p.price() < 100;
Predicate<Product> available = p -> p.stock() > 0;
Predicate<Product> buyable = affordable.and(available);
```

## Function<T, R>
`apply(value)` transforms a value. `first.andThen(second)` runs first then second; `first.compose(before)` runs before then first.

```java
Function<String, Integer> size = String::length;
Function<String, String> upper = String::toUpperCase;
Function<String, String> cleanedUpper = upper.compose(String::trim);
```

## Consumer and Supplier
`Consumer<T>.accept(value)` consumes a value, often for output or an action. `Supplier<T>.get()` produces a value without an input; it can provide a default lazily.

```java
Consumer<String> print = System.out::println;
Supplier<String> fallback = () -> "General";
```

## Four method-reference forms
- Static method: `String::valueOf`.
- Bound instance method: `System.out::println` (a particular object).
- Unbound instance method: `String::toUpperCase` (the receiver is supplied as an argument).
- Constructor: `String::new`.

## Custom functional interfaces
Annotate a single abstract method interface with `@FunctionalInterface` to ask the compiler to enforce the shape.

```java
@FunctionalInterface
interface PriceLabeler { String label(double price); }
PriceLabeler labels = price -> price < 100 ? "Budget" : "Premium";
```

## When to use lambdas
Use a lambda for short, stateless behavior that fits one functional interface, such as a filter, mapping rule, or callback. Keep ordinary classes for stateful behavior, multiple operations, meaningful identity, or logic that is clearer as named methods. Keep validation and exception handling explicit. Do not replace every conditional with a functional construct.
