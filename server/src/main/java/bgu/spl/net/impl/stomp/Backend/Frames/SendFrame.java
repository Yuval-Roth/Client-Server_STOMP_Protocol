package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SendFrame extends ExecutableFrame {

    protected SendFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SEND);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        // TODO Auto-generated method stub
        return null;
    }


}
