import java.util.ArrayList;
import java.util.List;

public final class GarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int batch = 0; batch < 8; batch++) {
            List<byte[]> temporary = new ArrayList<>();
            for (int i = 0; i < 40; i++) temporary.add(new byte[256 * 1024]);
            System.out.println("Allocated temporary batch " + (batch + 1));
        }
        System.gc();
        Thread.sleep(300);
        System.out.println("Temporary objects are out of scope");
    }
}
