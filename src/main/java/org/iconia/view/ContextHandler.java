package org.iconia.view;


import io.javalin.http.Context;
import org.iconia.domain.TwillioHelper;

import org.iconia.domain.chatManager.CommandHandler;

/**
 * Handles car-related API requests.
 */
public class ContextHandler {

    final TwillioHelper twillioHelper = new TwillioHelper("AC70827de044e48883370e72079f6ec701","d3790abd8353ac7cf7af66811c2e60f5");
    final Display display= new Display();

    CommandHandler commandHandler = new CommandHandler();


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

        if(message == null || to == null || from == null){
            throw new RuntimeException("values can't be null");
        }
        commandHandler.init(from);
        message = message.trim().toUpperCase();

        commandHandler.executeCommand(message);
        display.displayText(from,to,commandHandler.getFeedback());

    }






}








//                            if(numMedia != null & mediaUrl != null & mediaType != null){
//                    if(numMedia.equals("1")){
//                        if (mediaType.contains("image")) {
//                            message = mediaUrl;
//                        }else{
//                            display.displayText(to,from,ChatManagerFeedback.onlyAcceptImages);
//                        }
//                    }else{
//                        display.displayText(to,from,ChatManagerFeedback.onlyAcceptOneMedia);
//                    }
//                }