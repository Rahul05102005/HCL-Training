# Memory Lab Results

Runtime: OpenJDK 21.0.6, default JVM options unless shown. Timings and stack depths are observations from this run; they can vary by machine and JVM.

| Experiment | Configuration | Actual result | Explanation |
|---|---|---|---|
| StackOverflowError | `-Xss256k` | PASS — 2,450 recursive calls | The smaller thread stack filled sooner. |
| StackOverflowError | `-Xss1m` | PASS — 14,740 recursive calls | The larger stack allowed more recursive frames. JVM/compiler and frame size affect depth. |
| OutOfMemoryError | `-Xmx64m` | PASS — caught after about 31 MiB retained in byte arrays | The heap limit includes JVM objects and array/list overhead, so usable space is below 64 MiB. Allocation stops in the catch block and references are cleared. |
| String concatenation vs StringBuilder | 12,000 appends of `order-` | PASS — strings equivalent; `+=` 204,818,100 ns; `StringBuilder` 3,154,100 ns; output length 72,000 | Repeated immutable-string concatenation copies prior content and creates temporary strings. These timings are a single un-warmed run, not a benchmark. |
| Garbage collection logging | `-Xlog:gc`, G1 on this JVM | PASS — log captured in `gc-log.txt`; observed young pauses and a full collection after `System.gc()` | The log reported young collections reducing 22M to 8M and 37M to 15M; the explicit full collection reported 72M to 0M. Collection details vary by collector and runtime. |
| Integer cache and equality | Default Java 21 | PASS — 100: `== true`, `equals true`; 1000: `== false`, `equals true` | `==` checks references. `equals()` checks numeric values. Only the standard guaranteed cache range is used to make an identity expectation (-128 through 127); identity outside it is not relied on. |

The GC run also printed `Using G1`. Young generation collections reclaim short-lived objects; objects surviving collections may be promoted to an old generation. Exact region layouts and policies depend on the collector. No memory size is inferred from the GC lines beyond what the JVM printed.

