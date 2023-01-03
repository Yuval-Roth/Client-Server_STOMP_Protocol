package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

//TODO: fix to support int id instead of String username

public interface Subscribeable {

    void removeSubscriber(String username) throws GameChannelException;

    void addSubscriber(String username) throws GameChannelException;
    
    void notifySubscribers(String message);

}
