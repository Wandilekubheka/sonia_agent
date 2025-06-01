package Unittest.view;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.iconia.view.WebApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testApi {
    WebApi api;
    @Before
    public void start() {
        WebApi api = new WebApi();
        api.start();
    }

    @After
    public void stop() {
         api.stop();
    }
//
//    @Test
//    public void testWebApi() {
//
//        api.start();
//        HttpResponse<String> response = Unirest.post("http://localhost:7000/").body(someBody).asString();
//        assertEquals(200, response.getStatus());
//        assertEquals(someBody, response.getBody());
//
//    }


}
