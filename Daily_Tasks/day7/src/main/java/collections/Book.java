package collections;

public class Book {
    private final String bookId;
    private final String title;
    private final String author;
    private boolean available;
    private boolean overdue;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId() { return bookId; }
    public boolean isAvailable() { return available; }
    public boolean isOverdue() { return overdue; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setOverdue(boolean overdue) { this.overdue = overdue; }

    @Override
    public String toString() {
        return bookId + " | " + title + " by " + author + " | "
                + (available ? "Available" : "Checked out")
                + (overdue ? " | Overdue" : "");
    }
}
