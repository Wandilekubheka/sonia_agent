package org.iconia.view;


import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.http.Context;
import org.iconia.domain.TwillioHelper;
import org.iconia.domain.chatManager.CreateManagerFeedback;
import org.iconia.domain.chatManager.CommandHandler;

/**
 * Handles car-related API requests.
 */
public class ContextHandler {

    final Display display;

    CommandHandler commandHandler = new CommandHandler();

    ContextHandler() {
        Dotenv dotenv = Dotenv.load();
        String twilioKey = dotenv.get("TWILIO_KEY");
        String twilliAcc = dotenv.get("TWILIO_ACC");
        if (twilliAcc == null || twilliAcc.isEmpty() || twilioKey == null || twilioKey.isEmpty()) {
            throw new RuntimeException("TWILIO_KEY or TWILIO_ACC is empty");
        }
        TwillioHelper twillioHelper = new TwillioHelper(twilliAcc, twilioKey);
        display = new Display(twillioHelper);

    }


    /**
     * Handles user actions from whatsapp
     *
     * @param context the Javalin context containing the request and response
     */
    public void handleReceiveData(Context context) {
        String message = context.formParam("Body");
        String to = context.formParam("To");
        String from = context.formParam("From");
        String numMedia = context.formParam("NumMedia");
        String mediaUrl = context.formParam("MediaUrl0");
        String mediaType = context.formParam("MediaContentType0");

        if (numMedia != null & mediaUrl != null & mediaType != null) {
            if (numMedia.equals("1")) {
                if (mediaType.contains("image")) {
                    message = mediaUrl;
                } else {
                    display.displayText(to, from, CreateManagerFeedback.onlyAcceptImages);
                }
            } else {
                display.displayText(to, from, CreateManagerFeedback.onlyAcceptOneMedia);
            }
        }

        if (message == null || to == null || from == null) {
            throw new RuntimeException("values can't be null");
        }
        commandHandler.init(from);
        message = message.trim().toUpperCase();

        commandHandler.executeCommand(message);
        display.displayText(from, to, commandHandler.getFeedback());

    }


}







