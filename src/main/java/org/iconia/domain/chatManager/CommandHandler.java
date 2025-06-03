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
    private JoinManager joinManager;
    private String feedback;
    final private CreateManager createManager;

    public CommandHandler() {
        DatabaseAccessModel databaseAccessModel = new DatabaseAccess();
        tournamentTo = new Tournament(databaseAccessModel);
        createManager = new CreateManager();
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
                    joinManager = new JoinManager(tournament);
                    feedback = JoinManagerFeedback.feedbackMessage1;
                } else {
                    feedback = JoinManagerFeedback.unknownCommandMessage;

                }

            } catch (NumberFormatException e) {
                feedback = JoinManagerFeedback.invalidJoinCommandMessage;
            } catch (IllegalArgumentException e) {
                feedback = JoinManagerFeedback.unknownCommandMessage;
            } catch (RuntimeException e) {
                feedback = e.getMessage();
            }
            return;
        }
        try {
            Commands.valueOf(command);

        } catch (IllegalArgumentException e) {
            feedback = JoinManagerFeedback.unknownCommandMessage;
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
                feedback = JoinManagerFeedback.unknownCommandMessage;
            }
        }
    }

    private String helpCommand() {
        return JoinManagerFeedback.welcomeMessage;
    }

    private String startCommand() {


        return JoinManagerFeedback.welcomeMessage;

    }

    public String createTournament() {
        if (leaderNumber == null) {
            return JoinManagerFeedback.unknownErrorMessage;
        }
        createManager.init(leaderNumber);
        executing = !createManager.tournamentUpdated(command, leaderNumber);
        if (!executing) {
            this.command = null;
        }
        return createManager.getFeedbackMessage();
    }

    private String joinTournamentCommand() {
        if (arg == null || leaderNumber == null) {
            return JoinManagerFeedback.unknownErrorMessage;
        }
        joinManager.init(leaderNumber);
        executing = !joinManager.teamUpdated(arg, leaderNumber);
        if (!executing) {
            tournamentTo.uploadTeam(joinManager.getTeam(leaderNumber));
            this.command = null;
        }
        return joinManager.getFeedbackMessage();
    }

    public String getFeedback() {
        return feedback;
    }

    private String viewTournamentsList() {
        IntTournament[] tournaments;
        try {
            tournaments = tournamentTo.getIntTournaments();
            if (tournaments == null) {
                return JoinManagerFeedback.unknownErrorMessage;
            } else if (tournaments.length == 0) {
                return JoinManagerFeedback.noActiveTournamentsMessage;
            }
            return Tournament.viewTournamentsListAsString(tournaments);
        } catch (SQLException e) {
            return JoinManagerFeedback.unknownErrorMessage;
        }


    }
}
