package org.iconia.domain;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class TwillioHelper {


    public TwillioHelper(String accountSID, String authToken) {
        Twilio.init(accountSID, authToken);
    }

    public void sendMessage(String to, String content,String from) {
         Message
                .creator(
                        new PhoneNumber(from),
                        new PhoneNumber(to),
                        content
                )
                .create();
    }
}
