package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class SendFrame extends ExecutableFrame {

    protected SendFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SEND);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }


}
