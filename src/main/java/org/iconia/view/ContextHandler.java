package org.iconia.view;


import io.javalin.http.Context;
import org.iconia.domain.chatManager.ChatManager;
import org.iconia.model.IntTournament;
import org.iconia.persistence.DatabaseAccess;
import org.iconia.persistence.Team;
import org.iconia.domain.hostManager.Tournament;
import org.iconia.domain.TwillioHelper;
import org.iconia.persistence.DatabaseAccessModel;

import java.io.IOException;
import java.util.Arrays;

/**
 * Handles car-related API requests.
 */
public class ContextHandler {

    final TwillioHelper twillioHelper = new TwillioHelper("AC70827de044e48883370e72079f6ec701","d3790abd8353ac7cf7af66811c2e60f5");
    final ChatManager chatManager = new ChatManager();
    final Tournament tournament = new Tournament(
            new DatabaseAccess()
    ) ;
     IntTournament intTournament;
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
        if (message.equalsIgnoreCase("start")) {
            twillioHelper.sendMessage(to,from,"Welcome to iconia type 'view' to get all active tournaments by DOMINATORS.");
        } else if (tournament.isTournamentActive()) {
            if (message.equalsIgnoreCase("view")) {
//                view all dates...
//                twillioHelper.sendMessage(to,from,tournament.getTournamentDate());

            } else if (message.equalsIgnoreCase("list tournaments")) {
                try{
                    IntTournament[] tournament1 = tournament.getIntTournaments();
                    twillioHelper.sendMessage(to,from,String.join("," , String.valueOf(tournament1.length)));


                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (message.contains("Join")) {
                try{
                    String[] splitMessage = message.split(" ");
                    if(splitMessage.length != 2){
                    }
                    int tournamentID = Integer.parseInt(splitMessage[1]);
                    intTournament = tournament.getIntTournament(tournamentID);
                }catch (Exception e){
                }

                
            } else{
                // update leader number
                
                chatManager.init(from);
                boolean isTeamCreated =  chatManager.teamUpdated(message);

                if(isTeamCreated){
                    Team team = chatManager.getTeam();
                    tournament.uploadteam(team);
                }
                twillioHelper.sendMessage(to,from,chatManager.getFeedbackMessage());
            }

        }else{
            twillioHelper.sendMessage(to,from,"No active tournaments found");

        }
    }



    public void handleSendData(Context context) throws IOException {
        System.out.println(context.body());

    }

}