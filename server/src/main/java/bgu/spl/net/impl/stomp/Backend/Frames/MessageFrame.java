package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class MessageFrame extends Frame {

    protected MessageFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.MESSAGE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
