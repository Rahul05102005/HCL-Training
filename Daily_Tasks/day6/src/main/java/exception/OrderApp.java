package exception;

public class OrderProcessor {
    private int stock;

    public OrderProcessor(int stock) {
        this.stock = stock;
    }

    public void processOrder(int quantity) throws InsufficientStockException {
        try {
            if (quantity <= 0) {
                throw new InvalidQuantityException("Quantity must be greater than zero.");
            }

            if (quantity > stock) {
                IllegalStateException cause =
                        new IllegalStateException("Inventory reservation failed.");

                throw new InsufficientStockException(
                        "Insufficient stock. Available stock: " + stock, cause);
            }

            stock -= quantity;
            System.out.println("Order placed successfully.");
            System.out.println("Quantity ordered: " + quantity);
            System.out.println("Remaining stock: " + stock);

        } finally {
            System.out.println("Order processing audit completed.");
        }
    }

    public int getStock() {
        return stock;
    }
}