package collections;

import java.util.List;
import java.util.Objects;

/** Utility methods for generic comparisons and wildcard bounds. */
public final class GenericUtils {
    private GenericUtils() { }

    public static <T extends Comparable<T>> T max(T first, T second) {
        Objects.requireNonNull(first, "First value cannot be null.");
        Objects.requireNonNull(second, "Second value cannot be null.");
        return first.compareTo(second) >= 0 ? first : second;
    }

    public static void displayAll(List<?> values) {
        Objects.requireNonNull(values, "Values cannot be null.");
        for (Object value : values) System.out.println(value);
    }

    public static double sum(List<? extends Number> values) {
        Objects.requireNonNull(values, "Values cannot be null.");
        double total = 0;
        for (Number value : values) total += Objects.requireNonNull(value, "List cannot contain null.").doubleValue();
        return total;
    }

    public static void addIntegers(List<? super Integer> values) {
        Objects.requireNonNull(values, "Values cannot be null.");
        values.add(10);
        values.add(20);
    }
}
