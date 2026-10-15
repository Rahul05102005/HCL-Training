public final class IntegerCacheDemo {
    public static void main(String[] args) {
        Integer cachedA = 100;
        Integer cachedB = 100;
        Integer outsideA = Integer.valueOf(1000);
        Integer outsideB = Integer.valueOf(1000);

        System.out.println("100: == " + (cachedA == cachedB) + ", equals " + cachedA.equals(cachedB));
        System.out.println("1000: == " + (outsideA == outsideB) + ", equals " + outsideA.equals(outsideB));
        System.out.println("Integer values compare safely with equals(): " + outsideA.equals(outsideB));
    }
}
