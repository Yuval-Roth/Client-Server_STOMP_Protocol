package bgu.spl.net.impl.stomp.Backend.interfaces;

import java.io.IOException;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public interface ChannelsManager<T> {
    
    void subscribe(ConnectionHandler<T> handler, int subId, String channel) throws IOException;
    
    void unsubscribe(ConnectionHandler<T> handler, int subId) throws IOException;
    
    void whisper(ConnectionHandler<String> handler, T msg);

    void broadcast(String channel, T msg);
}
