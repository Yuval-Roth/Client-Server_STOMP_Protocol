package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class MessageFrame extends Frame{

    protected MessageFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.MESSAGE);
    }

    public static MessageFrame get(int subId, int messageId, String destination, String body) {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("subscription", Integer.toString(subId));
        headers.put("message-id", Integer.toString(messageId));
        headers.put("destination", destination);
        return new MessageFrame(headers, body);
    }




    
}
