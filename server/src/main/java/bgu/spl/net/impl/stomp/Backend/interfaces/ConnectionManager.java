package bgu.spl.net.impl.stomp.Backend.interfaces;

import java.io.IOException;
import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public interface ConnectionManager {

    /**
     * Connect a user to the server
     * @param username
     * @param password
     */
    public void connect(ConnectionHandler<String> handler, String username, String password) throws IOException;

    /**
     * Disconnect a user from the server
     * @param username
     */
    public void disconnect(ConnectionHandler<String> handler) throws IOException;
}
