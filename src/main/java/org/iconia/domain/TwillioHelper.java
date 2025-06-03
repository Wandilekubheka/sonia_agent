package org.iconia.domain;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwillioHelper {


    public TwillioHelper(String accountSID, String authToken) {
        Twilio.init(accountSID, authToken);
    }

    public void sendMessage(String from, String to, String content) {
        try {
            Message
                    .creator(
                            new PhoneNumber(to),
                            new PhoneNumber(from),
                            content
                    )
                    .create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
