package org.iconia.model;

public interface IntTeam {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(String name);
    public String getTag();
    public void setTag(String tag);

    public byte[] getLogo();
    public void setLogo(byte[] logo);

    public String getInstagram();
    public void setInstagram(String insta);
    public String getLeaderNumber();
    public void setLeaderNumber(String leaderNumber);

    public boolean isTeamComplete();


}
