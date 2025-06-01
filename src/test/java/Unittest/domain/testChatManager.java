package Unittest.domain;

import jdk.dynalink.linker.LinkerServices;
import org.iconia.domain.chatManager.ChatManager;
import org.iconia.domain.chatManager.ChatManagerFeedback;
import org.junit.Test;
import static org.junit.Assert.*;

public class testChatManager {
    @Test
    public void test() {
        ChatManager cm = new ChatManager();
        cm.init("+548385");
        //update team tag
        assertFalse(cm.teamUpdated("t1"));
        assertEquals(cm.getFeedbackMessage(), ChatManagerFeedback.feedbackMessage2);
        //update team members
        assertFalse(cm.teamUpdated("team1"));
        assertEquals(cm.getFeedbackMessage(), ChatManagerFeedback.feedbackMessage3);
        //update team instagram handler
        assertFalse(cm.teamUpdated("@retro"));
        assertEquals(cm.getFeedbackMessage(), ChatManagerFeedback.feedbackMessage4);
        //update team logo
        assertTrue(cm.teamUpdated(""));
        assertEquals(cm.getFeedbackMessage(), ChatManagerFeedback.feedbackMessage5);
    }
}
