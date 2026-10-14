package collections;

import java.util.Objects;

/** A reusable, immutable key/value pair. */
public final class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = Objects.requireNonNull(key, "Key cannot be null.");
        this.value = Objects.requireNonNull(value, "Value cannot be null.");
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override public boolean equals(Object other) {
        return this == other || other instanceof Pair<?, ?> pair
                && Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }
    @Override public int hashCode() { return Objects.hash(key, value); }
    @Override public String toString() { return "Pair[key=" + key + ", value=" + value + "]"; }
}
