# Day 6 – AI-Assisted Debugging

## Stack Trace 1: Invalid Quantity

### Stack Trace

java.lang.NumberFormatException: For input string: "abc"
    at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    at java.base/java.lang.Integer.parseInt(Integer.java:668)
    at exception.OrderApp.main(OrderApp.java:18)

### AI Explanation

The program attempted to convert the string "abc" into an integer using Integer.parseInt(). Since the string does not represent a valid integer, Java threw NumberFormatException.

### Verification

Correct: The input cannot be converted into an integer.

Correct: The exception occurs during integer parsing.

Correction: The application should catch NumberFormatException and allow the user to try again instead of terminating the menu.

### Fix

Use try-catch around Integer.parseInt() and display an appropriate error message.

---

## Stack Trace 2: Insufficient Stock

### Stack Trace

exception.InsufficientStockException: Insufficient stock. Available stock: 5
    at exception.OrderProcessor.processOrder(OrderProcessor.java:20)
    at exception.OrderApp.main(OrderApp.java:22)
Caused by: java.lang.IllegalStateException: Inventory reservation failed.
    at exception.OrderProcessor.processOrder(OrderProcessor.java:18)

### AI Explanation

The requested quantity exceeds the available stock. The OrderProcessor throws a custom checked exception named InsufficientStockException.

The Caused by section identifies the underlying IllegalStateException. This demonstrates exception chaining, which preserves information about the underlying failure.

### Verification

Correct: The custom checked exception represents a business-rule violation.

Correct: The cause is preserved through the exception constructor.

Correct: The calling method must handle InsufficientStockException or declare it using throws.

Correction: The application should display a meaningful message and return to the menu instead of terminating.

### Fix

Catch InsufficientStockException in the menu and display the error. Preserve the original cause when creating the custom exception.

---

## Key Learnings

1. Checked exceptions must be caught or declared using throws.
2. Unchecked exceptions extend RuntimeException.
3. throw explicitly raises an exception.
4. throws declares exceptions that a method may propagate.
5. Multi-catch handles multiple exception types in one catch block.
6. The finally block executes when control leaves the try statement, including when an exception occurs.
7. Exception chaining preserves the original cause.
8. Stack traces should be read from the exception message through the application frames and any Caused by sections.
9. AI-generated explanations must be verified against the code and stack trace.
10. Empty catch blocks should be avoided.