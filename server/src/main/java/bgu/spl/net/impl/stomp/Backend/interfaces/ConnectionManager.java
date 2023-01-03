package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface ConnectionManager {

    /**
     * Connect a user to the server
     * @param username
     * @param password
     */
    public void Connect(String username, String password) throws UserException;

    /**
     * Disconnect a user from the server
     * @param username
     */
    public void Disconnect(String username) throws UserException;
}
