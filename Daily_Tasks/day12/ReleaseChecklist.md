# Release Checklist

Intended tag: `v1.0-core-java`

Suggested annotated tag message: `Core Java Assessment 1 - completed console application`

| Check | Status | Evidence |
|---|---|---|
| Compilation | PASS | Java 21 `javac` compiled the Day 12 app and existing core domain sources. |
| Functional flows | PASS | Console smoke test registered teams and players, scheduled a fixture, displayed rosters and fixture, then exited. |
| Input validation | PASS | Required fields, positive numeric values and invalid menu choice are handled; malformed numeric prompt itself is not separately run. |
| Exception handling | PASS | Service check runner exercised duplicate jersey, missing records/references, duplicate IDs, self fixture and blank field errors. |
| Collections | PASS | Generic ID repositories and immutable list snapshots verified. |
| Core Java concept mapping | PASS | `CoreJavaConceptMapping.md` created. |
| Documentation | PASS | README, design, mapping, practice, results and checklist created. |
| Test results | PASS | Actual Java check runner and interactive smoke test results recorded. |
| Existing module tests | BLOCKED | Maven could not create configured `C:\.m2\repository`; see `TestResults.md`. |
| Git working tree review | PASS | Existing unrelated edits and untracked Sports Tournament Core Java files remain untouched; inspect `git status --short` before staging. |
| Release tag preparation | PREPARED, NOT CREATED | Name/message documented; create manually after reviewing the selected Day 12 files. |

No files have been staged or committed and no tag has been created.
