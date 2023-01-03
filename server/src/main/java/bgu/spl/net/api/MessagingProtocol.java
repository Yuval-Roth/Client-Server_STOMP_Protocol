package bgu.spl.net.api;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public interface MessagingProtocol<T> {

    /**
     * start the protocol with the given handler
     * @param handler
     */
    void start(ConnectionHandler<T> handler);
    
    /**
     * process the given message 
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    T process(T msg);
 
    /**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
 
}