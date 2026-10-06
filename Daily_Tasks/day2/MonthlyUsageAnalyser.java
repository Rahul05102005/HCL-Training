public class MonthlyUsageAnalyser {

    public static void main(String[] args) {

        int[] monthlyUsage = {
            120, 150, 180, 220, 250, 190,
            210, 175, 160, 230, 280, 300
        };

        long total = 0;
        int max = monthlyUsage[0];
        int min = monthlyUsage[0];

        for (int usage : monthlyUsage) {
            total += usage;

            if (usage > max) {
                max = usage;
            }

            if (usage < min) {
                min = usage;
            }
        }

        double average = (double) total / monthlyUsage.length;

        char grade = average >= 250 ? 'A'
                   : average >= 200 ? 'B'
                   : average >= 150 ? 'C'
                   : 'D';

        System.out.println("Monthly Usage Analyser");
        System.out.println("----------------------");
        System.out.println("Total Usage: " + total);
        System.out.println("Average Usage: " + average);
        System.out.println("Maximum Usage: " + max);
        System.out.println("Minimum Usage: " + min);
        System.out.println("Usage Grade: " + grade);

        long[][] houseUsage = {
            {120, 150, 180},
            {200, 220, 250},
            {100, 130, 160}
        };

        System.out.println("\n3-House Usage:");

        for (int house = 0; house < houseUsage.length; house++) {
            long houseTotal = 0;

            for (int month = 0; month < houseUsage[house].length; month++) {
                houseTotal += houseUsage[house][month];
            }

            System.out.println("House " + (house + 1) + ": " + houseTotal);
        }
    }
}