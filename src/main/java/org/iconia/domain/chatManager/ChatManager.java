package org.iconia.domain.chatManager;

import org.iconia.model.IntTournament;
import org.iconia.persistence.Team;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    /*This class is responsible of keeping track of user message history in order to create
     * valid team
     * */
    private final List<String> messagesFromUser = new ArrayList<String>();
    private String feedbackMessage;
    private final Team team = new Team();
    // Static instance (eager initialization)
    private static final ChatManager INSTANCE = new ChatManager();

    // Private constructor prevents external instantiation


    public void init(String leaderNumber) {
            team.setLeaderNumber(leaderNumber);
    }

    public boolean teamUpdated(String message) {
              if (messagesFromUser.isEmpty()) {
                team.setTag(message);
                feedbackMessage = "well done, please provide us your team instagram handler.";
                //update team members
                messagesFromUser.add(message);
                // might throw an unhandled error but we shall see.
            }else if (messagesFromUser.size() ==1) {
                team.setInstagram(message);
                feedbackMessage = "well done, please provide us your team logo.";
                //update team members
                messagesFromUser.add(message);
                // might throw an unhandled error but we shall see.
            }else if (messagesFromUser.size() ==2) {
                team.setLogo(stringToByte(message));
                feedbackMessage = "successfully registered!";
                //update team members
                messagesFromUser.add(message);
                // might throw an unhandled error but we shall see.
            }
       return team.isTeamComplete();
    }

    private byte[] stringToByte(String string) {

        return null;
    }

    public Team getTeam() {
        if(!team.isTeamComplete()){
            throw new RuntimeException("team is not complete.");
        }
        return team;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

}
