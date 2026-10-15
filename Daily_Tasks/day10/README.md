# Day 10: Memory Management

Small Java 21 examples for stack and heap memory, garbage collection, common memory errors, string building, and Integer comparisons. No added dependencies are needed.

## Files

- `StackHeapDemo.java` – order processing and local object references.
- `StackOverflowDemo.java` – controlled recursion under two stack sizes.
- `OutOfMemoryDemo.java` – retained arrays under a 64 MiB heap.
- `StringPerformanceDemo.java` – repeated `+=` compared with `StringBuilder`.
- `GarbageCollectionDemo.java` – temporary allocations and GC logging.
- `IntegerCacheDemo.java` – `==` and `equals()` examples.

Compile and run from this directory:

```powershell
javac -d . *.java
java StackHeapDemo
java -Xss256k StackOverflowDemo
java -Xss1m StackOverflowDemo
java -Xmx64m OutOfMemoryDemo
java StringPerformanceDemo
java -Xlog:gc GarbageCollectionDemo
java IntegerCacheDemo
```

See [memory-lab-results.md](memory-lab-results.md), [MemoryDiagram.md](MemoryDiagram.md), and [ProjectMemoryReview.md](ProjectMemoryReview.md) for explanations and the actual run results.
