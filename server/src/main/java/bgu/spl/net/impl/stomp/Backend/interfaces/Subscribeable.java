package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

public interface Subscribeable {

    void removeSubscriber(ConnectionHandler<String> handler) throws GameChannelException;

    void addSubscriber(ConnectionHandler<String> handler) throws GameChannelException;
    
    void notifySubscribers(String message);

}
