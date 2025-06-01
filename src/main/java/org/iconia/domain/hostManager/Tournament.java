package org.iconia.domain.hostManager;


import org.iconia.model.Error;
import org.iconia.model.IntTournament;
import org.iconia.persistence.DatabaseAccessModel;
import org.iconia.persistence.Team;

import java.sql.SQLException;

public class Tournament{

    DatabaseAccessModel dbModel;
    IntTournament intTournament;

    public Tournament(DatabaseAccessModel dbModel){
        this.dbModel = dbModel;
    }


    public boolean isTournamentActive() {
        try{
            return dbModel.isTournamentActive();

        }catch (Exception e){
            throw new RuntimeException(Error.isTournamentStatusError);
        }
    }

    public IntTournament getIntTournament() {
        if(intTournament == null){
            throw new RuntimeException(Error.isTournamentStatusError);
        }
        return intTournament;
    }
    public IntTournament getIntTournament(int id) throws SQLException {
        intTournament = dbModel.getIntTournament(id);
        return intTournament;
    }


    public IntTournament[] getIntTournaments() throws SQLException {
        return dbModel.getIntTournaments();
    }



    public String getTournamentDate(int id) {
        try{
            return dbModel.getTournamentDate(id);

        }catch (Exception e){
            throw new RuntimeException(Error.getTournamentDateError);
        }
    }

    public void uploadteam(Team team) {
        try{
            dbModel.setTeam(team);
        }catch (Exception e){
            throw new RuntimeException(Error.updateTeamError);
        }
    }

    public Team[] getTeams() {
        try{
            return dbModel.getTeams(getIntTournament());

        }catch (Exception e){
            throw new RuntimeException(Error.getTeamError);
        }
    }
}
