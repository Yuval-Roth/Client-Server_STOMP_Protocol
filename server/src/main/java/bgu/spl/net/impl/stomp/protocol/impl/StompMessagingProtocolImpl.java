package bgu.spl.net.impl.stomp.protocol.impl;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.protocol.impl.Frames.Frame;
import bgu.spl.net.impl.stomp.protocol.interfaces.StompMessagingProtocol;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    @Override
    public void start(int connectionId, Connections<String> connections) {
        // TODO start
        
    }

    @Override
    public String process(String message) {
        // TODO process
        Frame frame = Frame.parse(message);



        return null;
    }

    @Override
    public boolean shouldTerminate() {
        // TODO shouldTerminate
        return false;
    }

}