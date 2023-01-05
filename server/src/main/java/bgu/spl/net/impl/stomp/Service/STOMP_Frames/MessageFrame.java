package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class MessageFrame extends Frame{

    private static final String DESTINATION_FIELD = "destination";
    private static final String MESSAGE_ID_FIELD = "message-id";
    private static final String SUBSCRIPTION_FIELD = "subscription";

    protected MessageFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.MESSAGE);
    }

    public static MessageFrame get(int subId, int messageId, String destination, String body) {
        HashMap<String,String> headers = new HashMap<>();
        headers.put(SUBSCRIPTION_FIELD, Integer.toString(subId));
        headers.put(MESSAGE_ID_FIELD, Integer.toString(messageId));
        headers.put(DESTINATION_FIELD, destination);
        return new MessageFrame(headers, body);
    }   
}
