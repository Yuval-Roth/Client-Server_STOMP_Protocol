package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

public interface Subscribeable {

    void removeSubscriber(String username) throws GameChannelException;

    void addSubscriber(String username) throws GameChannelException;
    
    void notifySubscribers(String message);

}
