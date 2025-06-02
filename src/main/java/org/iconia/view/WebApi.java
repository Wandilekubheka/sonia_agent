package org.iconia.view;

import io.javalin.Javalin;

/**
 * WebApi class to initialize and start the Javalin server and handle car-related API requests.
 */
public class WebApi {
    private final Javalin app;
    private final ContextHandler contextHandler = new ContextHandler();

    /**
     * Constructor to create a new instance of the WebApi class and initialize the Javalin app.
     */
    public WebApi() {
        app = Javalin.create();
    }

    /**
     * Starts the Javalin server on port 7000 and sets up the endpoint for handling whatsapp requests
     */
    public void start() {
        app.start(7000);
        app.post("/", contextHandler::handleReceiveData);
    }

    /**
     * Main method to start the WebApi server.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        WebApi webApi = new WebApi();
        webApi.start();
    }

    /**
     * Stops the Javalin server.
     */
    public void stop() {
        app.stop();
    }
}