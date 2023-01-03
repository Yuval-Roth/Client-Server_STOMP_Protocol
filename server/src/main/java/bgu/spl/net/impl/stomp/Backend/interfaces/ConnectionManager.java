package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface ConnectionManager {

    /**
     * Connect a user to the server
     * @param username
     * @param password
     */
    public void connect(ConnectionHandler<String> handler, String username, String password) throws UserException;

    /**
     * Disconnect a user from the server
     * @param username
     */
    public void disconnect(String username) throws UserException;
}
