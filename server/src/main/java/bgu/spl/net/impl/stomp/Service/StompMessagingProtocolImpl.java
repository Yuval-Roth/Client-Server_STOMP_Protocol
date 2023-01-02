package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.Frames.Frame;
import bgu.spl.net.impl.stomp.Backend.Frames.ErrorFrame;
import bgu.spl.net.impl.stomp.Service.interfaces.StompMessagingProtocol;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    @Override
    public void start(int connectionId, Connections<String> connections) {
        // TODO This shit is probably redundant
    }

    @Override
    public String process(String message) {
        Frame frame = Frame.parse(message);

        if(frame == null){
            return ErrorFrame.generateInvalidCommandError(message).getFrameString();
        }
        else{
            return frame.execute();
        }
    }

    @Override
    public boolean shouldTerminate() {
        // TODO shouldTerminate
        return false;
    }

}
