package collections;

public class FacultyRequest {
    private final int requestId;
    private final String facultyId;
    private final String bookId;
    private final int priority;
    private final long sequence;

    public FacultyRequest(int requestId, String facultyId, String bookId, int priority, long sequence) {
        this.requestId = requestId;
        this.facultyId = facultyId;
        this.bookId = bookId;
        this.priority = priority;
        this.sequence = sequence;
    }

    public int getPriority() { return priority; }
    public long getSequence() { return sequence; }

    @Override
    public String toString() {
        return "Request " + requestId + " | Faculty " + facultyId + " | Book " + bookId
                + " | Priority " + priority;
    }
}
