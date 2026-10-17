# Test Results

Java runtime/compiler: OpenJDK 21.0.6. Assessment classes compiled together with the existing SportX Core Java sources and the dependency-free check runner.

Compile and check command (from repository root):

```powershell
$sources = @(Get-ChildItem Sports_tournament/core-java/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }) + @(Get-ChildItem Daily_Tasks/day12/src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }) + @(Get-ChildItem Daily_Tasks/day12/src/test/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d Daily_Tasks/day12/target/classes $sources
java -cp Daily_Tasks/day12/target/classes day12.AssessmentChecks
```

Actual output: `AssessmentChecks: PASS (13 checks)`.

Interactive smoke-test input was piped through the app: register Harriers and Rovers, register Asha and Ravi, schedule fixture 20 in round 1, view rosters, view fixtures, exit. Observed output included both registered teams, both player confirmations, `R1: HAR vs ROV [SCHEDULED ... Ref: TBD]`, both rosters, the fixture listing, and `Goodbye.`. The fixture timestamp is generated at runtime.

| Test case | Expected result | Actual result | Status |
|---|---|---|---|
| Assessment and reused domain compilation | Compilation succeeds | `javac` exited 0 using the command above | PASS |
| Player registration and team roster | Player stored and attached to team | Asha and Ravi registered; both appeared in their team rosters | PASS |
| Invalid duplicate jersey | Reject and avoid partial repository storage | Check confirmed registration rejected and stored player count remained one | PASS |
| Duplicate team ID | Reject duplicate | Check observed `Identifier already exists: 1` | PASS |
| Missing team reference | Reject registration | Check observed missing team failure | PASS |
| Required field validation | Reject blank name | Check observed validation failure | PASS |
| Fixture creation and retrieval | Save and display linked teams | R1 fixture linked HAR vs ROV; check passed | PASS |
| Fixture invalid operations | Reject self-match, duplicate fixture ID, and missing lookup | All three check assertions passed | PASS |
| Invalid menu selection and exit | Show error and continue, then exit cleanly | Input `invalid`, then `6`; error shown and `Goodbye.` printed | PASS |
| equals/hashCode | Equal entity IDs compare equal with equal hashes | Both assertions passed | PASS |
| Collection safety | Listing cannot be mutated | Attempt threw `UnsupportedOperationException` as expected | PASS |
| Existing Core Java Maven tests | Existing tests pass | `mvn test` failed before build: Maven could not create configured `C:\.m2\repository` (`Access is denied`). Retrying with `-Dmaven.repo.local=../../Daily_Tasks/day12/.m2` produced the same configured path error. | BLOCKED |
| Malformed numeric input through UI | Clear validation message and continue | Parser converts `NumberFormatException` to a field-neutral whole-number message; interactive malformed numeric case not separately piped | NOT RUN |

The `AssessmentChecks` runner reports 13 checks. Maven test status is not represented as a pass.
