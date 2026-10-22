package day13;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class FunctionalProgrammingChecks {
    private FunctionalProgrammingChecks() { }

    public static void main(String[] args) {
        List<FilterEngine.Product> products = List.of(
                new FilterEngine.Product("Notebook", "Stationery", 45, 20),
                new FilterEngine.Product("Pen Set", "Stationery", 120, 0),
                new FilterEngine.Product("Mug", "Home", 250, 8));
        check("product validation", throwsIllegalArgument(() -> new FilterEngine.Product("", "Home", 1, 1)));
        Predicate<FilterEngine.Product> price = p -> p.price() <= 100;
        Predicate<FilterEngine.Product> category = p -> p.category().equals("Stationery");
        Predicate<FilterEngine.Product> stock = p -> p.stock() > 0;
        check("price filtering", FilterEngine.filter(products, price).size() == 1);
        check("category filtering", FilterEngine.filter(products, category).size() == 2);
        check("stock filtering", FilterEngine.filter(products, stock).size() == 2);
        check("combined predicates", FilterEngine.filter(products, price.and(category).and(stock)).size() == 1);
        check("Predicate test/and/or/negate", price.test(products.get(0)) && price.and(stock).test(products.get(0))
                && price.or(stock).test(products.get(2)) && stock.negate().test(products.get(1)));
        Function<FilterEngine.Product, String> name = FilterEngine.Product::name;
        Function<String, Integer> length = String::length;
        check("Function transformation", name.apply(products.get(0)).equals("Notebook"));
        check("andThen/compose", name.andThen(length).apply(products.get(0)) == 8
                && Function.<String>identity().compose(String::trim).apply(" x ").equals("x"));
        check("Consumer output", consumes(products.get(0)));
        Supplier<String> fallback = () -> "General";
        check("Supplier default", fallback.get().equals("General"));
        FilterEngine.PriceLabeler labeler = amount -> amount < 100 ? "Budget" : "Premium";
        check("custom functional interface", labeler.label(45).equals("Budget"));
        Function<Integer, String> staticReference = String::valueOf;
        Function<String, String> unboundReference = String::toUpperCase;
        Supplier<String> constructorReference = String::new;
        String boundInput = "bound";
        Function<String, String> boundReference = boundInput::concat;
        check("method references", staticReference.apply(13).equals("13")
                && name.apply(products.get(0)).equals("Notebook")
                && unboundReference.apply("abc").equals("ABC")
                && constructorReference.get().isEmpty() && boundReference.apply(" method").equals("bound method"));
        System.out.println("All Day 13 checks completed.");
    }

    private static boolean consumes(FilterEngine.Product product) {
        StringBuilder output = new StringBuilder();
        Consumer<FilterEngine.Product> consumer = item -> output.append(item.name());
        consumer.accept(product);
        return output.toString().equals(product.name());
    }

    private static boolean throwsIllegalArgument(Runnable action) {
        try { action.run(); return false; }
        catch (IllegalArgumentException expected) { return true; }
    }

    private static void check(String name, boolean passed) {
        if (!passed) throw new AssertionError(name + " failed");
        System.out.println("PASS: " + name);
    }
}

