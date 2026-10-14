package collections;

import java.util.Objects;

/** A container restricted to Number values. */
public final class Box<T extends Number> {
    private final T value;

    public Box(T value) { this.value = Objects.requireNonNull(value, "Box value cannot be null."); }
    public T getValue() { return value; }
    public double asDouble() { return value.doubleValue(); }
}
