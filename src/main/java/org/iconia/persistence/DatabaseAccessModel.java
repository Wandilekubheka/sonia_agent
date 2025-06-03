package org.iconia.persistence;

import java.sql.SQLException;

public interface DatabaseAccessModel {
    Team getTeam() throws SQLException;

    void setTeam(Team team) throws SQLException;


    Team[] getTeams(IntTournament tournament) throws SQLException;

    String getTournamentDate(int id) throws SQLException;


    void setTournament(IntTournament tournament) throws SQLException;

    void setTournamentDate(String tournamentDate, IntTournament tournament) throws SQLException;


    void setMaxTeams(int maxTeams, int id) throws SQLException;

    int getMaxTeams(int id) throws SQLException;

    IntTournament getIntTournament(int id) throws SQLException;

    boolean isTournamentActive() throws SQLException;

    IntTournament[] getIntTournaments() throws SQLException;
}
