package day11;

import day11.model.Course;
import day11.model.Student;
import day11.repository.CourseRepository;
import day11.service.StudentService;
import java.util.HashSet;
import java.util.List;

public final class Day11Checks {
    private static int checks;

    private Day11Checks() { }

    public static void main(String[] args) {
        studentValidationAndEquality();
        studentServiceOperations();
        courseRepositoryOperations();
        System.out.println("PASS: " + checks + " checks");
    }

    private static void studentValidationAndEquality() {
        Student first = new Student(" S1 ", "Asha", "asha@example.com", "Computer Science");
        Student sameId = new Student("S1", "Asha Rao", "asha.rao@example.com", "Engineering");
        check(first.getStudentId().equals("S1"));
        check(first.equals(sameId));
        check(first.hashCode() == sameId.hashCode());
        HashSet<Student> students = new HashSet<>(List.of(first, sameId));
        check(students.size() == 1);
        first.setName("Asha Nair");
        check(first.getName().equals("Asha Nair"));
        check(!first.toString().contains("asha@example.com"));
        expect(IllegalArgumentException.class, () -> new Student("S2", " ", "a@b.com", "Science"));
        expect(NullPointerException.class, () -> new Student(null, "Asha", "a@b.com", "Science"));
        expect(IllegalArgumentException.class, () -> first.setEmail(" "));
    }

    private static void studentServiceOperations() {
        StudentService service = new StudentService();
        Student student = new Student("S1", "Asha", "asha@example.com", "Science");
        service.save(student);
        check(service.findById("S1").orElseThrow().equals(student));
        check(service.findById("missing").isEmpty());
        expect(IllegalArgumentException.class, () -> service.save(new Student("S1", "Other", "o@example.com", "Arts")));
        expect(NullPointerException.class, () -> service.save(null));
        expect(NullPointerException.class, () -> service.findById(null));
        List<Student> all = service.findAll();
        expect(UnsupportedOperationException.class, () -> all.add(student));
        check(service.findAll().size() == 1);
        check(service.deleteById("S1"));
        check(!service.deleteById("S1"));
    }

    private static void courseRepositoryOperations() {
        CourseRepository repository = new CourseRepository();
        Course course = new Course("CS101", "Programming", 4);
        repository.save(course);
        check(repository.findById("CS101").orElseThrow().equals(course));
        check(repository.findById("missing").isEmpty());
        expect(IllegalArgumentException.class, () -> repository.save(new Course("CS101", "Other", 3)));
        expect(IllegalArgumentException.class, () -> new Course("CS102", "Algorithms", 0));
        expect(NullPointerException.class, () -> repository.save(null));
        List<Course> all = repository.findAll();
        expect(UnsupportedOperationException.class, () -> all.clear());
        check(repository.findAll().size() == 1);
        check(repository.deleteById("CS101"));
        check(!repository.deleteById("CS101"));
    }

    private static void check(boolean condition) {
        checks++;
        if (!condition) throw new AssertionError("Check " + checks + " failed.");
    }

    private static void expect(Class<? extends Throwable> type, Runnable action) {
        checks++;
        try {
            action.run();
        } catch (Throwable error) {
            if (type.isInstance(error)) return;
            throw new AssertionError("Expected " + type.getSimpleName() + " but got " + error, error);
        }
        throw new AssertionError("Expected " + type.getSimpleName() + " to be thrown.");
    }
}
