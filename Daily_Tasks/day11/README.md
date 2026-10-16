# Day 11: AI Integration (Core Java)

## Objective

Practice reviewing AI-assisted Java examples and improving them with clear requirements.

## Topics covered

- Writing specific prompts
- POJO validation and encapsulation
- `equals()` and `hashCode()`
- Collections, repositories, and safe return values
- Code review and simple verification

## Experiments

Compared a short Student example with an improved version based on a detailed prompt. The short example is a demonstration, not code claimed to come from an external AI tool. See [PromptReview.md](PromptReview.md).

## Code review

The initial StudentService draft and corrected implementation are included. The review records six behavior and safety issues with their fixes in [StudentServiceReview.md](StudentServiceReview.md).

## Course repository

Added a validated `Course` entity and an in-memory repository with save, lookup, list, and delete operations.

## FR7 and FR8

Their definitions could not be found in the repository documentation or source. No implementation was made because their meaning is unclear.

## Testing

Java 21 is used for this exercise. The source compiles and the runnable checks report **PASS: 27 checks**. These checks cover validation, equality and hashing, duplicate IDs, service and repository operations, and safe collection access.

## Conclusion

Clear requirements made the examples easier to review. The checks also helped confirm that the corrected service and repository handle common invalid inputs safely.
