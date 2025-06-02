package org.iconia.domain.chatManager;

import org.iconia.model.IntTournament;
import org.iconia.persistence.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatManager {
    /*This class is responsible of keeping track of user message history in order to create
     * valid team
     * */
    private String feedbackMessage;

    private final Map<String,Team> teams = new HashMap<String,Team>();
    // Static instance (eager initialization)
    private  IntTournament intTournament;

    public ChatManager(IntTournament intTournament) {
        this.intTournament = intTournament;
    }

    // Private constructor prevents external instantiation


    public void init(String leaderNumber) {
        teams.putIfAbsent(leaderNumber, new Team());
        Team team = teams.get(leaderNumber);
        team.setLeaderNumber(leaderNumber);
    }

    public boolean teamUpdated(String message,String leaderNumber) {
        Team team = teams.get(leaderNumber);


              if (team.getTag() == null) {
                team.setTag(message);
                feedbackMessage = ChatManagerFeedback.feedbackMessage2;
                //update team members
                // might throw an unhandled error but we shall see.
            }else if (team.getName() == null) {
                  team.setName(message);
                  feedbackMessage = ChatManagerFeedback.feedbackMessage3;
                  //update team logo
                  // might throw an unhandled error but we shall see.
              }else if (team.getInstagram() == null) {
                  team.setInstagram(message);
                  feedbackMessage = ChatManagerFeedback.feedbackMessage5;
                  //update team logo
                  team.setLogo(stringToByte("dummy image"));
                  // might throw an unhandled error but we shall see.
              }

//              else if (messagesFromUser.size() ==3) {
//                team.setLogo(stringToByte(message));
//                feedbackMessage = ChatManagerFeedback.feedbackMessage5;
//                //update team members
//                messagesFromUser.add(message);
//                // might throw an unhandled error but we shall see.
//            }
        // update team
        teams.put(leaderNumber, team);
       return team.isTeamComplete();
    }

    private byte[] stringToByte(String string) {
        return string.getBytes();
    }

    public Team getTeam(String leaderNumber) {
        Team team = teams.get(leaderNumber);
        if (team == null) {
            throw new RuntimeException("team not found");
        }
        if(!team.isTeamComplete()){
            throw new RuntimeException("team is not complete.");
        }
        return team;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

}
