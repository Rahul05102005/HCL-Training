package collections;

import java.util.Objects;

/** Validated product row used by the CSV and repository demonstrations. */
public final class Product implements HasId<Integer> {
    private final Integer id;
    private final String name;
    private final String category;
    private final double price;

    public Product(int id, String name, String category, double price) {
        if (id <= 0) throw new IllegalArgumentException("Product ID must be positive.");
        this.id = id;
        this.name = requireText(name, "Name");
        this.category = requireText(category, "Category");
        if (!Double.isFinite(price) || price < 0) throw new IllegalArgumentException("Price must be finite and non-negative.");
        this.price = price;
    }

    @Override public Integer getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    private static String requireText(String value, String label) {
        Objects.requireNonNull(value, label + " cannot be null.");
        if (value.isBlank()) throw new IllegalArgumentException(label + " cannot be blank.");
        return value.trim();
    }

    @Override public String toString() { return "Product{id=" + id + ", name='" + name + "', category='" + category + "', price=" + String.format("%.2f", price) + "}"; }
}
