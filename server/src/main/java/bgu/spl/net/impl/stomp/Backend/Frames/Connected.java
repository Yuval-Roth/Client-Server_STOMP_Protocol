package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class Connected extends Frame {

    protected Connected(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECTED);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
