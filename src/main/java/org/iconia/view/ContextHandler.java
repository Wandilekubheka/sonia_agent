package org.iconia.view;


import io.javalin.http.Context;

import java.io.IOException;

/**
 * Handles car-related API requests.
 */
public class ContextHandler {

    /**
     * Handles user actions from whatsapp
     *
     * @param context the Javalin context containing the request and response
     */
    public void handleReceiveData(Context context) {
        System.out.println(context.body());
    }



    public void handleSendData(Context context) throws IOException {

    }

}