package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class DisconnectFrame extends ExecutableFrame {

    protected DisconnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.DISCONNECT);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }
  
}
