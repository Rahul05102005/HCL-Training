# HCL Training - Daily Tasks

This repository contains my daily hands-on tasks and learning activities completed as part of the HCL Training program.

---

## Day 1 - Java Platform Basics + Agile/Scrum Basics

### Topics Covered

- JDK, JRE and JVM
- JVM Architecture
- Java Compilation and Execution Flow
- Class Loader
- Runtime Data Areas
- Execution Engine
- Interpreter
- JIT Compiler
- Garbage Collection
- Agile and Scrum Basics
- Sprints
- Daily Standups
- Retrospectives
- User Stories
- Story Points
- Definition of Done

### Hands-on Task

Created `PlatformInfo.java` to display Java platform and system information.

The program displays:

- Java Version
- Operating System
- Number of Processors
- Maximum Heap Memory
- Free Heap Memory

### Commands Used

```bash
java -version
javac -version
javac PlatformInfo.java
java PlatformInfo
javap -c PlatformInfo
java -verbose:class PlatformInfo

## Day 2 - Language Fundamentals + Git Fundamentals

### Topics Covered

- Java Data Types
- Primitive Data Types
- Variables and Constants
- Arrays
- One-Dimensional Arrays
- Two-Dimensional Arrays
- Operators
- Type Casting
- Integer Overflow
- `int` and `long`
- Ternary Operator
- Git Working Directory
- Git Staging Area
- Git Commit
- Git Push
- `.gitignore`

### Hands-on Task

Created `Constants.java`, `MonthlyUsageAnalyser.java`, and `OverflowDemo.java` to practice Java language fundamentals and Git basics.

The programs demonstrate:

- Constants using `static final`
- 12-month usage data using an `int[]`
- Total usage
- Average usage
- Maximum usage
- Minimum usage
- Type casting
- Usage grade using the ternary operator
- Integer overflow
- Using `long` to prevent overflow
- Two-dimensional arrays for three houses
- Git version control and GitHub workflow

### Commands Used

```bash
javac Constants.java MonthlyUsageAnalyser.java OverflowDemo.java
java MonthlyUsageAnalyser
javac OverflowDemo.java
java OverflowDemo
git status
git add
git commit
git push
git log