package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class SubscribeFrame extends Frame{

    protected SubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SUBSCRIBE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }


}
