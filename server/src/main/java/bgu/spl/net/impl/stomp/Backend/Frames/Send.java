package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class Send extends Frame {

    protected Send(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SEND);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }


}
