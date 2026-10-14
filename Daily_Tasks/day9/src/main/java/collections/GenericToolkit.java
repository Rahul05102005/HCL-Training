package collections;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Main Day 9 generics and resource-management demonstration. */
public final class GenericToolkit {
    private GenericToolkit() { }

    public static void main(String[] args) throws IOException {
        demonstrateGenerics();
        demonstrateResources();
        demonstrateRepositories(resolveCsvPath(args));
    }

    private static void demonstrateGenerics() {
        Pair<String, Integer> named = new Pair<>("attempts", 3);
        Pair<Integer, String> coded = new Pair<>(404, "Not found");
        System.out.println(named + " | " + coded);
        Box<Integer> integerBox = new Box<>(42);
        Box<Double> doubleBox = new Box<>(3.5);
        System.out.println("Box values as doubles: " + integerBox.asDouble() + ", " + doubleBox.asDouble());
        System.out.println("max(Integer): " + GenericUtils.max(12, 27));
        System.out.println("max(String): " + GenericUtils.max("pear", "apple"));
        System.out.println("Unbounded wildcard:");
        GenericUtils.displayAll(List.of("generics", 9, true));
        System.out.println("Numeric sum: " + GenericUtils.sum(List.of(1, 2.5, 3L)));
        List<Number> sink = new ArrayList<>();
        GenericUtils.addIntegers(sink);
        System.out.println("Integers added through ? super Integer: " + sink);
        System.out.println("Box<Integer> and Box<Double> have same runtime class: " + (integerBox.getClass() == doubleBox.getClass()));
    }

    private static void demonstrateResources() {
        System.out.println("AutoCloseable event order: " + ResourceCloseDemo.closeOrder());
    }

    private static Path resolveCsvPath(String[] args) {
        if (args.length > 0) return Path.of(args[0]);
        Path local = Path.of("products.csv");
        if (local.toFile().isFile()) return local;
        return Path.of("Daily_Tasks", "day9", "products.csv");
    }

    private static void demonstrateRepositories(Path csvPath) throws IOException {
        CrudRepository<Product, Integer> products = new InMemoryRepository<>(Product::getId);
        for (Product product : new CsvProductLoader().load(csvPath)) products.save(product);
        System.out.println("Loaded products:");
        products.findAll().forEach(System.out::println);
        System.out.println("Product 1: " + products.findById(1));

        Product update = new Product(1, "Wireless Mouse", "Electronics", 24.99);
        products.save(update);
        System.out.println("After save replaces ID 1: " + products.findById(1));
        products.deleteById(2);
        System.out.println("After deleting ID 2: " + products.findAll());

        CrudRepository<Pair<String, Integer>, String> pairs = new InMemoryRepository<>(Pair::getKey);
        pairs.save(new Pair<>("attempts", 3));
        System.out.println("Same repository with Pair entity: " + pairs.findAll());
    }
}
