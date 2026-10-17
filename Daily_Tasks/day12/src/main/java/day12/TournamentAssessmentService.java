package day12;

import com.sportx.core.domain.*;
import com.sportx.core.exceptions.TeamRegistrationException;
import java.time.LocalDateTime;
import java.util.List;

public final class TournamentAssessmentService {
    private final EntityRepository<Team> teams = new EntityRepository<>();
    private final EntityRepository<Player> players = new EntityRepository<>();
    private final EntityRepository<Fixture> fixtures = new EntityRepository<>();

    public Team registerTeam(long id, String name, String shortName, String college) {
        requirePositiveId(id, "Team ID");
        Team team = new Team(id, 1L, required(name, "Team name"), required(shortName, "Short name"), required(college, "College"), null);
        teams.add(team);
        return team;
    }

    public Player registerPlayer(long id, String name, String email, long teamId, int jersey, String studentId, int age, String position) {
        requirePositiveId(id, "Player ID");
        requirePositiveId(teamId, "Team ID");
        Team team = teams.find(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found: " + teamId));
        Player player = new Player(id, required(name, "Player name"), required(email, "Email"), "N/A", teamId, jersey,
                required(studentId, "Student ID"), age, required(position, "Position"), true);
        if (age <= 0 || jersey <= 0) throw new IllegalArgumentException("Age and jersey number must be positive");
        if (players.find(id).isPresent()) throw new IllegalArgumentException("Identifier already exists: " + id);
        try {
            team.addPlayer(player);
        } catch (TeamRegistrationException exception) {
            throw new IllegalArgumentException(exception.getMessage(), exception);
        }
        players.add(player);
        return player;
    }

    public Fixture scheduleFixture(long id, long homeId, long awayId, int round) {
        requirePositiveId(id, "Fixture ID");
        requirePositiveId(homeId, "Home team ID");
        requirePositiveId(awayId, "Away team ID");
        Team home = teams.find(homeId).orElseThrow(() -> new IllegalArgumentException("Home team not found: " + homeId));
        Team away = teams.find(awayId).orElseThrow(() -> new IllegalArgumentException("Away team not found: " + awayId));
        if (round <= 0) throw new IllegalArgumentException("Round must be positive");
        Fixture fixture = new Fixture(id, 1L, round, home, away, null, null, LocalDateTime.now());
        fixtures.add(fixture);
        return fixture;
    }

    public List<Team> getTeams() { return teams.findAll(); }
    public List<Player> getPlayers() { return players.findAll(); }
    public List<Fixture> getFixtures() { return fixtures.findAll(); }
    public Team findTeam(long id) { return teams.find(id).orElseThrow(() -> new IllegalArgumentException("Team not found: " + id)); }
    public Fixture findFixture(long id) { return fixtures.find(id).orElseThrow(() -> new IllegalArgumentException("Fixture not found: " + id)); }

    private static String required(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required");
        return value.trim();
    }

    private static void requirePositiveId(long id, String field) {
        if (id <= 0) throw new IllegalArgumentException(field + " must be positive");
    }
}
