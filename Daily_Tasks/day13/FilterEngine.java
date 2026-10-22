package day13;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/** Runnable examples for Day 13 functional programming. */
public final class FilterEngine {
    private FilterEngine() { }

    public record Product(String name, String category, double price, int stock) {
        public Product {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name is required.");
            if (category == null || category.isBlank()) throw new IllegalArgumentException("Category is required.");
            if (!Double.isFinite(price) || price < 0) throw new IllegalArgumentException("Price must be non-negative.");
            if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        }
    }

    @FunctionalInterface
    public interface PriceLabeler { String label(double price); }

    public static List<Product> filter(List<Product> products, Predicate<Product> condition) {
        Objects.requireNonNull(products, "Products are required.");
        Objects.requireNonNull(condition, "A filter condition is required.");
        return products.stream().filter(condition).toList();
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Notebook", "Stationery", 45.0, 20),
                new Product("Pen Set", "Stationery", 120.0, 0),
                new Product("Mug", "Home", 250.0, 8),
                new Product("Desk Lamp", "Home", 850.0, 3));

        demonstrateLambdas();
        Predicate<Product> affordable = product -> product.price() <= 300;
        Predicate<Product> stationery = product -> product.category().equalsIgnoreCase("Stationery");
        Predicate<Product> inStock = product -> product.stock() > 0;
        System.out.println("Price <= 300: " + names(filter(products, affordable)));
        System.out.println("Stationery: " + names(filter(products, stationery)));
        System.out.println("In stock: " + names(filter(products, inStock)));
        System.out.println("Affordable stationery in stock: " + names(filter(products, affordable.and(stationery).and(inStock))));
        System.out.println("Affordable OR home: " + names(filter(products, affordable.or(p -> p.category().equalsIgnoreCase("Home")))));
        System.out.println("Not in stock: " + names(filter(products, inStock.negate())));

        Function<Product, String> productLabel = product -> product.name() + " ($" + product.price() + ")";
        Function<String, Integer> labelLength = String::length;
        System.out.println("Function: " + productLabel.andThen(labelLength).apply(products.get(0)));
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        System.out.println("compose: " + upper.compose(trim).apply("  home "));

        Consumer<Product> printer = System.out::println;
        printer.accept(products.get(0));
        Supplier<String> defaultCategory = () -> "General";
        System.out.println("Supplier default: " + defaultCategory.get());
        PriceLabeler priceLabeler = price -> price < 100 ? "Budget" : "Premium";
        System.out.println("Custom interface: " + priceLabeler.label(45));

        demonstrateMethodReferences(products.get(0));
    }

    private static void demonstrateLambdas() {
        Predicate<Integer> positive = (Integer value) -> value > 0;
        Predicate<Integer> even = value -> value % 2 == 0;
        java.util.function.BiFunction<Integer, Integer, Integer> add = (left, right) -> left + right;
        int capturedThreshold = 10; // Effectively final: initialized once and never reassigned.
        Predicate<Integer> aboveThreshold = value -> value > capturedThreshold;
        System.out.println("Lambda examples: " + positive.test(2) + ", " + even.test(2) + ", " + add.apply(2, 3));
        System.out.println("Captured effectively-final threshold: " + aboveThreshold.test(12));
        // Reassigning capturedThreshold after creating aboveThreshold would fail compilation:
        // capturedThreshold++; A captured local variable must be final or effectively final.
        // A lambda uses lexical this; an anonymous class has its own this and can declare state
        // and extra methods, so those two forms are not always interchangeable.
    }

    private static void demonstrateMethodReferences(Product product) {
        Function<Product, String> getter = Product::name;         
        Consumer<String> output = System.out::println;             
        Supplier<String> constructor = String::new;                
        java.util.function.BiFunction<String, String, String> join = String::concat; 
        System.out.println("Method references: " + getter.apply(product) + ", " + constructor.get() + ", " + join.apply("Day", "13"));
        // String::valueOf is a static method reference.
        Function<Integer, String> staticReference = String::valueOf;
        output.accept(staticReference.apply(13));
    }

    private static List<String> names(List<Product> products) {
        return products.stream().map(Product::name).collect(Collectors.toList());
    }
}
