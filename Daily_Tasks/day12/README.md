# Day 12: Assessment 1 — Core Java Checkpoint

## Objective
Bring the main Core Java skills from Days 1–11 together in a small, runnable application.

## Project
A plain Java console app for team/player registration and tournament fixture scheduling. It reuses the Sports Tournament Core Java module's domain classes and stores records in memory.

## Core Flows
1. Register teams and players, reject missing fields, duplicate identifiers, missing team references, and duplicate jersey numbers, then display rosters.
2. Schedule a fixture between two registered teams, reject missing teams, duplicate fixture IDs, invalid rounds, and a team playing itself, then list fixtures.

## Core Java Concepts
Encapsulation, existing inheritance and identity equality, generic repositories, maps, safe collection snapshots, validation, exceptions, loops, methods, console input, and memory-conscious in-memory storage.

## Project Structure
- `src/main/java/day12/AssessmentApp.java` — console menu and input.
- `src/main/java/day12/TournamentAssessmentService.java` — business rules.
- `src/main/java/day12/EntityRepository.java` — generic in-memory storage.
- `src/test/java/day12/AssessmentChecks.java` — dependency-free checks.
- `AssessmentDesign.md`, `CoreJavaConceptMapping.md`, `AssessmentPractice.md`, `TestResults.md`, `ReleaseChecklist.md` — design, mapping, practice, evidence, and release prep.

## How to Run
Requires the repository's Java 21. From the repository root, compile Day 12 plus its existing domain dependencies:

```powershell
$sources = @(Get-ChildItem Sports_tournament/core-java/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }) + @(Get-ChildItem Daily_Tasks/day12/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
$out = 'Daily_Tasks/day12/target/classes'
New-Item -ItemType Directory -Force $out | Out-Null
javac -encoding UTF-8 -d $out $sources
java -cp $out day12.AssessmentApp
```

Compile and run the checks:

```powershell
$sources = @(Get-ChildItem Sports_tournament/core-java/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }) + @(Get-ChildItem Daily_Tasks/day12/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }) + @(Get-ChildItem Daily_Tasks/day12/src/test/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
$out = 'Daily_Tasks/day12/target/classes'
New-Item -ItemType Directory -Force $out | Out-Null
javac -encoding UTF-8 -d $out $sources
java -cp $out day12.AssessmentChecks
```

## Sample Output
Captured from the console smoke run (runtime timestamp shortened):

```text
Registered team: Harriers (HAR) - City College [Players: 0, Eligible: 0]
Registered team: Rovers (ROV) - Valley College [Players: 0, Eligible: 0]
Registered player: Asha (#9, Forward)
Registered player: Ravi (#7, Back)
Scheduled fixture: R1: HAR vs ROV [SCHEDULED @ <runtime timestamp>, Ref: TBD]
Harriers (HAR) - City College [Players: 1, Eligible: 1]
  Asha (#9, Forward)
Rovers (ROV) - Valley College [Players: 1, Eligible: 1]
  Ravi (#7, Back)
Goodbye.
```

## Testing
See `TestResults.md` for commands and observed results. Existing related module tests are also recorded there when run.

## Conclusion
The assessment uses existing domain rules in a focused console workflow and applies the Core Java practices from the earlier daily exercises.
