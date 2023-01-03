package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class DisconnectFrame extends ExecutableFrame {

    protected DisconnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.DISCONNECT);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        // TODO DisconnectFrame - execute
        return null;
    }
  
}
