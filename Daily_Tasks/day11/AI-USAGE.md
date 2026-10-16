# AI Usage

| Date | Task | Prompt or AI assistance used | Changes made after review | Verification result |
| --- | --- | --- | --- |
| 2026-10-16 | Student POJO and prompt comparison | AI assisted with a vague-versus-specific prompt demonstration based on the task brief. The vague class is explicitly a written example, not claimed as external AI output. | Added encapsulation, required-field validation, stable ID equality, matching hash code, and email-free `toString`. | PASS: included in the compiled run of 27 checks. |
| 2026-10-16 | StudentService review | AI assisted in identifying review cases from the task requirements. The initial draft is an exercise example, not claimed as external AI output. | Replaced the exposed list with an ID-indexed map, rejected null and duplicate records, returned immutable snapshots, and used `Optional` for lookups. | PASS: duplicate, null, lookup, delete, and collection safety checks ran. |
| 2026-10-16 | Course entity and repository | AI assisted with a small in-memory repository design consistent with the Day 9 Java examples. | Added identifier and credit validation, duplicate handling, optional lookup, deletion, and immutable list results. | PASS: repository operations and invalid input checks ran. |

AI-assisted code needs review because a plausible-looking class can still miss validation, expose internal state, or violate equality rules. This exercise checked those cases along with duplicate IDs and missing records. Correctness was checked by compiling the source and running the dependency-free checks; the README records the actual result.
