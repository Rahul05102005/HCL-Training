# Day 9: Generics

## Objective
To understand Java Generics, bounded types, wildcards, type erasure, repositories, CSV loading, and automatic resource management.

## Topics Covered
- Generic classes: `Pair<K,V>` and `Box<T extends Number>`
- Generic methods using `Comparable<T>`
- Wildcards: `?`, `? extends`, and `? super`
- PECS: Producer Extends, Consumer Super
- Type erasure
- `try-with-resources` and `AutoCloseable`
- Generic repository using `CrudRepository<T, ID>`
- In-memory storage using `InMemoryRepository<T, ID>`
- Reading product data from a CSV file
- CRUD operations: Create, Read, Update, and Delete

## Project Structure
- `Pair.java` – Stores a key-value pair.
- `Box.java` – Stores numeric values.
- `GenericUtils.java` – Provides generic utility methods.
- `Product.java` – Represents a product.
- `CrudRepository.java` – Defines repository operations.
- `InMemoryRepository.java` – Implements generic data storage.
- `CsvProductLoader.java` – Loads products from CSV.
- `ResourceCloseDemo.java` – Demonstrates automatic resource closing.
- `GenericToolkit.java` – Main demonstration application.
- `GenericToolkitTest.java` – Tests the implementation.
- `products.csv` – Contains sample product data.

## Testing
- All 24 test checks passed.
- CSV loading and CRUD operations worked successfully.
- Resources closed in reverse initialization order.
- Compilation completed without unchecked warnings.
- No raw types or unchecked casts were used.

## Conclusion
Day 9 demonstrates how Java Generics provide type safety and code reuse. It also covers wildcard usage, generic repositories, CSV processing, and automatic resource management.