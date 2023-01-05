package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SendFrame extends ExecutableFrame {

    protected SendFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SEND);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        // TODO SendFrame - execute
        return null;
    }


}
