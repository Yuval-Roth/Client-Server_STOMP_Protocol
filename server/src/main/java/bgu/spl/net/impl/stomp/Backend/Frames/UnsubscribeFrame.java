package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class UnsubscribeFrame extends ExecutableFrame {

    protected UnsubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.UNSUBSCRIBE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    } 
}
