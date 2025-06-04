package org.iconia.domain.hostManager;


import org.iconia.model.JoinManagerFeedback;
import org.iconia.model.Error;
import org.iconia.persistence.DatabaseAccessModel;
import org.iconia.persistence.IntTournament;
import org.iconia.persistence.Team;

import java.sql.SQLException;

public class Tournament {

    DatabaseAccessModel dbModel;

    public Tournament(DatabaseAccessModel dbModel) {
        this.dbModel = dbModel;
    }

    public static String viewTournamentsListAsString(IntTournament[] intTournaments) {
        StringBuilder result = new StringBuilder();

        for (IntTournament tournament : intTournaments) {
            if (tournament != null) {
                result.append("ID: ")
                        .append(tournament.getId())
                        .append(", Description: ")
                        .append(tournament.getDescription())
                        .append(", Date: ")
                        .append(tournament.getDate())
                        .append("\n");
            }
        }

        return result.toString();

    }

    public static String viewTeamsListAsString(Team[] teams) {
        StringBuilder result = new StringBuilder();

        for (Team team : teams) {
            if (team != null) {
                result.append("Tag: ")
                        .append(team.getTag())
                        .append(", Name: ")
                        .append(team.getName())
                        .append(", Insta: ")
                        .append(team.getInstagram())
                        .append("\n");
            }
        }

        return result.toString();

    }

    public boolean isTournamentActive() {
        try {
            return dbModel.isTournamentActive();

        } catch (Exception e) {
            throw new RuntimeException(Error.isTournamentStatusError);
        }
    }

    public IntTournament getIntTournament(int id) {
        try {
            return dbModel.getIntTournament(id);
        } catch (SQLException e) {
            throw new RuntimeException(Error.isTournamentStatusError);
        }
    }

    public IntTournament[] getIntTournaments() throws SQLException {
        return dbModel.getIntTournaments();
    }

    public String getTournamentDate(int id) {
        try {
            return dbModel.getTournamentDate(id);

        } catch (Exception e) {
            throw new RuntimeException(Error.getTournamentDateError);
        }
    }

    public void uploadTeam(Team team) {
        try {
            dbModel.setTeam(team);
        } catch (SQLException e) {
            String s = JoinManagerFeedback.unknownErrorMessage;
            if (e.getMessage().contains("UNIQUE")) {
                s = JoinManagerFeedback.teamExistErrorMessage;
            }
            throw new RuntimeException(s);
        }
    }

    public void createTournament(IntTournament tournament) throws SQLException {
        dbModel.setTournament(tournament);

    }

    public Team[] getTeams(int tournamentId) {
        try {
            return dbModel.getTeams(getIntTournament(tournamentId));

        } catch (Exception e) {
            throw new RuntimeException(Error.getTeamError);
        }
    }

}
