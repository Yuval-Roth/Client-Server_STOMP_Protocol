package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.Frames.Frame;
import bgu.spl.net.impl.stomp.Service.interfaces.StompMessagingProtocol;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    @Override
    public void start(int connectionId, Connections<String> connections) {
        // TODO start
    }

    @Override
    public String process(String message) {
        // TODO process - need to see if this is enough
        Frame frame = Frame.parse(message);
        return frame.execute();
    }

    @Override
    public boolean shouldTerminate() {
        // TODO shouldTerminate
        return false;
    }

}
