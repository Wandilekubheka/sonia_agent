package org.iconia.domain.chatManager;

import org.iconia.domain.hostManager.Tournament;
import org.iconia.model.IntTournament;
import org.iconia.persistence.DatabaseAccess;
import org.iconia.persistence.DatabaseAccessModel;

import java.sql.SQLException;

public class CommandHandler   {
    private boolean executed = false;
    private String command;
    private String arg;

    private  String leaderNumber;
    private ChatManager chatManager;
    final Tournament tournamentTo;
    private String feedback;
    public CommandHandler(){
            DatabaseAccessModel databaseAccessModel = new DatabaseAccess();
            tournamentTo = new Tournament(databaseAccessModel);
    }

    public void init(String leaderNumber){
        this.leaderNumber = leaderNumber;
    }




    public void executeCommand(String command) {
        command = command.toLowerCase();
        if(command.split(" ").length == 2){
            String[] commands = command.split(" ");
            this.command = commands[0];
            try{
                int number = Integer.parseInt(commands[1]);
                IntTournament tournament = tournamentTo.getIntTournament(number);
                chatManager = new ChatManager(tournament);
                feedback = ChatManagerFeedback.feedbackMessage1;
            } catch (NumberFormatException e) {
                feedback = ChatManagerFeedback.invalidJoinCommandMessage;
                return;
            } catch (SQLException e) {
                feedback = ChatManagerFeedback.unknownErrorMessage;
            }
            return;
        }
        if(executed){
            this.command = command;
        }
        switch (Commands.valueOf(this.command)) {
            case help -> {
                feedback = helpCommand();
            }case start -> {
                feedback = showTournamentCommand();
            }case join -> {
                arg = command;
                feedback =joinTournamentCommand();
            }default -> {
                feedback = ChatManagerFeedback.unknownErrorMessage;
            }
        }
    }

    private String helpCommand() {
        executed = true;
        return ChatManagerFeedback.welcomeMessage;
    }
    private String showTournamentCommand() {
        IntTournament[] tournaments = null;
        executed = true;
        try{
             tournaments =tournamentTo.getIntTournaments();

        } catch (SQLException e) {
            return ChatManagerFeedback.unknownErrorMessage;

        }
        if (tournaments == null){
            return ChatManagerFeedback.unknownErrorMessage;
        }
        if (tournaments.length == 0){
            return ChatManagerFeedback.noActiveTournamentsMessage;
        }
        return Tournament.viewTournamentsListAsString(tournaments);
    }

    private String joinTournamentCommand() {
        if(arg == null || leaderNumber == null){
            return ChatManagerFeedback.unknownErrorMessage;
        }
        chatManager.init(leaderNumber);
        executed = chatManager.teamUpdated(arg,leaderNumber);
        if (executed) {
            tournamentTo.uploadteam(chatManager.getTeam(leaderNumber));
            this.command = null;
        }
        return chatManager.getFeedbackMessage();
    }

    public String getFeedback() {
        return feedback;
    }
}
