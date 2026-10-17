package day12;

import com.sportx.core.domain.Fixture;
import com.sportx.core.domain.Player;
import com.sportx.core.domain.Team;
import java.util.List;

public final class AssessmentChecks {
    public static void main(String[] args) {
        TournamentAssessmentService service = new TournamentAssessmentService();
        Team home = service.registerTeam(1, "Harriers", "HAR", "City College");
        Team away = service.registerTeam(2, "Rovers", "ROV", "Valley College");
        Player player = service.registerPlayer(10, "Asha", "asha@example.test", 1, 9, "S10", 20, "Forward");
        check(player.equals(new Player(10L, "Other", "other@example.test", "N/A", 1L, 2, "S11", 21, "Back", true)), "entity identity equality");
        check(player.hashCode() == new Player(10L, "Other", "other@example.test", "N/A", 1L, 2, "S11", 21, "Back", true).hashCode(), "hashCode contract");
        check(home.getPlayers().size() == 1, "player attached to team");
        expectFailure(() -> service.registerPlayer(11, "Duplicate jersey", "d@example.test", 1, 9, "S12", 20, "Back"), "jersey collision");
        check(service.getPlayers().size() == 1, "failed registration leaves no partial player");
        expectFailure(() -> service.registerPlayer(12, "Missing team", "m@example.test", 99, 3, "S13", 20, "Back"), "missing team");
        Fixture fixture = service.scheduleFixture(20, 1, 2, 1);
        check(fixture.getHomeTeam().equals(home) && fixture.getAwayTeam().equals(away), "fixture team linkage");
        expectFailure(() -> service.scheduleFixture(21, 1, 1, 2), "same team fixture");
        expectFailure(() -> service.findFixture(404), "missing fixture lookup");
        expectFailure(() -> service.scheduleFixture(20, 1, 2, 2), "duplicate fixture identifier");
        expectFailure(() -> service.registerTeam(3, " ", "BAD", "College"), "blank required field");
        expectFailure(() -> service.registerTeam(1, "Duplicate", "DUP", "Other"), "duplicate identifier");
        List<Team> snapshot = service.getTeams();
        expectFailure(() -> snapshot.add(home), "immutable repository result");
        System.out.println("AssessmentChecks: PASS (13 checks)");
    }

    private static void check(boolean condition, String label) { if (!condition) throw new AssertionError(label); }
    private static void expectFailure(Runnable action, String label) {
        try { action.run(); } catch (IllegalArgumentException | UnsupportedOperationException expected) { return; }
        throw new AssertionError("Expected failure: " + label);
    }
}
