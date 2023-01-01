package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.interfaces.Connections;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    @Override
    public void start(int connectionId, Connections<String> connections) {
        // TODO start
        
    }

    @Override
    public String process(String message) {
        // TODO process
        return null;
    }

    @Override
    public boolean shouldTerminate() {
        // TODO shouldTerminate
        return false;
    }

}
