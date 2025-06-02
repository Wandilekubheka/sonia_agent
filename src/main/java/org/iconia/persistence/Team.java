package org.iconia.persistence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.iconia.domain.hostManager.Tournament;
import org.iconia.model.IntTeam;
import org.iconia.model.IntTournament;

@DatabaseTable(tableName = "teams")
public class Team implements IntTeam {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String leaderNumber;

    @DatabaseField
    private String instagram;

    @DatabaseField(dataType = com.j256.ormlite.field.DataType.BYTE_ARRAY)
    private byte[] image;
    @DatabaseField(foreign = true, columnName = "tournament_id", foreignAutoRefresh = true)
    private IntTournament tournament;

    @DatabaseField
    private String tag;

    public Team(int id, String name, String leaderNumber, String instagram, byte[] image, IntTournament tournament, String tag) {
        this.id = id;
        this.name = name;
        this.leaderNumber = leaderNumber;
        this.instagram = instagram;
        this.image = image;
        this.tournament = tournament;
        this.tag = tag;
    }

    public Team() {}

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public String getTag() { return tag; }

    @Override
    public void setTag(String tag) { this.tag = tag; }

    @Override
    public byte[] getLogo() { return image; }


    @Override
    public void setLogo(byte[] logo) { this.image = logo; }

    @Override
    public String getInstagram() { return instagram; }

    @Override
    public void setInstagram(String insta) { this.instagram = insta; }

    @Override
    public String getLeaderNumber() { return leaderNumber; }

    @Override
    public void setLeaderNumber(String leaderNumber) { this.leaderNumber = leaderNumber; }

    @Override
    public boolean isTeamComplete() {
        return name != null && leaderNumber != null && instagram != null && tag != null && image != null && tournament != null;
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
