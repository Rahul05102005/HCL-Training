public final class StringPerformanceDemo {
    private static final int ITERATIONS = 12000;
    private static final String PART = "order-";

    public static void main(String[] args) {
        long start = System.nanoTime();
        String concatenated = "";
        for (int i = 0; i < ITERATIONS; i++) concatenated += PART;
        long concatNanos = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder builder = new StringBuilder(ITERATIONS * PART.length());
        for (int i = 0; i < ITERATIONS; i++) builder.append(PART);
        String built = builder.toString();
        long builderNanos = System.nanoTime() - start;

        System.out.println("Equivalent results: " + concatenated.equals(built));
        System.out.println("+= elapsed: " + concatNanos + " ns");
        System.out.println("StringBuilder elapsed: " + builderNanos + " ns");
        System.out.println("Characters: " + built.length());
    }
}
