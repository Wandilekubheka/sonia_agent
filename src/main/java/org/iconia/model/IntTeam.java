package org.iconia.model;

import org.iconia.persistence.IntTournament;

public interface IntTeam {
    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public String getTag();

    public void setTag(String tag);

    public String getLogo();

    public void setLogo(String logo);

    public String getInstagram();

    public void setInstagram(String insta);

    public String getLeaderNumber();

    public void setLeaderNumber(String leaderNumber);

    public void setTournament(IntTournament tournament);

    public boolean isTeamComplete();


}
