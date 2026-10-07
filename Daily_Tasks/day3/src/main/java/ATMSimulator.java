import java.util.Scanner;

public class ATMSimulator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        int attempts = 0;
        boolean authenticated = false;

        // Maximum 3 PIN attempts
        while (attempts < 3) {

            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();

            if (pin == CORRECT_PIN) {
                authenticated = true;
                System.out.println("Login successful!");
                break;
            }

            attempts++;

            if (attempts < 3) {
                System.out.println("Invalid PIN. Try again.");
                continue;
            }

            System.out.println("Too many incorrect attempts.");
        }

        if (!authenticated) {
            scanner.close();
            return;
        }

        double balance = 10000.0;

        double[] transactions = {
            500.0,
            -1000.0,
            2000.0
        };

        int choice;

        // do-while menu
        do {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            // switch menu
            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();

                    if (deposit <= 0) {
                        System.out.println("Invalid deposit amount.");
                        continue;
                    }

                    balance += deposit;
                    System.out.println("Deposit successful.");
                    System.out.println("New Balance: ₹" + balance);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = scanner.nextDouble();

                    if (withdrawal <= 0) {
                        System.out.println("Invalid withdrawal amount.");
                        continue;
                    }

                    if (withdrawal > balance) {
                        System.out.println("Insufficient balance.");
                        continue;
                    }

                    balance -= withdrawal;
                    System.out.println("Withdrawal successful.");
                    System.out.println("New Balance: ₹" + balance);
                    break;

                case 4:
                    System.out.println("\n===== MINI STATEMENT =====");

                    // Enhanced for loop
                    for (double transaction : transactions) {
                        System.out.println("Transaction: ₹" + transaction);
                    }

                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-5.");
                    continue;
            }

        } while (choice != 5);

        scanner.close();
    }
}