package org.iconia.persistence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.iconia.model.IntTeam;

@DatabaseTable(tableName = "teams")
public class Team implements IntTeam {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(unique = true, canBeNull = false)
    private String name;

    @DatabaseField(unique = true, canBeNull = false)
    private String leaderNumber;

    @DatabaseField(unique = true, canBeNull = false)
    private String instagram;

    @DatabaseField(canBeNull = false)
    private String image;
    @DatabaseField(foreign = true, columnName = "tournament_id", foreignAutoRefresh = true, canBeNull = false)
    private IntTournament tournament;

    @DatabaseField(unique = true)
    private String tag;

    public Team(int id, String name, String leaderNumber, String instagram, String image, IntTournament tournament, String tag) {
        this.id = id;
        this.name = name;
        this.leaderNumber = leaderNumber;
        this.instagram = instagram;
        this.image = image;
        this.tournament = tournament;
        this.tag = tag;
    }

    public Team() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (!isLink(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("team name can't be a link");
        }
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String getLogo() {
        return image;
    }


    @Override
    public void setLogo(String logoLink) {
        if (isLink(logoLink)) {
            this.image = logoLink;
        } else {
            throw new IllegalArgumentException("we couldn't update the logo");
        }
    }


    @Override
    public String getInstagram() {
        return instagram;
    }

    @Override
    public void setInstagram(String insta) {
        if (insta.startsWith("@") && insta.split(" ").length == 1) {
            this.instagram = insta;
        } else {
            throw new IllegalArgumentException("we couldn't update the instagram your, please start with @");
        }
    }

    @Override
    public String getLeaderNumber() {
        return leaderNumber;
    }

    @Override
    public void setLeaderNumber(String leaderNumber) {
        this.leaderNumber = leaderNumber;
    }

    @Override
    public boolean isTeamComplete() {
        return name != null && leaderNumber != null && instagram != null && tag != null && image != null && tournament != null;
    }

    private boolean isLink(String link) {
        return link != null && link.toUpperCase().startsWith("HTTP");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Team team = (Team) obj;
        return name != null && name.equals(team.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public IntTournament getTournament() {
        return tournament;
    }

    public void setTournament(IntTournament tournament) {
        this.tournament = tournament;
    }
}
