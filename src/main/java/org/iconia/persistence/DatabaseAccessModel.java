package org.iconia.persistence;

import org.iconia.domain.HostManager.Team;

public interface DatabaseAccessModel {
    Team getTeam();
    void setTeam(Team team);

    Team[] getTeams();
}
