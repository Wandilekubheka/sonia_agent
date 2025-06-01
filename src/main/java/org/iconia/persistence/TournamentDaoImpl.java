//package org.iconia.persistence;
//
//import com.j256.ormlite.dao.Dao;
//import org.iconia.domain.hostManager.Tournament;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class TournamentDaoImpl implements TournamentDao {
//
//    private final Dao<Tournament, Integer> tournamentDao;
//
//    public TournamentDaoImpl(Dao<Tournament, Integer> tournamentDao) {
//        this.tournamentDao = tournamentDao;
//    }
//
//    @Override
//    public void addTournament(Tournament tournament) throws SQLException {
//        tournamentDao.create(tournament);
//    }
//
//    @Override
//    public void updateTournament(Tournament tournament) throws SQLException {
//        tournamentDao.update(tournament);
//    }
//
//    @Override
//    public Tournament getTournamentById(int id) throws SQLException {
//        return tournamentDao.queryForId(id);
//    }
//
//
//
//    @Override
//    public void deleteTournament(Tournament tournament) throws SQLException {
//        tournamentDao.delete(tournament);
//    }
//
//    @Override
//    public boolean isAnyTournamentActive() {
//        try {
//            return !tournamentDao.queryForAll().isEmpty();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
