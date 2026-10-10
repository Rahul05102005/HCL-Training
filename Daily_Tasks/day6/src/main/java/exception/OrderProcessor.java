package exception;

import java.util.Scanner;

public class OrderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderProcessor processor = new OrderProcessor(10);

        while (true) {
            System.out.println("\n1. Place Order");
            System.out.println("2. View Stock");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        try {
                            processor.processOrder(quantity);
                        } catch (InsufficientStockException e) {
                            System.out.println("Order failed: " + e.getMessage());

                            if (e.getCause() != null) {
                                System.out.println("Cause: " + e.getCause().getMessage());
                            }
                        } catch (InvalidQuantityException | NumberFormatException e) {
                            System.out.println("Invalid order: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Available stock: " + processor.getStock());
                        break;

                    case 3:
                        System.out.println("Exiting Order Processor.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid menu choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (RuntimeException e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}