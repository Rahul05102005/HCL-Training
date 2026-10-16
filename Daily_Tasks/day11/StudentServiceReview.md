# StudentService Review

`StudentServiceInitial.java` is a small review draft kept alongside the corrected service. Its behavior was checked against `StudentService.java` and the runnable checks in `src/test/java/day11/Day11Checks.java`.

| # | Problem in the initial draft | Why it mattered | Original behavior | Fix | Verification |
| --- | --- | --- | --- | --- | --- |
| 1 | `add` accepted null | A later lookup could fail while dereferencing the null entry | `add(null)` stored null | `save` rejects null immediately | A null save is expected to throw `NullPointerException` |
| 2 | Duplicate IDs were accepted | A lookup could return the wrong record and the collection could contain conflicting identities | Repeated additions created multiple entries with one ID | A map stores one value per ID and `save` rejects an existing ID | Duplicate save is expected to throw `IllegalArgumentException` |
| 3 | The internal list was returned directly | Callers could add or remove records without using service methods | `getAll()` exposed the mutable list | `findAll()` returns an immutable snapshot | A caller's `add` is rejected and service size stays unchanged |
| 4 | Lookup scanned the full list | Cost increased with each stored record | `find` compared entries one by one | A `TreeMap` indexes IDs, giving predictable ordered results and logarithmic lookup | Saved record can be retrieved by ID in the runnable checks |
| 5 | Missing records returned null | Callers had to remember an undocumented null case | `find` returned null when absent | `findById` returns `Optional<Student>` | Missing lookup is checked with `isEmpty()` |
| 6 | Search IDs were not checked or normalized | Null caused an incidental failure and whitespace did not match normalized student IDs | `find(null)` dereferenced null; padded IDs failed to match | Lookup and deletion validate and trim identifiers | Null lookup and normal ID lookup are checked |

The draft is a review example written for this exercise, not code attributed to an AI tool. No errors are swallowed, and the corrected service does not need catch blocks for ordinary validation.
