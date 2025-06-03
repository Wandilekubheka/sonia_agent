package org.iconia.domain.chatManager;

import org.iconia.domain.hostManager.Tournament;
import org.iconia.persistence.DatabaseAccess;
import org.iconia.persistence.DatabaseAccessModel;
import org.iconia.persistence.IntTournament;

import java.sql.SQLException;

public class CommandHandler {
    final Tournament tournamentTo;
    private boolean executing = false;
    private String command;
    private String arg;
    private String leaderNumber;
    private ChatManager chatManager;
    private String feedback;

    public CommandHandler() {
        DatabaseAccessModel databaseAccessModel = new DatabaseAccess();
        tournamentTo = new Tournament(databaseAccessModel);
    }

    public void init(String leaderNumber) {
        this.leaderNumber = leaderNumber;
    }


    public void executeCommand(String command) {
        command = command.toLowerCase();
        if (command.split(" ").length == 2) {
            String[] commands = command.split(" ");
            try {
                // testing if its a valid command
                if (commands[0].equals("join")) {

                    this.command = commands[0];
                    int number = Integer.parseInt(commands[1]);
                    IntTournament tournament = tournamentTo.getIntTournament(number);
                    chatManager = new ChatManager(tournament);
                    feedback = ChatManagerFeedback.feedbackMessage1;
                } else {
                    feedback = ChatManagerFeedback.unknownCommandMessage;

                }

            } catch (NumberFormatException e) {
                feedback = ChatManagerFeedback.invalidJoinCommandMessage;
            } catch (IllegalArgumentException e) {
                feedback = ChatManagerFeedback.unknownCommandMessage;
            } catch (RuntimeException e) {
                feedback = e.getMessage();
            }
            return;
        }
        try {
            Commands.valueOf(command);

        } catch (IllegalArgumentException e) {
            feedback = ChatManagerFeedback.unknownCommandMessage;
            return;

        }
        if (!executing) {
            this.command = command;
        }
        switch (Commands.valueOf(this.command)) {
            case help -> {
                feedback = helpCommand();
            }
            case start -> {
                System.out.println("Command: " + command);

                feedback = startCommand();
            }
            case join -> {
                arg = command;
                feedback = joinTournamentCommand();
            }
            case view -> {
                feedback = viewTournamentsList();
            }
            case create -> {
                feedback = createTournament();
            }

            default -> {
                feedback = ChatManagerFeedback.unknownCommandMessage;
            }
        }
    }

    private String helpCommand() {
        return ChatManagerFeedback.welcomeMessage;
    }

    private String startCommand() {


        return ChatManagerFeedback.welcomeMessage;

    }

    public String createTournament() {
        // yet to validate if user can create tournament
        return ChatManagerFeedback.createTournamentInfo;
    }

    private String joinTournamentCommand() {
        if (arg == null || leaderNumber == null) {
            return ChatManagerFeedback.unknownErrorMessage;
        }
        chatManager.init(leaderNumber);
        executing = !chatManager.teamUpdated(arg, leaderNumber);
        if (!executing) {
            tournamentTo.uploadTeam(chatManager.getTeam(leaderNumber));
            this.command = null;
        }
        return chatManager.getFeedbackMessage();
    }

    public String getFeedback() {
        return feedback;
    }

    private String viewTournamentsList() {
        IntTournament[] tournaments;
        try {
            tournaments = tournamentTo.getIntTournaments();
            if (tournaments == null) {
                return ChatManagerFeedback.unknownErrorMessage;
            } else if (tournaments.length == 0) {
                return ChatManagerFeedback.noActiveTournamentsMessage;
            }
            return Tournament.viewTournamentsListAsString(tournaments);
        } catch (SQLException e) {
            return ChatManagerFeedback.unknownErrorMessage;
        }


    }
}
