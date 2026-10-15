# Stack and Heap: Processing an Order

The stack holds method-call frames. Each call has its own parameters and local variables, and the frame is removed when that method returns. The heap holds objects. A local variable such as `order` holds a reference to an object on the heap; the reference itself is part of the active frame.

```text
Thread stack                         Heap
+ processOrder frame ---------+      + Order object ------------+
¦ quantity = 2                ¦      ¦ id ? "ORD-10"            ¦
¦ order ----------------------------->¦ quantity = 2             ¦
+-----------------------------+      +--------------------------+
             ¦ calls
             ?
+ printOrder frame -----------+      + String label object      ¦
¦ order ----------------------------->¦ "Processing ORD-10"      ¦
¦ label ----------------------------->+--------------------------+
+-----------------------------+
```

`StackHeapDemo` calls `printOrder(order)`, adding a second frame with its own parameter reference. When it returns, that frame disappears. After `processOrder` sets `order` to `null` and returns, no application reference to that `Order` remains, so the object is eligible for garbage collection. Eligibility does not mean collection happens immediately. The JVM may also optimize away or represent values differently internally; the diagram shows the useful source-level model.

The stack is per thread and has limited space; excessive recursion can cause `StackOverflowError`. The heap is shared by threads in a JVM; inability to allocate more heap can cause `OutOfMemoryError`.
