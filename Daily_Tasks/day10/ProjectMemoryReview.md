# Project Memory Review

Reviewed source under `Sports_tournament/core-java/src/main/java` and the Day 9 resource example. No unrelated code was changed.

## Findings

1. **Event messages can accumulate.** `TournamentService` keeps a `ConcurrentLinkedQueue<String>` as an instance field and adds messages when teams register and matches start or finish. `getEventQueue()` exposes the queue, but this class has no consumer or size policy. If the application does not drain it, the strings remain reachable for the service lifetime. Consider a defined consumer, bounded queue/drop policy, or persistence appropriate to the app.
2. **In-memory repositories retain saved entities.** `InMemoryRepository` stores all entities in a `ConcurrentHashMap`; objects remain reachable until deleted or `clear()` is called or the repository itself becomes unreachable. This is expected for the current in-memory repository, but it is not bounded storage for a long-running production service. Use lifecycle cleanup or persistent storage where needed.
3. **Match history retains score events.** `TournamentService.addScoreEvent` stores each event on its match; match objects remain in the repository. This history may grow with activity. The source does not establish that this is unintended, so treat retention as a capacity/design consideration rather than a defect.

## Checks

- `ScoringRuleFactory` has a static map, but it is a small fixed registry initialized with rules; no unbounded growth was found there.
- No repeated string concatenation in loops was found in the reviewed Java source. The `+=` uses in `Match` and `Standing` update numeric fields.
- `Tournament` team registration is capped by `maxTeams`; fixture lists grow with generated tournament fixtures.
- Day 9 `CsvProductLoader` uses try-with-resources (and `ResourceCloseDemo` explicitly demonstrates closing), so the reviewed CSV resource is closed.
- No file or database handle leak was identified in the reviewed code.

No production fixes were made: queue consumption and history retention require application lifecycle decisions, and changing those could alter expected behavior.
