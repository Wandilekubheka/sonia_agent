package Unittest.database;

import org.iconia.model.IntTournament;
import org.iconia.persistence.DatabaseAccess;
import org.iconia.persistence.DatabaseAccessModel;
import org.iconia.persistence.Team;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAddTeamToDatabase() {
        // create a tournament and a team
        DatabaseAccess db = new DatabaseAccess();
        IntTournament intTournament = new IntTournament("retro","2 June 2025","girls tournament",4);
        Team team = new Team();
        team.setName("Team 1");
        team.setInstagram("Instagram 1");
        team.setLeaderNumber("+43264");
        team.setTag("sd");
        team.setTournament(intTournament);
        team.setLogo("a logo".getBytes());

        // verify if required team data is complete
        assertTrue(team.isTeamComplete());
        try {
            // attempt to add team to database
            db.setTournament(intTournament);
            db.setTeam(team);
            // fetch tournament from database to verify if team was added
            IntTournament[] intTournaments = db.getIntTournaments();
            assertTrue(intTournaments.length > 0);
            Team[] teams = db.getTeams(intTournaments[0]);
            assertNotNull(teams);
            assertTrue(teams.length > 0);
        } catch (SQLException e) {
            fail("SQLException was thrown: " + e.getMessage());
        }
    }


    @Test
    public void testAddTournamentToDatabase() {
        // testing if we can create team
        DatabaseAccess db = new DatabaseAccess();
        IntTournament intTournament = new IntTournament("retro","21 June 2025","boys tournament",4);
        try{
            int prevTournamentSize = db.getIntTournaments().length;
            db.setTournament(intTournament);
            int newTournamentSize = db.getIntTournaments().length;
            assertEquals(prevTournamentSize + 1, newTournamentSize);
        }catch (SQLException e){
            fail("SQLException was thrown: " + e.getMessage());
        }
    }

    // need to test if I add a team without complete fields or tournament without complete fields
}
