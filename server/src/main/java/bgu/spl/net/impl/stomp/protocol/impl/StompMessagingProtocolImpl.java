package bgu.spl.net.impl.stomp.protocol.impl;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.protocol.impl.Frames.Frame;
import bgu.spl.net.impl.stomp.protocol.interfaces.StompMessagingProtocol;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    
    

    public StompMessagingProtocolImpl() {
        // TODO constructor
    }

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
