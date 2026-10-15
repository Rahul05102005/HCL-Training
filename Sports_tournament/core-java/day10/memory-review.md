# Day 10 – Memory Management Review

## 1. Project and Method Reviewed

Reviewed the existing SportX Core Java source in `src/main/java`, including `Main`, `TournamentService`, `SchedulingService`, `InMemoryRepository`, `Tournament`, `Team`, `Match`, and related domain/repository classes. `Player.java` is not present in this project tree; Player references appear in existing domain code.

The diagram traces `com.sportx.core.service.SchedulingService.validateSchedule(Fixture, List<Fixture>)` and its helper `calculateSuggestedSlots`.

## 2. Stack and Heap

On entry, the current thread has a `validateSchedule` frame containing parameters `proposedFixture` and `existingFixtures`. The method reads the proposed fixture's scheduled time, home/away teams, venue, and referee into local references. During each loop iteration, `existing` points at one existing fixture; `existingTime`, `timeDiff`, and `teamClash` are additional locals. Those fixtures and their related model objects are supplied by or reachable from the caller; the method does not construct Fixture, Team, or Player entities.

Each comparison computes a Duration. On a conflict, the helper creates a three-element immutable list of LocalDateTime suggestions, then the caller builds a message and exception. When the method returns normally, its frame and locals go away. Fixtures and entities remain reachable through their owners (such as tournament collections or service repositories) as long as those owners remain reachable. Temporary values can be collected when no longer referenced.

## 3. Memory Pitfalls Identified

- **Potentially unbounded event queue — `TournamentService.registerTeam`, `startMatch`, `addScoreEvent`, `finishMatch`:** each adds a message to the instance `ConcurrentLinkedQueue<String>`. The source exposes it through `getEventQueue()` but does not itself consume it or enforce capacity. If callers do not drain it, queued strings remain reachable while the service is reachable. This is a conditional retention risk; source alone does not prove a runtime leak. Recommendation: define an application consumer/lifecycle, or a bounded/drop policy if losing events is acceptable. No behavior-changing fix was applied.
- **In-memory entity retention — `InMemoryRepository.save` / `storage`:** saved objects are strongly retained in a `ConcurrentHashMap` until deleted, cleared, or the repository becomes unreachable. This is expected for this in-memory repository, but storage grows with saved entities. A long-running deployment needs lifecycle cleanup or durable storage with retention limits.
- **Match score history — `TournamentService.addScoreEvent` and `Match.recordScoreEvent`:** score events are retained on matches while matches remain in the repository. This may be required match history, but the source imposes no history limit. Set a product retention policy if tournaments are kept indefinitely.
- **Temporary values in `SchedulingService.validateSchedule`:** comparisons compute Duration values; conflict handling creates suggestions and a message. These are short-lived allocations per examined fixture or conflict, with no evidence of problematic accumulation. No optimization is justified without profiling.
- **Collections and caches:** `Tournament` caps registered teams at `maxTeams`; fixture lists scale with generated fixtures. `ScoringRuleFactory.RULES` is a small fixed registry, not an unbounded cache. No recursive method or repeated string concatenation in a loop was found in reviewed core source. `+=` in Match and Standing updates numeric fields.
- **Resource handling:** this core implementation does not use file or database resources. The separate Day 9 CSV example uses try-with-resources.

## 4. Fixes Applied

No memory-specific application change was justified: queue and entity/history retention depend on service lifecycle and business retention requirements, and imposing a bound or deleting history could change functionality. To restore the incomplete project, missing domain and scoring source files were added under the existing packages: `BaseEntity`, `Person`, `Player`, `Referee`, `ScoringRule`, `FootballScoringRule`, and `CricketScoringRule`. These restore types already required by the checked-in source and tests; they are not memory-policy changes.

## 5. Stack/Heap Diagram

See [stack-heap-diagram.png](stack-heap-diagram.png). Arrows show references held by the active method frame and the caller-owned object graph. The diagram is a source-level model; JVM optimizations may change physical representation.

## 6. Verification

- Source inspection: completed for the classes listed above.
- Maven tests: `mvn test` passed — 9 tests, 0 failures, 0 errors, 0 skipped.
- Console application: ran via `java -cp target/classes com.sportx.core.Main`; it printed the tournament setup, analytics summary, and successful completion message.

## 7. Conclusion

No runaway recursion or obvious temporary-allocation hotspot was found. The in-memory service intentionally retains queued messages, entities, and match history, so the main concern is retention over a long-lived service. A queue consumer and explicit data retention policy are appropriate if the app runs beyond a short-lived demonstration.

