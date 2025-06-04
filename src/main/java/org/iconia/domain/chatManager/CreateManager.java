package org.iconia.domain.chatManager;

import org.iconia.persistence.IntTournament;
import org.iconia.persistence.Team;

import java.util.HashMap;
import java.util.Map;

public class CreateManager {
    private final Map<String, IntTournament> tournamentMap = new HashMap<String, IntTournament>();
    /*This class is responsible of keeping track of user message history in order to create
     * valid team
     * */
    private String feedbackMessage;



    public void init(String leaderNumber) {
        tournamentMap.putIfAbsent(leaderNumber, new IntTournament());
    }

    public boolean tournamentUpdated(String message, String leaderNumber) {
        IntTournament tournament = tournamentMap.get(leaderNumber);
        if (tournament.getName() == null) {
            feedbackMessage = CreateManagerFeedback.askTournamentDescription;
            tournament.setName(message);
        } else if (tournament.getDescription()== null) {
            tournament.setDescription(message);
            feedbackMessage = CreateManagerFeedback.askTournamentMaxSize;
        } else if (tournament.getMaxSize() == 0) {
            try{
                int size = Integer.parseInt(message);
                tournament.setMaxSize(size);
               feedbackMessage = CreateManagerFeedback.askTournamentDate;
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else if (tournament.getDate() == null) {
            tournament.setDate(message);
        }
        tournamentMap.put(leaderNumber, tournament);
        return tournament.isValid();
    }


    public IntTournament getTournament(String leaderNumber) {
        IntTournament tournament = tournamentMap.get(leaderNumber);
        if (tournament == null) {
            throw new RuntimeException("team not found");
        }
        if (!tournament.isValid()) {
            throw new RuntimeException("team is not complete.");
        }
        return tournament;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

}
