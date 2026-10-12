package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionPerformanceTest {
    public static final int DATASET_SIZE = 20_000;
    public static final int WARMUP_RUNS = 3;
    public static final int MEASURED_RUNS = 5;

    private CollectionPerformanceTest() { }

    public static void main(String[] args) {
        runAndPrint();
    }

    public static void runAndPrint() {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            measureAppend(new ArrayList<>()); measureAppend(new LinkedList<>());
            measureInsert(new ArrayList<>()); measureInsert(new LinkedList<>());
            measureGet(new ArrayList<>()); measureGet(new LinkedList<>());
        }
        long[] array = new long[3], linked = new long[3];
        for (int i = 0; i < MEASURED_RUNS; i++) {
            array[0] += measureAppend(new ArrayList<>()); linked[0] += measureAppend(new LinkedList<>());
            array[1] += measureInsert(new ArrayList<>()); linked[1] += measureInsert(new LinkedList<>());
            array[2] += measureGet(new ArrayList<>()); linked[2] += measureGet(new LinkedList<>());
        }
        System.out.println("Average time over " + MEASURED_RUNS + " runs (" + DATASET_SIZE + " elements), ms:");
        System.out.printf("%-24s %12s %12s%n", "Operation", "ArrayList", "LinkedList");
        String[] names = {"Append elements", "Insert at middle", "Indexed get(i)"};
        for (int i = 0; i < names.length; i++)
            System.out.printf("%-24s %12.3f %12.3f%n", names[i], array[i] / MEASURED_RUNS / 1_000_000.0,
                    linked[i] / MEASURED_RUNS / 1_000_000.0);
    }

    private static long measureAppend(List<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < DATASET_SIZE; i++) list.add(i);
        return System.nanoTime() - start;
    }

    private static long measureInsert(List<Integer> list) {
        for (int i = 0; i < DATASET_SIZE; i++) list.add(i);
        int middle = DATASET_SIZE / 2;
        long start = System.nanoTime();
        for (int i = 0; i < 1_000; i++) list.add(middle, -i);
        return System.nanoTime() - start;
    }

    private static long measureGet(List<Integer> list) {
        for (int i = 0; i < DATASET_SIZE; i++) list.add(i);
        long checksum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < DATASET_SIZE; i++) checksum += list.get(i);
        long elapsed = System.nanoTime() - start;
        if (checksum == Long.MIN_VALUE) System.out.println(checksum);
        return elapsed;
    }
}
