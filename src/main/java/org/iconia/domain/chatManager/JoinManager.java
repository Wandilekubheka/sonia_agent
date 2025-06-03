package org.iconia.domain.chatManager;

import org.iconia.persistence.IntTournament;
import org.iconia.persistence.Team;

import java.util.HashMap;
import java.util.Map;

public class JoinManager {
    private final Map<String, Team> teams = new HashMap<String, Team>();
    /*This class is responsible of keeping track of user message history in order to create
     * valid team
     * */
    private String feedbackMessage;
    private IntTournament tournament;

    public JoinManager(IntTournament tournament) {
        this.tournament = tournament;
    }


    public void init(String leaderNumber) {
        teams.putIfAbsent(leaderNumber, new Team());
        Team team = teams.get(leaderNumber);
        team.setLeaderNumber(leaderNumber);
        team.setTournament(tournament);
    }



    public boolean teamUpdated(String message, String leaderNumber) {

        Team team = teams.get(leaderNumber);


        if (team.getTag() == null) {
            try {
                team.setTag(message);
                feedbackMessage = JoinManagerFeedback.feedbackMessage2;
            } catch (IllegalArgumentException e) {
                feedbackMessage = e.getMessage();
            }

            //update team members
            // might throw an unhandled error but we shall see.
        } else if (team.getName() == null) {
            try {
                team.setName(message);
                feedbackMessage = JoinManagerFeedback.feedbackMessage3;
            } catch (IllegalArgumentException e) {
                feedbackMessage = e.getMessage();
            }

            //update team logo
            // might throw an unhandled error but we shall see.
        } else if (team.getInstagram() == null) {
            try {
                team.setInstagram(message);
                feedbackMessage = JoinManagerFeedback.feedbackMessage4;
            } catch (IllegalArgumentException e) {
                feedbackMessage = e.getMessage();
            }

        } else if (team.getLogo() == null) {
            try {
                team.setLogo(message);
                feedbackMessage = JoinManagerFeedback.feedbackMessage5;
            } catch (IllegalArgumentException e) {
                feedbackMessage = e.getMessage();
            }

        }
        // update team
        teams.put(leaderNumber, team);
        return team.isTeamComplete();
    }


    public Team getTeam(String leaderNumber) {
        Team team = teams.get(leaderNumber);
        if (team == null) {
            throw new RuntimeException("team not found");
        }
        if (!team.isTeamComplete()) {
            throw new RuntimeException("team is not complete.");
        }
        return team;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

}
