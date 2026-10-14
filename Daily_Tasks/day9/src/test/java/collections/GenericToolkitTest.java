package collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Dependency-free executable checks for Day 9. */
public final class GenericToolkitTest {
    private GenericToolkitTest() { }

    public static void main(String[] args) throws IOException {
        Pair<String, Integer> pair = new Pair<>("count", 2);
        Pair<String, Integer> equal = new Pair<>("count", 2);
        Pair<Integer, String> reverseTypes = new Pair<>(2, "count");
        check(pair.equals(equal) && pair.hashCode() == equal.hashCode(), "Pair equality and hash contract");
        check(!pair.toString().isBlank() && reverseTypes.getKey() == 2, "Pair supports different type arguments");
        check(new Box<>(7).asDouble() == 7.0 && new Box<>(2.5).getValue() == 2.5, "Box accepts Integer and Double");
        expect(NullPointerException.class, () -> new Box<Integer>(null), "Box rejects null");
        check(GenericUtils.max(4, 8) == 8, "max Integer");
        check(GenericUtils.max("zebra", "apple").equals("zebra"), "max String");
        check(GenericUtils.max(5, 5) == 5, "max equal values");
        expect(NullPointerException.class, () -> GenericUtils.max(null, 1), "max rejects null");
        check(GenericUtils.sum(List.of(1, 2.5, 3L)) == 6.5, "upper bound sums Number subtypes");
        List<Integer> integers = new ArrayList<>();
        GenericUtils.addIntegers(integers);
        check(integers.equals(List.of(10, 20)), "lower bound adds Integers to Integer list");
        List<Number> numbers = new ArrayList<>();
        GenericUtils.addIntegers(numbers);
        check(numbers.equals(List.of(10, 20)), "lower bound adds Integers to Number list");
        check(new Box<>(1).getClass() == new Box<>(1.0).getClass(), "type erasure shares runtime class");
        check(ResourceCloseDemo.closeOrder().equals(List.of("body", "close second", "close first")), "resources close in reverse order");

        InMemoryRepository<Product, Integer> repository = new InMemoryRepository<>(Product::getId);
        Product original = new Product(1, "Pen", "Stationery", 1.5);
        repository.save(original);
        check(repository.findById(1).orElseThrow() == original && repository.existsById(1), "repository save/find/exists");
        Product replacement = new Product(1, "Pencil", "Stationery", 0.5);
        repository.save(replacement);
        check(repository.findById(1).orElseThrow() == replacement, "duplicate ID replaces prior entity");
        check(!repository.findById(99).isPresent(), "missing ID is explicit");
        expect(UnsupportedOperationException.class, () -> repository.findAll().clear(), "findAll is immutable");
        check(repository.deleteById(1) && !repository.deleteById(1), "delete existing and missing IDs");
        expect(NullPointerException.class, () -> repository.findById(null), "repository rejects null IDs");
        expect(NullPointerException.class, () -> repository.save(null), "repository rejects null entities");
        InMemoryRepository<Pair<String, Integer>, String> pairRepository = new InMemoryRepository<>(Pair::getKey);
        pairRepository.save(pair);
        check(pairRepository.findById("count").orElseThrow().equals(pair), "repository supports another entity and ID type");

        Path csv = Files.createTempFile("day9-products", ".csv");
        Path malformed = Files.createTempFile("day9-malformed", ".csv");
        try {
            Files.writeString(csv, "id,name,category,price\n1,Pen,Stationery,1.5\n2,Book,Stationery,3.0\n");
            check(new CsvProductLoader().load(csv).size() == 2, "CSV header skipped and rows loaded");
            Files.writeString(malformed, "id,name,category,price\nnot-an-id,Pen,Stationery,1.5\n");
            try {
                new CsvProductLoader().load(malformed);
                throw new AssertionError("Malformed CSV should fail");
            } catch (IOException exception) {
                check(exception.getMessage().contains("line 2"), "malformed CSV reports line number");
            }
            try {
                new CsvProductLoader().load(csv.resolveSibling("day9-file-that-does-not-exist.csv"));
                throw new AssertionError("Missing CSV should fail");
            } catch (IOException exception) {
                check(exception.getMessage() != null && !exception.getMessage().isBlank(), "missing CSV has meaningful I/O error");
            }
        } finally {
            Files.deleteIfExists(csv);
            Files.deleteIfExists(malformed);
        }
        System.out.println("All Day 9 checks passed.");
    }

    private static void expect(Class<? extends Throwable> type, Runnable action, String scenario) {
        try { action.run(); }
        catch (Throwable exception) {
            if (type.isInstance(exception)) { check(true, scenario); return; }
            throw new AssertionError("Failed: " + scenario, exception);
        }
        throw new AssertionError("Failed: " + scenario + " (no exception)");
    }

    private static void check(boolean condition, String scenario) {
        if (!condition) throw new AssertionError("Failed: " + scenario);
        System.out.println("PASS: " + scenario);
    }
}
