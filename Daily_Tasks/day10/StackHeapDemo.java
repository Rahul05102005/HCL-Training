public final class StackHeapDemo {
    private static void processOrder() {
        int quantity = 2;
        Order order = new Order("ORD-10", quantity);
        printOrder(order);
        order = null; // the order object can be collected once no other references remain
    }

    private static void printOrder(Order order) {
        String label = "Processing " + order.id;
        System.out.println(label + ", quantity=" + order.quantity);
    }

    private static final class Order {
        private final String id;
        private final int quantity;
        private Order(String id, int quantity) { this.id = id; this.quantity = quantity; }
    }

    public static void main(String[] args) { processOrder(); }
}
