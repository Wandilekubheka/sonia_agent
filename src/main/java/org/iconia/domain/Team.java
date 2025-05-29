package org.iconia.domain;

public class Team implements IntTeam {
    private int id;
    private String name;
    private String[] members;


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
        this.name = name;
    }

    @Override
    public String[] getMembers() {
        return members;
    }

    @Override
    public void setMembers(String[] members) {
        this.members = members;
    }


    @Override
    public boolean equals(Object obj) {
        Team team = (Team) obj;
        return  team.getName().equals(this.getName());

    }

}
