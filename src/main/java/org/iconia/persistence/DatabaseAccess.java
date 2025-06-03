package org.iconia.persistence;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseAccess implements DatabaseAccessModel {
    Dao<Team, Integer> teamDao;
    Dao<IntTournament, Integer> tournamentsDao;


    public DatabaseAccess() {
        String DB_URL = "jdbc:sqlite:teams.db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)) {
            teamDao = DaoManager.createDao(connectionSource, Team.class);
            tournamentsDao = DaoManager.createDao(connectionSource, IntTournament.class);

            // Create the table if it doesn't exist
            TableUtils.createTableIfNotExists(connectionSource, Team.class);
            TableUtils.createTableIfNotExists(connectionSource, IntTournament.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team getTeam() {
        return null;
    }

    @Override
    public void setTeam(Team team) throws SQLException {
        teamDao.create(team);
    }


    @Override
    public Team[] getTeams(IntTournament tournament) throws SQLException {
        return teamDao.queryBuilder()
                .where()
                .eq("tournament_id", tournament.getId())
                .query().toArray(new Team[0]);
    }

    @Override
    public String getTournamentDate(int id) throws SQLException {
        IntTournament tournament = tournamentsDao.queryForId(id);
        return tournament.getDate();
    }

    @Override
    public void setTournament(IntTournament tournament) throws SQLException {
        tournamentsDao.create(tournament);

    }


    @Override
    public void setTournamentDate(String tournamentDate, IntTournament tournament) throws SQLException {
        tournament.setDate(tournamentDate);

    }


    @Override
    public void setMaxTeams(int maxTeams, int id) throws SQLException {
        IntTournament tournament = tournamentsDao.queryForId(id);
        tournament.setMaxSize(maxTeams);
        tournamentsDao.update(tournament);
    }

    @Override
    public int getMaxTeams(int id) throws SQLException {
        IntTournament tournament = tournamentsDao.queryForId(id);
        return tournament.getMaxSize();

    }

    @Override
    public IntTournament getIntTournament(int id) throws SQLException {
        return tournamentsDao.queryForId(id);
    }


    @Override
    public boolean isTournamentActive() throws SQLException {
        return getIntTournaments().length != 0;
    }

    @Override
    public IntTournament[] getIntTournaments() throws SQLException {
        return tournamentsDao.queryForAll().toArray(new IntTournament[0]);

    }
}
