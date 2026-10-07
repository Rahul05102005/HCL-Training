# Day 3 - Control Flow + Maven

## Topics Covered

### Java Control Flow

- if-else
- switch
- while loop
- do-while loop
- for loop
- Enhanced for loop
- break
- continue
- Labelled break
- Input validation

### Maven

- Maven project structure
- POM (Project Object Model)
- Group ID
- Artifact ID
- Version
- Maven lifecycle
- Maven profiles
- Maven build and packaging
- `mvn clean package`

---

## Hands-on Task

Created an **ATM Simulator** using Java control-flow statements and converted it into a Maven project.

### ATM Features

- PIN authentication
- Maximum 3 PIN attempts
- Check balance
- Deposit money
- Withdraw money
- Mini statement
- Exit option
- Invalid input handling

---

## Control Flow Used

### while

Used for allowing a maximum of three PIN attempts.

### do-while

Used to repeatedly display the ATM menu until the user selects Exit.

### switch

Used to handle different ATM menu options.

### if-else

Used for PIN validation, amount validation, and balance checking.

### break

Used to exit loops and switch cases.

### continue

Used to skip invalid input and return to the menu.

### Enhanced for

Used to display the transactions in the mini statement.

---

## Maven Project Structure

```text
day3/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── ATMSimulator.java

validate
   ↓
compile
   ↓
test
   ↓
package
   ↓
verify
   ↓
install
   ↓
deploy