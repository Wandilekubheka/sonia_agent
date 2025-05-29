package org.iconia.domain;

public class Team implements IntTeam {


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String[] getMembers() {
        return new String[0];
    }

    @Override
    public void setMembers(String[] members) {
    }


    @Override
    public boolean equals(Object obj) {
        Team team = (Team) obj;
        return  team.getName().equals(this.getName());

    }



}
