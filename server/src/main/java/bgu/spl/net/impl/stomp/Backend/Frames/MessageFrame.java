package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class MessageFrame extends ExecutableFrame {

    protected MessageFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.MESSAGE);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        // TODO MessageFrame - execute
        return null;
    }


    
}
