import java.util.ArrayList;
import java.util.List;

public final class StackOverflowDemo {
    private static long calls;
    private static volatile long keepAlive;

    private static void recurse() {
        calls++;
        keepAlive = calls;
        recurse();
    }

    public static void main(String[] args) {
        try {
            recurse();
        } catch (StackOverflowError error) {
            System.out.println("StackOverflowError after " + calls + " recursive calls");
        }
    }
}
