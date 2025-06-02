package org.iconia.view;


import io.javalin.http.Context;
import org.iconia.domain.TwillioHelper;
import org.iconia.domain.chatManager.ChatManager;
import org.iconia.domain.chatManager.ChatManagerFeedback;
import org.iconia.model.IntTournament;
import org.iconia.persistence.DatabaseAccess;
import org.iconia.persistence.Team;
import org.iconia.domain.hostManager.Tournament;

import java.io.IOException;
import java.util.Arrays;

/**
 * Handles car-related API requests.
 */
public class ContextHandler {

//    final TwillioHelper twillioHelper = new TwillioHelper("AC70827de044e48883370e72079f6ec701","d3790abd8353ac7cf7af66811c2e60f5");
    final Display display= new Display();
    final Tournament tournament = new Tournament(
            new DatabaseAccess()
    );
     IntTournament intTournament;
     ChatManager chatManager;



    /**
     * Handles user actions from whatsapp
     *
     * @param context the Javalin context containing the request and response
     */
    public void handleReceiveData(Context context) {
        String message = context.formParam("Body");
        String to = context.formParam("To");
        String from = context.formParam("From");
        if(message == null || to == null || from == null){
            throw new RuntimeException("values can't be null");
        }
        message = message.trim().toUpperCase();
        if (message.equalsIgnoreCase("start")) {
            display.displayText(to,from,ChatManagerFeedback.welcomeMessage);
        } else if (tournament.isTournamentActive()) {
            if (message.equalsIgnoreCase("view")) {
                try{
                    IntTournament[] tournaments = tournament.getIntTournaments();
                    display.displayText(to,from,Tournament.viewTournamentsListAsString(tournaments));
                    

                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (message.contains("JOIN")) {
                try{
                    String[] splitMessage = message.split(" ");
                    if(splitMessage.length != 2){
                        display.displayText(to,from,ChatManagerFeedback.invalidJoinCommandMessage);
                        return;
                    }
                    int tournamentID = Integer.parseInt(splitMessage[1]);
                    intTournament = tournament.getIntTournament(tournamentID);
                    chatManager = new ChatManager(intTournament);
                    display.displayText(to,from,ChatManagerFeedback.feedbackMessage1);

                }catch (Exception e){
                    display.displayText(to,from,ChatManagerFeedback.unknownErrorMessage);


                }

                
            } else{

                if(intTournament == null || chatManager == null){
                    return;
                }
                // update leader number
                
                chatManager.init(from);
                boolean isTeamCreated =  chatManager.teamUpdated(message,from);

                if(isTeamCreated){
                    Team team = chatManager.getTeam(from);
                    tournament.uploadteam(team);
                }
                display.displayText(from,to,chatManager.getFeedbackMessage());

            }

        }else{
            display.displayText(to,from,ChatManagerFeedback.noActiveTournamentsMessage);

        }

    }



    public void handleSendData(Context context) throws IOException {
//        display.displayText(to,from,context.body());

    }

}