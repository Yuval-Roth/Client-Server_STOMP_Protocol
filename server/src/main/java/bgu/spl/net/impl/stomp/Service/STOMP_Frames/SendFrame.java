package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SendFrame extends ExecutableFrame {

    private static final Object DESTINATION_FIELD = "destination";

    protected SendFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SEND);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        
        try {
            channelsManager.broadcast(headers.get(DESTINATION_FIELD), frameBody);
            return ""; //this frame has no response - not sure if this will work
        } catch (IOException e) {
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
}
