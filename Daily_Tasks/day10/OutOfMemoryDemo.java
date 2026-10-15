import java.util.ArrayList;
import java.util.List;

public final class OutOfMemoryDemo {
    public static void main(String[] args) {
        List<byte[]> retained = new ArrayList<>();
        long allocatedMiB = 0;
        try {
            while (true) {
                retained.add(new byte[1024 * 1024]);
                allocatedMiB++;
            }
        } catch (OutOfMemoryError error) {
            System.out.println("OutOfMemoryError after retaining about " + allocatedMiB + " MiB in byte arrays");
            retained.clear();
        }
    }
}
