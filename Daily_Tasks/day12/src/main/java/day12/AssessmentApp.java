package day12;

import com.sportx.core.domain.*;
import java.util.Scanner;

public final class AssessmentApp {
    private static final String TITLE = "SPORTS TOURNAMENT MANAGEMENT - CORE JAVA ASSESSMENT";
    private final TournamentAssessmentService service = new TournamentAssessmentService();
    private final Scanner input;

    AssessmentApp(Scanner input) { this.input = input; }

    public static void main(String[] args) { new AssessmentApp(new Scanner(System.in)).run(); }

    void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=====================================\n" + TITLE + "\n=====================================");
            System.out.println("1. Register Team\n2. Register Player\n3. View Teams and Players\n4. Schedule Fixture\n5. View Fixtures\n6. Exit");
            String choice = ask("Select an option: ");
            try {
                switch (choice) {
                    case "1" -> registerTeam();
                    case "2" -> registerPlayer();
                    case "3" -> viewRoster();
                    case "4" -> scheduleFixture();
                    case "5" -> viewFixtures();
                    case "6" -> { System.out.println("Goodbye."); running = false; }
                    default -> System.out.println("Error: Select a number from 1 to 6.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private void registerTeam() {
        Team team = service.registerTeam(number("Team ID: "), ask("Team name: "), ask("Short name: "), ask("College: "));
        System.out.println("Registered team: " + team);
    }
    private void registerPlayer() {
        Player player = service.registerPlayer(number("Player ID: "), ask("Name: "), ask("Email: "), number("Team ID: "),
                integer("Jersey number: "), ask("Student ID: "), integer("Age: "), ask("Position: "));
        System.out.println("Registered player: " + player);
    }
    private void viewRoster() {
        if (service.getTeams().isEmpty()) { System.out.println("No teams registered."); return; }
        for (Team team : service.getTeams()) {
            System.out.println(team);
            for (Player player : team.getPlayers()) System.out.println("  " + player);
        }
    }
    private void scheduleFixture() {
        Fixture fixture = service.scheduleFixture(number("Fixture ID: "), number("Home team ID: "), number("Away team ID: "), integer("Round: "));
        System.out.println("Scheduled fixture: " + fixture);
    }
    private void viewFixtures() {
        if (service.getFixtures().isEmpty()) System.out.println("No fixtures scheduled.");
        else service.getFixtures().forEach(System.out::println);
    }
    private String ask(String prompt) { System.out.print(prompt); return input.nextLine().trim(); }
    private long number(String prompt) { try { return Long.parseLong(ask(prompt)); } catch (NumberFormatException exception) { throw new IllegalArgumentException("Enter a whole number."); } }
    private int integer(String prompt) { try { return Integer.parseInt(ask(prompt)); } catch (NumberFormatException exception) { throw new IllegalArgumentException("Enter a whole number."); } }
}
