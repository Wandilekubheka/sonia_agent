package org.iconia.domain.HostManager;

import org.iconia.persistence.DatabaseAccessModel;

    public class Tournament implements IntTournament{
        final private DatabaseAccessModel databaseAccessModel;
    Tournament( DatabaseAccessModel databaseAccessModel) {
        this.databaseAccessModel = databaseAccessModel;
    }


    @Override
    public void uploadteam(Team team) {
        try {
            databaseAccessModel.setTeam(team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void updateTeam(Team team) {
        try{
            databaseAccessModel.setTeam(team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team getTeam() {
        try{
            return databaseAccessModel.getTeam();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team[] getTeams() {
        try{
            return databaseAccessModel.getTeams();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
