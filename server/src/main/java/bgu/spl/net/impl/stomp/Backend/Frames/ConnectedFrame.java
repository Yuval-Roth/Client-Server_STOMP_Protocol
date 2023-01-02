package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ConnectedFrame extends Frame {

    protected ConnectedFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECTED);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
