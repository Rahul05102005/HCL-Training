# Assessment Design

## Selected business flows
1. **Team and player registration:** create teams, add identified players to a team, enforce required data, unique IDs, and the existing domain's unique jersey rule, then inspect the roster.
2. **Fixture scheduling:** create and list a scheduled fixture between two registered teams, enforcing existing `Fixture` rules such as distinct teams and valid linked team records.

## Why these flows
The allocated Core Java project contains `Team`, `Player`, `Fixture`, `Tournament`, and `TournamentService` domain behavior. The existing demonstrator and tests explicitly cover team registration, player rosters, unique jerseys, and fixtures. These form a cohesive user-managed console scenario without inventing match scoring or tournament scoring policies.

## Existing code reused
`BaseEntity`, `Team`, `Player`, and `Fixture` from `Sports_tournament/core-java/src/main/java/com/sportx/core/domain`. `Team.addPlayer` enforces jersey uniqueness and returns an unmodifiable roster; `Fixture` rejects a team playing itself; `BaseEntity` defines ID-based equality and hash codes.

## Files created
All new files are within `Daily_Tasks/day12/`: three application classes, one dependency-free check runner, and the requested documents. No existing project source or daily task file was changed.

## Design decisions
A generic `EntityRepository<T extends BaseEntity>` stores entities by ID in insertion-ordered maps. The service owns repositories and validation. The app handles prompts and messages. Teams use the existing constructor with an assessment-local tournament ID of `1`; no tournament lifecycle is simulated. The app does not create a database or server.

## Collection choices
`LinkedHashMap<Long,T>` provides duplicate-ID detection, keyed retrieval, and predictable display order. `List.copyOf` exposes a snapshot for listings. The reused team model stores its player list and jersey set behind defensive/unmodifiable accessors.

## Exception handling
Invalid input and business-rule violations produce `IllegalArgumentException` messages shown at the menu boundary. Parsing errors become clear validation messages. No exceptions are silently ignored. Failed player registration checks repository ID and then relies on `Team.addPlayer` before storing the player, avoiding partial player persistence if the jersey conflicts.

## Validation rules
Names, email, college, short name, student ID, and position must be nonblank. IDs are required and duplicate IDs are rejected by repositories. Team references must exist. Age, jersey, and fixture round must be positive. Home and away teams must differ. No email format, age range, squad size, capacity, schedule collision, or sport-specific rule is added because those are not requirements of the reused model for this flow.

## Limitations
All data is held in memory and disappears when the app exits. The console offers team creation, not tournament creation. Fixtures have no venue, referee, or selected time prompt; the existing fixture model defaults a missing time to now. The project domain is compiled from its existing source tree rather than copied or altered.
