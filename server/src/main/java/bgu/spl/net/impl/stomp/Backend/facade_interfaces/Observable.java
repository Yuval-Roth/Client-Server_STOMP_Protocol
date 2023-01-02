package bgu.spl.net.impl.stomp.Backend.facade_interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

public interface Observable {
    void removeSubscriber(String username) throws GameChannelException;
    void addSubscriber(String username) throws GameChannelException;
    void notifyObservers();
}
