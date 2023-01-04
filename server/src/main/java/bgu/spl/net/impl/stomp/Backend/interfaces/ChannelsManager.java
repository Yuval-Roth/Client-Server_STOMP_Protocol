package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public interface ChannelsManager<T> {
    
    void subscribe(ConnectionHandler<T> handler, int subId, String channel);
    
    void unsubscribe(int subId);
    
    boolean whisper(int subId, T msg);
    
    void broadcast(String channel, T msg);
}
