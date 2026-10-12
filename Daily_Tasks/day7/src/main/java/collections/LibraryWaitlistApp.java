package collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LibraryWaitlistApp {
    private final ArrayList<Book> books = new ArrayList<>(); // Catalogue reads and indexed lookup are common.
    private final LinkedHashSet<String> students = new LinkedHashSet<>(); // Unique IDs, in registration order.
    private final ArrayDeque<String> regularWaitlist = new ArrayDeque<>(); // FIFO requests at both ends.
    private final PriorityQueue<FacultyRequest> facultyRequests = new PriorityQueue<>(
            Comparator.comparingInt(FacultyRequest::getPriority).reversed()
                    .thenComparingLong(FacultyRequest::getSequence));
    private int nextRequestId = 1;
    private long requestSequence;

    public static void main(String[] args) {
        new LibraryWaitlistApp().run();
    }

    private void run() {
        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                int choice = readInt(input, "Choose an option: ");
                switch (choice) {
                    case 1 -> displayBooks();
                    case 2 -> addBook(input);
                    case 3 -> registerStudent(input);
                    case 4 -> addRegularRequest(input);
                    case 5 -> addFacultyRequest(input);
                    case 6 -> serveNextRequest();
                    case 7 -> displayRegularWaitlist();
                    case 8 -> displayFacultyRequests();
                    case 9 -> markBookOverdue(input);
                    case 10 -> removeOverdueBooks();
                    case 11 -> displayStatistics();
                    case 12 -> CollectionPerformanceTest.runAndPrint();
                    case 13 -> running = false;
                    default -> System.out.println("Please choose a number from 1 to 13.");
                }
            }
            System.out.println("Goodbye.");
        }
    }

    private void printMenu() {
        System.out.println("\n--- Library Waitlist ---");
        System.out.println("1. Display book catalogue   2. Add a book");
        System.out.println("3. Register a student       4. Add regular waitlist request");
        System.out.println("5. Add faculty priority request   6. Serve next request");
        System.out.println("7. Display regular waitlist 8. Display faculty requests");
        System.out.println("9. Mark a book overdue      10. Remove overdue books");
        System.out.println("11. Collection statistics  12. Run performance comparison");
        System.out.println("13. Exit");
    }

    private int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            try { return Integer.parseInt(value); }
            catch (NumberFormatException e) { System.out.println("Enter a whole number."); }
        }
    }

    private String readRequired(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This value cannot be blank.");
        }
    }

    private void addBook(Scanner input) {
        String id = readRequired(input, "Book ID: ");
        if (findBook(id) != null) { System.out.println("That book ID is already in use."); return; }
        String title = readRequired(input, "Title: ");
        String author = readRequired(input, "Author: ");
        books.add(new Book(id, title, author));
        System.out.println("Book added.");
    }

    private void displayBooks() {
        if (books.isEmpty()) { System.out.println("The catalogue is empty."); return; }
        for (int i = 0; i < books.size(); i++) System.out.println((i + 1) + ". " + books.get(i));
    }

    private Book findBook(String id) {
        for (Book book : books) if (book.getBookId().equalsIgnoreCase(id)) return book;
        return null;
    }

    private void registerStudent(Scanner input) {
        String id = readRequired(input, "Student ID: ");
        if (students.add(id)) System.out.println("Student registered.");
        else System.out.println("That student is already registered.");
        System.out.println("Registered IDs: " + students);
    }

    private void addRegularRequest(Scanner input) {
        String id = readRequired(input, "Registered student ID: ");
        if (!students.contains(id)) { System.out.println("Register this student first."); return; }
        if (regularWaitlist.contains(id)) { System.out.println("This student is already waiting."); return; }
        regularWaitlist.addLast(id);
        System.out.println(id + " added to the regular waitlist.");
    }

    private void addFacultyRequest(Scanner input) {
        String facultyId = readRequired(input, "Faculty ID: ");
        String bookId = readRequired(input, "Book ID: ");
        if (findBook(bookId) == null) { System.out.println("That book is not in the catalogue."); return; }
        int priority = readInt(input, "Priority (higher number is served first): ");
        facultyRequests.add(new FacultyRequest(nextRequestId++, facultyId, bookId, priority, requestSequence++));
        System.out.println("Faculty request added.");
    }

    private void serveNextRequest() {
        if (!facultyRequests.isEmpty()) System.out.println("Serving " + facultyRequests.remove());
        else if (!regularWaitlist.isEmpty()) System.out.println("Serving student " + regularWaitlist.removeFirst());
        else System.out.println("There are no requests to serve.");
    }

    private void displayRegularWaitlist() {
        if (regularWaitlist.isEmpty()) System.out.println("The regular waitlist is empty.");
        else System.out.println("Regular FIFO waitlist: " + regularWaitlist);
    }

    private void displayFacultyRequests() {
        if (facultyRequests.isEmpty()) { System.out.println("There are no faculty requests."); return; }
        // A copy can be polled in priority order without changing the live queue.
        PriorityQueue<FacultyRequest> ordered = new PriorityQueue<>(facultyRequests);
        while (!ordered.isEmpty()) System.out.println(ordered.remove());
    }

    private void removeOverdueBooks() {
        ArrayList<Book> removed = new ArrayList<>();
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.isOverdue()) { removed.add(book); iterator.remove(); }
        }
        if (removed.isEmpty()) System.out.println("No overdue books were found.");
        else { System.out.println("Removed overdue books: " + removed); displayBooks(); }
    }

    private void markBookOverdue(Scanner input) {
        String id = readRequired(input, "Book ID to mark overdue: ");
        Book book = findBook(id);
        if (book == null) { System.out.println("That book is not in the catalogue."); return; }
        book.setOverdue(true);
        System.out.println("Marked overdue: " + book);
    }

    private void displayStatistics() {
        System.out.println("Books: " + books.size() + ", registered students: " + students.size()
                + ", regular requests: " + regularWaitlist.size()
                + ", faculty requests: " + facultyRequests.size());
    }
}
